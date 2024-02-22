package clean_search_keyword_about_search_keyword;


import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;





public class Settings {
	

	//程序主要名称
	public static String mainName = "Read csv file data insert into mysql.";
	
	public static String csvFilePath = "D:\\CSVFile\\clean_search_keyword_about_search_keyword.txt";
	
	public static Boolean ableProxy = false;
	//mysql 
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    public static final String DB_URL = "jdbc:mysql://192.168.0.11:3306/panfa?useSSL=false";
    public static final String USER = "root";
    public static final String PASS = "root98765";
    public static final String TABLE = "linshi_keyword_csv";

    
    //redis
    public static final String REDIS_HOST = "192.168.0.11";
    public static final int REDIS_PORT = 6379;
    //关键词在搜索引擎中最大的失败次数，当页面搜索到第5页时还没有找到此关键词结果，就将此关键词的计数+1，如果总数超过此次值则跳过此关键词，可提高效率
    public static final int MAX_SEARCH_TIMES = 3;

  


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
	public static String linuxChromeDriver = "/usr/bin/chromedriver";
	
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
