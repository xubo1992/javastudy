package com.xubo.socket;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer2 {
	/*
	 * 通过socket传送文件：服务端
	 */
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(8888);
		while(true) {
			Socket socket = serverSocket.accept();
			MyDownload download = new MyDownload(socket);
			Thread thread = new Thread(download);
			thread.start();
		}
		
	}
}
