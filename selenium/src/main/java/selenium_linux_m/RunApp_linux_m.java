package selenium_linux_m;


import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;



import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.sql.SQLException;
import java.time.Duration;

import cn.hutool.core.io.file.FileReader;

public class RunApp_linux_m {

	
	// ---------------------- main begin ----------------------

	public static void main(String[] args) throws IOException, SQLException {
		System.out.println(">>> Please input your domain. ");
		Scanner scan = new Scanner(System.in);
		if (scan.hasNext()) {
			Settings.searchWebsite = scan.next();
            System.out.println(">>> Domian：" + Settings.searchWebsite);
        }
        scan.close();
		while(true) {
			
			//keyword.txt
			String keywordFilePath = Settings.keywordFilePath+"m_"+Settings.searchWebsite+"_keyword.txt";
			FileReader keywordFile = new FileReader(keywordFilePath);
			List<String> keywordList = keywordFile.readLines();
			
			for(String searchKeyword : keywordList) {
				
				
				//driver
				//System.setProperty("webdriver.chrome.driver", "C:\\java_jar\\chromedriver.exe");
				//System.setProperty("webdriver.chrome.driver", "D:\\com.java.selenium_m\\chromedriver_win32\\chromedriver.exe");
				//Linux
				//System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
				

				String userAgent = RandUserAgent.randUserAgent();
				
				
		
				
				NewChromeOptions.CHROME_DRIVER = "/usr/bin/chromedriver";
				HttpsProxy httpsProxy = new HttpsProxy(Settings.proxyHost,Settings.proxyPort,Settings.proxyUser, Settings.proxyPass);
				NewChromeDriver driver = NewChromeDriver.newChromeInstance(false, false, httpsProxy);
				System.out.println(">>> userAgent:"+userAgent);
				
				//设置窗口大小
				Dimension dimension = new Dimension(414, 736);
				driver.manage().window().setSize(dimension);
				driver.manage().timeouts().setScriptTimeout(Duration.ofSeconds(60));//脚步执行超时
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));//页面加载超时
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));//设置匹配等待时间，如果在2s内匹配不到结果就返回错误
				try {
					//test ip
					//driver.get("https://www.panfa.net/index.php?s=testip/index");
					//System.out.println(driver.getPageSource());
					
					
					
					//driver.get("http://m.baidu.com");
					DriverGet.driverGet(driver, "http://m.baidu.com");
					//System.out.println(driver.getPageSource());
					System.out.println(">>> Program begin...");
					
					
					
				
					System.out.println(">>> searchKeyword:"+searchKeyword);
					
					
					
					//百度首页查找和点击
					driver.findElement(By.id("index-kw")).sendKeys(searchKeyword);
					OtherClass.timeSleep(1);
					driver.findElement(By.id("index-bn")).click();
					
					//打印当前url
					String baiduSearchLinkurl = driver.getCurrentUrl();
					System.out.println(">>> linkurl:"+baiduSearchLinkurl);
					
					int baiduPageNum = 0;
					int countNum = 0;
					while(baiduPageNum < Settings.maxBaiduPageNum) {
						//baiduPageNum count
						baiduPageNum += 1;
						
						System.out.println(">>> baiduPageNum:"+String.valueOf(baiduPageNum));
						
						
						//ֻ
						if(baiduPageNum > 1) {
							//driver.get(baiduSearchLinkurl);
							DriverGet.driverGet(driver, baiduSearchLinkurl);
							System.out.println(">>> BaiduSearchLinkurl01:"+baiduSearchLinkurl);
						}
						System.out.println(">>> 002");
						//获取当前的搜索url
						baiduSearchLinkurl = driver.getCurrentUrl();
						if(baiduPageNum == 1) {
							System.out.println(">>> BaiduSearchLinkurl02:"+baiduSearchLinkurl);
						}
						System.out.println(">>> 003");
						
						//获取网页源码，并且格式化掉源码，去掉空白字符
						//String str = String.replaceAll(regex,String);
						String htmlcode = driver.getPageSource();
						htmlcode = htmlcode.replaceAll("\s+", "");
						
						System.out.println(">>> htmlcode01:"+htmlcode);
						
						
						//ֵ
						//List<String> getMatchers = RunApp.getMatchers("<section.*?</section>",htmlcode);
						//t<String> getMatchers = RunApp.getMatchers("<divclass=\"c-result-content\">(.*?)</article>.*?</div>",htmlcode);
						
			
				
						countNum = 0;
						int findStatusNum = 0;
						
						//百度搜索结果项正常都是10个搜索结果，有时会出现广告，和其他的一些流媒体插入，此值设置为15
						while(countNum < 15) {
							countNum++;
							System.out.println(">>> countNum:"+String.valueOf(countNum));
							System.out.println(">>> BaiduPageNum:"+String.valueOf(baiduPageNum)+" Element:"+String.valueOf(countNum)+" ------------------------");
							
							
							String xpathStr = "//*[@id=\"results\"]/div["+String.valueOf(countNum)+"]/div";
							//System.out.println(">>> xpathStr:" + xpathStr);
							try {
								//System.out.println(">>> i:"+i);
								
								try {
									WebElement element = driver.findElement(By.xpath(xpathStr));
									String elementText = element.getText();
									System.out.println(">>> ElementText:"+elementText);
									MoveScroll.moveScrollIntoView(element,driver);
									OtherClass.timeSleep(1);
									//在搜索项中匹配自己的网站url
									
									List<String> matchSearchWebsite = OtherClass.getMatchers(Settings.searchWebsite, elementText);
									
									//size()大于0说明有匹配到自己的网站，执行点击
									if(matchSearchWebsite.size() > 0) {
										
										System.out.println(">>> Find website status: 1");
										//RunApp.timeSleep(1000);
										//
										driver.findElement(By.xpath(xpathStr)).click();
										
										findStatusNum = 1;
										
									}
									
								}catch(Exception e) {
									//e.printStackTrace();
									System.out.println(">>> Error,Do not find element.");
								}
								
								
												
								
								
								
								
								
								
								
								String htmlcode01 = driver.getPageSource();
								String driverLinkurl = driver.getCurrentUrl();
								System.out.println(">>> driverLinkurl:"+driverLinkurl);
								
								//点击目标搜索结果网站
								//匹配当前当即的url中是否有包含自己的主体url，如果包含就说明点击的url时正确的
								if(OtherClass.getMatchers(Settings.searchWebsite, driverLinkurl).size() > 0) {
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
												int randMaxOpenUrlNum = OtherClass.randomNum(Settings.maxOpenUrlNum);
												
												
												//确保最低能刷新一次，不然数据太难看
												if(randMaxOpenUrlNum == 0) {
													randMaxOpenUrlNum = 1;
												}
												//randMaxOpenUrlNumֵ 
//												switch(randMaxOpenUrlNum) {
//													case 0:
//														randMaxOpenUrlNum += 3;
//														break;
//													case 1:
//														randMaxOpenUrlNum += 2;
//														break;
//												}
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
													int openUrlSleepTime_ = OtherClass.getRandomBetweenNumbers(Settings.openUrlMinSleepTime, Settings.openUrlMaxSleepTime);
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
										System.out.println(">>> continue 1");
										continue;
									}
									
									
									
								}else {
									System.out.println(">>> continue 2");
									continue;
								}
							
							
			
								
							}catch(Exception e) {
								
								
								//e.printStackTrace();
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
						if(baiduPageNum == 1) {
							try {
								WebElement findBaiduNextPageLinkurl = driver.findElement(By.xpath("//a[@class=\"new-nextpage-only\"]"));
								System.out.println(">>> findBaiduNextPageLinkurl:"+findBaiduNextPageLinkurl.getAttribute("href"));
								//
								baiduSearchLinkurl = findBaiduNextPageLinkurl.getAttribute("href");
							}catch(Exception e) {
								//打印异常，如果没有找到就尝试点击下一页ַ
								e.printStackTrace();
								System.out.println(">>> baiduPageNum:1 ,findBaiduNextPageLinkurl error.");
								driver.findElement(By.xpath("//a[@class=\"new-nextpage-only\"]")).click();
								baiduSearchLinkurl = driver.getCurrentUrl();
								
							}
						}else {
							try {
								WebElement findBaiduNextPageLinkurl = driver.findElement(By.xpath("//a[@class=\"new-nextpage\"]"));
								System.out.println(">>> findBaiduNextPageLinkurl:"+findBaiduNextPageLinkurl.getAttribute("href"));
								//查找下一页的url
								baiduSearchLinkurl = findBaiduNextPageLinkurl.getAttribute("href");
							}catch(Exception e) {
								//打印异常，如果没有找到就尝试点击下一页ַ
								e.printStackTrace();
								System.out.println(">>> baiduPageNum:"+String.valueOf(baiduPageNum)+" ,findBaiduNextPageLinkurl error.");
								driver.findElement(By.xpath("//a[@class=\"new-nextpage\"]")).click();
								baiduSearchLinkurl = driver.getCurrentUrl();
								
							}
							
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

			}
		}

	}
}
