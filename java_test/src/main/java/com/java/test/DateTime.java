package com.java.test;


import java.util.Date;
import java.text.SimpleDateFormat;

public class DateTime {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Date date = new Date();
		long startTimestamp = date.getTime();
		
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		System.out.println(df.format(date));
		//2021-05-11 09:50:35
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//unixTimestamp
		Date date_01 = new Date();
		long endTimestamp = date_01.getTime();
		
		int timeCost = ((int)endTimestamp - (int)startTimestamp)/1000;
		System.out.println(">>> startTimestamp:"+String.valueOf(startTimestamp)+" endTimestamp:"+String.valueOf(endTimestamp));
		System.out.println(">>> timeCost:"+String.valueOf(timeCost)+"s");
		
		//startTimestamp:1620697835505 endTimestamp:1620697840529
		//>>> timeCost:5s
		
		
		
	}

}
