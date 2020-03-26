package com.xubo.RPC;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * 实现服务中心接口
 */
public class ServerCenter implements Server{
	private static HashMap<String,Class> serviceRegister = new HashMap<String,Class>();
	private static int port;
	// 连接池:连接池中存在多个连接对象，每个连接对象都能处理一个对象
	// 获取处理器个数，生产一个连接池
	private static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()); 
	
	private static boolean isRunning = false;
	
	
	public ServerCenter(int port) {
		this.port = port;
	}
	
	// 开启服务
	@Override
	public void start() {
		ServerSocket server = null;
		try {
			// 开启端口为7777的服务
			server = new ServerSocket();
			server.bind(new InetSocketAddress(port));
		} catch (IOException e) {
			e.printStackTrace();
		}
		isRunning = true;
		while(true) {
			Socket socket = null;
			try {
				// 等待客户连接
				socket = server.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// 启动线程 
			executorService.execute(new ServiceTask(socket)); 
		}
	}

	@Override
	public void stop() {
		isRunning = false;
		executorService.shutdown();
	}
	
	// 注册接口实现
	@Override
	public void register(Class service,Class serverImpl) {
		serviceRegister.put(service.getName(), serverImpl);
		
	}
	
	// 处理客户端请求
	private static class ServiceTask implements Runnable{
		private Socket socket;
		public ServiceTask(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			ObjectInputStream in = null;
			ObjectOutputStream out = null;
			try {
				System.out.println("连接成功");
				// 接收客户端请求	接收顺序要保持与客户发送顺序一致
				in = new ObjectInputStream(socket.getInputStream());
				// 接口名
				String serviceName = in.readUTF();
				// 接口方法
				String methodName = in.readUTF();
				// 参数类型
				Class[] parameters = (Class[])in.readObject();
				// 参数
				Object[] args = (Object[])in.readObject();
				
				// 处理客户端请求
				Class serviceClass = serviceRegister.get(serviceName);
				Method method = serviceClass.getMethod(methodName, parameters);
				// 发送返回结果
				Object result = method.invoke(serviceClass.newInstance(), args);
				out = new ObjectOutputStream(socket.getOutputStream());
				out.writeObject(result);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(out != null) out.close();
					if(in != null) in.close();
					if(socket != null) socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}
