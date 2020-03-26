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
		
	//��ȡ�������˽ӿڵĶ�̬�������
	@SuppressWarnings("unchecked")
	public static <T> T getRemoteProxyObj(Class serviceInterface,InetSocketAddress address) {
		/*
		 * newProxyInstance(a,b,c);
		 * a:�����������Ҫ�����Ǹ��࣬����Ҫ������ļ����������һ��������
		 * b:��Ҫ����Ķ���߱���Щ���� ���������ڽӿ��У���˵ڶ��������Žӿ�
		 * 		javaΪ���̳ɣ���ʵ�֣����Դ��ڶ���ӿ������Ϊ�˲�ȫ�﷨������һ���������͵Ĳ���
		 */
		return (T)Proxy.newProxyInstance(serviceInterface.getClassLoader(), new Class<?>[]{serviceInterface}, new InvocationHandler() {
			
			// proxy:����Ķ���	method������ķ���	args������Ĳ���
			@Override
			public Object invoke(Object proxy, Method method, Object[] args){
				Socket socket = null;
				ObjectOutputStream out = null;
				ObjectInputStream in = null;
				try {
					// �ͻ��������˷�������
					socket = new Socket();
					// ��������  address ������IP+�˿�
					socket.connect(address);
					out = new ObjectOutputStream(socket.getOutputStream());
					// �ͻ��˷�������
					// ���ͽӿ�
					out.writeUTF(serviceInterface.getName());
					// ���ͽӿڵķ�����
					out.writeUTF(method.getName());
					// ���ͷ����Ĳ������� 	�������Ͳ�ȷ����������ͨ�õ�object
					out.writeObject(method.getParameterTypes());
					// ���ͷ�������
					out.writeObject(args);
					
					// ��ȡ�������Ӧ
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
