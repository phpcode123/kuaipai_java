package com.java.icpbeian;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

import cn.hutool.json.*;


public class NewChromeOptions extends ChromeOptions {

	public static final String ANDROID_USER_AGENT = "Mozilla/5.0 (Linux; Android 7.0; PLUS Build/NRD90M) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.98 Mobile Safari/537.36";

	public static final String IOS_USER_AGENT = "Mozilla/5.0 (iPhone; CPU iPhone OS 11_0_1 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A402 Safari/604.1";

	public static final String CHROME_USER_AGENT = RandUserAgent.randUserAgent();
	
	public static String CHROME_DRIVER;
	
	public final String userAgent;
	
	public final boolean hideFingerprint;
	public final boolean disableLoadImage;
	public final boolean headless;
	
	public NewChromeOptions() {
		this(false, false);
	}
	
	public NewChromeOptions(boolean disableLoadImage, boolean headless) {
		this(disableLoadImage, headless, true, null, CHROME_USER_AGENT);
	}
	
	public NewChromeOptions(boolean disableLoadImage, boolean headless, HttpsProxy httpsProxy,String userAgent) {
		this(disableLoadImage, headless, true, httpsProxy, CHROME_USER_AGENT);
	}
	
	public NewChromeOptions(boolean disableLoadImage, boolean headless, boolean hideFingerprint, HttpsProxy httpsProxy,String userAgent) {
		this.disableLoadImage = disableLoadImage;
		this.headless = headless;
		this.hideFingerprint = hideFingerprint;
		
		ChromeOptions options = this;
		//ChromeOptions options = new ChromeOptions();
		
		if ((CHROME_DRIVER != null && new File(CHROME_DRIVER).exists()) || Boot.isMacSystem()) {
			String chromeDriver = CHROME_DRIVER;
			if (chromeDriver == null) {
				chromeDriver = System.getProperty("webdriver.chrome.driver");
			}
			if (chromeDriver == null) {
				throw new RuntimeException("请设置CHROME_DRIVER的路径");
			}
			System.setProperty("webdriver.chrome.driver", chromeDriver);
		}
		
		//options.addArguments("--headless");// headless mode
		
		options.addArguments("--header-args");
		options.addArguments("--disable-gpu");
		options.addArguments("--ignore-certificate-errors");
		options.addArguments("--no-sandbox"); //关闭沙盒模式
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--disable-blink-features=AutomationControlled");
		//options.addArguments("user-data-dir=C:/Users/Administrator/AppData/Local/Google/Chrome/User Data");//待研究
		//不提示“Chrome正受到自动测试软件控制” 
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation") );
		//options.setExperimentalOption("useAutomationExtension", false);

		
		// 增加代理
//		
//		try {
//			File extendFile;
//			extendFile = ChromeExtends.createProxyAuthExtension(ChromeExtends.proxyHost, ChromeExtends.proxyPort, ChromeExtends.proxyUser,
//ChromeExtends.proxyPass);
//			List<File> aFileList = new ArrayList<File>();
//			aFileList.add(extendFile);
//			options.addExtensions(aFileList);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
		
		
		
		if (userAgent == null) {
			userAgent = CHROME_USER_AGENT;
		}
		this.userAgent = userAgent;
		options.addArguments("--user-agent=" + userAgent);
		//忽略ssl错误
		options.setCapability("acceptSslCerts", true);
		options.setCapability("acceptInsecureCerts", true);
		if (httpsProxy != null) {
			if (httpsProxy.getUsername() != null && httpsProxy.getPassword() != null) {
				options.addArguments("--start-maximized");
				File extension = ChromeExtensionUtil.createProxyauthExtension(httpsProxy.getServer(), httpsProxy.getPort(),
						httpsProxy.getUsername(), httpsProxy.getPassword());
				//log.info("createProxyauthExtension:" + extension.getAbsolutePath());
				options.addExtensions(extension);
			} else {
				options.addArguments("--disable-extensions");
				options.addArguments("proxy-server=" + httpsProxy.getServer() + ":" + httpsProxy.getPort());
			}
		} else {
			options.addArguments("--disable-extensions");
		}
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		prefs.put("profile.default_content_settings.popups", 0);
		prefs.put("profile.password_manager_enabled", false);
		if (disableLoadImage) {
			prefs.put("profile.managed_default_content_settings.images", 2); // 禁止下载加载图片
		}
		options.setExperimentalOption("prefs", prefs);
		

		
		
		System.out.println("NewChromeOptions:"+JSONUtil.toJsonStr(options));
	}

	public String getUserAgent() {
		return userAgent;
	}
}
