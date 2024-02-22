package com.java.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexNewTest {

	public static void main(String[] args) {
		String content = "<span class=\"text\">在线客服</span>\r\n"
				+ "<span class=\"text\">反馈建议</span>\r\n"
				+ "<span class=\"text\">关注微信</span2>\r\n"
				+ "<span class=\"text\">返回顶部</span1>\r\n"
				+ "";
		String regex = "<span class=\"text\">(.*?)</.*?>";
		
		
		Pattern pat = Pattern.compile(regex);
		Matcher mat = pat.matcher(content);
		
		while(mat.find()) {
			System.out.println(mat.group(1));
		}

		
		
//		正则表达式：(\d\d)(\d\d)
//		groups(0)表示 0-4的字符串截取（即匹配到的整个字符串）
//		groups(1)表示0-2的字符串截取(即0-2)
//		groups(2)表示2-4的字符串截取(即2-4)
//
//		带括号表示分组的概念：(\d\d)(\d\d)  分别表示：(第一组)(第二组)
//
//		只要调整mat.group()里面的索引值即可。
	}

}
