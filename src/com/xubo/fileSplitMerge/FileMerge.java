package com.xubo.fileSplitMerge;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class FileMerge {

	public static void main(String[] args) {
		// ��ֺ������ļ�·��
		String filePath = "D:\\cs\\12.config";
		// �ϲ�ֻ���������
		// �ж��Ƿ��ǵ�һ��ʹ��
		File file = new File("D:\\cs\\useCount.propreties");
		Properties properties = new Properties();
		int count = 0;
		try {
			if (!file.exists()) {
				//��һ��ʹ��
				file.createNewFile();
			}else {
				//�ǵ�һ��ʹ��
				properties.load(new FileInputStream(file));
				count = Integer.valueOf((String)properties.get("useCount"));
			}
			if (count < 5) {
				mergeFile(filePath);
				count++;
				properties.setProperty("useCount", String.valueOf(count));
				properties.store(new FileOutputStream(file), "��¼�ϲ��ļ���ʹ�ô���");
			} else {
				System.out.println("�ѳ���ʹ�ô������븶��ʹ�ã�");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	// �ϲ���ֺ���ļ�
	public static void mergeFile(String filePath) {
		Properties properties = new Properties();
		try {
			// ��ȡ�����ļ���Ϣ
			properties.load(new FileInputStream(new File(filePath)));
			String fileName = (String) properties.get("fileName");
			int count = Integer.valueOf((String) properties.get("count"));
			// д�����ļ�
			File file = new File("D:\\cs\\", fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream out = new FileOutputStream(file);
			FileInputStream in = null;
			for (int i = 1; i <= count; i++) {
				in = new FileInputStream(new File("D:\\cs\\" + i + ".sp"));
				byte[] bt = new byte[1024 * 1024];
				int len = 0;
				while ((len = in.read(bt)) != -1) {
					out.write(bt, 0, len);
				}
			}
			System.out.println("�ļ��ϲ���ϣ�");
			out.close();
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
