package com.java.icpbeian;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import cn.hutool.core.io.file.*;

public class RunApp {

	public static void main(String[] args) {
		while(true) {
			FileReader domainFile = new FileReader("D:\\test\\domain.txt");
			List<String> domainList = domainFile.readLines();
			
			//遍历域名列表，然后批量查询
			int countNum = 0;
			for(String domain : domainList) {
				countNum += 1;
				System.out.println(">>> domain:"+domain+" countNum:"+countNum);
				
				NewChromeOptions.CHROME_DRIVER = "C:\\Program Files\\chromedriver_win32\\chromedriver.exe";
				HttpsProxy httpsProxy = new HttpsProxy(ChromeExtends.proxyHost,ChromeExtends.proxyPort,ChromeExtends.proxyUser, ChromeExtends.proxyPass);
				NewChromeDriver driver = NewChromeDriver.newChromeInstance(false, false, httpsProxy);
				
				
				
				//System.out.println("driver:"+JSONUtil.toJsonStr(driver));
				
				
				driver.get("https://beian.miit.gov.cn/index#/Integrated/recordQuery");
				//driver.get("https://www.panfa.net/index.php?s=testip/index");
				WebElement icpBeianButton = driver.findElement(By.xpath("//*[@id=\"app\"]/div/header/div[2]/div/div[2]/h5"));
				icpBeianButton.click();
				
				//填写域名
				WebElement inputElement = driver.findElement(By.xpath("//*[@id=\"app\"]/div/header/div[3]/div/div/input"));
				inputElement.clear();
				inputElement.sendKeys(domain);
				OtherClass.timeSleep(1);
				
				//点击搜索按钮
				WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"app\"]/div/header/div[3]/div/button"));
				searchButton.click();
				
				//找到滑块按钮，并拖动滑块
				WebElement slideblock = driver.findElement(By.xpath("//*[@id=\"sildeBox\"]/div"));
				Actions action = new Actions(driver);
				action.clickAndHold(slideblock);
				//action.moveByOffset(100, 0).perform();
				action.dragAndDropBy(slideblock,305, 0).perform();
				action.release();
				
				
				OtherClass.timeSleep(300);
				
				//当在当前页面请求次数超过5次就重启浏览器，避免ip被拉黑，否则就刷新页面
				if(countNum > 5) {
					driver.quit();
				}else {
					driver.navigate().refresh();
				}
				
				
				
			}
			
			if(countNum > 500) {
				break;
			}
		}

	}

}
