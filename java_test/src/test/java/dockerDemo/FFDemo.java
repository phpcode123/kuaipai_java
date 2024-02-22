package dockerDemo;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;



public class FFDemo {


	public static void testApp() throws MalformedURLException, InterruptedException 
	{
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setBrowserName(BrowserType.FIREFOX);
		WebDriver driver=new RemoteWebDriver(new URL("http://192.168.0.103:4546/wd/hub"), cap);
		driver.get("http://www.baidu.com/");
		System.out.println(driver.getPageSource());
		Thread.sleep(10*1000);
		
		driver.quit();
	}

	
	
	public void main(String[] args) throws MalformedURLException, InterruptedException {
		FFDemo.testApp();
	}
}
