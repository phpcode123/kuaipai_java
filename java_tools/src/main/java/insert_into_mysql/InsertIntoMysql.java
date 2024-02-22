package insert_into_mysql;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;


public class InsertIntoMysql {
	

	// ---------------------- main begin ----------------------

	public static void main(String[] args) throws IOException, SQLException {
		
		
		List<String> csvFilePathList = OtherClass.readLines(Settings.csvFilePath);
		int countNum = 0;
		for(String i: csvFilePathList) {
			countNum += 1;
			//OtherClass.timeSleep(1);
			
			String[] iArray = i.split(",");
			String searchKeyword = iArray[0];
			String title = iArray[1];
			String fromurl = iArray[2];
			

			System.out.println(">> countNum:"+String.valueOf(countNum) +" searchKeyword:"+searchKeyword+", title:"+title+" fromurl:"+fromurl);
		
			String insertSql = "insert into "+Settings.TABLE+"(search_keyword,title,fromurl)VALUES('"+searchKeyword+"','"+title+"','"+fromurl+"');";
			System.out.println(">> insertSql:"+insertSql);
			
			if(MysqlClass.insert(insertSql)) {
				System.out.println("Data insert success!");
			}else {
				System.out.println("Data insert error!");
			}
		}
		
		
		
		
		OtherClass.timeSleep(1000);
		
		int beginNum = 7999;
		int countNum_ = 0;
		while(beginNum < 314701) {
			beginNum += 1;
			try {
				// ---------------- 数据库中请求IP 开始 ----------------------------
				String proxyIpString = "";
				
				//从数据库中请求ip
				while(Settings.ableProxy) {
					
					String getOneSql = "";
					System.out.println(">> getOneSql:"+getOneSql);
					
					ArrayList<Object> result = MysqlClass.getOne(getOneSql);
					System.out.println(">> getOneResult:"+result);
					int itemid;
					if(result.size() > 0) {
						itemid = (Integer) result.get(0);
						proxyIpString = (String) result.get(1);
						System.out.println(">> itemid:"+itemid);
						System.out.println(">> proxyIpString:"+proxyIpString);
						
						
						
						
						String updateSql = "";
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
				
				String ip = "";
				int port = 0;
				
				if(Settings.ableProxy) {
					ip = OtherClass.getProxyIp(proxyIpString);
					port = OtherClass.getProxyPort(proxyIpString);
					System.out.println(">> ip:"+ip);
					System.out.println(">> port:"+port);
				}
			
				
				
				String fromurl = "https://www.szgt17.com/guzhangjiejue__"+String.valueOf(beginNum)+".html";
				
				System.out.println(">> linkurl:"+fromurl);
				
				String userAgent = UserAgent.randMUserAgent();
				//链式构建请求: baiduRemoteWGifUrl
				String htmlcode = "";
				if(Settings.ableProxy) {
					htmlcode = HttpRequest.get(fromurl)						
						    .header(Header.USER_AGENT, userAgent)
						    .setHttpProxy(ip, port)
						    .timeout(15000)
						    .execute().body();
				}else {
					htmlcode = HttpRequest.get(fromurl)						
						    .header(Header.USER_AGENT, userAgent)
						    .timeout(15000)
						    .execute().body();
				}
				
				
				htmlcode = OtherClass.cleanLine("\\r+|\\n+", htmlcode);
				//System.out.println(">> htmlcode:"+htmlcode);
				
				String title = OtherClass.getMatcherString("<title>(.*?)</title>", htmlcode);
				System.out.println(">> title:"+title);
				
				
				String content = OtherClass.getMatcherString("news-content'>(.*?)</article>", htmlcode);
				
				
				//String[] regexList = {"</?div.*?>","</?p.*?>","<span.*?>",""};
				//for(String i : regexList) {
				//	content = OtherClass.cleanLine(i, content);
				//}
				
				//content = OtherClass.cleanLine("</span>","<br />\n",content);
				content = OtherClass.cleanLine("\\d{9,10}","\\{\\$content_phone_number\\}",content);
				System.out.println(">> content:"+content);
				
				
				
				OtherClass.timeSleep(100);
				
				
				String insertSql = "insert into wx_guzhangjiejue(title,content,fromurl)values('"+title+"','"+content+"','"+fromurl+"');";
				MysqlClass.insert(insertSql);
				
				//将计数器归0
				countNum_ = 0;
				
				System.out.println(">> --------------------------------");
				//OtherClass.timeSleep(100000);
			}catch(Exception e) {
				e.printStackTrace();
				beginNum -= 1;
				countNum_ += 1;
				
				//如果当前链接的请求失败次数大于3就直接+2，跳过当前链接，并将计数器归0
				if(countNum_ >3) {
					
					beginNum += 2;
					countNum_ = 0;
				}
			}
		}
		
		System.out.println(">> Running success!");
		

	}
}
