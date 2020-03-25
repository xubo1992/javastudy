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
			//׼�������͵��ļ�
			File file = new File("D:\\commons-net-ftp-2.0.jar");
			if(!file.exists()) {
				System.out.println("δ�ҵ�ָ���ļ�");
				return;
			}
			//���建����Ϊ100�ֽ�
			byte[] bs = new byte[100];
			// �����ļ���Ӳ�̶����ڴ�
	        FileInputStream fileInputStream = new FileInputStream(file);
			int len = -1;
			while((len = fileInputStream.read(bs)) != -1 ){
				out.write(bs,0,len);
	        }
			System.out.println("�ļ�������ϣ�");
			fileInputStream.close();
			out.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
