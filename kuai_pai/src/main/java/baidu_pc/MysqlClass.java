package baidu_pc;



import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;





public class MysqlClass {

	public static Connection getConn() {
	    String driver = Settings.JDBC_DRIVER;
	    String url = Settings.DB_URL;
	    String username = Settings.USER;
	    String password = Settings.PASS;
	    Connection conn = null;
	    try {
	        Class.forName(driver); //classLoader,加载对应驱动
	        conn = (Connection) DriverManager.getConnection(url, username, password);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return conn;
	}
	
	
	//insert
	public static boolean insert(String sql) throws SQLException {
	    Connection conn = MysqlClass.getConn();
	    PreparedStatement pstmt;
	    int i = 0;
	    try {
	    	pstmt = (PreparedStatement)conn.prepareStatement(sql);
	    	i =pstmt.executeUpdate();
	    	pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    conn.close();
	    
	    if(i == 0) {
	    	return false;
	    }else {
	    	return true;
	    }
	}
		
	//update
	public static boolean update(String sql) throws SQLException {
	    Connection conn = MysqlClass.getConn();
	    int i = 0;
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        i = pstmt.executeUpdate();
	        
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    conn.close();
	    
	    if(i == 0) {
	    	return false;
	    }else {
	    	return true;
	    }
	}
	
	public static ArrayList<Object> getOne(String sql) throws SQLException{
		Connection conn = MysqlClass.getConn();
	    PreparedStatement pstmt;
	    ArrayList<Object> result = new ArrayList<Object>();
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        if(rs.next()) {
	        	int itemid = rs.getInt("itemid");
	        	String ip = String.valueOf(rs.getString("ip_port"));
	        	Long timestamp = rs.getLong("timestamp");
	        	String datetime = rs.getString("datetime");
	        	result.add(itemid);
	        	result.add(ip);
	        	result.add(timestamp);
	        	result.add(datetime);
	        }
	        rs.close();
	        pstmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    conn.close();
	    return result;
	}
	
	
	//select
	public static List<Object> getAll(String sql) throws SQLException {
	    Connection conn = getConn();
	    PreparedStatement pstmt;
	    
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();

	        while (rs.next()) {

	        }
	        rs.close();
	        pstmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    conn.close();
	    return null;
	}
		
		
	//delete
	public static boolean delete(String sql) throws SQLException {
	    Connection conn = getConn();
	    int i = 0;
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        i = pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    conn.close();
	    
	    
	    if(i == 0) {
	    	return false;
	    }else {
	    	return true;
	    }
	}
	
	
	public static String getOneSql() {
		//Long timestamp = OtherClass.getTimestamp();
		
		String sql;
		//不论是linux还是windows在，获取ip时，最好使用desc降序，这样获取就是最新ip，永远保证高可用。
		//windows下的时区与linux的不一致，设置起来麻烦，如果在windows下直接请求最新的IP即可，只要能保证ip可用就行
		//屏蔽掉时间戳的差异，多台主机和docker容器中的linux时间戳环境异常复杂，肯定会导致有差异，直接使用itemid desc降序，能省不少事，能保证每次抓取的ip都是最新可用的。
		if(OtherClass.isWindows()) {
			sql = "select * from "+Settings.TABLE+" where "+Settings.tableColumn+"=0 order by itemid desc limit 1;";
		}else {
			sql = "select * from "+Settings.TABLE+" where "+Settings.tableColumn+"=0 order by itemid desc limit 1;";
		}
		return sql;
	}
	
	public static String updateSql(int itemid) {
		String sql = "update "+Settings.TABLE+" set "+Settings.tableColumn+"=1 where itemid="+itemid+";";
		return sql;
	}
	
}
