package com.java.selenium_pc_linux;



import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.sql.SQLException;
import java.time.Duration;

import cn.hutool.core.io.file.FileReader;

public class RunAppPc_linux {

	//-------------------------------- Setting begin -------------------------------------------
	
	public static String searchWebsite;
	
	
	//mkeyword path
	//linux
	//public static String keywordFilePath = "."+File.separator+"mkeyword.txt";
	//windows
	public static String keywordFilePath = "/Backups/java/";
	//public static String keywordFilePath = "D:\\com.java.selenium_pc\\";
	
	public static String classPathJsPath = "/Backups/java/stealth.min.js";
	//public static String classPathJsPath = "D:\\com.java.selenium_pc\\stealth.min.js";
	
	//pc端keyword的：pc_xxx.net_keyword.txt m端keyword：m_xxx.net_keyword.txt
	//public static String keywordFileHost = "http://192.168.0.101:8083/click_middle/";
	
	//输出的zip代理插件的路径格式，程序在当前页面输出即可
	public static String pluginZipFilePath = "."+File.separator;
	
	//mysql 
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    public static final String DB_URL = "jdbc:mysql://192.168.0.101:3306/click_middleware?useSSL=false";
    public static final String USER = "root";
    public static final String PASS = "root98765";
	
	
	//setting
	public static int maxOpenUrlNum = 2;//最大打开自己网站的链接个数，随机数如果是0，程序会自动+3，如果是1程序会自动+2，确保每次最小的打开次数为2
	public static int openUrlMinSleepTime = 25;//打开自己网站的最小随机暂停数，单位秒
	public static int openUrlMaxSleepTime = 50;//打开自己网站的最大随机暂停数，单位秒
	public static int maxBaiduPageNum = 5;//百度查找分页时最大分页数 
	
	//-------------------------------- Setting end ---------------------------------------------

	
	// ---------------------- main begin ----------------------

	public static void main(String[] args) throws IOException, SQLException {
		
		
		//LoggerContextFactory loggerContextFactory = new SLF4JLoggerContextFactory();
		//LoggerContext loggerContext = loggerContextFactory.getContext(null, null, io.netty.channel.DefaultChannelId.class, false);
	
		System.out.println(">>> Please input your domain. ");
		Scanner scan = new Scanner(System.in);
		if (scan.hasNext()) {
            RunAppPc_linux.searchWebsite = scan.next();
            System.out.println(">>> Domian：" + searchWebsite);
        }
        scan.close();
        
		
		
		while(true) {
			//List<String> keywordList = new ArrayList<String>();
			//网络请求方式
//			try {
//				String remoteKeywordFileStr = keywordFileHost+"pc_"+searchWebsite+"_keyword.html";
//				System.out.println(">>> remoteKeywordFileStr:"+remoteKeywordFileStr);
//				String keywordFileString = OtherClass.httpGet(remoteKeywordFileStr);
//				//List<String> keywordStringArea = OtherClass.findAllLinkurl("<body>(.*?)</body>", keywordFileString);
//				//System.out.println(keywordStringArea.toString());
//				
//				//以\n为分隔符，但是当前行中还是有\n，要去掉
//				String[] keywordFileStringList = keywordFileString.split("\n");
//				
//				for(String i : keywordFileStringList) {
//					if(!i.equals("")) {
//						
//						String line = OtherClass.cleanLine(i);
//						System.out.println(">>> i"+line);
//						keywordList.add(line);
//					}
//					
//				}
//				OtherClass.timeSleep(100);
//			}catch(Exception e) {
//				e.printStackTrace();
//				//break;
//				OtherClass.timeSleep(3);
//			}
//			
			//keyword.txt for file read
			String keywordFilePath = RunAppPc_linux.keywordFilePath+"pc_"+RunAppPc_linux.searchWebsite+"_keyword.txt";
			//System.out.println(keywordFilePath);
			FileReader keywordFile = new FileReader(keywordFilePath);
			List<String> keywordList = keywordFile.readLines();
			
			for(String searchKeyword : keywordList) {
				try {
				
					//driver
					//System.setProperty("webdriver.chrome.driver", "C:\\java_jar\\chromedriver.exe");
					//System.setProperty("webdriver.chrome.driver", "D:\\com.java.selenium_pc\\chromedriver_win32\\chromedriver.exe");
					//Linux
					//System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
					
	
					String userAgent = RandUserAgent.randUserAgent();
					
					
			
					
					NewChromeOptions.CHROME_DRIVER = "/usr/bin/chromedriver";
					HttpsProxy httpsProxy = new HttpsProxy(ChromeExtends.proxyHost,ChromeExtends.proxyPort,ChromeExtends.proxyUser, ChromeExtends.proxyPass);
					NewChromeDriver driver = NewChromeDriver.newChromeInstance(false, false, httpsProxy);
					System.out.println(">>> userAgent:"+userAgent);
					
					//设置窗口大小
					Dimension dimension = new Dimension(1400, 800);
					driver.manage().window().setSize(dimension);
					driver.manage().timeouts().setScriptTimeout(Duration.ofSeconds(30));//脚步执行超时
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));//页面加载超时
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));//设置匹配等待时间，如果在2s内匹配不到结果就返回错误
					try {
						//test ip
						
						//System.out.println(driver.getPageSource());
						
						
						
						//driver.get("http://m.baidu.com");
						DriverGet.driverGet(driver, "http://www.baidu.com");
						//当显示Bad Gateway时，就刷新当前页面
						//if(driver.getPageSource().equals("Bad Gateway")) {
						//	driver.navigate().refresh();
						//}
						//System.out.println(driver.getPageSource());
						System.out.println(">>> Program begin...");
						
						
						
					
						System.out.println(">>> searchKeyword:"+searchKeyword);
						
						
						
						//百度首页查找和点击
						driver.findElement(By.id("kw")).sendKeys(searchKeyword);
						OtherClass.timeSleep(1);
						driver.findElement(By.id("su")).click();
						
						//打印当前url
						String baiduSearchLinkurl = driver.getCurrentUrl();
						System.out.println(">>> linkurl:"+baiduSearchLinkurl);
						
						int baiduPageNum = 0;
						int countNum = 0;
						while(baiduPageNum < RunAppPc_linux.maxBaiduPageNum) {
							//baiduPageNum count
							baiduPageNum += 1;
							
							System.out.println(">>> baiduPageNum:"+String.valueOf(baiduPageNum));
							
							
							//ֻ
							if(baiduPageNum > 1) {
								//driver.get(baiduSearchLinkurl);
								DriverGet.driverGet(driver, baiduSearchLinkurl);
								System.out.println(">>> BaiduSearchLinkurl01:"+baiduSearchLinkurl);
							}
							
							//获取当前的搜索url
							baiduSearchLinkurl = driver.getCurrentUrl();
							if(baiduPageNum == 1) {
								System.out.println(">>> BaiduSearchLinkurl02:"+baiduSearchLinkurl);
							}
							
							
							//获取网页源码，并且格式化掉源码，去掉空白字符
							//String str = String.replaceAll(regex,String);
							String htmlcode = driver.getPageSource();
							htmlcode = htmlcode.replaceAll("\s+", "");
							
							System.out.println(">>> htmlcode01:htmlcode01");
							
							
							//ֵ
							//List<String> getMatchers = RunApp.getMatchers("<section.*?</section>",htmlcode);
							//t<String> getMatchers = RunApp.getMatchers("<divclass=\"c-result-content\">(.*?)</article>.*?</div>",htmlcode);
							
				
					
							countNum = 0;
							int findStatusNum = 0;
							
							//百度搜索结果项正常都是10个搜索结果，有时会出现广告，和其他的一些流媒体插入，此值设置为15
							while(countNum < 11) {
								countNum++;
								OtherClass.timeSleep(3);
								System.out.println(">>> countNum:"+String.valueOf(countNum));
								System.out.println(">>> BaiduPageNum:"+String.valueOf(baiduPageNum)+" Element:"+String.valueOf(countNum)+" ------------------------");
								
								
								String xpathStr = "/html/body/div[1]/div[3]/div[1]/div[3]/div["+String.valueOf(countNum)+"]";
								//System.out.println(">>> xpathStr:" + xpathStr);
								try {
									//System.out.println(">>> i:"+i);
									
									try {
												
										WebElement element = driver.findElement(By.xpath(xpathStr));
										String elementText = element.getText();
										System.out.println(">>> ElementText:"+elementText);
										
										//当countnum大于1时，将上一个结果块移动到左上角可视化的区域，这样可以避免移动当前块被百度搜索框给挡住,blockNum是搜索结果中的结果块，3就是第三个结果
										int blockNum = 2;	
										if(countNum > blockNum) {
											String newXpath = "/html/body/div[1]/div[3]/div[1]/div[3]/div["+String.valueOf(countNum-1)+"]";
											WebElement newElement = driver.findElement(By.xpath(newXpath));
											//String newElementText = newElement.getText();
											//System.out.println(">>> ElementText:"+newElementText);
											MoveScroll.moveScrollIntoView(newElement,driver);
										}
										OtherClass.timeSleep(1);
									
									
										//在搜索项中匹配自己的网站url
										
										List<String> matchSearchWebsite = OtherClass.getMatchers(searchWebsite, elementText);
										
										//size()大于0说明有匹配到自己的网站，执行点击
										if(matchSearchWebsite.size() > 0) {
											
											System.out.println(">>> Find website status: 1");
											//RunApp.timeSleep(1000);
											try {
												String clickXpath = "/html/body/div[1]/div[3]/div[1]/div[3]/div["+String.valueOf(countNum)+"]/h3/a";
												System.out.println(">>> clickXpath:"+clickXpath);
												driver.findElement(By.xpath(clickXpath)).click();
												System.out.println(">>> Element click success.");
												OtherClass.timeSleep(2);
												
												// 获取当前页面句柄
										        String handle = driver.getWindowHandle();  
										        // 获取所有页面的句柄，并循环判断不是当前的句柄 实则只有两个窗口，两个窗口可以使用此方法，注意String的比较是equals
										        for (String handles : driver.getWindowHandles()) {  
										            if (handles.equals(handle))  
										                continue;  
										            driver.switchTo().window(handles);  
										        }
												findStatusNum = 1;
											}catch(Exception e) {
												e.printStackTrace();
											}
											
										}
										
										
									}catch(Exception e) {
										//e.printStackTrace();
										System.out.println(">>> Error,Do not find element.");
									}
									
									
													
									
									
									
									
									
									
									
									String htmlcode01 = driver.getPageSource();
									String driverLinkurl = driver.getCurrentUrl();
									//System.out.println(">>> driverLinkurl:"+driverLinkurl);
									
									//点击目标搜索结果网站
									//匹配当前当即的url中是否有包含自己的主体url，如果包含就说明点击的url时正确的
									if(OtherClass.getMatchers(searchWebsite, driverLinkurl).size() > 0) {
										if(findStatusNum == 1) {
											try {
												htmlcode01 = htmlcode01.replaceAll("\s+", "");
												System.out.println(">>> htmlcode01:htmlcode01");
												//获取当前的页面所有的linkurl
												List<String> allPageLinkurl = OtherClass.findAllLinkurl("href=[\"'](.*?)[\"']", driver.getPageSource());
												List<String> allPageLinkurlList = new ArrayList<String>();
												for(String i01 : allPageLinkurl) {
													try {
														if(OtherClass.getMatchers("\\d{3,}.html", i01).size() > 0) {
															//System.out.println(">>> i01:"+i01);
															allPageLinkurlList.add(OtherClass.absoluteURL(driver.getCurrentUrl(), i01));
														}
													}catch(Exception e) {
														e.printStackTrace();
													}
													
												}
												
												//判断当前页面的url是否大于0，返回当前的页面的urlַ
												if(allPageLinkurlList.size() > 0) {
													for(String i02 : allPageLinkurlList) {
														System.out.println(">>> linkurl:"+i02);
													}
													System.out.println(">>> ------------------------");
													
													//获取到最大的打开页面的数量，然后随机取值
													int randMaxOpenUrlNum = OtherClass.randomNum(RunAppPc_linux.maxOpenUrlNum);
													
													//确保最低能刷新一次，不然数据太难看
													if(randMaxOpenUrlNum == 0) {
														randMaxOpenUrlNum = 1;
													}
													//randMaxOpenUrlNumֵ 
//													switch(randMaxOpenUrlNum) {
//														case 0:
//															randMaxOpenUrlNum += 3;
//															break;
//														case 1:
//															randMaxOpenUrlNum += 2;
//															break;
//													}
													System.out.println(">>> randMaxOpenUrlNum:"+String.valueOf(randMaxOpenUrlNum));
													int openNum = 0;
													while(openNum < randMaxOpenUrlNum) {
														openNum ++;
														System.out.println(">>> openNum:"+openNum);
														//随机获取一个url，并且打开，达到刷pv的目的
														int randomUrlNum = OtherClass.randomNum(allPageLinkurlList.size());
														String openUrl = allPageLinkurlList.get(randomUrlNum);
														System.out.println(">>> openUrl:"+openUrl);
														//driver.get(openUrl);
														DriverGet.driverGet(driver, openUrl);
														
														//挡开当前页面后随机时间暂停
														int openUrlSleepTime_ = OtherClass.getRandomBetweenNumbers(RunAppPc_linux.openUrlMinSleepTime, RunAppPc_linux.openUrlMaxSleepTime);
														//
														int countNum02 = 0;
														//移动浏览器的滚动条假装浏览刷新页面
														System.out.println(">>> Move scroll begin ------------------------");
														while(countNum02 < 3) {
															countNum02 += 1;
															int moveScrollBarRandNum = OtherClass.randomNum(40);
															System.out.println(">>> countNum:"+String.valueOf(countNum02)+" moveScrollBarRandNum:"+String.valueOf(moveScrollBarRandNum)+" timeSleep(1)");
															MoveScroll.moveScrollBar(moveScrollBarRandNum * 100, driver);
															OtherClass.timeSleep(1);
														}
														System.out.println(">>> Move scroll end --------------------------");
														System.out.println(">>> timeSleep("+String.valueOf(openUrlSleepTime_)+")");
														OtherClass.timeSleep(openUrlSleepTime_);
														
													}
												}
												
												
												
													
												//异常就跳出当前的循环
												break;
											}catch(Exception e) {
												e.printStackTrace();
											}
											
										}else {
											//System.out.println(">>> continue 1");
											continue;
										}
										
										
										
									}else {
										//System.out.println(">>> continue 2");
										continue;
									}
								
								
				
									
								}catch(Exception e) {
									
									
									e.printStackTrace();
									System.out.println(">>> Error,Do not find element.");
									
									//当计数大于10时就跳出当前循环
									if(countNum > 10) {
										break;
									}
								}
								
								//匹配查找状态
								if(findStatusNum == 1 ) {
									System.out.println(">>> Find status num is 1, Stop findding element.");
									break;
								}
								OtherClass.timeSleep(2);
				
							}
							
							//匹配查找状态
							if(findStatusNum == 1 ) {
								System.out.println(">>> Find status num is 1, Stop findding element.");
								break;
							}
							//分隔符，如果第一页没有找到就开始找下一页
							System.out.println(">>> ------------------------");
							//点击下一页
							try {
								
								WebElement findBaiduNextPageLinkurl = driver.findElement(By.linkText("下一页 >"));//注意，这个链接文本一定要与可视化页面的一样，非html代码
								findBaiduNextPageLinkurl.click();
								System.out.println(">>> findBaiduNextPageLinkurl:"+findBaiduNextPageLinkurl.getAttribute("href"));
								
								baiduSearchLinkurl = findBaiduNextPageLinkurl.getAttribute("href");
							}catch(Exception e) {
								//打印异常，如果没有找到就尝试点击下一页ַ
								e.printStackTrace();
								WebElement findBaiduNextPageLinkurl = driver.findElement(By.linkText("下一页 >"));//注意，这个链接文本一定要与可视化页面的一样，非html代码
								findBaiduNextPageLinkurl.click();
								System.out.println(">>> findBaiduNextPageLinkurl:"+findBaiduNextPageLinkurl.getAttribute("href"));
								
								baiduSearchLinkurl = findBaiduNextPageLinkurl.getAttribute("href");
								
							}	
						}
						
						
						OtherClass.timeSleep(1);
						System.out.println(">>> Running success!");
						driver.quit();
					}catch(Exception e) {
						System.out.println(">>> Open url error,Timestamp(1)");
						OtherClass.timeSleep(1);
						e.printStackTrace();
						try {
							driver.quit();
						}catch(Exception e1){
							e1.printStackTrace();
							
						}
					}
				}catch(Exception e5) {
					continue;
				}

			}
		}

	}
}
