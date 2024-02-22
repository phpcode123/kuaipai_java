package get_proxy_ip;


import java.io.IOException;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import cn.hutool.http.HttpUtil;



public class GetProxyIp {

	public static void main(String[] args) throws IOException,SQLException {
		
		
		
		while(true) {
			
			String htmlCode = null;
			while(true) {
				htmlCode= HttpUtil.get(Settings.apiUrl);

				
				if(OtherClass.getMatcher("errno", htmlCode)) {
					System.out.println(">> Get ip error, timesleep("+Settings.sleepTime+") "+htmlCode);
					OtherClass.timeSleep(Settings.sleepTime);
					
				}else {
					break;
				}
				
			}


	
			String[] ipList = htmlCode.split("\r\n");
			Connection conn = MysqlClass.getConn();
			PreparedStatement pstmtInsert = null;
     		
     		//遍历ip列表
     		for(String ip :ipList) {
     			String timestamp = String.valueOf(OtherClass.getTimestamp());
     			String dateTime = OtherClass.getDateTime();
     			String insertSql = "insert into kp_proxy(ip_port,timestamp,datetime)values('"+ip+"',"+timestamp+",'"+dateTime+"');";
     			System.out.println(">> insertSql:"+insertSql);
     			
     			pstmtInsert = (PreparedStatement)conn.prepareStatement(insertSql);
     			if(pstmtInsert.executeUpdate(insertSql) == 1) {
	        		System.out.println(">> insert success! ");
	        		
	        	}else {
	        		System.out.println(">> insert error! ");
	        	}
	        	
     			
     		}
     		
     		pstmtInsert.close();
     		
     		
     		//删除过期的IP
     		String deleteSql= "delete from kp_proxy where timestamp <"+String.valueOf(OtherClass.getTimestamp() - 60*5)+";";
     		System.out.println(">> "+ deleteSql);
     		PreparedStatement pstmtDelete = (PreparedStatement)conn.prepareStatement(deleteSql);
     		if(pstmtDelete.executeUpdate(deleteSql) == 1) {
        		System.out.println(">> delete success! ");
        		
        	}else {
        		System.out.println(">> delete error! ");
        	}
     		pstmtDelete.close();
     		
     		
			
     		//插入数据完成后暂停时间。
			OtherClass.timeSleep(Settings.sleepTime);

			
		}
	}
}

