package com.java.requests;


//import net.dongliu.requests.*;
import java.util.Date;
import java.text.SimpleDateFormat;
public class RequestTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String url = "http://www.baidu.com/";
		
		//String resp = Requests.get(url).send().readToText();
		//System.out.println(">>> htmlcode:"+resp);
		
		
		System.out.println(">>> Running success!");
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		System.out.println(">>> dateTime:"+String.valueOf(df.format(date.getTime())));
		long unixTimestamp = date.getTime();
		System.out.println(">>> unixTimestamp:"+String.valueOf((int)(unixTimestamp/1000)));
		
	}

}
