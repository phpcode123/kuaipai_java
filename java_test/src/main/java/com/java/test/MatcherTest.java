package com.java.test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class MatcherTest {
	
	public static void main(String[] args) {
		String linkurl = "href=\"/Win10/2019-02-19/12726.html\"";
		Pattern pat = Pattern.compile("href=[\"'](.*?)[\"']");
		Matcher mat = pat.matcher(linkurl);
		List<String> list = new ArrayList<String>();
		while(mat.find()) {
			list.add(mat.group(1));
		}
		
		for(String i : list) {
			System.out.println(">>> i:"+i);
		}

	}

}
