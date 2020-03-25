package com.xubo.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/*
 * ����
 */
public class ReflectDemo1 {

	public static void main(String[] args) {
		//getClass1();
		//getMethods();
		//getInterfaces();
		//getSupperClasss();
		//getConstructors();
//		getFields();
		getInstances();
	}
	
	public static void getClass1() {
		/*
		 * ��������ַ�ʽ��
		 * 		1��Class.forName("ȫ����")
		 * 		2������.class
		 * 		3������.getClass
		 */
		try {
			// 1.Class.forName("ȫ����")
			Class<?> class1 = Class.forName("com.xubo.reflect.Person");
			System.out.println(class1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// 2.����.class
		Class<?> class2 = Person.class;
		System.out.println(class2);
		
		//3.����.getClass
		Person person = new Person();
		Class<?> class3 = person.getClass();
		System.out.println(class3);
	}
	
	//��ȡ����
	public static void getMethods() {
		try {
			Class<?> class1 = Class.forName("com.xubo.reflect.Person");
			Method[] methods = class1.getMethods();
			int count = 0;
			for(Method method:methods) {
				System.out.println(method);
				count++;
			}
			System.out.println("�������еĹ��з���(�����ࡢ�ӿڲ���˽�е�)����"+count);
			Method[] methods2 = class1.getDeclaredMethods();
			int count2 = 0;
			for(Method method:methods2) {
				System.out.println(method);
				count2++;
			}
			System.out.println("���Ǹ�������з���(�������ࡢ�ӿں�˽�е�)����"+count2);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//��ȡ�ӿ�
	public static void getInterfaces() {
		try {
			Class<?> class1 = Class.forName("com.xubo.reflect.Person");
			Class<?>[] interfaces = class1.getInterfaces();
			for(Class<?> interface1 :interfaces) {
				System.out.println(interface1);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// ��ȡ����
	public static void getSupperClasss() {
		try {
			Class<?> class1 = Class.forName("com.xubo.reflect.Person");
			Class<?> superclass = class1.getSuperclass();
			System.out.println(superclass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// ��ȡ���췽��
	public static void getConstructors() {
		try {
			Class<?> class1 = Class.forName("com.xubo.reflect.Person");
			Constructor<?>[] constructors = class1.getConstructors();
			for(Constructor<?> constructor:constructors) {
				System.out.println(constructor);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// ��ȡ����
	public static void getFields() {
		try {
			Class<?> class1 = Class.forName("com.xubo.reflect.Person");
			System.out.println("��ȡ���๫�е�����");
			Field[] fields = class1.getFields();
			for(Field field : fields) {
				System.out.println(field);
			}
			System.out.println("��ȡ�������е�����");
			Field[] fields2 = class1.getDeclaredFields();
			for(Field field : fields2) {
				System.out.println(field);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// ��ȡ��ǰ�����������ࣨ�ӿڣ��Ķ���ʵ��
	public static void getInstances() {
		try {
			Class<?> class1 = Class.forName("com.xubo.reflect.Person");
			Person person = (Person)class1.newInstance();
			person.testMethod();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
