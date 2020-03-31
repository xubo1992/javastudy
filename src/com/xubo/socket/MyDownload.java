package com.xubo.socket;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class MyDownload implements Runnable{
	private Socket socket;

	public MyDownload(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			OutputStream out = socket.getOutputStream();
			//准备待传送的文件
			File file = new File("D:\\commons-net-ftp-2.0.jar");
			if(!file.exists()) {
				System.out.println("未找到指定文件");
				return;
			}
			//定义缓存区为100字节
			byte[] bs = new byte[100];
			// 将此文件从硬盘读入内存
	        FileInputStream fileInputStream = new FileInputStream(file);
			int len = -1;
			while((len = fileInputStream.read(bs)) != -1 ){
				out.write(bs,0,len);
	        }
			System.out.println("文件发送完毕！");
			fileInputStream.close();
			out.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
