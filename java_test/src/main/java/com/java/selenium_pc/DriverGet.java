package com.java.selenium_pc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.openqa.selenium.WebDriver;


public class DriverGet {

	public static void driverGet(WebDriver driver,String openUrl) throws SQLException {

		int getOpenUrlStatus = 0;

		Connection conn = null;
        Statement stmt = null;
        try {
        	
			Class.forName(RunAppPc.JDBC_DRIVER);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        conn = DriverManager.getConnection(RunAppPc.DB_URL,RunAppPc.USER,RunAppPc.PASS);
        
    
        

        
        stmt = conn.createStatement();
        String sql;
        sql = "SELECT * FROM java_proxy_queue";
        ResultSet rs = stmt.executeQuery(sql);
        rs.next();
        
        int itemid = rs.getInt("itemid");
		long timestamp = rs.getLong("timestamp");
		int queue_times = rs.getInt("queue_times");
		int lock_status = rs.getInt("lock_status");
		
		rs.close();
        
        
        if(queue_times < 5 && lock_status == 0) {
        	int updateQueueTimes = queue_times + 1;
        	
        	int updateLockStatus = 0;
        	
        	if(updateQueueTimes > 3) {
        		updateLockStatus = 1;
        	}
        	
        	try {
        		//System.out.println(">>> Running 004");
        		String updateSql = "update java_proxy_queue set timestamp="+String.valueOf(System.currentTimeMillis()/1000)+",queue_times="+String.valueOf(updateQueueTimes)+",lock_status="+String.valueOf(updateLockStatus)+" where itemid=1;";
        		System.out.println(">>> updateSql:"+updateSql);
        		stmt.executeUpdate(updateSql);
        		//conn.commit();
        		
        		getOpenUrlStatus = 1;
        		
        	} catch(SQLException e) {
        		e.printStackTrace();
        		//System.out.println(">>> Running 005");
        		System.out.println(">>> update_mysql_error");
        		OtherClass.timeSleep(2);
        		
        	}
        	
        }
 
        Date nowDate = new Date();
        long nowUnixTimestamp = nowDate.getTime();
        
        if(((nowUnixTimestamp - timestamp) > 1 && lock_status == 1) || (nowUnixTimestamp - timestamp) > 5) {
        	try {
        		//System.out.println(">>> Running 006");
        		String newUpdateSql = "update java_proxy_queue set timestamp="+String.valueOf(System.currentTimeMillis()/1000)+",queue_times=0,lock_status=0 where itemid="+String.valueOf(itemid)+";";
        		stmt.executeUpdate(newUpdateSql);
        		System.out.println(">>> newUpdateSql:"+newUpdateSql);
        		//conn.commit();
        	}catch(SQLException e) {
        		e.printStackTrace();
        		System.out.println(">>> update_mysql_error_02");
        		OtherClass.timeSleep(2);
        	}
        }
		
        System.out.println(">>> getOpenUrlStatus:"+String.valueOf(getOpenUrlStatus));
        
        if(getOpenUrlStatus == 1) {
        	try {
        		//System.out.println(">>> Running 007");
        		driver.get(openUrl);
        		System.out.println(">>> openurl:"+driver.getCurrentUrl());
//	        		if(OtherClass.getMatcher("Concurrency Over Limit", driver.getPageSource())) {
//						System.out.println(">>> Concurrency Over Limit");
//					}else {
//						
//						break;
//					}
//	        		System.out.println(">>> Running 007_1");
//					
//					int countNum = 0;
//					while(countNum < 2) {
//						countNum += 1;
//						System.out.println(">>> Running 008");
//						System.out.println(">>> Concurrency Over Limit.countNum:"+String.valueOf(countNum));
//						try {
//							System.out.println(">>> Running 009");
//							DriverGet.driverGet(driver, openUrl);
//							if(OtherClass.getMatcher(">>> Concurrency Over Limit", driver.getPageSource())) {
//								System.out.println(">>> Concurrency Over Limit");
//								OtherClass.timeSleep(2);
//							}else {
//								
//								break;
//							}
//							
//						}catch(Exception e1) {
//							System.out.println(">>> Running 010");
//							System.out.println(">>> open url error,timestamp(2)");
//							OtherClass.timeSleep(2);
//						}
//						System.out.println(">>> Concurrency Over Limit.While");
//					}
        		
        	}catch(Exception e) {
        		//System.out.println(">>> Running 012");
        		e.printStackTrace();
        		System.out.println(">>> driver.getError_03,timestmap(3)");
        		OtherClass.timeSleep(3);
        		//driver.refresh();
        	}
        }else {
        	System.out.println(">>> Running 013");
        	System.out.println(">>> Wait mysql queue , time.sleep(2)");
        	OtherClass.timeSleep(2);
        }
        
        try {
        	//System.out.println(">>> Running 014");
        	stmt.close();
 	        conn.close();
 	        //mysql close
        }catch(SQLException e) {
        	//System.out.println(">>> Running 015");
        	e.printStackTrace();
        }
       

		
		
	}
	

}
