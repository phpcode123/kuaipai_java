package selenium_linux_pc;

import java.io.File;

public class Settings {
	
	//代理隧道设置 
	public static String proxyHost = "tps132.kdlapi.com";
	public static int proxyPort = 15818;
	public static String proxyUser = "t11620533622167";
	public static String proxyPass = "klo66i77";
	
	//mysql 
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    public static final String DB_URL = "jdbc:mysql://192.168.0.101:3306/click_middleware?useSSL=false";
    public static final String USER = "root";
    public static final String PASS = "root98765";
    public static final String TABLE = "java_proxy_queue_01";
    //用户控制代理的表，每新买一个代理隧道就新建一个队列表。单个代理隧道每秒的并发是5个，可供6-8个线程使用。
    //+--------+------------+-------------+-------------+
    //| itemid | timestamp  | queue_times | lock_status |
    //+--------+------------+-------------+-------------+
    //|      1 | 1620175717 |           0 |           0 |
    //+--------+------------+-------------+-------------+
    //共建立了10个表，可供10条代理通道使用，最好在生成的jar后面标上对应的通道表后缀数字。
    //使用记录说明
    //java_proxy_queue_01 panfa.net pc m
    //java_proxy_queue_02 panfa.net pc m
    
    
	//setting 以下设置不要随意更改
    public static String searchWebsite;//需要点击搜索的站点，程序运行后使用参数输入的方式确认点击
	public static int maxOpenUrlNum = 2;//最大打开自己网站的链接个数，随机数如果是0，程序会自动+3，如果是1程序会自动+2，确保每次最小的打开次数为2
	public static int openUrlMinSleepTime = 25;//打开自己网站的最小随机暂停数，单位秒
	public static int openUrlMaxSleepTime = 50;//打开自己网站的最大随机暂停数，单位秒
	public static int maxBaiduPageNum = 5;//百度查找分页时最大分页数 
	
	//关键词
	public static String keywordFilePath = "/Backups/java/";
	//隐藏指纹
	public static String classPathJsPath = "/Backups/java/stealth.min.js";
	//输出的zip代理插件的路径格式，程序在当前页面输出即可
	public static String pluginZipFilePath = "."+File.separator + "";
	
}
