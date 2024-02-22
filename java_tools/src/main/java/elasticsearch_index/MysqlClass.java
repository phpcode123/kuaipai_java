package elasticsearch_index;



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
	
	public static Connection getConn(String driver, String db_url, String username, String password) {
	    Connection conn = null;
	    try {
	        Class.forName(driver); //classLoader,加载对应驱动
	        conn = (Connection) DriverManager.getConnection(db_url, username, password);
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
	
	
	//insert
	public static boolean insert(String driver, String db_url, String username, String password, String sql) throws SQLException {
	    Connection conn = MysqlClass.getConn(driver, db_url, username, password);
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
	
	
}
