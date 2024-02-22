package elasticsearch_index;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;



public class ElasticSearchIndex {

	// ---------------------- main begin ----------------------

	public static void main(String[] args) throws IOException, SQLException {
		
		System.out.println(">> java -jar UpdateMysqlData.java processNum beginItemidNum");

		//java的args参数是从下标0开始的。
		
		//String processNum = args[0];
		String beginItemidNum= args[0];
		
	
		
		while(true) {
			
			Connection conn = MysqlClass.getConn();
			PreparedStatement pstmt;
			
			//从tp_pan_100开始,读取数据然后插入到tp_pan_100中
			//String getSql;
//			if(Integer.valueOf(processNum) == 1){
//				getSql = "select * from tp_pan_100 where itemid > "+beginItemidNum+" and itemid < 360000 and search_keyword_status=0 order by itemid desc limit 100;";
//			}else {
//				getSql = "select * from tp_pan_100 where itemid > "+beginItemidNum+" and itemid < "+String.valueOf(Integer.valueOf(beginItemidNum) * Integer.valueOf(processNum))+" and search_keyword_status=0 order by itemid desc limit 100;";
//			}
			
			String getSql = "select * from tp_pan_100 where itemid > "+beginItemidNum+" and search_keyword_status=0 order by itemid asc limit 100;";
			
			//String getSql = "select * from tp_pan_100 where itemid > "+beginItemidNum+" and search_keyword_status=0 order by itemid desc limit 100;";
			System.out.println(">> getSql:"+getSql);
			//OtherClass.timeSleep(10);

		    try {
		        pstmt = (PreparedStatement)conn.prepareStatement(getSql);
		        ResultSet rs = pstmt.executeQuery();
		        
		      
		        //如果为空就停止当前程序
		        if(rs.wasNull()) {
		        	OtherClass.timeSleep(10);
		        	break;
		        }
		        
		        while(rs.next()) {

		        	long itemid =rs.getLong("itemid");
		        	String search_keyword = rs.getString("search_keyword");
		        	System.out.println(">> itemid:"+String.valueOf(itemid)+" search_keyword:"+search_keyword);
		        	
		        	
		        	

		        	String[] searchKeywordArray = null;
		        	String keyword;
		        	if(!search_keyword.equals("")) {
		        		searchKeywordArray = search_keyword.split("\\[pb\\]");
		        		keyword = searchKeywordArray[0];
		        	}else {
		        		keyword = "";
		        		
		        	}
		        	
		        	//组装sql
		        	String updateSql;
		        	if(!keyword.equals("")) {
		        		updateSql = "update tp_pan_100 set keyword=\""+keyword+"\", search_keyword_status=1 where itemid="+String.valueOf(itemid)+";";
		        	}else {
		        		updateSql = "update tp_pan_100 set search_keyword_status=2 where itemid="+String.valueOf(itemid)+";";
		        	}
		        	
		     
	   	
		     
		        	//现象：实现一个删除数据库前10条记录的练习时，出现了Operation not allowed after ResultSet closed的错误。
		        	//原因：由于要先进行查询操作，在while中也就是意味着rs还没有关闭，因为使用了next()函数，这时候再执行删除操作时，也就是意味着查询的rs会close,也就是没有查询的rs，就无法执行rs.next()。因此就出现了上面的错误。
		        	//解决：多条操作最好使用多个Statement对象，将查询和删除分开。修改如下：
		        	
		        	
		        	System.out.println(">> updateSql:"+updateSql);
		        	PreparedStatement pstmtUpdate = (PreparedStatement)conn.prepareStatement(getSql);
			        
		        	if(pstmtUpdate.executeUpdate(updateSql) == 1) {
		        		System.out.println(">> update success! "+itemid);
		        		
		        	}else {
		        		System.out.println(">> update error! "+itemid);
		        	}
		        	pstmtUpdate.close();
		        	System.out.println("-----------------------------");
		        	
		        }
		        OtherClass.timeSleep(1);
		        pstmt.close();
		        //System.out.println(">> insert success!");
		        OtherClass.timeSleepByMilliSecond(200);
		    }catch(Exception e){
		    	e.printStackTrace();
		    	OtherClass.timeSleepByMilliSecond(1000);
		    }
		   
		    conn.close();
		    
		    
		}
		System.out.println(">> Running success!");
		
		
	}
}
