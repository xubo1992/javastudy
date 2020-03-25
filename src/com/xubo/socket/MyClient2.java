package com.xubo.socket;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyClient2 {
	/*
	 * 通过socket接收文件：客户端
	 */
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("127.0.0.1",8888);
			InputStream in = socket.getInputStream();
			File file = new File("D:\\aaa.jar");
			if(!file.exists()) {
				//若没有该文件则创建
				file.createNewFile();
			}
			// 将此文件从内存写入硬盘
			FileOutputStream out = new FileOutputStream(file);
			byte[] bs = new byte[100];
			int len = -1;
			//不断从内存中读取文件
			while((len = in.read(bs)) != -1) {
				//将内存中读的文件不断写入硬盘中
				out.write(bs, 0, len);
			}
			System.out.println("文件接收完成");
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
