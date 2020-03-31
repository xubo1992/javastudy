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
	 *  动态加载类和方法
	 *  根据配置文件的类名、方法名动态调用
	 */
	public static void demo1() {
		Properties properties = new Properties();
		try {
			properties.load(new FileReader(new File("src/com/xubo/reflect/class.txt")));
			// 获取类名
			String classname = (String)properties.get("classname");
			// 获取方法名
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
	
	// 反射可以越过泛型校验
	//虽然可以通过反射获取private的属性方法，也可以忽略泛型的校验，但实际开发中不建议这么做
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
	
	//通过万能的工具赋值
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
