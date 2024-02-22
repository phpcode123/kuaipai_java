package com.java.test;

import java.util.regex.Pattern;

//import javax.swing.Spring;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;



public class RegexTest {
	
	
	public static List<String> getMatchers(String regex, String source){
		Pattern pat = Pattern.compile(regex);
		Matcher mat = pat.matcher(source);
		List<String> list = new ArrayList<String>();
		while(mat.find()) {
			list.add(mat.group());
		}
		return list;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<String> listTest = RegexTest.getMatchers("\\d+", "asdadadad123asdadadad123213asdadasd1231312313asdadsa213");
		System.out.print(">>> list:"+String.valueOf(listTest)+listTest);
		
		
		
	}

}
