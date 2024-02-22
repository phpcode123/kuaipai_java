package com.java.alipay;

import java.text.SimpleDateFormat;
import java.util.Date;


public class GetAlipayMoney {

	public static void main(String[] args) {
		
		//String url = "http://www.baidu.com/";
		
		//String resp = Requests.get(url).send().readToText();
		//System.out.println(">>> htmlcode:"+resp);
		
		
		System.out.println(">>> Running success!");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date();
		System.out.println(">>> dateTime:"+String.valueOf(df.format(date.getTime())));
		long unixTimestamp = date.getTime();
		System.out.println(">>> unixTimestamp:"+String.valueOf((int)(unixTimestamp/1000)));

	}

}
