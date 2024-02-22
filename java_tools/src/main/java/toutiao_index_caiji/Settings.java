package toutiao_index_caiji;


import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;






public class Settings {
	
	//程序版本
	public static String version = "1.0";
	
	//客户端是PC还是移动
	public static Boolean isPc = true;
	
	//程序主要名称
	public static String mainName = "baidu_pc";
	
	
	//ChromeDriver是否禁止下载图片 true为禁止，false为不禁止
	public static boolean disableLoadImage = false;
	//是否不是使用代理
	public static boolean ableProxy = false;
	//是否隐藏指纹
	public static boolean hideFingerprint = true; 
	
	
	//mysql 
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    public static final String DB_URL = "jdbc:mysql://192.168.0.101:3306/kuaipai?useSSL=false";
    public static final String USER = "root";
    public static final String PASS = "root98765";
    public static final String TABLE = "kp_proxy";
    //代理数据库中的时间戳，如果为3则代理ip的时效为5-3=2分钟，正常刷一个网页90S左右够用。
    public static int maxMinuteNum = 3;
    //kp_proxy表中的项，请求时请求此项为0的项，更新时，将此项更新为1
    public static String tableColumn = "baidu";
    
    
    
    //redis
    public static final String REDIS_HOST = "192.168.0.101";
    public static final int REDIS_PORT = 6379;
    //关键词在搜索引擎中最大的失败次数，当页面搜索到第5页时还没有找到此关键词结果，就将此关键词的计数+1，如果总数超过此次值则跳过此关键词，可提高效率
    public static final int MAX_SEARCH_TIMES = 3;

	

	//setting 以下设置不要随意更改
    public static String searchWebsite;//需要点击搜索的站点，程序运行后使用参数输入的方式确认点击
    
    //用来降低web点击跳出率，网页至少都能点击一次，如果跳出率过高就将0改为1，如果跳出率过低就将1改为0，10个数字每个数字比重都是10%，随机取值.设置为2是为了适当有小部分增加pv，点击页面3次
    //PV无用，就是统计后台数据好看些，实则对快排排名作用不大
    public static int[] openUrlNumArray = new int[]{0,0,0,0,0,1,1,1,2,2};
    
    //在搜索引擎的搜索找到一个特殊的字符串，如果匹配到了此值，说明搜索首页打开状态为正常，避免输出大量的HTML影响数据分析结果。
    public static String regexSearchEngineStr = "语音搜索";
    
	public static int openUrlMinSleepTime = 5;//打开自己网站的最小随机暂停数，单位秒
	public static int openUrlMaxSleepTime = 20;//打开自己网站的最大随机暂停数，单位秒 三个页面随机暂停秒数，将值平均起来
	public static int maxBaiduPageNum = 5;//百度查找分页时最大分页数 
	
	public static String searchEngineBaiduPcUrl = "http://www.baidu.com/";
	public static String searchEngineBaiduMUrl = "http://m.baidu.com/";
	public static String searchEngineSogouPcUrl = "https://www.sogou.com/";
	public static String searchEngineSogouMUrl = "https://m.sogou.com/";
	
	
	public static String baseWindowsPath = "D:\\kuaipai"+File.separator+mainName+File.separator;
	public static String baseLinuxPath = "/kuaipai/"+Settings.mainName+File.separator;
	
	//系统自动根据win和linux环境切换目录
	public static String BasePath() {
		
		//linux
		if(OtherClass.isLinux()) {
			return Settings.baseLinuxPath;
		}
		//windows
		if(OtherClass.isWindows()) {
			return Settings.baseWindowsPath;
		}
		
		return "System.getProperty error.";
	}
	
	//关键词
	public static String keywordFilePath = Settings.BasePath();

	
	//读取域名列表pc_domian.txt
	public static List<String> domainList(){
		List<String> domainList = OtherClass.readLines(Settings.BasePath()+"pc_domain.txt");
		return domainList;
	}
	
	//读取pc的点击关键词
	public static ArrayList<String> clickKeywordList(){
		HashSet<String> keywordList = OtherClass.readLinesToHashSet(Settings.BasePath()+"pc_keyword.txt");
		ArrayList<String> arrayList = new ArrayList<String>();
		for(String i : keywordList) {
			if(!i.equals("")) {
				arrayList.add(i);
			}
		}
		return arrayList;
	}
	
	
	
	//chromedriver path
	public static String winChromeDriver = "C:\\Program Files\\chromedriver_win32\\chromedriver.exe";
	public static String linuxChromeDriver = "/kuaipai/chromium/chromedriver";
	
	public static String getChromeDriverPath() {
		if(OtherClass.isLinux()) {
			return Settings.linuxChromeDriver;
		}

		if(OtherClass.isWindows()) {
			return Settings.winChromeDriver;
		}
		
		return "ChromeDriver path error.";
	}
}
