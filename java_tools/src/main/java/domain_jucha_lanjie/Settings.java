package domain_jucha_lanjie;








public class Settings {
	
	

	public static String tableContentFileName() {
		if(OtherClass.isLinux()) {
			return "./lanjie_table.txt";
		}
		
		if(OtherClass.isWindows()) {
			return "D:\\txt\\lanjie_table.txt";
		}
		
		return "file path Error.";
	}
}
