package baidu_pc;


import java.io.File;

public class Settings {
	
	//程序版本
	public static String version = "Version 1.0.0 20220709";
		
	//程序主要名称  如果是移动端就改为baidu_m， 搜狗的就改成sogou_pc或者sogou_m
	public static String mainName = "baidu_pc";
	
	//api 接口地址和url  结尾不要"/"
	public static String  apiHost = "http://192.168.0.11:8089";
	
	//web api相关设置，不要随意更改
	public static String  apiPassword = "a8S5a6duAs9";
	public static String  getKeywordUrl= "/keyword.txt";
	public static String  getDomainUrl= "/pc_url.txt";
	public static String  getIpUrl= "/index.php?s=index/get_ip/index";
	public static String  setRankUrl= "/index.php?s=index/set_rank/index";
	public static String  updateClickUrl= "/index.php?s=index/update_click/index";
	public static String  updateHostActiveStatusUrl= "/index.php?s=index/update_host_active_status/index";
	public static String  updateHostClickStatusUrl= "/index.php?s=index/update_host_click_status/index";
	
	
	
	
	
	
	
	
	//是否禁止下载图片 true为禁止，false为不禁止
	public static boolean disableLoadImage = false;
	//是否不是使用代理
	public static boolean ableProxy = true;
	//是否隐藏指纹
	public static boolean hideFingerprint = true; 
	
	

	

	//setting 以下设置不要随意更改
    public static String searchWebsite;//需要点击搜索的站点，程序运行后使用参数输入的方式确认点击

    //百度查找分页时最大分页数 
	public static int maxBaiduPageNum = 5;
	
	public static String searchEngineBaiduPcUrl = "http://www.baidu.com/";
	public static String searchEngineBaiduMUrl = "http://m.baidu.com/";
	public static String searchEngineSogouPcUrl = "https://www.sogou.com/";
	public static String searchEngineSogouMUrl = "https://m.sogou.com/";
	
	
	public static String baseWindowsPath = "/kuaipai"+File.separator+mainName+File.separator;
	

	//关键词
	public static String keywordFilePath = Settings.baseWindowsPath;

	
	
	//winChromePath
	public static String winChromePath = "/kuaipai/OwlBrowser-1.1.4.3\\chrome.exe";
	
	
	//chromedriver path
	public static String winChromeDriver = "/kuaipai/chromedriver_win32\\chromedriver.exe";
	
	

}
