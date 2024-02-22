package com.java.test;


import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;



public class RestTemplateTest {
	public static String httpGet(String url) {
		RestTemplate template = new RestTemplate();
		ResponseEntity<String> entity = template.getForEntity(url, String.class);
	    return entity.getBody();
	}
	 

	
	public static String cleanLine(String str) {
		String line = str.replaceAll("[\r|\n]", "");
		return line;
	}
	
	public static void main(String[] args) {
		String htmlcode = RestTemplateTest.httpGet("http://192.168.0.101:8083/win10.html");
		
		//以\n为分隔符，但是当前行中还是有\n，要去掉
		String[] htmlcode_list = htmlcode.split("\n");
		int countNum = 0;
		for(String i : htmlcode_list) {
			countNum ++;
			String line = cleanLine(i);
			System.out.println(">>> countNum:"+countNum+" "+line);
		}
	}

}
