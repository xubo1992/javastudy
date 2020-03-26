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
 * ʵ�ַ������Ľӿ�
 */
public class ServerCenter implements Server{
	private static HashMap<String,Class> serviceRegister = new HashMap<String,Class>();
	private static int port;
	// ���ӳ�:���ӳ��д��ڶ�����Ӷ���ÿ�����Ӷ����ܴ���һ������
	// ��ȡ����������������һ�����ӳ�
	private static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()); 
	
	private static boolean isRunning = false;
	
	
	public ServerCenter(int port) {
		this.port = port;
	}
	
	// ��������
	@Override
	public void start() {
		ServerSocket server = null;
		try {
			// �����˿�Ϊ7777�ķ���
			server = new ServerSocket();
			server.bind(new InetSocketAddress(port));
		} catch (IOException e) {
			e.printStackTrace();
		}
		isRunning = true;
		while(true) {
			Socket socket = null;
			try {
				// �ȴ��ͻ�����
				socket = server.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// �����߳� 
			executorService.execute(new ServiceTask(socket)); 
		}
	}

	@Override
	public void stop() {
		isRunning = false;
		executorService.shutdown();
	}
	
	// ע��ӿ�ʵ��
	@Override
	public void register(Class service,Class serverImpl) {
		serviceRegister.put(service.getName(), serverImpl);
		
	}
	
	// ����ͻ�������
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
				System.out.println("���ӳɹ�");
				// ���տͻ�������	����˳��Ҫ������ͻ�����˳��һ��
				in = new ObjectInputStream(socket.getInputStream());
				// �ӿ���
				String serviceName = in.readUTF();
				// �ӿڷ���
				String methodName = in.readUTF();
				// ��������
				Class[] parameters = (Class[])in.readObject();
				// ����
				Object[] args = (Object[])in.readObject();
				
				// ����ͻ�������
				Class serviceClass = serviceRegister.get(serviceName);
				Method method = serviceClass.getMethod(methodName, parameters);
				// ���ͷ��ؽ��
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
