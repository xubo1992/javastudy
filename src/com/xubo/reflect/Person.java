package com.xubo.reflect;

public class Person implements ReflectInterface1,ReflectInterface2{
	public int id;
	private String name;
	private int age;
	
	public Person(){
	}
	
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	private Person(int id) {
		this.id = id;
	}
	
	public Person(int id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

	private void say() {
		System.out.println("private say...");
	}
	private void say2(String names) {
		System.out.println("private say2..."+names);
	}

	@Override
	public void testMethod2() {
		System.out.println("interface testMethod2...");
		
	}

	@Override
	public void testMethod() {
		System.out.println("interface testMethod1...");
	}
}
