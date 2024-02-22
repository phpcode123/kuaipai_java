package baidu_pc;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
		
		//headless模式 无头浏览器
		options.addArguments("--headless");
		
		
		//user-agent
		String userAgent = UserAgent.randPcUserAgentString(OtherClass.getRandomBetweenNumbers(1, 3));
		System.out.println(">> userAgent:"+userAgent);
		options.addArguments("--user-agent=" + userAgent);
		
		
		//指纹因子 独立参数--browser-rand-hash
		int browserRandHash = OtherClass.getRandomBetweenNumbers(1, 99999999);


		//hardwareConcurrency 设置后此项值会是0，不如不设置
		int[] hardwareConcurrencyList = {2,4,6,8,12,16,24,32};
		int hardwareConcurrency = hardwareConcurrencyList[OtherClass.randomNum(hardwareConcurrencyList.length)];
		
	
		//deviceMemory
		int[] deviceMemoryList = {2,4,8,16,24,32,48,64,128};
		int deviceMemory = deviceMemoryList[OtherClass.randomNum(deviceMemoryList.length)];
		
		
		//platform
		String[] platformList = {"Win32","Win64"};
		String platform = platformList[OtherClass.randomNum(platformList.length)];
		
		
		//colorDepth
		int[] colorDepthList = {16,24,32,48};
		int colorDepth = colorDepthList[OtherClass.randomNum(colorDepthList.length)];
		
		//pixelDepth
		int pixelDepth = colorDepth;
		
		

		
		
		
		
		//screen
		String[] screenList= {"800*600","1200*800","1024*600","1024*768","1152*864","1280*720","1280*768","1280*800","1280*900","1280*960","1280*1024","1360*768","1360*1024","1400*1050","1400*2100","1600*600","1600*1024","1600*1200","1680*1050","1792*1344","1800*1440","1920*1080","1920*1200","1920*1440"};
		String screenStr = screenList[OtherClass.randomNum(screenList.length)];
		

		String screenWidth = String.valueOf(Integer.valueOf(screenStr.split("\\*")[0]));
		String screenHeight = String.valueOf(Integer.valueOf(screenStr.split("\\*")[1]));
		//String screenAvailWidth = String.valueOf(Integer.valueOf(screenStr.split("\\*")[0]));
		//String screenAvailHeight = String.valueOf(Integer.valueOf(screenStr.split("\\*")[1]) - 40);


		//systemArchitecture
		String[] systemArchitectureList = {"x86","x86_64"};
		String systemArchitecture = systemArchitectureList[OtherClass.randomNum(systemArchitectureList.length)];

		
		//browserVersion  103.0.5060.114
		String browserVersion = "92.0."+String.valueOf(OtherClass.getRandomBetweenNumbers(4472, 5060))+"."+String.valueOf(OtherClass.getRandomBetweenNumbers(15, 60));
		
		
		//gpuRenderer gpuVendor (webGL Renderer\ webGL Vendor) 
		
		ArrayList<String> gpuList = new ArrayList<String>();
		gpuList.add("600");
		gpuList.add("610");
		gpuList.add("620");
		gpuList.add("630");
		gpuList.add("640");
		gpuList.add("650");
		String gpuRenderer = "ANGLE (Intel, Intel(R) UHD Graphics "+gpuList.get(OtherClass.randomNum(gpuList.size()))+" Direct3D11 vs_5_0 ps_5_0, D3D11-"+String.valueOf(OtherClass.getRandomBetweenNumbers(1, 99))+"."+String.valueOf(OtherClass.getRandomBetweenNumbers(1, 99))+"."+String.valueOf(OtherClass.getRandomBetweenNumbers(1, 999))+"."+String.valueOf(OtherClass.getRandomBetweenNumbers(100, 9999))+")";
		String gpuVendor = "Google Inc. (Intel)";
		

			
		
		//languages
		String languages= "zh-CN,en-US";
		
	
		//languages
		//String language = "zh-CN";
		
		
		//computerName
		String[] computerNameList = {"Dell-","DESKTOP-","LENOVO-","HP-","HUAWEI-"};
		String computerName = computerNameList[OtherClass.getRandomBetweenNumbers(0, computerNameList.length-1)]+String.valueOf(OtherClass.getRandomBetweenNumbers(100, 999));
		
		
		
		//UserName
		String userName = "administrator";
		
		
		
		//开始添加参数
		options.addArguments("--browser-rand-hash="+browserRandHash);
		
		String commonParamStr;
		
		//禁止下载图片的设置项
		//hardwareConcurrency cpu核心数无法自定义，总是识别为0，不如不动
		//System.out.println(">> hardwareConcurrency:"+hardwareConcurrency);
		if(Settings.disableLoadImage) {
			commonParamStr = "&hardwareConcurrency="+hardwareConcurrency+"&deviceMemory="+deviceMemory+"&platform="+platform+"&colorDepth="+colorDepth+"pixelDepth="+pixelDepth+"&screenWidth="+screenWidth+"&screenHeight="+screenHeight+"&systemArchitecture="+systemArchitecture+"&browserVersion="+browserVersion+"&gpuRenderer="+gpuRenderer+"&gpuVendor="+gpuVendor+"&disabledImage=1&hideSmallWindow=1&languages="+languages+"&productSub=20030107&vendor=Google Inc.&vendorSub=&computerName="+computerName+"userName="+userName+"&";	
		}else{
			commonParamStr = "&hardwareConcurrency="+hardwareConcurrency+"&deviceMemory="+deviceMemory+"&platform="+platform+"&colorDepth="+colorDepth+"pixelDepth="+pixelDepth+"&screenWidth="+screenWidth+"&screenHeight="+screenHeight+"&systemArchitecture="+systemArchitecture+"&browserVersion="+browserVersion+"&gpuRenderer="+gpuRenderer+"&gpuVendor="+gpuVendor+"&hideSmallWindow=1&languages="+languages+"&productSub=20030107&vendor=Google Inc.&vendorSub=&computerName="+computerName+"userName="+userName+"&";
		}
		
		//System.out.println(commonParamStr);
		options.addArguments("--browser-common-param=\""+commonParamStr+"\"");
		
		
		
		
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


		//设置chrome的文件夹路径
		options.setBinary(Settings.winChromePath);
		
		//webdriver 路径
		System.setProperty("webdriver.chrome.driver", Settings.winChromeDriver);
		WebDriver driver = new ChromeDriver(options);
		
		
		
	    
	    driver.manage().timeouts().setScriptTimeout(Duration.ofSeconds(20));//脚本执行超时
	    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));//页面加载超时
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //设置匹配等待时间，如果在2s内匹配不到结果就返回错误
	    driver.manage().window().setSize(new Dimension(Integer.valueOf(screenWidth), Integer.valueOf(screenHeight) ));

	    try{
	    	driver.get(url);
	    }catch (Exception e) {
			driver.quit();
			OtherClass.timeSleep(2);
		}
	    
	    return driver;
	    
	}


}