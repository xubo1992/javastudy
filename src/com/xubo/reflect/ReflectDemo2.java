package com.xubo.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectDemo2 {
	public static void main(String[] args) {
//		demo1();
//		demo2();
		demo3();
	}
	
	// 通过反射给类赋值
	public static void demo1() {
		try {
			Class<?> class1 = Class.forName("com.xubo.reflect.Person");
			Person per = (Person)class1.newInstance();
			per.setName("张三");
			System.out.println(per.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	// 通过反射调构造方法
	public static void demo2() {
		try {
			Class<?> class1 = Class.forName("com.xubo.reflect.Person");
			// 获取private的属性
			Field idField = class1.getDeclaredField("age");
			// 修改属性的访问权限
			idField.setAccessible(true);
			Person per = (Person)class1.newInstance();
			idField.set(per, 2);
			System.out.println(per.getAge());
			// 获取private的无参方法
			Method method = class1.getDeclaredMethod("say",null);
			method.setAccessible(true);
			method.invoke(per, null);
			// 获取private的有参方法
			Method method2 = class1.getDeclaredMethod("say2",String.class);
			method2.setAccessible(true);
			method2.invoke(per, "zs");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void demo3() {
		try {
			Class<?> class1 = Class.forName("com.xubo.reflect.Person");
			Constructor<?> constructor = class1.getConstructor(String.class,int.class);
			System.out.println(constructor);
			Constructor<?> constructor2 = class1.getDeclaredConstructor(int.class);
			System.out.println(constructor2);
			Person per = (Person)constructor.newInstance("zs",50);
			System.out.println(per.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
