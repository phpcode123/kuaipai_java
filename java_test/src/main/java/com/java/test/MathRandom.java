package com.java.test;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class MathRandom {

	public static void main(String[] args) {
		ArrayList<String>  a = new ArrayList<String>();
		int intNum = -1;
		while(intNum < 9) {
			intNum += 1;
			a.add(String.valueOf(intNum));
			
		}
		
		
		int charNum = 96;
		while( charNum < 122) {
			charNum += 1;
			a.add(String.valueOf((char)charNum));
			
		}
		
		String rsv_did = "";
		int countNum = 0;
		while(countNum < 32) {
			countNum += 1;
			
			Random random = new Random();
			int num = random.nextInt(a.size());
			String charString = a.get(num);
			rsv_did += charString;
		}
		
		System.out.println(rsv_did);
		
		
		//Match算法
		String rsc_did1 = "";
		countNum = 0;
		while(countNum < 32) {
			countNum += 1;
			double c = Math.floor(16 * Math.random());
			String d = Integer.toHexString((int)c);
			rsc_did1 += d;
		}
		System.out.println(rsc_did1);
		
		
		
		//UUID
		String aa = UUID.randomUUID().toString().replace("-", "");
		System.out.println(">> aa:"+aa);
	}

}
