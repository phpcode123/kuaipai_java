package com.java.test;

public class TuoNiao extends FeiNiao {
	
	@Override
	public void fly() {
		System.out.println("I just run... Cannot fly...");
	}
	
	public void callOverridedMethod() {
		//super�ǵ���
		super.fly();
	}
	
	public static void main(String[] args) {
		
		TuoNiao os = new TuoNiao();
		os.fly();
		
		
		os.callOverridedMethod();

	}

}
