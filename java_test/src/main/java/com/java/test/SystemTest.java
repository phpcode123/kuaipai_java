package com.java.test;

import java.io.File;

public class SystemTest{
	
	public static String str = "test";
	
	
	public static void main(String[] args) {
		boolean a = System.getProperty("os.name").toLowerCase().contains("win");
		System.out.println(a);
		System.out.println(SystemTest.str);
		
		File a1 = new File("D:\\test\\");
		File[] b = a1.listFiles();
		for(File i:b) {
			System.out.println(i);
		}
	}
}
