package com.xubo.socket;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyClient2 {
	/*
	 * ͨ��socket�����ļ����ͻ���
	 */
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("127.0.0.1",8888);
			InputStream in = socket.getInputStream();
			File file = new File("D:\\aaa.jar");
			if(!file.exists()) {
				//��û�и��ļ��򴴽�
				file.createNewFile();
			}
			// �����ļ����ڴ�д��Ӳ��
			FileOutputStream out = new FileOutputStream(file);
			byte[] bs = new byte[100];
			int len = -1;
			//���ϴ��ڴ��ж�ȡ�ļ�
			while((len = in.read(bs)) != -1) {
				//���ڴ��ж����ļ�����д��Ӳ����
				out.write(bs, 0, len);
			}
			System.out.println("�ļ��������");
			out.close();
			in.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
