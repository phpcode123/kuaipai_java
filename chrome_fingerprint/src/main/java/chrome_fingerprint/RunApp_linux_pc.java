package chrome_fingerprint;



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
	

   
		
		
		while(true) {

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
						
						
						//String fingerPrintLinkurl = "https://yisin.gitee.io/ysfile/checkbrowser.html";
						String fingerPrintLinkurl = "https://beian.miit.gov.cn/index#/Integrated/recordQuery";
						//String fingerPrintLinkurl = "http://www.baidu.com";
						DriverGet.driverGet(driver, fingerPrintLinkurl);
						System.out.println(">>> Program begin...");
				
						System.out.println(">>>> "+driver.getPageSource());
						System.out.println("===============================");
						
						

						
						OtherClass.timeSleep(100000);
						
						
					}catch(Exception e) {
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
		}

	}
}
