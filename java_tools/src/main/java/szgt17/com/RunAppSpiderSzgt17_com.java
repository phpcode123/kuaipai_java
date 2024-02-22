package szgt17.com;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;


public class RunAppSpiderSzgt17_com {
	

	// ---------------------- main begin ----------------------

	public static void main(String[] args) throws IOException, SQLException {
		
		String getSql = "select * from linshi_keyword_csv where status=0 order by itemid asc;";


	    Connection conn = MysqlClass.getConn();
	    PreparedStatement pstmt;
	    
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(getSql);
	        ResultSet rs = pstmt.executeQuery();
	        int countNum = 0;
	        while(rs.next()) {
	        	countNum += 1;
	        	
	        	
	        	//获取数据库表项
	        	String itemid = rs.getString(1);
	        	String searchKeyword = rs.getString(2);
	        	//String title = rs.getString(3);
	        	String fromurl = rs.getString(4);
	        	String status = rs.getString(5);
	        	
	        	
	        	//设置最小的起始itemid，用于调试程序，避免程序重启后itemid又是从1开始
	        	int miniBeginItemid = 0;
	        	
	        	
	        	if(Integer.valueOf(itemid) < miniBeginItemid) {
	        		System.out.println(">> Itemid less then miniBeginItemid, itemid:"+itemid+" miniBeginItemid:"+miniBeginItemid+".");
	        		continue;
	        	}
	        	
	        	
	        	System.out.println(">> countNum:"+String.valueOf(countNum)+" itemid:"+itemid+" searchKeyword:"+searchKeyword+" fromurl:"+fromurl+" status:"+status);
	        	
	        	
	        	//处理数据
	        	
	        	try {
					// ---------------- 数据库中请求IP 开始 ----------------------------
					String proxyIpString = "";
					
					//从数据库中请求ip
					while(Settings.ableProxy) {
						
						String getOneSql = MysqlClass.getOneSql();
						System.out.println(">> getOneSql:"+getOneSql);
						
						ArrayList<Object> result = MysqlClass.getOne(getOneSql);
						System.out.println(">> getOneResult:"+result);
						int itemid_;
						if(result.size() > 0) {
							itemid_ = (Integer) result.get(0);
							proxyIpString = (String) result.get(1);
							System.out.println(">> itemid:"+itemid_);
							System.out.println(">> proxyIpString:"+proxyIpString);
							
							
							
							
							String updateSql = MysqlClass.updateSql(itemid_);
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
					
					
					//http://kt.szgt17.com/0EhgT4__1741835.html url模板1 已采集
					//String title = OtherClass.getMatcherString("<title>(.*?)</title>", htmlcode);
					//System.out.println(">> title:"+title);
					//String content = OtherClass.getMatcherString("news-content'>(.*?)</article>", htmlcode);
					
					
					//http://www.szgt17.com/vattirqzwx/post-1.html url模板2
//					String title = OtherClass.getMatcherString("<h1>(.*?)</h1>", htmlcode);
//					System.out.println(">> title:"+title);
//					String content = OtherClass.getMatcherString("<div class=\"honxian\".*?>(.*?)</p><p style=\"font-size:16px; color:#F00\">86%的人还看了以下相关资料", htmlcode);

					
					
					
					//http://www.szgt17.com/TBO4/cf/5Uta__5077171.html url模板3
					//String title = OtherClass.getMatcherString("<title>(.*?)</title>", htmlcode);
					//System.out.println(">> title:"+title);
					//String content = OtherClass.getMatcherString("<div class=\"infoCon\".*?>(.*?)</div>", htmlcode);
					
					//http://www.szgt17.com/yiwenjieda__6511.html url模板4
					//String title = OtherClass.getMatcherString("<title>(.*?)</title>", htmlcode);
					//System.out.println(">> title:"+title);
					//String content = OtherClass.getMatcherString("<div class=\"help_info_des\">(.*?)</div>", htmlcode);
					
					//https://www.szgt17.com/xiuli_29503/ url模板5
					String title = OtherClass.getMatcherString("<title>(.*?)</title>", htmlcode);
					System.out.println(">> title:"+title);
					String content = OtherClass.getMatcherString("<div class=\"titer_center\".*?>(.*?)</div>", htmlcode);
					
					
					
					//String[] regexList = {"</?div.*?>","</?p.*?>","<span.*?>",""};
					//for(String i : regexList) {
					//	content = OtherClass.cleanLine(i, content);
					//}
					
					//content = OtherClass.cleanLine("</span>","<br />\n",content);
					content = OtherClass.cleanLine("\\d{9,10}","\\{\\$content_phone_number\\}",content);
					content = OtherClass.cleanLine("https://www.szgt17.com","",content);
					content = OtherClass.cleanLine("http://www.szgt17.com","",content);
					content = OtherClass.cleanLine("www.szgt17.com","",content);
					content = OtherClass.cleanLine("</p><p>&nbsp;$","",content);
					content = OtherClass.cleanLine("<p>&nbsp;$","",content);
					content = OtherClass.cleanLine("'","\\\"",content);
					content = OtherClass.cleanLine("<p style=\"font-size:16px; color:#F00\">86%的人还看了以上相关资料后问题解决了</p>","",content);
					System.out.println(">> content:"+content);
					
					
					OtherClass.timeSleep(1);
					
					
					if(content.length() > 10) {
						//OtherClass.timeSleep(100);
						String insertSql = "insert into tp_jiadianweixiu(title,search_keyword,content,fromurl,title_about_search_keyword,search_keyword_about_search_keyword)values('"+title+"','"+searchKeyword+"','"+content+"','"+fromurl+"','0','0');";
						if(MysqlClass.insert(insertSql)) {
							System.out.println("Data insert success!");
						}else {
							System.out.println("Data insert error!");
						}
						
						String updateSql = "update linshi_keyword_csv set status=1 where itemid="+itemid+";";
						if(MysqlClass.update(updateSql)) {
							System.out.println("Update success!");
						}else {
							System.out.println("Update error!");
						}
						
					}
					
					
					
					
	        	
	        	}catch(Exception e) {
					e.printStackTrace();
	        	}
	        	
	        	OtherClass.timeSleep(1);
	        	
	        }
	        
	        
	        rs.close();
	        pstmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    conn.close();


		OtherClass.timeSleep(1);
		System.out.println(">> Running success!");
	}
}
