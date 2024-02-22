package com.java.icpbeian;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OtherClass {
	
	

	
	
	public static int randomNum(int maxNum) {
		Random random = new Random();
		return random.nextInt(maxNum);
	}

	public static int getRandomBetweenNumbers(int m, int n) {
		return (int) (m + Math.random() * (n - m + 1));
	}

	public static void timeSleep(int num) {
		try {
			Thread.sleep(num * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void moveScrollBar(int moveNum, WebDriver driver) {

		JavascriptExecutor driver_js = (JavascriptExecutor) driver;

		int countNum = 0;
		while (countNum < moveNum) {
			countNum += 2;
			driver_js.executeScript("document.querySelector(\"html\").scrollTo(0," + String.valueOf(countNum) + ")");

			int countRandNum = OtherClass.randomNum(100) + 99;

			if (countNum % countRandNum == 0 && countNum > 0) {
				
				OtherClass.timeSleep(1);
			}
		}

	}

	public static void moveScrollIntoView(WebElement element, WebDriver driver) {
		JavascriptExecutor driver_js = (JavascriptExecutor) driver;
		driver_js.executeScript("arguments[0].scrollIntoView()", element);
		OtherClass.timeSleep(1);
	}

	public static List<String> getMatchers(String regex, String source) {
		Pattern pat = Pattern.compile(regex);
		Matcher mat = pat.matcher(source);
		List<String> list = new ArrayList<String>();
		while (mat.find()) {
			list.add(mat.group());
		}
		return list;
	}

	// url mat.group(1) href=\"/Win10/2019-02-19/12726.html\"
	// mat.group() href="/Win10/2019-02-19/12726.html"
	// mat.group(1) /Win10/2019-02-19/12726.html"
	public static List<String> findAllLinkurl(String regex, String source) {
		Pattern pat = Pattern.compile(regex);
		Matcher mat = pat.matcher(source);
		List<String> list = new ArrayList<String>();
		while (mat.find()) {
			list.add(mat.group(1));
		}
		return list;
	}

	public static String absoluteURL(String absolutePath, String relativePath) {
		URL absoluteUrl = null;
		try {
			absoluteUrl = new URL(absolutePath);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}

		URL aUrl = null;
		try {
			aUrl = new URL(absoluteUrl, relativePath);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return aUrl.toString();

	}

	

	public static Boolean getMatcher(String regex, String source){
		Pattern pat = Pattern.compile(regex);
		Matcher mat = pat.matcher(source);
		List<String> list = new ArrayList<String>();
		while(mat.find()) {
			list.add(mat.group());
		}
		if(list.size() > 0) {
			return true;
		}else {
			return false;
		}
		
	}

	
	
	public static String cleanLine(String line) {
		String cleanLine = line.replaceAll("\r\n", line);
		return cleanLine;
	}
	
	
	public static String readStealthMinJs() {
		String classPathJs = "D:\\test\\stealth.min.js";
		try {
			URL fileURL = NewChromeDriver.class.getResource(classPathJs); 
			byte[] data = IOUtils.readInputStream(fileURL.openStream());
			return new String(data);
		} catch (Exception e) {
		}
		try {
			URL fileURL = NewChromeDriver.class.getResource("/"+classPathJs); 
			byte[] data = IOUtils.readInputStream(fileURL.openStream());
			return new String(data);
		} catch (Exception e) {
		}
		return null;
	}

}
