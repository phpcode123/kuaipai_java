package get_proxy_ip;


public class Settings {

	//Setting
	//熊猫代理的api ip请求网址
	public static final String  apiUrl = "http://route.xiongmaodaili.com/xiongmao-web/api/gbip?secret=20b3bfa5318d56ced2061df2a03e24bd&orderNo=GB20210820095510bTkhnUeS&count=5&isTxt=1&proxyType=1";
	//请求一次后的暂停时间,通常ip代理请求一次是10秒间隔一次，将此值设置为11
	public static final int sleepTime = 11;

	//Mysql 
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    public static final String DB_URL = "jdbc:mysql://192.168.0.101:3306/kuaipai?useSSL=false";
    public static final String USER = "root";
    public static final String PASS = "root98765";
   
    
    
    
}
