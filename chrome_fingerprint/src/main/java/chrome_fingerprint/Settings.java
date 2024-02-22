package chrome_fingerprint;

import java.io.File;

public class Settings {
	

	
	//代理隧道设置01
//	public static String proxyHost = "tps132.kdlapi.com";
//	public static int proxyPort = 15818;
//	public static String proxyUser = "t11620533622167";
//	public static String proxyPass = "klo66i77";
	
	//代理隧道设置 02
	public static String proxyHost = "tps138.kdlapi.com";
	public static int proxyPort = 15818;
	public static String proxyUser = "t12026483007967";
	public static String proxyPass = "308equk0";
	//mysql 
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    public static final String DB_URL = "jdbc:mysql://192.168.0.101:3306/click_middleware?useSSL=false";
    public static final String USER = "root";
    public static final String PASS = "root98765";
    public static final String TABLE = "java_proxy_queue_02";
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
	
	
	//linux
	//关键词目录 //隐藏指纹
//	public static String keywordFilePath = "/Backups/java/";
//	public static String classPathJsPath = "/Backups/java/stealth.min.js";
	
	//windows
	public static String keywordFilePath = "D:\\test\\";
	public static String classPathJsPath = "D:\\test\\stealth.min.js";
	public static String fingerPrintJsPath = "D:\\test\\fingerprint.js";
	
	//chrome extends chrome插件，来处理指纹等相关信息
    //Webgl图像指纹：WebGL Fingerprint Defender
    //Canvas画布指纹： Canvas Fingerprint Defender
    //音频指纹：AudioContext Fingerprint Defender
    //Canvas字体指纹：xxx
    //css字体指纹：Font Fingerprint Defender
	
	public static String webglCrxFilePath = "D:\\test\\extends\\webgl-fingerprint-defende.crx";
	public static String audioContentCrxFilePath = "D:\\test\\extends\\audiocontext-fingerprint.crx";
	public static String fontCrxFilePath = "D:\\test\\extends\\font-fingerprint-defender.crx";
	public static String canvasCrxFilePath = "D:\\test\\extends\\canvas-fingerprint-defend.crx";
	
	
	//输出的zip代理插件的路径格式，程序在当前页面输出即可
	public static String pluginZipFilePath = "."+File.separator + "";
	
	
	
	
}
