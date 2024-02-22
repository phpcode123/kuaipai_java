package com.java.test;

public class ContinueBreakTest {

	public static void main(String[] args) {
		
		int countNum = 0;
		while(countNum < 10) {
			countNum+=1;
			if(countNum == 5) {
				continue;
			}else {
				System.out.println(countNum);
			}
		}
		
		System.out.println("-------- 分割线01 --------");

		countNum = 0;
		while(countNum < 10) {
			countNum+=1;
			if(countNum == 5) {
				break;
			}else {
				System.out.println(countNum);
			}
		}
		
		
		System.out.println("-------- 分割线02 --------");
		
		int countNum2=0;
		while(countNum2 < 3) {
			countNum2 +=1;
			
			countNum = 0;
			while(countNum < 10) {
				countNum+=1;
				if(countNum == 5) {
					break;
				}else {
					System.out.println(countNum);
				}
			}
			
		}
		
	}
}
