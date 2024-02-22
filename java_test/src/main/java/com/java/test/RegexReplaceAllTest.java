package com.java.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class RegexReplaceAllTest {

	public static void main(String[] args) {
		String a = "H_PS_PSSID=34131_33763_33848_33607_34134_26350; path=/; domain=.baidu.com, BD_UPN=13314452;";
		Pattern pat = Pattern.compile("domain.*?,");
		Matcher mat = pat.matcher(a);
		String newStr = mat.replaceAll("");
		System.out.println(">> newStr:"+newStr);

	}

}
