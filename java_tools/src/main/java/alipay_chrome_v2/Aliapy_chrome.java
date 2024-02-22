package alipay_chrome_v2;



import org.openqa.selenium.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;




public class Aliapy_chrome {
	
	public static WebDriver driver;
	public volatile static Set<Cookie> cookie = new HashSet<Cookie>();

	// ---------------------- main begin ----------------------


	public static void main(String[] args) throws IOException {
			
		
		//v1是获取jsonurl链接返回的数据，此接口最长稳定时间在两小时左右。
	    //v2版本是直接测试请求alipayAccountUrl html代码，直接从html代码中匹配数据   页面关键词"资产明细"
			
		try {
			Aliapy_chrome.driver =  DriverGet.get(Settings.alipayHomeUrl,"");
			System.out.println(">> Please scan QRCode in 20s. "+OtherClass.getDateTime()+" TimeSleep(20)");
			OtherClass.timeSleep(20);
			driver.get(Settings.alipayAccountUrl);
			
			Set<Cookie> cookies = driver.manage().getCookies();
			System.out.println(">> Main First cookie:"+cookies);
			for(Cookie i : cookies) {
				System.out.println(">> Main cookie_i:"+i);
			}
			
			ThreadGetCookie th1 = new ThreadGetCookie(driver);
			//ThreadGetData th2 = new ThreadGetData(cookie);
			
			th1.start();
			//th2.start();

				
		}catch(Exception e) {
			e.printStackTrace();
			OtherClass.getRandomTimeSleep(">> Open url error");
			
			
		}

	}

}
