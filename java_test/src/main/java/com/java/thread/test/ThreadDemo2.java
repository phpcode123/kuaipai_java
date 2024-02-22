package com.java.thread.test;

import com.java.icpbeian.OtherClass;

public class ThreadDemo2 extends Thread{
	
	private String who;
	
	public void run() {
		while(true) {
			System.out.println(who + ":"+Thread.currentThread().getName());
			OtherClass.timeSleep(1);
		}
		
	}
	
	public ThreadDemo2(String who) {
		this.who = who;

		
	}
	

}
