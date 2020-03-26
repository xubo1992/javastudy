package com.xubo.RPC;

import java.net.InetSocketAddress;

public class RPCClientTest {
	public static void main(String[] args) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				HelloService helloService;
				try {
					helloService = Client.getRemoteProxyObj(Class.forName("com.xubo.RPC.HelloService"), new InetSocketAddress("127.0.0.1",7777));
				String sayHello = helloService.sayHello("zs");
				System.out.println(sayHello);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

}
