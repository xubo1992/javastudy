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
	
	// ͨ��������ำֵ
	public static void demo1() {
		try {
			Class<?> class1 = Class.forName("com.xubo.reflect.Person");
			Person per = (Person)class1.newInstance();
			per.setName("����");
			System.out.println(per.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	// ͨ����������췽��
	public static void demo2() {
		try {
			Class<?> class1 = Class.forName("com.xubo.reflect.Person");
			// ��ȡprivate������
			Field idField = class1.getDeclaredField("age");
			// �޸����Եķ���Ȩ��
			idField.setAccessible(true);
			Person per = (Person)class1.newInstance();
			idField.set(per, 2);
			System.out.println(per.getAge());
			// ��ȡprivate���޲η���
			Method method = class1.getDeclaredMethod("say",null);
			method.setAccessible(true);
			method.invoke(per, null);
			// ��ȡprivate���вη���
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
