package selenium_linux_pc;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class MoveScroll {
	//move scroll in rand
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
	
	//move  scroll into view 
	public static void moveScrollIntoView(WebElement element, WebDriver driver) {
		JavascriptExecutor driver_js = (JavascriptExecutor)driver;
		driver_js.executeScript("arguments[0].scrollIntoView()", element);
		OtherClass.timeSleep(1);
	}
	public static void main(String[] args) {
		

	}

}
