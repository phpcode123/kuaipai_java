package sogou_pc;



import org.openqa.selenium.*;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;


public class Main_sogou_pc {
	

	// ---------------------- main begin ----------------------

	public static void main(String[] args) throws IOException {

		while(true) {
			System.out.println(">> Version "+Settings.version);
			//System.out.println(">> Reading keyword file...");
	        //读取需要点击的keywordList,先用HashSet去重
			ArrayList<String> keywordList = Settings.clickKeywordList();

			//keywordList的数据下标容器，通过随机下标读取数据，用于随机读取关键词，避免有序输出
			//从0-keywordList.size()的递增String类型数组，使用数字下标是要转化为int类型。
			ArrayList<String> keywordIndexNumList = OtherClass.keywordIndexNumSet(keywordList.size());
			
			//OtherClass.getSearchKeywordFromListTest(keywordIndexNumList, keywordList);
			//System.exit(0);

			int keywordCountNum = 0;//用于计数
			for(String searchKeyword : keywordList) {
				keywordCountNum += 1;
				
				//清理没有正常退出的chrome程序
				OtherClass.killChrome();
				
				
				
				
				
				// ---------------- 通过数据下标随机读取关键词 开始 ----------------------------
				
				//当数字下标数组的长度为0说明所有的数字下标都已经调用过了，跳出当前循环。
				//正常逻辑运行时此中断应该是永远不会运行的，加此可保万无一失。
				if(keywordIndexNumList.size() == 0) {
					break;
				}
				
				searchKeyword = OtherClass.getSearchKeywordFromList(keywordIndexNumList, keywordList);
				
				// ---------------- 通过数据下标随机读取关键词 结束 ----------------------------
				
				System.out.println(">> Program begin...");
				System.out.println(">> keywordList.size():"+keywordList.size());
				System.out.println(">> keywordIndexNumList.size():"+keywordIndexNumList.size());
				System.out.println(">> keywordCountNum:"+keywordCountNum+" searchKeyword:"+searchKeyword);
					

				
		
				// ---------------- 用关键词作为KEY请求REDIS 开始 ----------------------------
				String redisKey = RedisClass.getKey(searchKeyword);
				System.out.println(">> redisKey:"+redisKey);
				
				try {
					if(RedisClass.getRedisValue(searchKeyword) == null) {
						
						if(RedisClass.setRedisValue(searchKeyword, "0")) {
							System.out.println(">> redis set keyword success.");
						}else {
							System.out.println(">> redis set keyword fail.");
						}
						
					}else {
						
						//redisValule为String类型，要转为int类型
						String redisValue = RedisClass.getRedisValue(searchKeyword);
						System.out.println(">> redisValue:"+redisValue);
						if(Integer.valueOf(redisValue) > Settings.MAX_SEARCH_TIMES) {
							System.out.println(">> MAX_SEARCH_TIMES more len "+Settings.MAX_SEARCH_TIMES+", continue.");
							continue;
						}
					}
			
				}catch(Exception e) {
					e.printStackTrace();;
				}
				// ---------------- 用关键词作为KEY请求REDIS 结束 ----------------------------
				

				try {
					
					// ---------------- 数据库中请求IP 开始 ----------------------------
					String proxyIpString = "";
					
					//从数据库中请求ip
					while(Settings.ableProxy) {
						
						String getOneSql = MysqlClass.getOneSql();
						System.out.println(">> getOneSql:"+getOneSql);
						
						ArrayList<Object> result = MysqlClass.getOne(getOneSql);
						System.out.println(">> getOneResult:"+result);
						int itemid;
						if(result.size() > 0) {
							itemid = (Integer) result.get(0);
							proxyIpString = (String) result.get(1);
							System.out.println(">> itemid:"+itemid);
							System.out.println(">> proxyIpString:"+proxyIpString);
							
							String updateSql = MysqlClass.updateSql(itemid);
							if(MysqlClass.update(updateSql)) {
								System.out.println(">> updateSql:"+updateSql);
								break;
							}else {
								OtherClass.getRandomTimeSleep(">> updateSql error.");
							}
							
						}else{
							OtherClass.getRandomTimeSleep(">> ProxyIpString get error");
						}
					}
					
					// ---------------- 数据库中请求IP 结束 ----------------------------
					
					
					WebDriver driver =  DriverGet.get(Settings.searchEngineSogouPcUrl,proxyIpString);
					
					if(OtherClass.isLinux()) {
						if(OtherClass.getMatcher(Settings.regexSearchEngineStr, driver.getPageSource())) {
							System.out.println(">> regexIndex: true");
						}
					}
					
					OtherClass.timeSleep(2);
					
					try {

						//首页查找和点击
						driver.findElement(By.id("query")).sendKeys(searchKeyword);
						OtherClass.timeSleep(1);
						driver.findElement(By.id("stb")).click();
						
						//暂停3s，方便元素全部加载完
						OtherClass.timeSleep(3);
						
						//打印当前url
						String baiduSearchLinkurl = driver.getCurrentUrl();
						System.out.println(">> linkurl:"+baiduSearchLinkurl);
					
					
					
						//driver.quit();
	
						int baiduPageNum = 0;
						int countNum = 0;
						while(baiduPageNum < Settings.maxBaiduPageNum) {
							//baiduPageNum count
							baiduPageNum += 1;
							
							System.out.println(">> baiduPageNum:"+String.valueOf(baiduPageNum));
							
							
							//ֻ
							if(baiduPageNum > 1) {
								//driver.get(baiduSearchLinkurl);
								driver.get(baiduSearchLinkurl);
								System.out.println(">> BaiduSearchLinkurl01:"+baiduSearchLinkurl);
							}
							
							//获取当前的搜索url
							baiduSearchLinkurl = driver.getCurrentUrl();
							if(baiduPageNum == 1) {
								System.out.println(">> BaiduSearchLinkurl02:"+baiduSearchLinkurl);
							}
							
							
							//获取网页源码，并且格式化掉源码，去掉空白字符
							//String str = String.replaceAll(regex,String);
							String htmlcode = driver.getPageSource();
							htmlcode = htmlcode.replaceAll("\s+", "");
							//System.out.println(">> htmlcode01:"+htmlcode);

							
														
							//直接匹配源代码，在源代码中匹配域名，如果匹配到了就进入while循环单元素查找，否则就直接点击进入下一页
							boolean findDomainInHtml = false;
	
							//从域名集合中匹配值，如果匹配到值就将此域名值赋值给Settings.searchWebsite	
							for(String domain_ :Settings.domainList()) {
								if(OtherClass.getMatcher(domain_, htmlcode)) {			
									Settings.searchWebsite = domain_;
									findDomainInHtml = true;
									System.out.println(">> findDomainInHtml:true, Domain:"+domain_);
									break;
								}
							}
	
							//将边框拉到页面底部，方便下一步点击元素，如果1400未到页面底部，可以适当调大此值
							if(findDomainInHtml == false) {
								System.out.println(">> findDomainInHtml:false");
								OtherClass.timeSleep(1);
								MoveScroll.moveScrollBar(1000, driver);
								
							}
						
							
							countNum = 0;
							int findStatusNum = 0;
							//如果当前页面中匹配到了当前域名
							
							if(findDomainInHtml == true) {
								//百度搜索结果项正常都是10个搜索结果，有时会出现广告，和其他的一些流媒体插入，此值设置为15
								while(countNum < 11) {
									countNum++;
									System.out.println(">> countNum:"+String.valueOf(countNum));
									System.out.println(">> BaiduPageNum:"+String.valueOf(baiduPageNum)+" Element:"+String.valueOf(countNum)+" ------------------------");
									
									
									
									//当countNum为1时则说明是第一个元素，不知道为什么每页的第一个元素无法匹配到，直接匹配域名栏
									String xpathStr = "";
									if(countNum == 1) {
										xpathStr = "/html/body/div[3]/div[2]/div[1]/div[2 or 3]/div/div["+String.valueOf(countNum)+"]/div[2]";
										System.out.println(">> xpathStr:" + xpathStr);
									}else {
										xpathStr = "/html/body/div[3]/div[2]/div[1]/div[2 or 3]/div/div["+String.valueOf(countNum)+"]";
										System.out.println(">> xpathStr:" + xpathStr);
									}
									
									try {
										//System.out.println(">> i:"+i);
										
										try {
													
											WebElement element = driver.findElement(By.xpath(xpathStr));
											String elementText = element.getText();
											System.out.println(">> ElementText:"+elementText);
											
											
											//当countnum大于1时，将上一个结果块移动到左上角可视化的区域，这样可以避免移动当前块被百度搜索框给挡住,blockNum是搜索结果中的结果块，3就是第三个结果
											int blockNum = 3;	
											if(countNum > blockNum) {
												String newXpath = "/html/body/div[3]/div[2]/div[1]/div[2 or 3]/div/div["+String.valueOf(countNum-3)+"]";
												WebElement newElement = driver.findElement(By.xpath(newXpath));
												//String newElementText = newElement.getText();
												//System.out.println(">> ElementText:"+newElementText);
												MoveScroll.moveScrollIntoView(newElement,driver);
											}
											OtherClass.timeSleep(1);
										
										
											//在搜索项中匹配自己的网站url
											
											List<String> matchSearchWebsite = OtherClass.getMatchers(Settings.searchWebsite, elementText);
											
											//size()大于0说明有匹配到自己的网站，执行点击
											if(matchSearchWebsite.size() > 0) {
												
												System.out.println(">> Find website status: 1");
												//RunApp.timeSleep(1000);
												try {
													//xpath
													String clickXpath = "/html/body/div[3]/div[2]/div[1]/div[2 or 3 or 4]/div/div["+String.valueOf(countNum)+"]/h3";
													
													System.out.println(">> clickXpath:"+clickXpath);
													driver.findElement(By.xpath(clickXpath)).click();
													System.out.println(">> Element click success.");
													
													
													
													// ---------------- 用关键词作为KEY请求REDIS 开始 ----------------------------
													RedisClass.redisFuncSecond(searchKeyword);
													// ---------------- 用关键词作为KEY请求REDIS 结束 ----------------------------
													
													
													//OtherClass.timeSleep(200);
													
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
													OtherClass.timeSleep(1);
												}
												
											}
											
											
										}catch(Exception e) {
											e.printStackTrace();
											System.out.println(">> Error,Do not find element.");
											OtherClass.timeSleep(1);
										}
										
										
														
										
	
										
										String htmlcode01 = driver.getPageSource();
										String driverLinkurl = driver.getCurrentUrl();
										//System.out.println(">> driverLinkurl:"+driverLinkurl);
										
										//点击目标搜索结果网站
										//匹配当前当即的url中是否有包含自己的主体url，如果包含就说明点击的url时正确的
										if(OtherClass.getMatchers(Settings.searchWebsite, driverLinkurl).size() > 0) {
											if(findStatusNum == 1) {
												try {
													htmlcode01 = htmlcode01.replaceAll("\s+", "");
													System.out.println(">> htmlcode01:htmlcode01");
													//获取当前的页面所有的linkurl
													List<String> allPageLinkurl = OtherClass.findAllLinkurl("href=[\"'](.*?)[\"']", driver.getPageSource());
													List<String> allPageLinkurlList = new ArrayList<String>();
													for(String i : allPageLinkurl) {
														try {
															if(OtherClass.getMatchers("\\d{3,}.html", i).size() > 0) {
																//System.out.println(">> i01:"+i01);
																allPageLinkurlList.add(OtherClass.absoluteURL(driver.getCurrentUrl(), i));
															}
														}catch(Exception e) {
															e.printStackTrace();
														}
														
													}
													
													//moveScrollBar 移动浏览器的边框栏，使其拖到最底部
													MoveScroll.moveScrollBar(driver, 2, 5);
													
													//暂停几秒
													System.out.println(">> timeSleep("+String.valueOf(OtherClass.getRandomBetweenNumbers(Settings.openUrlMinSleepTime, Settings.openUrlMaxSleepTime))+")");
													OtherClass.timeSleep(OtherClass.getRandomBetweenNumbers(Settings.openUrlMinSleepTime, Settings.openUrlMaxSleepTime));
													
													//判断当前页面的url是否大于0，返回当前的页面的urlַ
													if(allPageLinkurlList.size() > 0) {
														for(String i : allPageLinkurlList) {
															System.out.println(">> linkurl:"+i);
														}
														//System.out.println(">> ------------------------");
														
														//获取到最大的打开页面的数量，然后随机取值 跳出率在60%-70%左右最为合理
														int randMaxOpenUrlNum = Settings.openUrlNumArray[OtherClass.randomNum(Settings.openUrlNumArray.length)];
														
														//点击时已经算一次，如果再刷新可能就定是两次
														System.out.println(">> randMaxOpenUrlNum:"+String.valueOf(randMaxOpenUrlNum));
														int openNum = 0;
														while(openNum < randMaxOpenUrlNum) {
															openNum ++;
															System.out.println(">> openNum:"+openNum);
															//随机获取一个url，并且打开，达到刷pv的目的
															int randomUrlNum = OtherClass.randomNum(allPageLinkurlList.size());
															String openUrl = allPageLinkurlList.get(randomUrlNum);
															System.out.println(">> openUrl:"+openUrl);
															//driver.get(openUrl);
															driver.get(openUrl);
															
															//挡开当前页面后随机时间暂停
															int openUrlSleepTime_ = OtherClass.getRandomBetweenNumbers(Settings.openUrlMinSleepTime, Settings.openUrlMaxSleepTime);
															
															//moveScrollBar 移动浏览器的边框栏，使其拖到最底部
															MoveScroll.moveScrollBar(driver, 2, 10);
															
															System.out.println(">> timeSleep("+String.valueOf(openUrlSleepTime_)+")");
															OtherClass.timeSleep(openUrlSleepTime_);
														}
														
														
														
														
														for (String handles:driver.getWindowHandles()) {
												            driver.switchTo().window(handles);
												            driver.close();
												            OtherClass.timeSleep(1);
												        }
														
													}
													
													
													
														
													//异常就跳出当前的循环
													break;
												}catch(Exception e) {
													e.printStackTrace();
												}
												
											}else {
												//System.out.println(">> continue 1");
												continue;
											}
											
											
											
										}else {
											//System.out.println(">> continue 2");
											continue;
										}
									
									
					
										
									}catch(Exception e) {
										
										
										e.printStackTrace();
										System.out.println(">> Error,Do not find element.");
										
										//OtherClass.timeSleep(100);
										
										//当计数大于10时就跳出当前循环
										if(countNum > 10) {
											break;
										}
									}
									
									//匹配查找状态
									if(findStatusNum == 1 ) {
										System.out.println(">> Find status num is 1, Stop findding element.");
										break;
									}
									OtherClass.timeSleep(2);
					
								}
							}
							
							
							// ---------------- 用关键词作为KEY请求REDIS 开始 ----------------------------
							RedisClass.redisFuncThird(baiduPageNum, findStatusNum, redisKey, searchKeyword);
							// ---------------- 用关键词作为KEY请求REDIS 结束 ----------------------------
							
							//匹配查找状态
							if(findStatusNum == 1 ) {
								System.out.println(">> Find status num is 1, Stop findding element.");
								break;
							}
							//分隔符，如果第一页没有找到就开始找下一页
							System.out.println(">> ------------------------");
							
							//当第5页时，还会点击下一页到了第6页才会退出，多了一次点击就多一次请求，如果当前页面大于设置页时就跳出当前循环。
							if(baiduPageNum >= Settings.maxBaiduPageNum) {
								System.out.println(">> baiduPageNum:"+baiduPageNum+", Stoping click next page.");
								break;
							}
							
							//开始点击下一页
							try {
								
								WebElement findBaiduNextPageLinkurl = driver.findElement(By.id("sogou_next"));//注意，这个链接文本一定要与可视化页面的一样，非html代码
								baiduSearchLinkurl = findBaiduNextPageLinkurl.getAttribute("href");
								//System.out.println(">> findBaiduNextPageLinkurl:"+findBaiduNextPageLinkurl);
								findBaiduNextPageLinkurl.click();
								
						
								
							}catch(Exception e) {
								//打印异常，如果没有找到就尝试再点一次ַ
								e.printStackTrace();
								try {
									WebElement findBaiduNextPageLinkurl = driver.findElement(By.id("sogou_next"));//注意，这个链接文本一定要与可视化页面的一样，非html代码
									baiduSearchLinkurl = findBaiduNextPageLinkurl.getAttribute("href");
									//System.out.println(">> findBaiduNextPageLinkurl:"+findBaiduNextPageLinkurl);
									findBaiduNextPageLinkurl.click();
									
									
									
								}catch(Exception a) {
									driver.quit();
									OtherClass.timeSleep(1);
								}
							}
							
							
							
							
						}
						
					}catch (Exception e) {
						e.printStackTrace();
						driver.quit();
						OtherClass.timeSleep(OtherClass.getRandomBetweenNumbers(3, 15));
						
						
					}
					
					
					OtherClass.timeSleep(1);
					System.out.println(">> Running success! "+OtherClass.getDateTime());
					driver.quit();
				}catch(Exception e) {
					OtherClass.getRandomTimeSleep(">> Open url error");
					e.printStackTrace();
					
				}
				
				
				


			}
		}

	}
}
