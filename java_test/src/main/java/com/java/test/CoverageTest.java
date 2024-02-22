package com.java.test;

public class CoverageTest {

	public static void main(String[] args) {
		for(int i = 0;i < 100; i++ ) {
			System.out.println(">>> i:"+i);
			if(i == 80) {
				break;
			}
			if(i == 101) {
				System.out.println(">>> 101");
			}
		
		}
		System.out.println(">>> Running success!");

	}

}
