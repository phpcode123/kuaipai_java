package clean_log;








public class Settings {
	
	//清除掉日志中的蜘蛛，google：GoogleBot ; baidu:baidu; sogou:sogou 
	public static String blackSpiderKeyword = "GoogleBot";
	
	public static String logTxtFileName() {
		if(OtherClass.isLinux()) {
			return "./web_log.log";
		}
		
		if(OtherClass.isWindows()) {
			return "D:\\txt\\web_log.log";
		}
		
		return "file path Error.";
	}
	
	public static String logTxtWriteFileName() {
		if(OtherClass.isLinux()) {
			return "./web_clean_success_log.log";
		}
		
		if(OtherClass.isWindows()) {
			return "D:\\txt\\web_clean_success_log.log";
		}
		
		return "file path Error.";
	}
}
