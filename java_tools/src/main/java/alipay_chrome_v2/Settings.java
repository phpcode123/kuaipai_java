package alipay_chrome_v2;


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
	public static String mainName = "alipay";
	
	

	//pc端与web端通信api接口密码，此密码必须与web app.php中的配置文件一致
	public static String apiPassword = "123afe2ab6easdadaasd687";
	
	//web_api接口url
	public  static String webApiUrl = "https://pay.wlmini.com/index.php?s=pcnotify/index";
	public  static String webApiStatusCodeUrl = "https://pay.wlmini.com/index.php?s=pcnotify/status_code";
	
	
	//v1是获取jsonurl链接返回的数据，此接口最长稳定时间在两小时左右。
    //v2版本是直接测试请求alipayAccountUrl html代码，直接从html代码中匹配数据   页面关键词"资产明细"
	//alipay url
	public static String alipayHomeUrl = "https://my.alipay.com/portal/i.htm";
	public static String alipayAccountUrl = "https://lab.alipay.com/consume/record/items.htm";
	

	//alipay 获取json数据的url，后面要带cookie中的ctoken，数据采用post方式发送
	//public static String getJsonDataUrl = "https://mbillexprod.alipay.com/enterprise/fundAccountDetail.json";
	
	
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
    public static String tableColumn = "sogou";
    
    
    
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
	
	
	//系统自动根据win和linux环境切换目录
	public static String extendsBasePath() {
		
		//linux
		if(OtherClass.isLinux()) {
			String filePath;
			if(isPc) {
				filePath = "/kuaipai/extends"+File.separator+"pc"+File.separator;
			}else {
				filePath = "/kuaipai/extends"+File.separator+"m"+File.separator;
			}
			
			return filePath;
		}
		//windows
		if(OtherClass.isWindows()) {
			String filePath;
			if(isPc) {
				filePath = "d:\\kuaipai\\extends"+File.separator+"pc"+File.separator;
			}else {
				filePath = "d:\\kuaipai\\extends"+File.separator+"m"+File.separator;
			}
			return filePath;
		}
		
		return "System.getProperty error.";
	}
	
	//chrome extends chrome插件，来处理指纹等相关信息
    //Webgl图像指纹：WebGL Fingerprint Defender
    //Canvas画布指纹： Canvas Fingerprint Defender
    //音频指纹：AudioContext Fingerprint Defender
    //Canvas字体指纹：xxx
    //css字体指纹：Font Fingerprint Defender
	public static String webglCrxFilePath = Settings.extendsBasePath()+"webgl-fingerprint-defende.crx";
	public static String audioContentCrxFilePath = Settings.extendsBasePath()+"audiocontext-fingerprint.crx";
	public static String fontCrxFilePath = Settings.extendsBasePath()+"font-fingerprint-defender.crx";
	public static String canvasCrxFilePath = Settings.extendsBasePath()+"canvas-fingerprint-defend.crx";
	public static String webrtcControlFilePath = Settings.extendsBasePath()+"webrtc-control.crx";
	
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
