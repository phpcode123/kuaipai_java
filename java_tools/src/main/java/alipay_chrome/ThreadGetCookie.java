package alipay_chrome;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
				
				List<String> alipayUrlList = new ArrayList<String>();
				alipayUrlList.add("https://mbillexprod.alipay.com/enterprise/bizTradeOrder.htm#/salesOrder");
				alipayUrlList.add("https://mbillexprod.alipay.com/enterprise/fundList.htm#/");
				alipayUrlList.add("https://mbillexprod.alipay.com/enterprise/fundAccountDetail.htm#/");
				alipayUrlList.add("https://mbillexprod.alipay.com/enterprise/accountTotalAssetQuery.htm#/");
				alipayUrlList.add("https://mbillexprod.alipay.com/enterprise/financialProductQuery.htm#/");
				alipayUrlList.add("https://mbillexprod.alipay.com/enterprise/mbillDownload.htm#/fundBill");
				alipayUrlList.add("https://mbillexprod.alipay.com/enterprise/serviceFeeBillQuery.htm#/monthly");
				alipayUrlList.add("https://mbillexprod.alipay.com/enterprise/mainIndex.htm#/");
				alipayUrlList.add("https://mrchportalweb.alipay.com/user/home");
				alipayUrlList.add("https://mrchportalweb.alipay.com/assetmanage/user/asset/view_v2");
				alipayUrlList.add("https://mrchportalweb.alipay.com/dynlink/operationCenter/operateTool.htm");
				String openUrl = alipayUrlList.get(OtherClass.randomNum(alipayUrlList.size()));
				
	
				driver.get(openUrl);
				Set<Cookie> cookies = driver.manage().getCookies();
									
				
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
						Cookie seleniumCookie;
						//Linux下总是报错，domain匹配不到，单独处理下此异常
						try {
							seleniumCookie = new Cookie(i.getName(), i.getValue(), i.getDomain(), i.getPath(), cookieTypeDate ,i.isSecure());
						}catch(Exception e) {
							seleniumCookie = new Cookie(i.getName(), i.getValue(), ".alipay.com", i.getPath(), cookieTypeDate ,i.isSecure());
						}
						
						
						//修改ALIPAYJSESSIONID为RZ42测试
						if(i.getName().equals("ALIPAYJSESSIONID")) {
							System.out.println(">> sessionValue:"+i.getValue());
							String sessionIdValue = OtherClass.stringReplaceAll("RZ41", "RZ42", i.getValue());
							System.out.println(">> ALIPAYJSESSIONID:"+sessionIdValue);
							sessionIdValue = OtherClass.stringReplaceAll("GZ00", "", sessionIdValue);
							System.out.println(">> ALIPAYJSESSIONID:"+sessionIdValue);
							seleniumCookie = new Cookie(i.getName(), sessionIdValue, i.getDomain(), i.getPath(), cookieTypeDate ,i.isSecure());
							
							System.out.println(">> cookie:"+seleniumCookie);
						}
						
						//将现有的数据添加到cookie中
						driver.manage().addCookie(seleniumCookie);
						
//						//将数值添加到全局cookie容器中，供外部数据调用,这里加了一个锁
						synchronized (ThreadGetCookie.Lock) {
							AppMain.cookie.add(new Cookie(i.getName(),i.getValue()));
						}
//						
						//测试将所有的数据都添加到cookie中然后发送
						//此思路行不通，甚至请求都无法请求，会不会先得需要传播数据进行验证？
//						synchronized (ThreadGetCookie.Lock) {
//							Aliapy_chrome.cookie.add(seleniumCookie);
//						}
						
					}catch(Exception e){
						e.printStackTrace();
					}
					
				}
				
	
				//获取新的cookies
				Set<Cookie> newCookies = driver.manage().getCookies();
				System.out.println(">> Thread1 cookie:"+newCookies);
//				for(Cookie i : newCookies) {
//					System.out.println(">> Thread1 cookie_i:"+i);
//				}
	

				//滑动滚动条
				MoveScroll.moveScrollBar(600, driver);
	
				System.out.println(">> Thread1 Date:"+OtherClass.getDateTime()+" Get cookie success! TimeSleep(360)" );
				System.out.println(">> Thread1 ---------------------------------------");
				OtherClass.timeSleep(360);
				
				//退出当前driver
				driver.quit();
				break;
			}catch(Exception e) {
				e.printStackTrace();
				OtherClass.getRandomTimeSleep(">> Thread1 url open error");
			}
		}
	}
	
	public ThreadGetCookie(WebDriver driver) {
		this.driver = AppMain.driver;
	}

}
