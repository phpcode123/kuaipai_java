package baidu_pc;



import org.openqa.selenium.*;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;


import java.util.ArrayList;
import java.util.Set;
import java.io.IOException;


public class Main_baidu_pc {
	
 
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
				
				String searchKeeywordLine = OtherClass.getSearchKeywordFromList(keywordIndexNumList, keywordList);
				
				String[] searchKeeywordLineList = searchKeeywordLine.split(";");
				searchKeyword = searchKeeywordLineList[0];
				String siteTitle = searchKeeywordLineList[1];
				String baiduUrl = searchKeeywordLineList[2];
				
				System.out.println(">> searchKeyword:"+searchKeyword);
				System.out.println(">> siteTitle:"+siteTitle);
				System.out.println(">> baiduUrl:"+baiduUrl);
				
				
				
				// ---------------- 通过数据下标随机读取关键词 结束 ----------------------------
				
				System.out.println(">> Program begin...");
				System.out.println(">> keywordList.size():"+keywordList.size());
				System.out.println(">> keywordIndexNumList.size():"+keywordIndexNumList.size());
				System.out.println(">> keywordCountNum:"+keywordCountNum+" searchKeyword:"+searchKeyword);
					
			
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
					
					
					WebDriver driver =  DriverGet.get(Settings.searchEngineBaiduPcUrl,proxyIpString);
					
					if(OtherClass.isLinux()) {
						if(OtherClass.getMatcher(Settings.regexSearchEngineStr, driver.getPageSource())) {
							System.out.println(">> regexIndex: true");
						}
					}
					
					OtherClass.timeSleep(2);
					
					
				
					
					try {

						//首页查找和点击
						driver.findElement(By.id("kw")).sendKeys(searchKeyword);
						OtherClass.timeSleep(1);
						driver.findElement(By.id("su")).click();
						
						//暂停3s，方便元素全部加载完
						OtherClass.timeSleep(3);
						
						//打印当前url
						String baiduSearchLinkurl = driver.getCurrentUrl();
						System.out.println(">> linkurl:"+baiduSearchLinkurl);
					
						//System.out.println(">> baiduSearchLinkurlEncode:"+OtherClass.getURLEncoderString(baiduSearchLinkurl));
						
						//System.out.println(">> keywordEncode:"+OtherClass.getURLEncoderString(searchKeyword));
						
						
						Set<Cookie> cookiesSet = driver.manage().getCookies();
						//System.out.println(">> cookies:"+cookiesSet);
						//System.out.println(">> Running success! "+OtherClass.getDateTime());
						
						
						
						
						String cookieStr = OtherClass.getCookieString(cookiesSet);
						System.out.println(">> cookieStr:"+cookieStr);
						
						//滚动滑动条
						MoveScroll.moveScrollBar(760, driver);
						
						String htmlcode = driver.getPageSource();
						
						//随机一个数字去匹配结果中的1-10条结果中的一条
						boolean randResult = false;
						
						//是否匹配到了相关的结果
						int isMatcherResultNum = 0;
						int countNum = 0;
						
						
						String rsv_srcid = "";
						String rsv_tpl = "";
						String F = "";
						String F1 = "";
						String F2 = "";
						String F3 = "";
						String T = "";
						String y = "";
						
						int randResultNum = 0;
						while(countNum < 10) {
							countNum += 1;
							
							randResultNum = OtherClass.getRandomBetweenNumbers(2, 8);
							System.out.println(">> randResultNum:"+randResultNum);
							
							String xpathStr = "//div[@id=\""+String.valueOf(randResultNum)+"\"]";
							System.out.println(">> xpathStr:" + xpathStr);
							
							//获取选取部分的html源码
							WebElement element = driver.findElement(By.xpath(xpathStr));						
							String randNumElementtHtml  = element.getAttribute("outerHTML");
							//System.out.println(">> randNumElementHtml:"+randNumElementtHtml);
							
							randNumElementtHtml = OtherClass.cleanLine("\s+",randNumElementtHtml);
							
							rsv_srcid = OtherClass.getMatcherBaiduPageParamString("srcid=\"(.*?)\"", randNumElementtHtml);
							rsv_tpl = OtherClass.getMatcherBaiduPageParamString("tpl=\"(.*?)\"", randNumElementtHtml);
							F = OtherClass.getMatcherBaiduPageParamString("'F':'(.*?)'", randNumElementtHtml);
							F1 = OtherClass.getMatcherBaiduPageParamString("'F1':'(.*?)'", randNumElementtHtml);
							F2 = OtherClass.getMatcherBaiduPageParamString("'F2':'(.*?)'", randNumElementtHtml);
							F3 = OtherClass.getMatcherBaiduPageParamString("'F3':'(.*?)'", randNumElementtHtml);
							T = OtherClass.getMatcherBaiduPageParamString("'T':'(.*?)'", randNumElementtHtml);
							y = OtherClass.getMatcherBaiduPageParamString("'y':'(.*?)'", randNumElementtHtml);
							
							System.out.println(">> rsv_srcid:"+rsv_srcid);
							System.out.println(">> rsv_tpl:"+rsv_tpl);
							System.out.println(">> F:"+F);
							System.out.println(">> F1:"+F1);
							System.out.println(">> F2:"+F2);
							System.out.println(">> F3:"+F3);
							System.out.println(">> T:"+T);
							System.out.println(">> y:"+y);
							
							
							//判断元素是否为空，如果为空就重新判断
							if(!F.equals("") && !F1.equals("") && !F2.equals("") && !F3.equals("")  && !T.equals("")) {
								randResult = true;
							}
							
							if(randResult == true) {
								isMatcherResultNum = 1;
								break;
							}
						}
						
						if(isMatcherResultNum == 0) {
							System.out.println(">> Do not match result, continue...");
							continue;
						}
						
						
						//OtherClass.timeSleep(1000);
						
						
						htmlcode = OtherClass.cleanLine("\s+",htmlcode);
						
						//ubsurl
						String ubsurl = OtherClass.getMatcherBaiduPageParamString("ubsurl:\"(.*?)\"",htmlcode);
						System.out.println(">> ubsurl:"+ubsurl);
						
						//q=Encode(searchKeyword)
						String q = OtherClass.getURLEncoderString(searchKeyword);
						System.out.println(">> q:"+q);
												
						
						String rsv_height = String.valueOf(OtherClass.getRandomBetweenNumbers(90,304));
						
						String rsv_sid = OtherClass.getMatcherBaiduPageParamString("sid:\"(.*?)\"", htmlcode);
						
						String qid = OtherClass.getMatcherBaiduPageParamString("qid:\"(.*?)\"", htmlcode);
						String rsv_did = OtherClass.getUUID();
						
						System.out.println(">> rsv_height:"+rsv_height);
						System.out.println(">> rsv_sid:"+rsv_sid);
						System.out.println(">> qid:"+qid);
						System.out.println(">> rsv_did:"+rsv_did);
						
						

						
						countNum = 0;
						while(countNum < 2) {
							countNum += 1;
							System.out.println(">> countNum:"+countNum);
							// ---------------------------  获取URL开始  ---------------------------
							String baiduRemoteWGifUrl = 
									ubsurl+"?"+
									"q="+q+"&"+
									"rsv_xpath=h3-a(title)&"+
									"title="+OtherClass.getURLEncoderString(siteTitle)+"&"+
									"url="+OtherClass.getURLEncoderString(baiduUrl)+"&"+
									"rsv_height="+rsv_height+"&"+
									"rsv_width=560"+"&"+
									"rsv_tpl="+rsv_tpl+"&"+
									"p1=1"+"&"+
									"rsv_srcid="+rsv_srcid+"&"+
									"F="+F+"&"+
									"F1="+F1+"&"+
									"F2="+F2+"&"+
									"F3="+F3+"&"+
									"T="+T+"&"+
									"y="+y+"&"+
									"rsv_bdr="+"0"+"&"+
									"p5="+String.valueOf(randResultNum)+"&"+
									"fm="+"as"+"&"+
									"rsv_sid="+rsv_sid+"&"+
									"cid="+"0"+"&"+
									"qid="+qid+"&"+
									"t="+String.valueOf(OtherClass.getLongTimestamp())+"&"+
									"rsv_cftime="+"1"+"&"+
									"rsv_iorr="+"1"+"&"+
									"rsv_tn="+"baidu"+"&"+
									"rsv_ssl="+"1"+"&"+
									"path="+OtherClass.getURLEncoderString(baiduSearchLinkurl)+"&"+
									"rsv_did="+rsv_did;
							
							System.out.println(">> ---------------------");
							System.out.println(">> baiduRemoteWGifUrl:"+baiduRemoteWGifUrl);
							System.out.println(">> ---------------------");
							
							
							
							String lcr = OtherClass.getMatcherBaiduPageParamString("\"lcr.open.baidu.com\":\"(.*?)\"",htmlcode);
							
							
							//根据wGifUrl的头协议去补全lcr url
							if(OtherClass.getMatcher("https", baiduRemoteWGifUrl)) {
								lcr = "https:"+lcr;
							}else {
								lcr = "http:"+lcr;
							}
									
							System.out.println(">> lcr:"+lcr);
							System.out.println(">> ---------------------");
							
							String baiduRemoteLinkUrl = 
								lcr+"/link?"+
								"url="+OtherClass.getMatcherBaiduPageParamString("url=(.*?)$", baiduUrl)+"&"+
								"query="+OtherClass.getURLEncoderString(searchKeyword)+"&"+
								"cb=jQuery11020"+OtherClass.getRandomNumString(16)+"_"+OtherClass.getLongTimestamp()+"&"+
								"data_name="+"recommend_common_merger_online"+"&"+
								"ie="+"utf-8"+"&"+
								"oe="+"utf-8"+"&"+
								"format="+"json"+"&"+
								"t="+String.valueOf(OtherClass.getLongTimestamp())+"&"+
								"_="+String.valueOf(OtherClass.getLongTimestamp()-OtherClass.getRandomBetweenNumbers(100000, 1000000));
							
							System.out.println(">> baiduRemoteLinkUrl:"+baiduRemoteLinkUrl);
							System.out.println(">> ---------------------");	
							
							// ---------------------------  获取URL结束  ---------------------------
							
							
							
							
							
							String userAgent = UserAgent.randMUserAgent();
							
							//开始get请求参数网址
							
							//设置ip和端口
							String ip = OtherClass.getProxyIp(proxyIpString);
							int port = OtherClass.getProxyPort(proxyIpString);
							
							
							//链式构建请求: baiduRemoteWGifUrl
							String result1 = HttpRequest.get(baiduRemoteWGifUrl)
								.header(Header.ACCEPT, "image/avif,image/webp,image/apng,image/svg+xml,image/*,*/*;q=0.8")
								.header(Header.ACCEPT_ENCODING, "gzip, deflate, br")
								.header(Header.ACCEPT_LANGUAGE, "zh-CN,zh;q=0.9")
								.header(Header.CONNECTION,"keep-alive")
								.header(Header.COOKIE, cookieStr)
								.header(Header.HOST, OtherClass.getMatcherBaiduPageParamString("//(.*?com)", baiduRemoteWGifUrl))
								.header(Header.REFERER, baiduSearchLinkurl)								
							    .header(Header.USER_AGENT, userAgent)
							    .setHttpProxy(ip, port)
							    .timeout(20000)//超时，毫秒
							    .execute().body();
							System.out.println(">> result1:"+result1);
							
							//链式构建请求: baiduRemoteLinkUrl
							String result2 = HttpRequest.get(baiduRemoteLinkUrl)
								.header(Header.ACCEPT, "*/*")
								.header(Header.ACCEPT_ENCODING, "gzip, deflate, br")
								.header(Header.ACCEPT_LANGUAGE, "zh-CN,zh;q=0.9")
								.header(Header.CONNECTION,"keep-alive")
								.header(Header.COOKIE, cookieStr)
								.header(Header.HOST, OtherClass.getMatcherBaiduPageParamString("//(.*?com)", baiduRemoteLinkUrl))
								.header(Header.REFERER, baiduSearchLinkurl)								
							    .header(Header.USER_AGENT, userAgent)
							    .timeout(20000)//超时，毫秒
							    .setHttpProxy(ip, port)
							    .execute().body();
							System.out.println(">> result2:"+result2);
							
							OtherClass.timeSleep(OtherClass.getRandomBetweenNumbers(1, 3));
						}
						
						
					}catch (Exception e) {
						e.printStackTrace();
						driver.quit();
						OtherClass.timeSleep(OtherClass.getRandomBetweenNumbers(1, 10));
						
						
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
