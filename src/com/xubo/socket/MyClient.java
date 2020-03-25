package com.xubo.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MyClient {
	public static void main(String[] args) throws IOException{
		Socket socket = new Socket("127.0.0.1",9999);
		BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
		System.out.println(input.readLine());
		input.close();
		socket.close();
	}
}
