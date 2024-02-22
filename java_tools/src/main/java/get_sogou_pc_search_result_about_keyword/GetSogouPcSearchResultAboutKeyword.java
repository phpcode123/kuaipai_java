package get_sogou_pc_search_result_about_keyword;



import org.openqa.selenium.*;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


public class GetSogouPcSearchResultAboutKeyword {
	

	// ---------------------- main begin ----------------------

	public static void main(String[] args) throws IOException, SQLException {
		

		while(true) {

		
			String getSql;
			
		
			getSql = "select * from tp_jiadianweixiu where search_keyword_status=1 order by rand() limit 1;";

			
			
			
			
			System.out.println(">> getSql:"+getSql);
			
		    Connection conn = MysqlClass.getConn();
		    PreparedStatement pstmt1;
		   
		    try {
		        pstmt1 = (PreparedStatement)conn.prepareStatement(getSql);
		        ResultSet rs1 = pstmt1.executeQuery();
		        
		        //判断第一条数据是否为空，如果为空就说明没有数据返回,重新生成数据
		        if(rs1.wasNull()) {
		        	System.out.println(">> Result data is null.");
		        	continue;
		        }
		        
		        int countNum = 0;
		        while(rs1.next()) {
		        	try {
			        	countNum += 1;
			        	
			        	
			        	//获取数据库表项
			        	//String itemid = rs1.getString(1);
			        	//String searchKeyword = rs1.getString(3);
			        	//String searchKeywordStatus = rs1.getString(8);
			        	
			        	//单独更新数据
			        	String itemid = "8152";
			        	String searchKeyword = "德国威能壁挂炉显示f20故障";
			        	String searchKeywordStatus = "0";
			        	
			    
			        	
			        	//设置最小的起始itemid，用于调试程序，避免程序重启后itemid又是从1开始
			        	int miniBeginItemid = 0;
			        	
			        	
			        	if(Integer.valueOf(itemid) < miniBeginItemid) {
			        		System.out.println(">> Itemid less then miniBeginItemid, itemid:"+itemid+" miniBeginItemid:"+miniBeginItemid+".");
			        		continue;
			        	}
			        	
			        	
			        	System.out.println(">> countNum:"+String.valueOf(countNum)+" itemid:"+itemid+" searchKeyword:"+searchKeyword+" searchKeywordStatus:"+searchKeywordStatus);
			        	OtherClass.timeSleep(1);
			        	
			        	
			        	
			        	//-------------------- 请求IP开始 -----------------------------
			        	String proxyIpString = "";
			        	try {
							
							// ---------------- 数据库中请求IP 开始 ----------------------------
							
							
							//从数据库中请求ip
							while(Settings.ableProxy) {
								
								
								Connection conn2 = MysqlClass.getConn("com.mysql.jdbc.Driver", "jdbc:mysql://192.168.0.101:3306/kuaipai?useSSL=false", "root", "root98765");
							    
							    
							    try {
							    	String getOneSql = "select * from kp_proxy where sogou=0 order by itemid desc limit 1;";
							    	System.out.println(">> getOneSql:"+getOneSql);
							        
							        ArrayList<Object> result = MysqlClass.getOne(conn2, getOneSql);
							        
		
							        
									System.out.println(">> getOneResult:"+result);
									int itemid_;
									if(result.size() > 0) {
										itemid_ = (Integer) result.get(0);
										proxyIpString = (String) result.get(1);
										System.out.println(">> itemid:"+itemid_);
										System.out.println(">> proxyIpString:"+proxyIpString);
										
										
										String updateSql = "update kp_proxy set sogou=1 where itemid="+itemid_+";";
										if(MysqlClass.update(conn2, updateSql)) {
											System.out.println(">> updateSql:"+updateSql);
											break;
										}else {
											OtherClass.getRandomTimeSleep(">> updateSql error.");
										}
										
									}else{
										OtherClass.getRandomTimeSleep(">> ProxyIpString get error");
									}
								
							    }catch(Exception e) {
							    	e.printStackTrace();
							    }
							    
							    
							    //conn2.close();
							}
		
			        	}catch(Exception e) {
			        		e.printStackTrace();
			        	}
			        	//-------------------- 请求IP结束 -----------------------------
			        	
			        	//开始请求网站数据
			        	
			        	WebDriver driver =  DriverGet.get(Settings.searchEngineSogouPcUrl,proxyIpString);
						
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
						
						
						
		
							
							//获取网页源码，并且格式化掉源码，去掉空白字符
							//String str = String.replaceAll(regex,String);
							String htmlcode = driver.getPageSource();
							htmlcode = htmlcode.replaceAll("\s+", "");
							
							WebElement xiangGuanSouSuo = driver.findElement(By.className("hint"));
							String xiangGuanSouSuoText = xiangGuanSouSuo.getText();
							String[] xiangGuanSouSuoTextArray_ = xiangGuanSouSuoText.split("\n|\r");
							
							//当数组的长度大于0时，说明匹配到了相关搜索结果的关键词
							String aboutSearchKeywordString = "";
							if(xiangGuanSouSuoTextArray_.length > 2) {
								//建立一个新的数据容器用于储存数据
								List<String> xiangGuanSouSuoTextArray = new ArrayList<String>();
								
								for(String i:xiangGuanSouSuoTextArray_) {
									String i_ = OtherClass.cleanLine(i);
									if(!OtherClass.getMatcher("相关搜索", i_)) {
										xiangGuanSouSuoTextArray.add(i_);
									}
								}
								
								//遍历输出数据容器里面的数据
								
								countNum = 0;
								for(String i:xiangGuanSouSuoTextArray) {
									countNum += 1;
									//System.out.println(">> countNum:"+countNum+" "+i);
									if(countNum == 1) {
										aboutSearchKeywordString = i+"[pb]";
									}else {
										if(countNum == 2) {
											aboutSearchKeywordString += i;
										}else {
											aboutSearchKeywordString += "[pb]"+i;
										}
										
									}
								}
								
								System.out.println(">> aboutSearchKeywordString:"+aboutSearchKeywordString);
							}
							
							
							//当相关搜索关键词不为0时，将搜索相关关键词插入数据库
							if(aboutSearchKeywordString.length() > 5) {
								String updateSql = "update tp_jiadianweixiu set search_keyword_about_search_keyword=\""+aboutSearchKeywordString+"\",search_keyword_status=1 where itemid="+itemid+";";
								System.out.println(">> updateSql:"+updateSql);
								if(MysqlClass.update(conn, updateSql)) {
									System.out.println(">> Update data success!");
								}else{
									System.out.println(">> Update data error!");
								}
								
							}
							
							
							
							
							
							//System.out.println(htmlcode);
							
							
							
						}catch(Exception e) {
							e.printStackTrace();
						}
			        	driver.quit();
						//driver.close();
		        	}catch(Exception e){
		        		e.printStackTrace();
		        	}
		        	
		        	OtherClass.timeSleep(10000);
			
		        }
		        rs1.close();
		        pstmt1.close();
		        
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    
		    
		    conn.close();
		}
		
	}
    
    
    
}