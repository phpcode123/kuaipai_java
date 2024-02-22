package domain_jucha_whois;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.regex.Pattern;
import java.util.regex.Matcher;



//刷选备案域名步骤：
//1、使用juchaicp备案查询，将全部的备案域名筛选出来
//2、查询历史记录域名
//3、清除掉被墙的记录域名
//4、筛选出高sogou搜录的域名，sogou只要收录，一般都会有排名。
//
//专注于sogou或许会有活路


public class whois {

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
		
		String pat = "<tr.*?>(.*?)</tr>";
		
		Pattern p = Pattern.compile(pat);
		Matcher m = p.matcher(a);
		
		while(m.find()) {
			
			String pat1 = "<spanclass=\"id\">.*?</span></td><td><p>(.*?)</p></td><td><p>.*?</p></td><td><p>.*?</p></td><td><p>.*?</p></td><td>.*?</td><td><p>(.*?)&nbsp;</p></td><td><p>(.*?)&nbsp;</p></td><td><p>(.*?)&nbsp;</p>";
			Matcher m_ = OtherClass.regexReturnMatcher(m.group(0), pat1);
			while(m_.find()) {
				//去掉纯数字域名，此类域名多半是用于灰色，即便是竞价价格也奇高，不如直接去掉省事
				if(!m_.group(1).matches("\\d+.com")) {
					//过滤掉域名年龄小于1年和大于4年的域名
					if(!(Integer.valueOf(m_.group(4)) <= 1) &&  !(Integer.valueOf(m_.group(4)) >4)) {
						System.out.println(""+m_.group(1)+" 注册时间："+m_.group(2)+" 到期时间:"+m_.group(3)+" 域名年龄:"+m_.group(4));
					}
					
			
				}
			}
		}
	}

}
