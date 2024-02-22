package changchenghao_chrome_spider;



import org.openqa.selenium.Dimension;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.io.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Duration;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.util.ReUtil;
import cn.hutool.http.HttpRequest;

public class RunApp_linux_pc {
	
	// ---------------------- main begin ----------------------

	public static void main(String[] args) throws IOException, SQLException {
		
		
		//LoggerContextFactory loggerContextFactory = new SLF4JLoggerContextFactory();
		//LoggerContext loggerContext = loggerContextFactory.getContext(null, null, io.netty.channel.DefaultChannelId.class, false);
	

   
		
	

		//keyword.txt for file read
		String keywordFilePath = Settings.keywordFilePath+"csv\\new_www.changchenghao.cn.csv";
		//System.out.println(keywordFilePath);
		FileReader keywordFile = new FileReader(keywordFilePath);
		List<String> keywordList = keywordFile.readLines();
		
		
		
		int countNum = 0;
		for(String csvLine : keywordList) {
			countNum += 1;
			try {					
				
				String[] lineArray = csvLine.split(",");
				String searchKeyword = lineArray[0];
				String websiteLinkurl = lineArray[1];
				
				
				String userAgent = RandUserAgent.randUserAgent();
				
				
				NewChromeOptions.CHROME_DRIVER = "/usr/bin/chromedriver";
				HttpsProxy httpsProxy = new HttpsProxy(Settings.proxyHost,Settings.proxyPort,Settings.proxyUser, Settings.proxyPass);
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
					
					
					System.out.println(">>> searchKeyword:"+searchKeyword+", websiteLinkurl:"+websiteLinkurl);
					//driver.get("http://m.baidu.com");
					DriverGet.driverGet(driver, websiteLinkurl);
					
					System.out.println(">>> Program begin...");
					
					//测试百度链接速度，为什么平常时会那么慢，现在却那么快？
					//OtherClass.timeSleep(5);
					//DriverGet.driverGet(driver, "http://www.baidu.com");
					//OtherClass.timeSleep(1000);
					
					
//					
					List<String> content = ReUtil.findAll("<div id=\"cont\">(.*?)<div class=\"content-more\">", driver.getPageSource(),0, new ArrayList<String>());
					String contentStr = content.get(0);
					
					//System.out.println(contentStr);
					
					List<String> title = ReUtil.findAll("<h1 class=\"entry-title\">(.*?)</h1>", driver.getPageSource(),0, new ArrayList<String>());
					String titleStr = title.get(0);
					System.out.println(titleStr);
					
					System.out.println("----------------------------------");
					
					
					int catid = 2;
					int tableId = 100;
					//日期和时间戳  时间戳数据类型为long
					Date date = new Date();
					long timestamp = ((int)date.getTime());
					//System.out.println(timestamp);
					
					
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					String addtime = df.format(date);
					
					String postUrl = "http://192.168.0.101:8085/index.php?s=index/articlenewsapi/index";
					
					Map<String,Object> fromData = new HashMap<String,Object>();
					fromData.put("api_password",Settings.apiPassword);
					fromData.put("title_before",titleStr);
					fromData.put("search_keyword",searchKeyword);
					fromData.put("fromurl",websiteLinkurl);
					fromData.put("content",contentStr);
					fromData.put("cate_id",catid);
					fromData.put("table_id",tableId);
					fromData.put("timestamp",timestamp);
					fromData.put("addtime",addtime);

					
					//post data
					String requestHtml = HttpRequest.post(postUrl).form(fromData).timeout(20000).execute().body();
					
					System.out.println("countNum:"+countNum+" "+requestHtml);
					System.out.println("----------------------------");
					
					

					
					OtherClass.timeSleep(200);
					
					
				}catch(Exception e) {
					System.out.println(">>> Open url error,Timestamp(1)");
					FileWriter fw = new FileWriter("./errorurl.txt");
					fw.append(csvLine+"\n");
					OtherClass.timeSleep(1);
					e.printStackTrace();
					try {
						driver.quit();
					}catch(Exception e1){
						e1.printStackTrace();
						
					}
				}
				
				try {
					driver.quit();
				}catch(Exception e1){
					e1.printStackTrace();
					
				}
			}catch(Exception e5) {
				continue;
			}

		}
		System.out.println("Running success!");

	}
}
