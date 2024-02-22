package read_table_data_into_other_table;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;



public class ReadTableDataIntoOtherTable {

	// ---------------------- main begin ----------------------

	public static void main(String[] args) throws IOException, SQLException {
		
		
		//System.out.println(">> java -jar xxx.jar tableId DescOrAsc");
		
		//System.out.println(">> args[0]:"+args[0]);
		//System.out.println(">> args[1]:"+args[1]);
		
		// settings 
		//String tableId = args[0];
		//String descOrAsc = args[1];
		
		
		while(true) {
			
			Connection conn = MysqlClass.getConn();
			PreparedStatement pstmt;
			
			//从tp_pan_101开始,读取数据然后插入到tp_pan_100中
			String getSql = "select * from tp_pan_103 where insert_status=0  and itemid > 15866824 order by itemid asc limit 100;";
			
			//Long itemid;
		    try {
		        pstmt = (PreparedStatement)conn.prepareStatement(getSql);
		        ResultSet rs = pstmt.executeQuery();
		        
		        
		        
		        while(rs.next()) {

		        	long itemid =rs.getLong("itemid");
		        	int rand_num = rs.getInt("rand_num");
		        	String file_name = rs.getString("file_name");
		        	file_name = OtherClass.stringReplaceAll("'", "\\\\'", file_name);
		        	int file_from = rs.getInt("file_from");
		        	String sharer = rs.getString("sharer");
		        	sharer = OtherClass.stringReplaceAll("'", "\\\\'", sharer);
		        	String file_size = rs.getString("file_size");
		        	String share_time = rs.getString("share_time");
		        	int hits = rs.getInt("hits");
		        	String include_time = rs.getString("include_time");
		        	String pan_url = rs.getString("pan_url");
		        	String passcode = rs.getString("passcode");
		        	String fromurl = rs.getString("fromurl");
		        	String keyword = rs.getString("keyword");
		        	keyword = OtherClass.stringReplaceAll("'", "\\\\'", keyword);
		        	int timestamp = rs.getInt("timestamp");
		        	int rand_recom_id = rs.getInt("rand_recom_id");
		        	int catid = rs.getInt("catid");
		        	int status = rs.getInt("status");
		        	int xs_status = rs.getInt("xs_status");
		        	int xml_status = rs.getInt("xml_status");
		        	int key_status = rs.getInt("key_status");
		        	int push_num = rs.getInt("push_num");
		        	int sitemap_num = rs.getInt("sitemap_num");
		        	String rand_url_str = rs.getString("rand_url_str");
		        	
		        	
		        	
		        	
		        	//System.out.println(">> itemid:"+itemid+" file_name:"+file_name+" rand_url_str:"+rand_url_str);
		        	
		        	String insertSql = "insert into tp_pan_100("
		        			+ "rand_num,"
		        			+ "file_name,"
		        			+ "file_from,"
		        			+ "sharer,"
		        			+ "file_size,"
		        			+ "share_time,"
		        			+ "hits,"
		        			+ "include_time,"
		        			+ "pan_url,"
		        			+ "passcode,"
		        			+ "fromurl,"
		        			+ "keyword,"
		        			+ "timestamp,"
		        			+ "rand_recom_id,"
		        			+ "catid,"
		        			+ "status,"
		        			+ "xs_status,"
		        			+ "xml_status,"
		        			+ "key_status,"
		        			+ "push_num,"
		        			+ "sitemap_num,"
		        			+ "rand_url_str)VALUES('"
		        			+rand_num+"','"
		        			+file_name+"','"
		        			+file_from+"','"
		        			+sharer+"','"
		        			+file_size+"','"
		        			+share_time+"','"
		        			+hits+"','"
		        			+include_time+"','"
		        			+pan_url+"','"
		        			+passcode+"','"
		        			+fromurl+"','"
		        			+keyword+"','"
		        			+timestamp+"','"
		        			+rand_recom_id+"','"
		        			+catid+"','"
		        			+status+"','"
		        			+xs_status+"','"
		        			+xml_status+"','"
		        			+key_status+"','"
		        			+push_num+"','"
		        			+sitemap_num+"','"
		        			+rand_url_str
		        			+"');";
		        	
		        	//现象：实现一个删除数据库前10条记录的练习时，出现了Operation not allowed after ResultSet closed的错误。
		        	//原因：由于要先进行查询操作，在while中也就是意味着rs还没有关闭，因为使用了next()函数，这时候再执行删除操作时，也就是意味着查询的rs会close,也就是没有查询的rs，就无法执行rs.next()。因此就出现了上面的错误。
		        	//解决：多条操作最好使用多个Statement对象，将查询和删除分开。修改如下：
		        	
		        	
		        	PreparedStatement pstmtInsert = (PreparedStatement)conn.prepareStatement(getSql);
			        
		        	System.out.println(">> insertSql:"+insertSql);
		        	if(pstmtInsert.executeUpdate(insertSql) == 1) {
		        		System.out.println(">> insert success! "+itemid);
		        		
		        	}else {
		        		System.out.println(">> insert error! "+itemid);
		        	}
		        	pstmtInsert.close();
		        	
		        	
		        	
		        	//String updateSql = "update tp_pan_"+tableId+" set insert_status=1 where itemid="+itemid;
		        	String updateSql = "update tp_pan_103 set insert_status=1 where itemid="+itemid;
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
		        
		        pstmt.close();
		        //System.out.println(">> insert success!");
		        OtherClass.timeSleepByMilliSecond(200);
		    }catch(Exception e){
		    	e.printStackTrace();
		    	OtherClass.timeSleepByMilliSecond(1000);
		    }
		   
		    conn.close();
		    
		    
		}
		
		
		
	}
}
