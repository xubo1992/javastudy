package com.xubo.reflect;

public class Student {
	
	private int score;
	
	public void sayHi() {
		System.out.println("I am a student");
	}

	@Override
	public String toString() {
		return "Student [score=" + score + "]";
	}
	
	
}
