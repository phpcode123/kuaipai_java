package sogou_m;

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

	    //禁止弹窗提醒 好像无用？
	    //options.addArguments("--disable-notifications");
	    
	    options.addArguments("--header-args");
     	options.addArguments("--disable-gpu");
		options.addArguments("--ignore-certificate-errors");
		options.addArguments("--no-sandbox"); //关闭沙盒模式
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--disable-blink-features=AutomationControlled");
		
		
		//user-agent
		String userAgent = UserAgent.randMUserAgentString(OtherClass.getRandomBetweenNumbers(1, 2));
		System.out.println(">> userAgent:"+userAgent);
		options.addArguments("--user-agent=" + userAgent);
		
		
		
		//languages
		ArrayList<String> languagesList = new ArrayList<String>();
		languagesList.add("zh-TW");
		languagesList.add("zh-HK");
		languagesList.add("en");
		languagesList.add("en-GB");
		languagesList.add("en-US");
		
		//随机组合languages
		int countNum = 0;
		ArrayList<String> languagesList_ = new ArrayList<String>();
		while(countNum < OtherClass.getRandomBetweenNumbers(2, languagesList.size()-1)) {
			countNum += 1;
			String languages_ = languagesList.get(OtherClass.getRandomBetweenNumbers(0, languagesList.size()-1));
			while(!languagesList_.contains(languages_)){
				languagesList_.add(languages_);
			}
			
		}
		//System.out.println(">> "+languagesList_.toString());
		
		countNum = 0;
		String languagesParam = "zh-CN";
		while(countNum < languagesList_.size()-1) {
			languagesParam += ","+languagesList_.get(countNum);
			countNum += 1;
		}
		
		System.out.println(">> languages-param:"+languagesParam);
		
		options.addArguments("--languages-param=" + languagesParam);
		
		//languages
		String languageParam = "zh-CN";
		options.addArguments("--language-param=" + languageParam);
		
		//指纹因子 独立参数--browser-rand-hash
		int browserRandHash = OtherClass.getRandomBetweenNumbers(1, 99999999);
		options.addArguments("--rand-hash-param="+String.valueOf(browserRandHash));
		

		


		//hardwareConcurrency
		int[] hardwareConcurrencyList = {2,3,4,6,8,10,12};
		int hardwareConcurrency = hardwareConcurrencyList[OtherClass.randomNum(hardwareConcurrencyList.length)];
		options.addArguments("--hardware-concurrency-param="+String.valueOf(hardwareConcurrency));
		

		//deviceMemory
		int[] deviceMemoryList = {1,2,3,4,5,6,8,10,12};
		int deviceMemory = deviceMemoryList[OtherClass.randomNum(deviceMemoryList.length)];
		options.addArguments("--device-memory-param="+String.valueOf(deviceMemory));
		
		
		//screen
		String[] screenList= {"320*676","320*712","320*570","320*640","320*693","358*755","360*760","360*800","360*780","360*640","360*720","360*712","360*772","360*748","375*667","375*812","375*813","384*854","384*845","385*833","390*844","393*818","393*786","393*873","393*851","393*699","393*818","400*712","401*845","401*867","412*915","412*732","412*846","412*869","412*883","414*896","414*736","424*896","424*918","424*906","424*875","424*907","428*926","534*854","428*926","550*879","600*912","640*1024"};
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
			screenAvailHeight = String.valueOf(Integer.valueOf(screenStr.split("\\*")[1]) - addRandNum - 20);
			
		}else {
			screenWidth = String.valueOf(Integer.valueOf(screenStr.split("\\*")[0]) + addRandNum);
			screenHeight = String.valueOf(Integer.valueOf(screenStr.split("\\*")[1]) + addRandNum);
			screenAvailWidth = String.valueOf(Integer.valueOf(screenStr.split("\\*")[0]) + addRandNum);
			screenAvailHeight = String.valueOf(Integer.valueOf(screenStr.split("\\*")[1]) + addRandNum - 20);
			
		}
		
		options.addArguments("--screen-width-param="+screenWidth);
		options.addArguments("--screen-height-param="+screenHeight);
		options.addArguments("--screen-avail-width-param="+screenAvailWidth);
		options.addArguments("--screen-avail-height-param="+screenAvailHeight);
		
		ArrayList<String> gpuVendorList = new ArrayList<String>();
		gpuVendorList.add("Qualcomm");
		gpuVendorList.add("Imagination Technologies");
		gpuVendorList.add("ARM");

		
		
		//gpuRenderer gpuVendor (webGL Renderer\ webGL Vendor) 
		String gpuVendorParam = gpuVendorList.get(OtherClass.randomNum(gpuVendorList.size()));
		String gpuRendererParam;
		String platform;
		

		ArrayList<String> gpuList = new ArrayList<String>();
		gpuList.add("Adreno (TM) 650");
		gpuList.add("Adreno (TM) 660");
		gpuList.add("Adreno (TM) 630");
		gpuList.add("Adreno (TM) 620");
		gpuList.add("Adreno (TM) 619");
		gpuList.add("Adreno (TM) 618");
		gpuList.add("Adreno (TM) 610");
		gpuList.add("Adreno (TM) 530");
		gpuList.add("Adreno (TM) 512");
		gpuList.add("Adreno (TM) 509");
		gpuList.add("Adreno (TM) 506");
		gpuList.add("Adreno (TM) 405");
		gpuList.add("Adreno (TM) 308");
		gpuList.add("Adreno (TM) 306");
		gpuList.add("Mali-450 MP");
		gpuList.add("Mali-G51");
		gpuList.add("Mali-G52");
		gpuList.add("Mali-G52 MC2");
		gpuList.add("Mali-G57");
		gpuList.add("Mali-G57 MC2");
		gpuList.add("Mali-G57 MC3");
		gpuList.add("Mali-G57 MC4");
		gpuList.add("Mali-G57 MC5");
		gpuList.add("Mali-G71");
		gpuList.add("Mali-G72");
		gpuList.add("Mali-G72 MP3");

		gpuRendererParam = gpuList.get(OtherClass.randomNum(gpuList.size()));
		
		ArrayList<String> platformList = new ArrayList<String>();
		platformList.add("Linux armv8l");
		platformList.add("Linux armv7l");
		platformList.add("Linux aarch64");
		platform = platformList.get(OtherClass.randomNum(platformList.size()));
		
		options.addArguments("--gpu-renderer-param="+gpuRendererParam);
		options.addArguments("--gpu-vendor-param="+gpuVendorParam);
		options.addArguments("--platform-param="+platform);
		
		
		//电池状态 电量 0.95代表95%的电量
		String batteryLevel = "0."+String.valueOf(OtherClass.getRandomBetweenNumbers(10, 99));
		options.addArguments("--battery-level-param="+batteryLevel);
		
		//电池状态  是否在充电
		String batteryCharging = String.valueOf(OtherClass.getRandomBetweenNumbers(0, 1));
		options.addArguments("--battery-charging-param="+batteryCharging);
		
		//触摸屏最大触摸点数
		int maxTouchPoints = OtherClass.getRandomBetweenNumbers(3, 8);
		options.addArguments("--max-touch-points-param="+String.valueOf(maxTouchPoints));
		
		
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
		
		//禁止浏览器弹窗 设置弹窗提醒的设置，2表示block 好像无用？
	    //prefs.put("profile.default_content_setting_values.notifications.notifications", 2);
		
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
	    driver.manage().window().setSize(new Dimension(Integer.valueOf(screenWidth), Integer.valueOf(screenHeight)));

	    try{
	    	driver.get(url);
	    }catch (Exception e) {
			driver.quit();
			OtherClass.timeSleep(2);
		}
	    
	    return driver;
	    
	}


}
