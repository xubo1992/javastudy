package com.xubo.fileSplitMerge;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class FileMerge {

	public static void main(String[] args) {
		// 拆分后配置文件路劲
		String filePath = "D:\\cs\\12.config";
		// 合并只能试用五次
		// 判断是否是第一次使用
		File file = new File("D:\\cs\\useCount.propreties");
		Properties properties = new Properties();
		int count = 0;
		try {
			if (!file.exists()) {
				//第一次使用
				file.createNewFile();
			}else {
				//非第一次使用
				properties.load(new FileInputStream(file));
				count = Integer.valueOf((String)properties.get("useCount"));
			}
			if (count < 5) {
				mergeFile(filePath);
				count++;
				properties.setProperty("useCount", String.valueOf(count));
				properties.store(new FileOutputStream(file), "记录合并文件的使用次数");
			} else {
				System.out.println("已超过使用次数，请付费使用！");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	// 合并拆分后的文件
	public static void mergeFile(String filePath) {
		Properties properties = new Properties();
		try {
			// 获取配置文件信息
			properties.load(new FileInputStream(new File(filePath)));
			String fileName = (String) properties.get("fileName");
			int count = Integer.valueOf((String) properties.get("count"));
			// 写入后的文件
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
			System.out.println("文件合并完毕！");
			out.close();
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
