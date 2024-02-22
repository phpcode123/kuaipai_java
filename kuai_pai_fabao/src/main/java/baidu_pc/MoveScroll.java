package baidu_pc;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class MoveScroll {

	public static void moveScrollBar(int moveNum, WebDriver driver) {
		
		JavascriptExecutor driver_js = (JavascriptExecutor)driver;

		int countNum = 0;
		while(countNum < moveNum) {
			countNum += 2;
			driver_js.executeScript("document.querySelector(\"html\").scrollTo(0,"+String.valueOf(countNum)+")");
			
			//random num
			int countRandNum = OtherClass.randomNum(100) + 99;
			
			if(countNum % countRandNum == 0 && countNum > 0) {
				
				OtherClass.timeSleep(1);
			}
		}

	}
	
	
	//Main方法中最好是调用这个方法，此方法系moveScrollBar重载
	//默认值最好是设置为，driver,2,5
	public static void moveScrollBar(WebDriver driver, int minCountNum, int maxRandomNum) {
		int countNum = 0;
		//移动浏览器的滚动条假装浏览刷新页面
		System.out.println(">> Move scroll begin ------------------------");
		while(countNum < minCountNum) {
			countNum += 1;
			int moveScrollBarRandNum = OtherClass.randomNum(maxRandomNum);
			System.out.println(">> countNum:"+String.valueOf(countNum)+" moveScrollBarRandNum:"+String.valueOf(moveScrollBarRandNum));
			MoveScroll.moveScrollBar(moveScrollBarRandNum * 100, driver);
			
		}
		System.out.println(">> Move scroll end ------------------------");
	}
	
	
	//move  scroll bar into view 
	public static void moveScrollIntoView(WebElement element, WebDriver driver) {
		JavascriptExecutor driver_js = (JavascriptExecutor)driver;
		driver_js.executeScript("arguments[0].scrollIntoView()", element);
		OtherClass.timeSleep(1);
	}
	
}
