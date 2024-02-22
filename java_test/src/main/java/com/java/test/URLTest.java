package com.java.test;


import java.net.MalformedURLException;
import java.net.URL;
public class URLTest {

	public static void main(String[] args) throws MalformedURLException {
	
		URL	baseURI = new URL("http://www.baidu.com/12313/");

		URL	absoluteURL = new URL(baseURI, "../12313a/asdadasd/test.html");


		System.out.println(absoluteURL.toString());
		
		
	
	}

}
