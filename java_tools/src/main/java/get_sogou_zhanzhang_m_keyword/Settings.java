package get_sogou_zhanzhang_m_keyword;




public class Settings {
	
	public static String sogouZhanZhangUrl = "https://zhanzhang.sogou.com/index.php/keyword/index?fr=more&sid=19816885";

	
//	end: "2021-10-30"
//	page: 2
//	pageSize: 20
//	query: ""
//	sid: 45196817
//	siteAddress: "m.panduoduo.me"
//	start: "2021-09-30"
//	terminal: "wap"
	public static int maxPageNum = 1002;//根据站长平台的页数来处理


	//ChromeDriver是否禁止下载图片 true为禁止，false为不禁止
	public static boolean disableLoadImage = false;
	//是否不是使用代理
	public static boolean ableProxy = false;
	//是否隐藏指纹
	public static boolean hideFingerprint = true; 

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
