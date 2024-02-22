package com.java.test;

public class ThreadTest1 extends Thread{
	
	public void run() {
		System.out.println(this.getName());
	}

	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName());
		
		ThreadTest1 threadTest1 = new ThreadTest1();
		ThreadTest1 threadTest2 = new ThreadTest1();
		
		threadTest1.start();
		threadTest2.start();
		
		
	}

}
