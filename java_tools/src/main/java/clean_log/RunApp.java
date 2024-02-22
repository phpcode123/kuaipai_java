package clean_log;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;




public class RunApp {

	public static void main(String[] args) throws IOException {
		

		FileReader fr = new FileReader(Settings.logTxtFileName());
		BufferedReader br = new BufferedReader(fr);
		String line ="";
		int countNum = 0;
		FileWriter fw = new FileWriter(Settings.logTxtWriteFileName());
		BufferedWriter bw = new BufferedWriter(fw);
		while((line = br.readLine()) != null) {
			countNum += 1;
			
			
			String pat = Settings.blackSpiderKeyword;
			Pattern p = Pattern.compile("(?i)"+pat);
			Matcher m = p.matcher(line);
			
			
			if(!m.find()) {
				System.out.println(">> countNum:"+String.valueOf(countNum)+" line:"+line);
				//OtherClass.timeSleep(2);
				
				bw.write(line+"\n");
				
				
				
			}
			
			
			
		}
		
		fw.close();
		fr.close();

		System.out.println("Running success！");
	}

}
