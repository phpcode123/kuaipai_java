package com.java.chrometest;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;

import org.openqa.selenium.Dimension;


public class ITest {


	    public static void main(String[] args) throws InterruptedException,IOException {
	        
			String userAgent = "Mozilla/5.0 (iPhone; CPU iPhone OS 10_1_1 like Mac OS X) AppleWebKit/602.2.14 (KHTML, like Gecko) Version/10.0 MQQBrowser/8.8.2 Mobile/14B100 Safari/602.11";
			
//			System.setProperty("webdriver.chrome.driver", "C:\\path\\to\\chromedriver.exe");
//			ChromeOptions options = new ChromeOptions();
//			options.addArguments("start-maximized"); // open Browser in maximized mode
//			options.addArguments("disable-infobars"); // disabling infobars
//			options.addArguments("--disable-extensions"); // disabling extensions
//			options.addArguments("--disable-gpu"); // applicable to windows os only
//			options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
//			options.addArguments("--no-sandbox"); // Bypass OS security model
//			WebDriver driver = new ChromeDriver(options);
//			driver.get("https://google.com");
			
			
			
			ChromeOptions options = new ChromeOptions();
			//options.addArguments("--headless");
		    options.addArguments("user-agent=\""+userAgent+"\"");
		    options.addArguments("--no-sandbox");
		    options.addArguments("--ignore-certificate-errors");
		    options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
	
		    
			WebDriver driver = new ChromeDriver(options);
			System.out.println(">>> userAgent:"+userAgent);
			
			Dimension dimension = new Dimension(414, 736);
			driver.manage().window().setSize(dimension);		
			driver.get("http://m.baidu.com");
			
			
			
			System.out.println(">>> Program begin...");
	        System.out.println(driver.getPageSource());
	        System.out.println(">>> unheadless.");
	        Thread.sleep(1000000);
	        driver.close();
	    }


}
