package alipay_chrome_v2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import cn.hutool.core.date.DateUtil;

public class ThreadGetCookie extends Thread {
	public WebDriver driver;

	//cookie锁
	public static final Object Lock = new Object();
	

	public void run() {
		while(true) {
			try {
	
				driver.navigate().refresh();
				Set<Cookie> cookies = driver.manage().getCookies();
				
				System.out.println(">> Thread1 First cookie:"+cookies);
				
				//获取cookie中的日期类型格式  格式：星期一, 19 七月 2021 16:45:29 CST
				Date date = new Date();
				SimpleDateFormat ft = new SimpleDateFormat ("EEE, dd MMM yyyy HH:mm:ss z");
			    String cookieTypeDateString = ft.format(date);
			    //将String类型的日期转化为Date类型
			    Date cookieTypeDate = DateUtil.parse(cookieTypeDateString, "EEE, dd MMM yyyy HH:mm:ss z");
				
			    //将时间戳延期一个月
			    cookieTypeDate = DateUtil.nextMonth();
			    
				//删除原先的cookie
			    driver.manage().deleteAllCookies();
				//循环cookies，并将数据添加到新容器中
				for(Cookie i : cookies) {
					try {
						Cookie seleniumCookie = null;
						//Linux下总是报错，domain匹配不到，单独处理下此异常
						try {
							seleniumCookie = new Cookie(i.getName(), i.getValue(), i.getDomain(), i.getPath(), cookieTypeDate ,i.isSecure());
						}catch(Exception e) {
							seleniumCookie = new Cookie(i.getName(), i.getValue(), ".alipay.com", i.getPath(), cookieTypeDate ,i.isSecure());
						}
	
						
						//将现有的数据添加到cookie中
						driver.manage().addCookie(seleniumCookie);
						
						//将数值添加到全局cookie容器中，供外部数据调用,这里加了一个锁
//						synchronized (ThreadGetCookie.Lock) {
//							Aliapy_chrome.cookie.add(new Cookie(i.getName(),i.getValue()));
//							System.out.println(">> Add cookie: i.getName():"+i.getName()+" i.getValue():"+i.getValue());
//						}
						
						
						synchronized (ThreadGetCookie.Lock) {
							Aliapy_chrome.cookie.add(seleniumCookie);
							System.out.println(">> Add cookie: "+seleniumCookie);
						}
						
						
						driver.get(Settings.alipayAccountUrl);
						while(true) {
							driver.navigate().refresh();
							OtherClass.timeSleep(5);
						}
						
					}catch(Exception e){
						e.printStackTrace();
					}
					
				}
				
	
				//获取新的cookies
				Set<Cookie> newCookies = driver.manage().getCookies();
				System.out.println(">> Thread1 cookie:"+newCookies);
				for(Cookie i : newCookies) {
					System.out.println(">> Thread1 cookie_i:"+i);
				}
	

				//滑动滚动条
				MoveScroll.moveScrollBar(600, driver);
	
				System.out.println(">> Thread1 Date:"+OtherClass.getDateTime()+" Get cookie success! TimeSleep(600)" );
				System.out.println(">> Thread1 ---------------------------------------");
				OtherClass.timeSleep(600);
				
				//退出当前driver
				//driver.quit();
				//break;
			}catch(Exception e) {
				e.printStackTrace();
				OtherClass.getRandomTimeSleep(">> Thread1 url open error");
			}
		}
	}
	
	public ThreadGetCookie(WebDriver driver) {
		this.driver = Aliapy_chrome.driver;
	}

}
