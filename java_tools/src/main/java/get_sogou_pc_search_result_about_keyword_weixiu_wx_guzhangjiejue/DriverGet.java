package get_sogou_pc_search_result_about_keyword_weixiu_wx_guzhangjiejue;

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
		
		
		//user-agent
		String userAgent = UserAgent.randPcUserAgentString(OtherClass.getRandomBetweenNumbers(1, 3));
		System.out.println(">> userAgent:"+userAgent);
		options.addArguments("--user-agent=" + userAgent);
		
		
		
		//languages
		String languagesParam = "zh-CN,en,en-GB,en-US";
		options.addArguments("--languages-param=" + languagesParam);
		
		//languages
		String languageParam = "zh-CN";
		options.addArguments("--language-param=" + languageParam);
		
		//指纹因子 独立参数--browser-rand-hash
		int browserRandHash = OtherClass.getRandomBetweenNumbers(1, 99999999);
		options.addArguments("--rand-hash-param="+String.valueOf(browserRandHash));
		
		//platform
		//String[] platformList = {"Win32","Win64"};
		//String platform = platformList[OtherClass.randomNum(platformList.length)];
		String platform =OtherClass.getUUID();
		options.addArguments("--platform-param="+platform);
		


		//hardwareConcurrency
		int[] hardwareConcurrencyList = {2,4,6,8,12,16,24,32};
		int hardwareConcurrency = hardwareConcurrencyList[OtherClass.randomNum(hardwareConcurrencyList.length)];
		options.addArguments("--hardware-concurrency-param="+String.valueOf(hardwareConcurrency));
		

		//deviceMemory
		int[] deviceMemoryList = {2,4,8,10,12,14,16,18,20,24,28,32,36,40,42,46,48,52,56,60,64,128};
		int deviceMemory = deviceMemoryList[OtherClass.randomNum(deviceMemoryList.length)];
		options.addArguments("--device-memory-param="+String.valueOf(deviceMemory));
		
		
		//screen
		String[] screenList= {"800*600","1200*800","1024*600","1024*768","1152*864","1280*720","1280*768","1280*800","1280*900","1280*960","1280*1024","1360*768","1360*1024","1400*1050","1400*2100","1600*600","1600*1024","1600*1200","1680*1050","1792*1344","1800*1440","1920*1080","1920*1200","1920*1440","2048*768","2048*1536","2304*864","2560*800","2560*1024","2800*1050","3200*1200","4096*1536"};
		String screenStr = screenList[OtherClass.randomNum(screenList.length)];
		
		//随机将宽度加减
		int addRandNum = OtherClass.randomNum(200);
		
		
		String screenWidth;
		String screenHeight;
		String screenAvailWidth;
		String screenAvailHeight;
		System.out.println(">> screenStr:"+screenStr);

		
		if(addRandNum%2 ==  0) {
			screenWidth = String.valueOf(Integer.valueOf(screenStr.split("\\*")[0]) - addRandNum);
			screenHeight = String.valueOf(Integer.valueOf(screenStr.split("\\*")[1]) - addRandNum);
			screenAvailWidth = String.valueOf(Integer.valueOf(screenStr.split("\\*")[0]) - addRandNum);
			screenAvailHeight = String.valueOf(Integer.valueOf(screenStr.split("\\*")[1]) - addRandNum - 40);
			
		}else {
			screenWidth = String.valueOf(Integer.valueOf(screenStr.split("\\*")[0]) + addRandNum);
			screenHeight = String.valueOf(Integer.valueOf(screenStr.split("\\*")[1]) + addRandNum);
			screenAvailWidth = String.valueOf(Integer.valueOf(screenStr.split("\\*")[0]) + addRandNum);
			screenAvailHeight = String.valueOf(Integer.valueOf(screenStr.split("\\*")[1]) + addRandNum - 40);
			
		}
		
		options.addArguments("--screen-width-param="+screenWidth);
		options.addArguments("--screen-height-param="+screenHeight);
		options.addArguments("--screen-avail-width-param="+screenAvailWidth);
		options.addArguments("--screen-avail-height-param="+screenAvailHeight);
		
		
		//gpuRenderer gpuVendor (webGL Renderer\ webGL Vendor) 
		String gpuVendorParam = "Google Inc. (Intel)";
		ArrayList<String> gpuList = new ArrayList<String>();
		gpuList.add("600");
		gpuList.add("610");
		gpuList.add("620");
		gpuList.add("630");
		gpuList.add("640");
		gpuList.add("650");
		String gpuRendererParam = "ANGLE (Intel, Intel(R) UHD Graphics "+gpuList.get(OtherClass.randomNum(gpuList.size()))+" Direct3D11 vs_5_0 ps_5_0, D3D11-"+String.valueOf(OtherClass.getRandomBetweenNumbers(1, 99))+"."+String.valueOf(OtherClass.getRandomBetweenNumbers(1, 99))+"."+String.valueOf(OtherClass.getRandomBetweenNumbers(1, 999))+"."+String.valueOf(OtherClass.getRandomBetweenNumbers(100, 9999))+")";
		
		options.addArguments("--gpu-renderer-param="+gpuRendererParam);
		options.addArguments("--gpu-vendor-param="+gpuVendorParam);
		
		

		
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
		
		//设置Linux下的二进制文件目录
		if(OtherClass.isLinux()) {
			options.setBinary("/kuaipai/chromium/Default/chrome");
		}
		System.setProperty("webdriver.chrome.driver", Settings.getChromeDriverPath());
		WebDriver driver = new ChromeDriver(options);
		
		
		
	    
	    driver.manage().timeouts().setScriptTimeout(Duration.ofSeconds(30));//脚本执行超时
	    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));//页面加载超时
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); //设置匹配等待时间，如果在2s内匹配不到结果就返回错误
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
