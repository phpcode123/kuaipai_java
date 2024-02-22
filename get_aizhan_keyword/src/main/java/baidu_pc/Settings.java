package baidu_pc;


import java.io.File;
import java.util.List;





public class Settings {
	
	//proxy 代理ip请求url，熊猫代理，每s都可以请求一个代理ip
	public static String proxyApiUrl = "http://route.xiongmaodaili.com/xiongmao-web/api/gbip?secret=20b3bfa5318d56ced2061df2a03e24bd&orderNo=QGB20210602133117GbbG7NSx&count=1&isTxt=1&proxyType=1";
	
	
	//ChromeDriver是否禁止下载图片 true为禁止，false为不禁止
	public static boolean disableLoadImage = false;
	//是否不是使用代理
	public static boolean ableProxy = false;
	//是否隐藏指纹
	public static boolean hideFingerprint = true; 

	

	//setting 以下设置不要随意更改
    public static String searchWebsite;//需要点击搜索的站点，程序运行后使用参数输入的方式确认点击
	public static int maxOpenUrlNum = 3;//最大打开自己网站的链接次数，用于刷pv,当随机值小于1时，设置的次数就为2，最小是两次
	public static int openUrlMinSleepTime = 5;//打开自己网站的最小随机暂停数，单位秒
	public static int openUrlMaxSleepTime = 20;//打开自己网站的最大随机暂停数，单位秒 三个页面随机暂停秒数，将值平均起来
	public static int maxBaiduPageNum = 5;//百度查找分页时最大分页数 
	
	
	//chrome extends chrome插件，来处理指纹等相关信息
    //Webgl图像指纹：WebGL Fingerprint Defender
    //Canvas画布指纹： Canvas Fingerprint Defender
    //音频指纹：AudioContext Fingerprint Defender
    //Canvas字体指纹：xxx
    //css字体指纹：Font Fingerprint Defender
	
	public static String baseWindowsPath = "D:\\kuaipai\\baidu_pc"+File.separator;
	public static String baseLinuxPath = "/kuaipai"+File.separator;
	
	//系统自动根据win和linux环境切换目录
	public static String BasePath() {
		
		//linux
		if(System.getProperty("os.name").toLowerCase().contains("linux")) {
			return Settings.baseLinuxPath;
		}
		//windows
		if(System.getProperty("os.name").toLowerCase().contains("win")) {
			return Settings.baseWindowsPath;
		}
		
		return "System.getProperty error.";
	}
	
	
	public static String webglCrxFilePath = Settings.BasePath()+"extends"+File.separator+"webgl-fingerprint-defende.crx";
	public static String audioContentCrxFilePath = Settings.BasePath()+"extends"+File.separator+"audiocontext-fingerprint.crx";
	public static String fontCrxFilePath = Settings.BasePath()+"extends"+File.separator+"font-fingerprint-defender.crx";
	public static String canvasCrxFilePath = Settings.BasePath()+"extends"+File.separator+"canvas-fingerprint-defend.crx";
	
	
	//关键词
	public static String keywordFilePath = Settings.BasePath();
	//隐藏指纹
	public static String classPathJsPath = Settings.BasePath()+"extends"+File.separator+"stealth.min.js";
	//输出的zip代理插件的路径格式，程序在当前页面输出即可
	public static String pluginZipFilePath = Settings.BasePath()+"extends"+File.separator;
	
	
	
	
	
	//读取域名列表pc_domian.txt
	public static List<String> domainList(){
		List<String> domainList = OtherClass.readLines(Settings.BasePath()+"pc_domain.txt");
		return domainList;
	}
	
	//读取pc的点击关键词
	public static List<String> clickKeywordList(){
		List<String> domainList = OtherClass.readLines(Settings.BasePath()+"pc_keyword.txt");
		return domainList;
	}
	
	//chromedriver path
	public static String winChromeDriver = "C:\\Program Files\\chromedriver_win32\\chromedriver.exe";
	public static String linuxChromeDriver = "/usr/bin/chromedriver";
	
	public static String getChromeDriverPath() {
		if(System.getProperty("os.name").toLowerCase().contains("linux")) {
			return Settings.linuxChromeDriver;
		}

		if(System.getProperty("os.name").toLowerCase().contains("win")) {
			return Settings.winChromeDriver;
		}
		
		return "ChromeDriver path error.";
	}
}
