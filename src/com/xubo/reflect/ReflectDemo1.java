package com.xubo.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/*
 * 反射
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
		 * 反射的三种方式：
		 * 		1、Class.forName("全类名")
		 * 		2、类名.class
		 * 		3、对象.getClass
		 */
		try {
			// 1.Class.forName("全类名")
			Class<?> class1 = Class.forName("com.xubo.reflect.Person");
			System.out.println(class1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// 2.类名.class
		Class<?> class2 = Person.class;
		System.out.println(class2);
		
		//3.对象.getClass
		Person person = new Person();
		Class<?> class3 = person.getClass();
		System.out.println(class3);
	}
	
	//获取方法
	public static void getMethods() {
		try {
			Class<?> class1 = Class.forName("com.xubo.reflect.Person");
			Method[] methods = class1.getMethods();
			int count = 0;
			for(Method method:methods) {
				System.out.println(method);
				count++;
			}
			System.out.println("这是所有的公有方法(含父类、接口不含私有的)共："+count);
			Method[] methods2 = class1.getDeclaredMethods();
			int count2 = 0;
			for(Method method:methods2) {
				System.out.println(method);
				count2++;
			}
			System.out.println("这是该类的所有方法(不含父类、接口含私有的)共："+count2);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//获取接口
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
	
	// 获取父类
	public static void getSupperClasss() {
		try {
			Class<?> class1 = Class.forName("com.xubo.reflect.Person");
			Class<?> superclass = class1.getSuperclass();
			System.out.println(superclass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// 获取构造方法
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

	// 获取属性
	public static void getFields() {
		try {
			Class<?> class1 = Class.forName("com.xubo.reflect.Person");
			System.out.println("获取该类公有的属性");
			Field[] fields = class1.getFields();
			for(Field field : fields) {
				System.out.println(field);
			}
			System.out.println("获取该类所有的属性");
			Field[] fields2 = class1.getDeclaredFields();
			for(Field field : fields2) {
				System.out.println(field);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// 获取当前反射所代表类（接口）的对象实例
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
