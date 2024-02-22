package com.java.test;

import java.util.Date;
import java.text.*;
public class ChromeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int countNum = 0;
		while(countNum < 10) {
			countNum += 1;
			
			System.out.println(countNum);
			
			try {
				System.out.println(">>> Thread.sleep(100)");
				Thread.sleep(100);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
		System.out.println(">>> Running success!");

	
	
		Date date = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		System.out.println(ft.format(date));
		
		long unixTimestamp = date.getTime();
		System.out.println(">>> unixTimestamp:"+String.valueOf(unixTimestamp));
	}

}
