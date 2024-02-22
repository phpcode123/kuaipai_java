package szgt17.com;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;



public class DriverGet extends ChromeOptions {
	
	
	public static WebDriver get(String url,String proxyString) {
	
		
		ChromeOptions options = new ChromeOptions();
		
	    //设置代理
	    if(Settings.ableProxy) {
	    	//System.out.println(">> proxyString:"+proxyString);

			Proxy proxy = new Proxy();
			proxy.setHttpProxy(proxyString).setSslProxy(proxyString);
			options.setProxy(proxy);
					    
	    }

	    options.addArguments("--header-args");
		options.addArguments("--disable-gpu");
		options.addArguments("--ignore-certificate-errors");
		options.addArguments("--no-sandbox"); //关闭沙盒模式
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--disable-blink-features=AutomationControlled");
		
	
		
		String userAgent = UserAgent.randPcUserAgent();
		System.out.println(">> userAgent:"+userAgent);
		options.addArguments("--user-agent=" + userAgent);
		//忽略ssl错误
		options.setCapability("acceptSslCerts", true);
		options.setCapability("acceptInsecureCerts", true);
		
		//窗口默认最大化
		options.addArguments("--start-maximized");
		
		//不提示“Chrome正受到自动测试软件控制” 
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation") );
		
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		prefs.put("profile.default_content_settings.popups", 0);
		prefs.put("profile.password_manager_enabled", false);
		
		// 禁止下载加载图片
		if (Settings.disableLoadImage) {
			prefs.put("profile.managed_default_content_settings.images", 2); 
		}
		options.setExperimentalOption("prefs", prefs);
		

		
		
		//增加浏览器指纹插件 开始-----------------------
		File webglCrxFile = new File(Settings.webglCrxFilePath);
		File audioCrxFile = new File(Settings.audioContentCrxFilePath);
		File fontCrxFile = new File(Settings.fontCrxFilePath);
		File canvasCrxFile = new File(Settings.canvasCrxFilePath);
		
		List<File> extendsFileList = new ArrayList<File>();
		extendsFileList.add(webglCrxFile);
		extendsFileList.add(audioCrxFile);
		extendsFileList.add(fontCrxFile);
		extendsFileList.add(canvasCrxFile);
		options.addExtensions(extendsFileList);
		//增加浏览器指纹插件 结束-----------------------
		
		//执行增加stealthMinJs隐藏浏览器指纹					

		Map<String,Object> parameters = new HashMap<String,Object>();
		String stealth = OtherClass.readStealthMinJs();
		parameters.put("source", stealth);
		
		WebDriver driver;
		
		try {
			driver = new ChromeDriver(options);
		}catch (Exception e) {
			driver = new ChromeDriver(options);
			e.printStackTrace();
		}
		
		((ChromeDriver)driver).executeCdpCommand("Page.addScriptToEvaluateOnNewDocument", parameters);
		
	    System.setProperty("webdriver.chrome.driver", Settings.getChromeDriverPath());
	    driver.manage().timeouts().setScriptTimeout(Duration.ofSeconds(30));//脚本执行超时
	    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));//页面加载超时
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); //设置匹配等待时间，如果在2s内匹配不到结果就返回错误
	    driver.manage().window().setSize(new Dimension(1300, 1024));

	    try{
	    	driver.get(url);
	    }catch (Exception e) {
			driver.quit();
			OtherClass.timeSleep(2);
		}
	    
	    return driver;
	    
	}

}
