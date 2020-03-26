package com.xubo.RPC;

public class HelloServiceImpl implements HelloService {

	@Override
	public String sayHello(String name) {
		return "say Hello:"+name;
	}

}
