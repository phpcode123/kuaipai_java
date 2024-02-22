package com.java.thread.test;


public class ThreadMain extends Thread {


	public static void main(String[] args) throws InterruptedException {
		ThreadDemo1 th1 = new ThreadDemo1("th1");
		ThreadDemo2 th2 = new ThreadDemo2("th2");
		
		th1.start();
		th2.start();

	}

}
