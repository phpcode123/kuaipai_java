package domain_jucha_weizhuce;








public class Settings {
	
	

	public static String tableContentFileName() {
		if(OtherClass.isLinux()) {
			return "./whois_table.txt";
		}
		
		if(OtherClass.isWindows()) {
			return "D:\\txt\\whois_weizhuce_table.txt";
		}
		
		return "file path Error.";
	}
}
