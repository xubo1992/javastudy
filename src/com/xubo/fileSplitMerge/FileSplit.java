package com.xubo.fileSplitMerge;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class FileSplit {
	public static void main(String[] args) {
		//原文件
		File resfile = new File("D:\\apache-tomcat-7.0.92-windows-x64.zip");
		//拆分后的文件夹
		File spFile = new File("D:\\cs");
		FileSplit(resfile,spFile);
		
	}
	public static void FileSplit(File resfile,File spFile) {
		if(!resfile.exists()) {
			System.out.println("未找到原文件");
			return;
		}
		if(!spFile.exists()) {
			spFile.mkdirs();
		}
		try {
			//定义一个输入流，N个输出流
			FileInputStream in = new FileInputStream(resfile);
			FileOutputStream out = null;
			byte[] bs = new byte[1024*1024];
			//定义拆分后文件名称
			int count = 1;
			//定义发送文件长度
			int len = -1;
			while((len = in.read(bs)) != -1) {
				out = new FileOutputStream(new File(spFile,count+".sp"));
				out.write(bs, 0, len);
				out.flush();
				count ++ ;
			}
			System.out.println("文件拆分完毕！");
			// 将文件个数及文件名写入配置文件中
			out = new FileOutputStream(new File(spFile,count+".config"));
			Properties properties = new Properties();
			properties.setProperty("fileName", resfile.getName());
			properties.setProperty("count",String.valueOf(count-1));
			properties.store(out, "假装有些注释！");
			out.close();
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
