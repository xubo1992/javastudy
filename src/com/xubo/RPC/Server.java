package com.xubo.RPC;

/*
 * �������Ľӿ�
 */
public interface Server {
	// ��������
	public void start();
	
	// ֹͣ����
	public void stop();
	
	// ע�����
	public void register(Class service,Class serverImpl);
}
