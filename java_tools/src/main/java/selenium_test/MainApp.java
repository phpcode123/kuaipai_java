package selenium_test;



import org.openqa.selenium.*;

import java.util.Set;


import java.io.IOException;


public class MainApp {
	

	

	// ---------------------- main begin ----------------------

	public static void main(String[] args) throws IOException {

		
			System.out.println(">> Version "+Settings.version);

			try {
				WebDriver driver =  DriverGet.get(Settings.openUrl,"");
				OtherClass.timeSleep(15);
				Set<Cookie> cookiesSet = driver.manage().getCookies();
				System.out.println(">> cookies:"+cookiesSet);
				System.out.println(">> Running success! "+OtherClass.getDateTime());
				
				System.out.println(">> mainTest");
				
				OtherClass.timeSleep(100000);
				

			}catch(Exception e) {
				OtherClass.getRandomTimeSleep(">> Open url error");
				e.printStackTrace();
				
			}

	}

}
