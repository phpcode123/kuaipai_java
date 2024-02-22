package com.java.test;

public class ThreadSleep {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int countNum = 0 ;
		while(true) {
			countNum += 1;
			try {
				Thread.sleep(1000);
				System.out.println(">>> Hello World!"+String.valueOf(countNum));
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}

}
