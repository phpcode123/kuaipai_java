package domain_jucha_history;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.regex.Pattern;
import java.util.regex.Matcher;


//筛选清除掉灰色历史记录步骤：
//1、查询域名历史记录，将有灰色记录的域名清除掉
//2、查询有备案记录的域名
//3、查询域名是否被墙
//4、查询域名历史和建站历史再筛选。
//


public class History {

	public static void main(String[] args) throws IOException {
		
		
//		int countNum = 0;
//		for(String i : Settings.titleBlockKeyword) {
//			countNum += 1;
//			System.out.println(">> countNum:"+String.valueOf(countNum)+" i:"+i);
//		}
//		
		//read file return String
		File tableFile = new File(Settings.tableContentFileName());
		InputStream input = new FileInputStream(tableFile);
		byte b[] = new byte[(int)tableFile.length()];
		
		for(int i=0; i<b.length; i++) {
			b[i] = (byte)input.read();
		}
		input.close();
		String fileContent = new String(b);
		//System.out.println(">> fileContent:"+fileContent);
		
		
		String a = OtherClass.cleanLine("[\s|\r|\n]+", fileContent);
		//a = OtherClass.cleanLine("<p>", a);
		//a = OtherClass.cleanLine("</p>", a);
		//System.out.println(">> a:"+a);
		
		String pat = "<trid=\".*?\"><tdclass=\"no-exl\"><label><inputclass=\"boxInfo\"data-plid=\"(.*?)\"data-ym=\"(.*?)\"type=\"checkbox\"value=\"(.*?)\"></label></td><td><spanclass=\"id\">(.*?)</span></td><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)</p></td><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td></tr>";
		
		Pattern p = Pattern.compile(pat);
		Matcher m = p.matcher(a);
		

		while(m.find()) {

			if(m.group(8).contains("中文")) {
				int blockNum = 0;
				for(String i:Settings.titleBlockKeyword) {
					//m.group(2)为域名
					//m.group(6)为标题
					//m.group(7)为域名年龄
					//m.group(8)为标题类别：中文、英文、未知
					//m.group(9)为历史记录数
					//m.group(10)为历史最老记录时间
					//m.group(11)为最新记录时间
					blockNum = 0;
					if(m.group(6).contains(i)) {
						blockNum = 1;
						break;
					}
				}
				if(blockNum == 0) {
					String domainYear = OtherClass.cleanLine("<.*?>",m.group(7));
					String title = OtherClass.cleanLine("<.*?>",m.group(6));
					
					if(Integer.valueOf(domainYear) >= Settings.minYear && Integer.valueOf(domainYear) <= Settings.maxYear) {
						if(OtherClass.getMatcher(String.valueOf(Settings.nearYear), m.group(11))) {
							System.out.println(m.group(2)+" title:"+title);
						}
					}
				}
				
				//System.out.println(">> "+String.valueOf(countNum)+" "+m.group(2));
			}
		}
	}

}
