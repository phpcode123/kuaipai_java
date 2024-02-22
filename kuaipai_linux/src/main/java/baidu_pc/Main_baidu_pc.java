package baidu_pc;



import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;


public class Main_baidu_pc {
	

	// ---------------------- main begin ----------------------

	public static void main(String[] args) throws IOException {
		

		try {
		
			String hostName = "";
			try {
				hostName = args[0];
				if(hostName.length() == 0) {
					System.out.println(">> hostName command line arguments is null.");
					return;
				}else{
					System.out.println(">> hostName:"+hostName);
					
				}
			}catch(Exception e) {
				System.out.println(">> hostName command line arguments is null.");
				return;
			}
			
			
			for(String domain:OtherClass.getDomain()) {
				System.out.println(">> domain:"+domain);
			}
	
			while(true) {
				
				System.out.println(">> "+Settings.version);
				
	
				
				ArrayList<String> keywordList = OtherClass.getKeyword();
	
				ArrayList<String> keywordIndexNumList = OtherClass.keywordIndexNumSet(keywordList.size());
				
	
				
	
				for(String searchKeyword_ : keywordList) {
					
					
					
					//killChromeProcess 清理掉chrome.exe和chromedriver.exe
					OtherClass.killChromeProcess();
					System.out.println(">> killProcess.");
					
					
					
					// ---------------- 通过数据下标随机读取关键词 开始 ----------------------------
					
					//当数字下标数组的长度为0说明所有的数字下标都已经调用过了，跳出当前循环。
					//正常逻辑运行时此中断应该是永远不会运行的，加此可保万无一失。
					if(keywordIndexNumList.size() == 0) {
						break;
					}
					
					searchKeyword_ = OtherClass.getSearchKeywordFromList(keywordIndexNumList, keywordList);
					
					
					
					String[] searchKewyordList = searchKeyword_.split("::");
					String itemid = searchKewyordList[0];
					String searchKeyword = OtherClass.cleanLine(searchKewyordList[1]);
					
					System.out.println(">> itmeid:"+itemid+" keyword:"+searchKeyword);
					
					//自定义关键词用于测试
					//searchKeyword = "史上最强台风";
					
					
					try {
						
						// ---------------- 请求IP 开始 ----------------------------
						String proxyIpString = "";
						
						while(Settings.ableProxy) {
							proxyIpString = OtherClass.getIp();
							if(proxyIpString.contains("fail")) {
								System.out.println(">> get proxy ip error, timesleep(5)");
								OtherClass.timeSleep(5);
							}else{
								//如果获取了代理ip，就跳出当前循环
								System.out.println(">> proxyIp:"+proxyIpString);
								break;
							}
						}
						
						// ---------------- 请求IP 结束 ----------------------------
						
						
						WebDriver driver =  DriverGet.get(Settings.searchEngineBaiduPcUrl,proxyIpString);
						
						
						OtherClass.timeSleep(2);
						
						try {
	
							//首页查找和点击
							driver.findElement(By.id("kw")).sendKeys(searchKeyword);
							OtherClass.timeSleep(1);
							driver.findElement(By.id("su")).click();
							
							//暂停3s，方便元素全部加载完
							OtherClass.timeSleep(5);
							
							//打印当前url
							String baiduSearchLinkurl = driver.getCurrentUrl();
							System.out.println(">> linkurl:"+baiduSearchLinkurl);
							
							//如果匹配到是验证码页面就直接退出
							if(baiduSearchLinkurl.contains("wappass.baidu.com")) {
								System.out.println(">> Verification code page,driver.quit()");
								
								//暂停一定的时间来用于调试信息
								//OtherClass.timeSleep(1000000);
								driver.quit();
								continue;
							}
						
							//OtherClass.timeSleep(10);
						
		
		
							int baiduPageNum = 0;
							int countNum = 0;
							int findStatusNum;
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
								for(String domain_ :OtherClass.getDomain()) {
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
								findStatusNum = 0;
								//如果当前页面中匹配到了当前域名
								int clickStatus = 0;
								if(findDomainInHtml == true) {
									
									
									
									
									//百度搜索结果项正常都是10个搜索结果，有时会出现广告，和其他的一些流媒体插入，此值设置为15
									int findElementExceptionNum=0;
									while(countNum < 11) {
										countNum++;
										System.out.println(">> countNum:"+String.valueOf(countNum));
										System.out.println(">> BaiduPageNum:"+String.valueOf(baiduPageNum)+" Element:"+String.valueOf(countNum)+" ------------------------");
										
										
										
										//当countNum为1时则说明是第一个元素，不知道为什么每页的第一个元素无法匹配到，直接匹配域名栏
										String xpathStr = "";
										if(countNum == 1) {
											xpathStr = "//*[@id=\"content_left\"]/*[@id=\""+String.valueOf(baiduPageNum*10-10+countNum)+"\"]/div";
											System.out.println(">> xpathStr:" + xpathStr);
										}else {
											xpathStr = "//*[@id=\"content_left\"]/*[@id=\""+String.valueOf(baiduPageNum*10-10+countNum)+"\"]/div";
											System.out.println(">> xpathStr:" + xpathStr);
										}
										
										try {
											//System.out.println(">> i:"+i);
											
											try {
												WebElement element;
												String elementText="";
												try {	
													element = driver.findElement(By.xpath(xpathStr));
													elementText = element.getText();
													//匹配搜索结果的所在列，第一个输出错误是因为第一个结果是问答卡片，与其它的不同
													System.out.println(">> ElementText:"+elementText);
												}catch (Exception e) {
													//如果查找元素异常超过1次就退出当前循环
													findElementExceptionNum+=1;
													if(findElementExceptionNum > 1) {
														System.out.println(">> findElementExceptionNum > 1.");
														break;
													}
												}
												
												
												
												
												
												//当countnum大于1时，将上一个结果块移动到左上角可视化的区域，这样可以避免移动当前块被百度搜索框给挡住,blockNum是搜索结果中的结果块，3就是第三个结果
												int blockNum = 3;	
												if(countNum > blockNum) {
													//*[@id="3"]/div
													String newXpath = "//*[@id=\"content_left\"]/*[@id=\""+String.valueOf(baiduPageNum*10-10+countNum-1)+"\"]/div";
													WebElement newElement = driver.findElement(By.xpath(newXpath));
													//String newElementText = newElement.getText();
													//System.out.println(">> ElementText:"+newElementText);
													MoveScroll.moveScrollIntoView(newElement,driver);
												}
												//暂时时间用于加载页面中w.gif
												OtherClass.timeSleep(1);
											
											
												//在搜索项中匹配自己的网站url
												
												List<String> matchSearchWebsite = OtherClass.getMatchers(Settings.searchWebsite, elementText);
												
												//size()大于0说明有匹配到自己的网站，执行点击
												if(matchSearchWebsite.size() > 0) {
													
													System.out.println(">> Find website status: 1");
													OtherClass.timeSleep(1);
													try {
														try {
															//xpath
															String clickXpath = "//*[@id=\"content_left\"]/*[@id=\""+String.valueOf(baiduPageNum*10-10+countNum)+"\"]/div/div[1]/h3";
															
															System.out.println(">> clickXpath:"+clickXpath);
															driver.findElement(By.xpath(clickXpath)).click();
															System.out.println(">> Element click success.");
															clickStatus = 1;
														}catch(Exception e) {
															//xpath
															String clickXpath = "//*[@id=\"content_left\"]/*[@id=\""+String.valueOf(baiduPageNum*10-10+countNum)+"\"]/div/h3";
															
															System.out.println(">> clickXpath:"+clickXpath);
															driver.findElement(By.xpath(clickXpath)).click();
															System.out.println(">> Element click success.");
															
														}
														
														
														
														//点击成功后暂停3秒钟，用于加载页面，有可能页面都没有加载完或者url在加载中时就关闭浏览器退出的情况
														OtherClass.timeSleep(3);
														
														// 获取当前页面句柄
												        String handle = driver.getWindowHandle(); 
												   
												        // 获取所有页面的句柄，并循环判断不是当前的句柄 实则只有两个窗口，两个窗口可以使用此方法，注意String的比较是equals
												        for (String handles : driver.getWindowHandles()) {  
												            if (!handles.equals(handle)) {
												            	driver.switchTo().window(handles); 
												            } 
												             
												        }
														findStatusNum = 1;
														
														//点击成功后暂停10s 并且退出当前循环
														OtherClass.timeSleep(2);
												
														
														//点击状态为1时，即点击成功后，先关闭所有的窗口，然后再退出driver 再退出当前循环
														if(clickStatus == 1) {
															String handle_ = driver.getWindowHandle();
															for (String handles : driver.getWindowHandles()) {  
																if(handles.equals(handle_)){
																	driver.switchTo().window(handles); 
														            driver.close();
														            OtherClass.timeSleep(1);
																}
													       
																
																//更新点击数、主机和rank状态值
																OtherClass.updateClick();
																OtherClass.updateHostClickStatus(hostName);
																OtherClass.setRank(itemid, "rank_"+baiduPageNum+"_"+countNum);
												
																
																driver.quit();
																break;
															}
														}
														
														
														
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
								
								
	
								//匹配查找状态
								if(findStatusNum == 1 && clickStatus == 1) {
									System.out.println(">> Find status num is 1, Stop findding element.");
									break;
								}
								//分隔符，如果第一页没有找到就开始找下一页
								System.out.println(">> ------------------------");
								
								
								
								//更新rank值
								if(baiduPageNum >= 5 && findStatusNum == 0) {
									String rank_num = "rank_6";
									OtherClass.setRank(itemid, rank_num);
								}
								
								//
								
								
								//当第5页时，还会点击下一页到了第6页才会退出，多了一次点击就多一次请求，如果当前页面大于设置页时就跳出当前循环。
								if(baiduPageNum >= Settings.maxBaiduPageNum) {
									System.out.println(">> baiduPageNum:"+baiduPageNum+", Stoping click next page.");
									break;
								}
								
	
								
								
								//开始点击下一页
								try {
									
									WebElement findBaiduNextPageLinkurl = driver.findElement(By.linkText("下一页 >"));//注意，这个链接文本一定要与可视化页面的一样，非html代码
									baiduSearchLinkurl = findBaiduNextPageLinkurl.getAttribute("href");
									//System.out.println(">> findBaiduNextPageLinkurl:"+findBaiduNextPageLinkurl);
									findBaiduNextPageLinkurl.click();
									
							
									
								}catch(Exception e) {
									//打印异常，如果没有找到就尝试再点一次ַ
									e.printStackTrace();
									try {
										WebElement findBaiduNextPageLinkurl = driver.findElement(By.linkText("下一页 >"));//注意，这个链接文本一定要与可视化页面的一样，非html代码
										baiduSearchLinkurl = findBaiduNextPageLinkurl.getAttribute("href");
										//System.out.println(">> findBaiduNextPageLinkurl:"+findBaiduNextPageLinkurl);
										findBaiduNextPageLinkurl.click();
										
										
										
									}catch(Exception a) {
										driver.quit();
										OtherClass.timeSleep(1);
									}
								}
								
								
								
								
								
								
							}
							
							//更新主机值
							OtherClass.updateHostActiveStatus(hostName);
							
							
							
							
						}catch (Exception e) {
							e.printStackTrace();
							driver.quit();
							OtherClass.timeSleep(OtherClass.getRandomBetweenNumbers(1, 3));
							
							
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
		}catch(Exception e) {
			OtherClass.getRandomTimeSleep(">> Open url error");
			e.printStackTrace();
			
		}

	}
}
