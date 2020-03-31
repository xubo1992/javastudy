package com.xubo.RPC;

/*
 * 服务中心接口
 */
public interface Server {
	// 启动服务
	public void start();
	
	// 停止服务
	public void stop();
	
	// 注册服务
	public void register(Class service,Class serverImpl);
}
