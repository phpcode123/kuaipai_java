package com.java.test;

public class ThreadTest extends Thread {
	private Thread t;
	private String threadName;

	ThreadTest(String name) {
		threadName = name;
		System.out.println("Creating " + threadName);
	}

	public void run() {
		System.out.println("Running " + threadName);
		try {
			for (int i = 4; i > 0; i--) {
				System.out.println("Thread: " + threadName + ", " + i);
				// 让线程睡眠一会
				Thread.sleep(50);
			}
		} catch (InterruptedException e) {
			System.out.println("Thread " + threadName + " interrupted.");
		}
		System.out.println("Thread " + threadName + " exiting.");
	}

	public void start() {
		System.out.println("Starting " + threadName);
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}


	public static void main(String args[]) {
		ThreadTest T1 = new ThreadTest("Thread-1");
		T1.start();

		ThreadTest T2 = new ThreadTest("Thread-2");
		T2.start();
		
		ThreadTest T3 = new ThreadTest("Thread-3");
		T3.start();
	}
}
