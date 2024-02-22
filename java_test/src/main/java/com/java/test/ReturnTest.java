package com.java.test;



public class ReturnTest {
	public static String SystemEnv() {
		if(System.getProperty("os.name").toLowerCase().contains("linux")) {
			return "123";
		}
		if(System.getProperty("os.name").toLowerCase().contains("win")) {
			return "456";
		}
		return "System.getProperty error.";
	}
	public static void main(String[] args) {
		System.out.println(ReturnTest.SystemEnv());
		//win 456
		//linux 123
		//other System.getProperty error
	}
}
