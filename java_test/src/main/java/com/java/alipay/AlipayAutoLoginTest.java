package com.java.alipay;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeOptions;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;



public class AlipayAutoLoginTest {


	//http隧道代理设置zip压缩文件 begin-------------------------------------------------------------------
	public static void fileWriter(String str, String fileName) throws IOException{
		File f = new File(fileName);
		Writer out = new FileWriter(f,false);
		out.write(str);
		out.close();
	}

	

	
	//返回随机值
	public static int randomNum(int maxNum){
		Random random = new Random();
		return random.nextInt(maxNum);
	}

	//获取两个数字之间的随机值
	public static int getRandomBetweenNumbers(int m,int n){     
	    return (int)(m + Math.random() * (n - m + 1));
	}
	
	//暂停时间,直接调用这个类方法,单位是秒
	public static void timeSleep(int num) {
		try {
			Thread.sleep(num * 1000);
		} catch (InterruptedException e) {	
			e.printStackTrace();
		}
	}
	
	//移动浏览器滚动条
	public static void moveScrollBar(int moveNum, WebDriver driver) {
		
		JavascriptExecutor driver_js = (JavascriptExecutor)driver;

		int countNum = 0;
		while(countNum < moveNum) {
			countNum += 2;
			driver_js.executeScript("document.querySelector(\"html\").scrollTo(0,"+String.valueOf(countNum)+")");
			
			//随机数字，当数值符合条件时就暂停1s钟
			int countRandNum = AlipayAutoLoginTest.randomNum(100) + 99;
			
			if(countNum % countRandNum == 0 && countNum > 0) {
				//暂停1S
				AlipayAutoLoginTest.timeSleep(1);
			}
		}

	}
	
	
	
	//将元素定位到屏幕的正中间，使元素处于可以可视点击的状态
	public static void moveScrollIntoView(WebElement element, WebDriver driver) {
		JavascriptExecutor driver_js = (JavascriptExecutor)driver;
		driver_js.executeScript("arguments[0].scrollIntoView()", element);
		AlipayAutoLoginTest.timeSleep(1);
	}
	
	
	
	//返回所有匹配结果
	public static List<String> getMatchers(String regex, String source){
		Pattern pat = Pattern.compile(regex);
		Matcher mat = pat.matcher(source);
		List<String> list = new ArrayList<String>();
		while(mat.find()) {
			list.add(mat.group());
		}
		return list;
	}
	
	//查找所有的url，与上面的函数只有mat.group(1)不同。href=\"/Win10/2019-02-19/12726.html\"，
	//mat.group()匹配结果： href="/Win10/2019-02-19/12726.html"
	//mat.group(1)匹配结果： /Win10/2019-02-19/12726.html"
	public static List<String> findAllLinkurl (String regex, String source){
		Pattern pat = Pattern.compile(regex);
		Matcher mat = pat.matcher(source);
		List<String> list = new ArrayList<String>();
		while(mat.find()) {
			list.add(mat.group(1));
		}
		return list;
	}
	
	
	//将相对url转换为绝对url
	public static String absoluteURL(String absolutePath, String relativePath){
		URL absoluteUrl = null;
		try {
			absoluteUrl = new URL(absolutePath);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}

		URL aUrl = null;
		try {
			aUrl = new URL(absoluteUrl,relativePath);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return aUrl.toString();
		

	}
	

	
	// ---------------------- main主函数，程序开始运行 ----------------------
	//已分层开发，所有功能移动至RunApp.java中
	public static void main(String[] args) throws IOException {

		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver_win32\\chromedriver.exe");
		
		
		//增加浏览器的user-agent等参数
		ChromeOptions options = new ChromeOptions();
	    options.addArguments("user-agent=\"Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Mobile Safari/537.36\"");
	    options.addArguments("--no-sandbox");
	    options.addArguments("--ignore-certificate-errors");
	   //取消头部的chrome正在受自动化测试软件控制的提示信息
	    options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

	    
		WebDriver driver = new ChromeDriver(options);

			
		//设置窗口大小
		Dimension dimension = new Dimension(1280, 1000);
		driver.manage().window().setSize(dimension);
		
		//开始请求网址
		driver.get("https://mbillexprod.alipay.com/enterprise/mainIndex.htm#/");
		//System.out.println(driver.getPageSource());
		System.out.println(">>> Program begin...");
		AlipayAutoLoginTest.timeSleep(15);
		
		while(true) {
			int randNum = AlipayAutoLoginTest.randomNum(2);
			if(randNum == 0) {
				System.out.println(">>> timesleep("+String.valueOf(180)+")");
				AlipayAutoLoginTest.timeSleep(180);
			}else {
				System.out.println(">>> timesleep("+String.valueOf(200)+")");
				AlipayAutoLoginTest.timeSleep(200);
			}
			
			driver.navigate().refresh();
		}
			
			
	}

}
