package com.java.test;

import java.util.*;
import java.util.regex.*;
import java.
public class StringTest {
	
	
	public List<String> getMatchers(String regex, String source){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);
        List<String> list = new ArrayList<String>();
        while (matcher.find()) {
            list.add(matcher.group());
        }
        return list;
    }

	
	

	public static void main(String[] args) {
		
		
		StringTest a = new StringTest();
		List<String> b = a.getMatchers("\\d+", "123asdad1456123");
		
		System.out.println(b);
		
		
		
		String c = "192.168.0.101:80";
		String[] d = c.split(":");
		System.out.println(getType(d));
		for(int i=0;i<d.length;i++) {
			System.out.println(d[i]);
		}
		
		
		
		
		
		
	}

}
