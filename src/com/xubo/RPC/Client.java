package com.xubo.RPC;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
		
	//获取代理服务端接口的动态代理对象
	@SuppressWarnings("unchecked")
	public static <T> T getRemoteProxyObj(Class serviceInterface,InetSocketAddress address) {
		/*
		 * newProxyInstance(a,b,c);
		 * a:类加载器：需要代理那个类，就需要将该类的加载器传入第一个参数中
		 * b:需要代理的对象具备哪些方法 ，方法放于接口中，因此第二个参数放接口
		 * 		java为单继成，多实现，所以存在多个接口情况，为了补全语法，传入一个数组类型的参数
		 */
		return (T)Proxy.newProxyInstance(serviceInterface.getClassLoader(), new Class<?>[]{serviceInterface}, new InvocationHandler() {
			
			// proxy:代理的对象	method：代理的方法	args：代理的参数
			@Override
			public Object invoke(Object proxy, Method method, Object[] args){
				Socket socket = null;
				ObjectOutputStream out = null;
				ObjectInputStream in = null;
				try {
					// 客户端向服务端发送请求
					socket = new Socket();
					// 建立连接  address 包含了IP+端口
					socket.connect(address);
					out = new ObjectOutputStream(socket.getOutputStream());
					// 客户端发送请求
					// 发送接口
					out.writeUTF(serviceInterface.getName());
					// 发送接口的方法名
					out.writeUTF(method.getName());
					// 发送方法的参数类型 	参数类型不确定，所以用通用的object
					out.writeObject(method.getParameterTypes());
					// 发送方法参数
					out.writeObject(args);
					
					// 获取服务端响应
					in = new ObjectInputStream(socket.getInputStream());
					return in.readObject(); 
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					try {
						if(in != null) in.close();
						if(out != null) out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				return null;
			}
		});
	}
	public static void main(String[] args) throws ClassNotFoundException {
		getRemoteProxyObj(Class.forName("com.xubo.RPC.HelloService"),new InetSocketAddress("127.0.0.1",7777));
	}

}
