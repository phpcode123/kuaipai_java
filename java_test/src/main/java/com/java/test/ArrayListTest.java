package com.java.test;

import java.util.ArrayList;

public class ArrayListTest {

	

	public static void main(String[] args) {
		
		ArrayList<String> stringArray = new ArrayList<String>();
		stringArray.add("hello");
		stringArray.add("world");
		stringArray.add("123");
		stringArray.add("456");
		stringArray.remove("hello");
		System.out.println(stringArray);
		for(String i : stringArray) {
			System.out.println(i);
		}

		
		
		ArrayList<String> intList = new ArrayList<String>();
				
		intList.add("10");
		intList.add("11");
		intList.add("12");
		intList.add("13");
		
		System.out.println(intList);
		intList.remove("10");
		System.out.println(intList);
		
	}



}
