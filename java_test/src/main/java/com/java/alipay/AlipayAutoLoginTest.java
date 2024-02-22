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


	//http�����������zipѹ���ļ� begin-------------------------------------------------------------------
	public static void fileWriter(String str, String fileName) throws IOException{
		File f = new File(fileName);
		Writer out = new FileWriter(f,false);
		out.write(str);
		out.close();
	}

	

	
	//�������ֵ
	public static int randomNum(int maxNum){
		Random random = new Random();
		return random.nextInt(maxNum);
	}

	//��ȡ��������֮������ֵ
	public static int getRandomBetweenNumbers(int m,int n){     
	    return (int)(m + Math.random() * (n - m + 1));
	}
	
	//��ͣʱ��,ֱ�ӵ�������෽��,��λ����
	public static void timeSleep(int num) {
		try {
			Thread.sleep(num * 1000);
		} catch (InterruptedException e) {	
			e.printStackTrace();
		}
	}
	
	//�ƶ������������
	public static void moveScrollBar(int moveNum, WebDriver driver) {
		
		JavascriptExecutor driver_js = (JavascriptExecutor)driver;

		int countNum = 0;
		while(countNum < moveNum) {
			countNum += 2;
			driver_js.executeScript("document.querySelector(\"html\").scrollTo(0,"+String.valueOf(countNum)+")");
			
			//������֣�����ֵ��������ʱ����ͣ1s��
			int countRandNum = AlipayAutoLoginTest.randomNum(100) + 99;
			
			if(countNum % countRandNum == 0 && countNum > 0) {
				//��ͣ1S
				AlipayAutoLoginTest.timeSleep(1);
			}
		}

	}
	
	
	
	//��Ԫ�ض�λ����Ļ�����м䣬ʹԪ�ش��ڿ��Կ��ӵ����״̬
	public static void moveScrollIntoView(WebElement element, WebDriver driver) {
		JavascriptExecutor driver_js = (JavascriptExecutor)driver;
		driver_js.executeScript("arguments[0].scrollIntoView()", element);
		AlipayAutoLoginTest.timeSleep(1);
	}
	
	
	
	//��������ƥ����
	public static List<String> getMatchers(String regex, String source){
		Pattern pat = Pattern.compile(regex);
		Matcher mat = pat.matcher(source);
		List<String> list = new ArrayList<String>();
		while(mat.find()) {
			list.add(mat.group());
		}
		return list;
	}
	
	//�������е�url��������ĺ���ֻ��mat.group(1)��ͬ��href=\"/Win10/2019-02-19/12726.html\"��
	//mat.group()ƥ������ href="/Win10/2019-02-19/12726.html"
	//mat.group(1)ƥ������ /Win10/2019-02-19/12726.html"
	public static List<String> findAllLinkurl (String regex, String source){
		Pattern pat = Pattern.compile(regex);
		Matcher mat = pat.matcher(source);
		List<String> list = new ArrayList<String>();
		while(mat.find()) {
			list.add(mat.group(1));
		}
		return list;
	}
	
	
	//�����urlת��Ϊ����url
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
	

	
	// ---------------------- main������������ʼ���� ----------------------
	//�ѷֲ㿪�������й����ƶ���RunApp.java��
	public static void main(String[] args) throws IOException {

		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver_win32\\chromedriver.exe");
		
		
		//�����������user-agent�Ȳ���
		ChromeOptions options = new ChromeOptions();
	    options.addArguments("user-agent=\"Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Mobile Safari/537.36\"");
	    options.addArguments("--no-sandbox");
	    options.addArguments("--ignore-certificate-errors");
	   //ȡ��ͷ����chrome�������Զ�������������Ƶ���ʾ��Ϣ
	    options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

	    
		WebDriver driver = new ChromeDriver(options);

			
		//���ô��ڴ�С
		Dimension dimension = new Dimension(1280, 1000);
		driver.manage().window().setSize(dimension);
		
		//��ʼ������ַ
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
