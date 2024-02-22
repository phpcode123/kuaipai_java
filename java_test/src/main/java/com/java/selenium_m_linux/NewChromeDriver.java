package com.java.selenium_m_linux;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;



public class NewChromeDriver extends ChromeDriver {

public static final Random random = new Random();
	
	private NewChromeOptions options;
	
	private static volatile String stealthMinJsData = null;
	
	public NewChromeDriver() {
		this(new NewChromeOptions());
	}
	
	@SuppressWarnings("static-access")
	public NewChromeDriver(NewChromeOptions options) {
		super(options);
		manage().timeouts().setScriptTimeout(Duration.ofSeconds(30));//脚步执行超时
		manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));//页面加载超时
		manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
		
		
		
		this.options = options;
		manage().window().setSize(new Dimension(1300, 1024));
		Map<String,Object> parameters = new HashMap<String,Object>();
		if (options.hideFingerprint) {
			if (stealthMinJsData == null) {
				stealthMinJsData = readStealthMinJs();
			}
			String stealth = stealthMinJsData;
			if (stealth != null) {
				parameters.put("source", stealth);
				executeCdpCommand("Page.addScriptToEvaluateOnNewDocument", parameters);
			}
		}
	}
	
	public static String readStealthMinJs() {
		String classPathJs = RunApp_m_linux.classPathJsPath;
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
	
	
	public void hideElement(WebElement elm) {
		if (elm != null) {
			executeScript("arguments[0].style='display:none;';", elm);
		}
	}

	@Override
	public void get(String url) {
		if (url.startsWith("//")) {
			url = "https:" + url;
		}
		for (int i = 0 ; i < 2 ; i++) {
			try {
				super.get(url);
				//log.debug("visit:"+url);
			}catch(Exception e){
				//log.warn("chrome请求异常:"+url, e);
				continue;
			}
			break;
		}
	}
	
	public void getIgnoreTimeout(String url) {
		try {
			super.get(url);
			//log.debug("visit:"+url);
		}catch(Exception e){
		}
	}
	
	public boolean checkElement(String cssSelect) {
		try {
			return findElement(By.cssSelector(cssSelect)).isDisplayed();
		}catch(Exception e) {
		}
		return false;
	}
	
	

	public String getUserAgent() {
		return options.getUserAgent();
	}
	

	@SuppressWarnings("static-access")
	@Override
	public void quit() {
		try {
			super.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
		}
	}
	
	
	public boolean switchSlide(WebElement rdsSlideReset, WebElement rdsSlideBtn) {
		try {
			//manage().window().maximize();
			//manage().window().fullscreen();
			int width = rdsSlideReset.getRect().width;
			Actions actions = new Actions(this);
			try {
				actions.moveToElement(rdsSlideBtn).perform();Thread.sleep(random.nextInt(1500));
			} catch (Exception e) {
				e.printStackTrace();
			}
			Thread.sleep(random.nextInt(1500));
			actions.clickAndHold(rdsSlideBtn).perform();
			int toWidth = 0;
			int floatY = 0;
			while (true) {
				int xOffset = random.nextInt(25) + 1;
				int fy = random.nextInt(3);
				if (floatY > 10) {
					fy = - fy;
				}
				floatY += fy;
				actions.moveByOffset(xOffset, floatY).perform();
				toWidth += xOffset;
				if (toWidth > width) {Thread.sleep(random.nextInt(50));
					actions.release().perform();
					break;
				}
				Thread.sleep(random.nextInt(100));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public byte[] screenshot(WebElement webElement) throws Exception {
		return webElement.getScreenshotAs(OutputType.BYTES);
	}
	
	public byte[] screenshot() throws Exception {
		return getScreenshotAs(OutputType.BYTES);
	}
	
	public void mouseClick(WebElement webElement) throws Exception {
		Actions actions = new Actions(this);
		actions.moveToElement(webElement).perform();
		Thread.sleep(random.nextInt(800));
		actions.clickAndHold(webElement).perform();
		Thread.sleep(random.nextInt(300));
		actions.release().perform();
		Thread.sleep(random.nextInt(1500));
	}
	
	public void keyboardClear(WebElement webElement, int backSpace) throws Exception {
		mouseClick(webElement);
		for (int k = 0; k < backSpace + random.nextInt(3); k++) {
			webElement.sendKeys(Keys.BACK_SPACE);Thread.sleep(random.nextInt(100));
		}
	}
	
	public void keyboardInput(WebElement webElement, String text) throws Exception {
		keyboardInput(webElement, text, true);
	}
	
	public void keyboardInput(WebElement webElement, String text, boolean needClick) throws Exception {
		if (needClick)
			mouseClick(webElement);
		int inputed = 0;
		int backTimes = 0;
		int prebackNums = random.nextInt(text.length() / 3);
		for (int k = 0; k < text.length(); k++) {
			webElement.sendKeys(String.valueOf(text.charAt(k)));Thread.sleep(random.nextInt(300) + 300);
			inputed ++;
			int backNum = inputed >= 3 && backTimes <= prebackNums ?random.nextInt(3) : 0;
			backTimes += backNum;
			for (int i = 0; i < backNum; i++) {
				webElement.sendKeys(Keys.BACK_SPACE);Thread.sleep(random.nextInt(300) + 300);
				k--;
			}
		}
	}
	
	public void jsInput(WebElement webElement, String text) throws Exception {
		executeScript("arguments[0].value='"+text+"';", webElement);
	}
	
	public void jsClick(WebElement webElement) throws Exception {
		jsClick(webElement, false);
	}
	
	public void jsClick(WebElement webElement, boolean async) throws Exception {
		if (async) {
			try {
				executeAsyncScript("arguments[0].click();", webElement);Thread.sleep(1000);
			} catch (Exception e) {
				//log.warn("js点击出错", e);
			}
		}else {
			executeScript("arguments[0].click();", webElement);
		}
	}

	public boolean switchToTab(String containsTitleChars) {
		if (getTitle().contains(containsTitleChars)) {
			return true;
		}
		String currentHandlerId = getWindowHandle();
		for (String handlerId : getWindowHandles()) {
			if (handlerId.equals(currentHandlerId)) {
				continue;
			}
			String title = getTitle();
			if (title.contains(containsTitleChars)) {
				return true;
			}
		}
		return false;
	}
	
	public static NewChromeDriver newChromeInstance(boolean disableLoadImage, boolean headless, HttpsProxy httpsProxy) {
		return new NewChromeDriver(new NewChromeOptions(disableLoadImage, headless,httpsProxy, NewChromeOptions.CHROME_USER_AGENT));
	}

	public static NewChromeDriver newAndroidInstance(boolean disableLoadImage, boolean headless, HttpsProxy httpsProxy) {
		return new NewChromeDriver(new NewChromeOptions(disableLoadImage, headless, httpsProxy, NewChromeOptions.ANDROID_USER_AGENT));
	}

	public static NewChromeDriver newIOSInstance(boolean disableLoadImage, boolean headless, HttpsProxy httpsProxy) {
		return new NewChromeDriver(new NewChromeOptions(disableLoadImage, headless,httpsProxy, NewChromeOptions.IOS_USER_AGENT));
	}
	
	public static NewChromeDriver newInstance(boolean disableLoadImage, boolean headless, boolean hideFingerprint, HttpsProxy httpsProxy, String userAgent) {
		return new NewChromeDriver(new NewChromeOptions(disableLoadImage, headless, hideFingerprint,httpsProxy, userAgent));
	}
}
