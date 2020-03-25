package com.xubo.reflect;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

public class ReflectDemo3 {

	public static void main(String[] args) {
//		demo1();
//		demo2();
		demo3();
	}
	
	/*
	 *  ��̬������ͷ���
	 *  ���������ļ�����������������̬����
	 */
	public static void demo1() {
		Properties properties = new Properties();
		try {
			properties.load(new FileReader(new File("src/com/xubo/reflect/class.txt")));
			// ��ȡ����
			String classname = (String)properties.get("classname");
			// ��ȡ������
			String methodname = (String)properties.get("methodname");
			Class<?> class1 = Class.forName(classname);
			Method method = class1.getDeclaredMethod(methodname);
			method.setAccessible(true);
			method.invoke(class1.newInstance());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// �������Խ������У��
	//��Ȼ����ͨ�������ȡprivate�����Է�����Ҳ���Ժ��Է��͵�У�飬��ʵ�ʿ����в�������ô��
	public static void demo2() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(222);
//		list.add("zs");
		Class<?> class1 = list.getClass();
		try {
			Method method = class1.getMethod("add",Object.class);
			method.invoke(list, "zs");
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(list);
	}
	
	//ͨ�����ܵĹ��߸�ֵ
	public static void demo3() {
		Person ps = new Person();
		PropertyUtil.setProperty(ps, "name", "zs");
		PropertyUtil.setProperty(ps, "age", 1);
		System.out.println(ps.toString());
		Student st = new Student();
		PropertyUtil.setProperty(st, "score", 99);
		System.out.println(st.toString());
	}
	
	
	
}
