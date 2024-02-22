package alipay_chrome;



import org.openqa.selenium.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;




public class AppMain {
	
	public static WebDriver driver;
	public volatile static Set<Cookie> cookie = new HashSet<Cookie>();

	// ---------------------- main begin ----------------------


	public static void main(String[] args) throws IOException {
		
//		String a = "8.31";
//		System.out.println(OtherClass.stringReplaceAll("0+$", "", a));

			
			try {
				AppMain.driver =  DriverGet.get(Settings.alipayHomeUrl,"");
				System.out.println(">> Please scan QRCode in 30s. "+OtherClass.getDateTime()+" TimeSleep(30)");
				OtherClass.timeSleep(30);
				driver.get(Settings.alipayAccountUrl);
				
				
				ThreadGetCookie th1 = new ThreadGetCookie(driver);
				ThreadGetData th2 = new ThreadGetData(cookie);
				
				th1.start();
				th2.start();

					
			}catch(Exception e) {
				e.printStackTrace();
				OtherClass.getRandomTimeSleep(">> Open url error");
				
				
			}

	}

}
