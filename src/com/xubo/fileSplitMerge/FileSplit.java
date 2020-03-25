package com.xubo.fileSplitMerge;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class FileSplit {
	public static void main(String[] args) {
		//ԭ�ļ�
		File resfile = new File("D:\\apache-tomcat-7.0.92-windows-x64.zip");
		//��ֺ���ļ���
		File spFile = new File("D:\\cs");
		FileSplit(resfile,spFile);
		
	}
	public static void FileSplit(File resfile,File spFile) {
		if(!resfile.exists()) {
			System.out.println("δ�ҵ�ԭ�ļ�");
			return;
		}
		if(!spFile.exists()) {
			spFile.mkdirs();
		}
		try {
			//����һ����������N�������
			FileInputStream in = new FileInputStream(resfile);
			FileOutputStream out = null;
			byte[] bs = new byte[1024*1024];
			//�����ֺ��ļ�����
			int count = 1;
			//���巢���ļ�����
			int len = -1;
			while((len = in.read(bs)) != -1) {
				out = new FileOutputStream(new File(spFile,count+".sp"));
				out.write(bs, 0, len);
				out.flush();
				count ++ ;
			}
			System.out.println("�ļ������ϣ�");
			// ���ļ��������ļ���д�������ļ���
			out = new FileOutputStream(new File(spFile,count+".config"));
			Properties properties = new Properties();
			properties.setProperty("fileName", resfile.getName());
			properties.setProperty("count",String.valueOf(count-1));
			properties.store(out, "��װ��Щע�ͣ�");
			out.close();
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
