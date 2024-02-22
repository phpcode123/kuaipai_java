package com.java.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProcessTest {

	public static void main(String[] args) {
		InputStream in = null;
		try {
			Process pro = Runtime.getRuntime().exec(new String[]{"kill -9 `ps -ef|grep \"chrome\" | grep -v \"grep\"|awk '{print $2}'`"});
			pro.waitFor();
			in = pro.getInputStream();
			BufferedReader read = new BufferedReader(new InputStreamReader(in));
			String result = read.readLine();
			System.out.println("INFO:"+result);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
