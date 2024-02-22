package com.java.thread.test;

import com.java.icpbeian.OtherClass;

public class ThreadDemo1 extends Thread{
	
	private String who;
	
	public void run() {
		while(true) {
			System.out.println(who+":"+Thread.currentThread().getName());
			OtherClass.timeSleep(3);
		}
	}

	
	public ThreadDemo1(String who) {
		this.who = who;
	}
	
	
}
