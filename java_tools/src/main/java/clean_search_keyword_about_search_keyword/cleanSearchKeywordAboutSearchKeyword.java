package clean_search_keyword_about_search_keyword;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;



public class cleanSearchKeywordAboutSearchKeyword {
	

	// ---------------------- main begin ----------------------

	public static void main(String[] args) throws IOException, SQLException {
		
		
		List<String> csvFilePathList = OtherClass.readLines(Settings.csvFilePath);
		int countNum =0 ;
		String a = "";
		for(String i: csvFilePathList) {
			System.out.println(">> i:"+i);
			i = OtherClass.cleanLine(i);
			
			if(!i.equals("")) {
				
				//System.out.println(">> i"+i);
				countNum += 1;
				
				if(countNum == 1) {
					a = i;	
				}else {
					a += "[pb]"+i;
				}
				
			}
			
		}
		
		
		System.out.println(">> a: \n"+a);

	}
}
