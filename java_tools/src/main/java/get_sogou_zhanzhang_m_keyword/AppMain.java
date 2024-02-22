package get_sogou_zhanzhang_m_keyword;



import org.openqa.selenium.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class AppMain {
	

	// ---------------------- main begin ----------------------

	public static void main(String[] args) throws IOException {
		
		
		WebDriver driver =  DriverGet.get(Settings.sogouZhanZhangUrl,"");
		OtherClass.timeSleep(30);

		driver.get(Settings.sogouZhanZhangUrl);
		
		OtherClass.timeSleep(10);
		int pageNum = 0;
		while(pageNum < Settings.maxPageNum) {
			pageNum += 1;
			if(pageNum <= 4) {
				driver.findElement(By.xpath("/html/body/div/div/div/div/div[2]/div/div/div[3]/div/div/ul/li["+pageNum+"]")).click();
			}else {
				driver.findElement(By.xpath("/html/body/div/div/div/div/div[2]/div/div/div[3]/div/div/ul/li[6]")).click();
			}
			
			String htmlcode = driver.getPageSource();
			htmlcode = OtherClass.cleanLine(htmlcode);
			//System.out.println("htmlcode:"+htmlcode);
			WebElement areaHtmlcode = driver.findElement(By.xpath("//tbody"));
			//System.out.println(areaHtmlcode.getAttribute("outerHTML"));
			
			String areaStr = OtherClass.cleanLine(areaHtmlcode.getAttribute("outerHTML"));
			String pat = "<a.*?>(.*?)</a>";
			
			Pattern p = Pattern.compile(pat);
			Matcher m = p.matcher(areaStr);
			
			//int countNum = 0;
			File f = new File("D:\\txt\\panduoduo_sogou_keyword.txt");
			OutputStream os = new FileOutputStream(f, true);
			while(m.find()) {
				System.out.println(m.group(1));
				os.write((m.group(1)+"\n").getBytes());
			
			}
			os.close();
			OtherClass.timeSleep(3);
		}
	}
}
