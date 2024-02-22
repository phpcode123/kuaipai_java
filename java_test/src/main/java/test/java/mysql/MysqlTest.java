package test.java.mysql;


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MysqlTest {
	
	
	//connect
	public static Connection getConn() {
	    String driver = "com.mysql.jdbc.Driver";
	    String url = "jdbc:mysql://192.168.0.101:3306/test_db?useSSL=false";
	    String username = "root";
	    String password = "root98765";
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
	public static int insert(Student student) {
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "insert into students (Name,Sex,Age) values(?,?,?)";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setString(1, student.getName());
	        pstmt.setString(2, student.getSex());
	        pstmt.setString(3, student.getAge());
	        i = pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
	
	//update
	public static int update(Student student) {
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "update students set Age='" + student.getAge() + "' where Name='" + student.getName() + "'";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        i = pstmt.executeUpdate();
	        System.out.println("resutl: " + i);
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
	
	
	//select
	
	public static Integer getAll() {
	    Connection conn = getConn();
	    String sql = "select * from students";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        int col = rs.getMetaData().getColumnCount();
	        System.out.println("============================");
	        while (rs.next()) {
	            for (int i = 1; i <= col; i++) {
	                System.out.print(rs.getString(i) + "\t");
	                if ((i == 2) && (rs.getString(i).length() < 8)) {
	                    System.out.print("\t");
	                }
	             }
	            System.out.println("");
	        }
	            System.out.println("============================");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	
	//delete
	public static int delete(String name) {
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "delete from students where Name='" + name + "'";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        i = pstmt.executeUpdate();
	        System.out.println("resutl: " + i);
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
	
	
	public static void main(String[] args) {
	    MysqlTest.getAll();
	    MysqlTest.insert(new Student("Achilles", "Male", "14"));
	    MysqlTest.getAll();
	    MysqlTest.update(new Student("Bean", "", "7"));
	    MysqlTest.delete("Achilles");
	    MysqlTest.getAll();
	    
	}
	
	
	

}
