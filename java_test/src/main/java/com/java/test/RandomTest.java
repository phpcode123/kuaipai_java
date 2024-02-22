package com.java.test;


import java.util.Random;
//import java.lang.Math.*;


public class RandomTest {
	
	//random num
	public static int randomNum(int maxNum){
		Random random = new Random();
		return random.nextInt(maxNum);
	}

	public static void main(String[] args) {
		double testRandomNum = Math.random();
		
		System.out.println(">>> testRandomNum:"+String.valueOf(Math.round(testRandomNum*1000)));
		
		
		Random testNum = new Random();
		System.out.println(">>> testNum:"+String.valueOf(testNum.nextInt(2)));
//		
//		>>> testRandomNum:14
//		>>> testNum:82
//		>>> testRandomNum:503
//		>>> testNum:97

		while(true) {
			int randNum = RandomTest.randomNum(10);
			
			if(randNum == 10 || randNum == 0) {
				System.out.println(">> "+randNum);
			}
		}
	}
	

}
