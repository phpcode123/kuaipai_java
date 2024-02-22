package ATestJavaCode;

import java.awt.AWTException;
import java.awt.Robot;

import get_proxy_ip.OtherClass;

public class TestJavaCode {

	public static void main(String[] args) throws AWTException {
		
//		int countNum = 0;
//		int itemidNum = 0;
//		while(countNum < 96) {
//			countNum += 1;
//			itemidNum += 360000;
//			
//			System.out.println(">> countNum:"+countNum+" itemidNum:"+itemidNum);
//		}
//		
		
		
		Robot rt = new Robot();
		int countNum = 0;
		int x = 0;
		int y = 0;
		while(countNum < 1000) {
			countNum += 1;
			x += 1;
			y += 1;
			rt.mouseMove(x, y);
			System.out.println(">> countNum:"+countNum+" x:"+x+" y:"+y);
			//OtherClass.timeSleepByMilliSecond(100);;
		    rt.delay(2);
		    
		}
		

	}

}
