package com.xubo.RPC;

public class RPCServerTest {
	public static void main(String[] args) {
		Server server = new ServerCenter(7777);
		server.register(HelloService.class, HelloServiceImpl.class);
		server.start();
	}
}
