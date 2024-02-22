package baidu_pc;



import org.openqa.selenium.*;
import cn.hutool.http.HttpUtil;

import java.util.ArrayList;
import java.util.List;
import java.io.*;




public class Main_baidu_pc {
	
	
	
	// ---------------------- main begin ----------------------

	public static void main(String[] args) throws IOException {
		
		String keywordDomain = "www.panfa.net";
		
		int maxPageNum = 11;
		
		
		String proxyIpString = "";
		
		if(Settings.ableProxy == true) {
			proxyIpString = HttpUtil.get(Settings.proxyApiUrl);
		}
		WebDriver driver =  DriverGet.get("https://www.aizhan.com",proxyIpString);
		
		int pageNum = 0;
		while(pageNum < maxPageNum) {
			pageNum += 1;

			
			String keywordPageLinkurl = "https://baidurank.aizhan.com/baidu/"+keywordDomain+"/-1/0/"+String.valueOf(pageNum)+"/position/1/";
			
			driver.get(keywordPageLinkurl);
			
			if(pageNum == 1) {
				OtherClass.timeSleep(15);
			}
			
			
			String htmlcode = driver.getPageSource();
			htmlcode = OtherClass.cleanHtml(htmlcode);

			int keywordNum = 0;
			List<String> keywordList = new ArrayList<String>();
			
			while(keywordNum < 25) {
				keywordNum += 1;
				WebElement keywordElement = driver.findElement(By.xpath("/html/body/div[4]/div[8]/div[3]/table/tbody/tr["+keywordNum+"]/td[1 or 2]/a"));
				String keyword = keywordElement.getText();
				
				keywordList.add(keyword);
				System.out.println(keyword);
				
				
				
			}
			
			
			OtherClass.timeSleep(2);
					
				

		}
		
		System.out.println(">> Running success!");

	}
}
