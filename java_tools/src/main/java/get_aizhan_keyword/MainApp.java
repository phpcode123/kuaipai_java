package get_aizhan_keyword;



import org.openqa.selenium.*;


import java.util.ArrayList;
import java.util.List;
import java.io.IOException;


public class MainApp {
	

	// ---------------------- main begin ----------------------

	public static void main(String[] args) throws IOException {


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
			
			
			WebDriver driver =  DriverGet.get(OtherClass.getAizhanUrl(),proxyIpString);
			
			System.out.println(">> Please scan QRcode  login in, TimeSleep(15)");
			OtherClass.timeSleep(15);
			
			
			List<String> allKeywordList = new ArrayList<String>();
			int pageNum = 0;
			while (pageNum < Settings.maxPageNum) {
				pageNum += 1;

				String url = OtherClass.stringReplaceAll("\\[pageNum\\]", String.valueOf(pageNum), OtherClass.getAizhanUrl());

				System.out.println(">> url:"+url);
				
				driver.get(url);
				String htmlcode = driver.getPageSource();
				
				htmlcode = OtherClass.cleanHtml(htmlcode);
				
				//System.out.println(">> htmlcode:"+htmlcode);
				
				List<String> keywordList = OtherClass.findAllLinkurl("<tdclass=\"title\"><aclass=\"gray\"rel=\"nofollow\"target=\"_blank\"href=\".*?\">(.*?)</a>",htmlcode);
				
				//System.out.println(">> keywordList:"+keywordList);
				
				for(String i : keywordList) {
					System.out.println(">> i:"+i);
					allKeywordList.add(i);
				}
				
				//System.out.println(">> htmlcode:"+htmlcode);
				
				
				OtherClass.timeSleep(2);
				
			}
			
			
			for(String keyword_ : allKeywordList) {
				if(Settings.isPc) {
					System.out.println("pc:"+keyword_);
				}else {
					System.out.println("m:"+keyword_);
				}
			}
			
			System.out.println("Running success!");
			
		}catch(Exception e) {
			e.printStackTrace();

		}	
			
				
				


	}
}
