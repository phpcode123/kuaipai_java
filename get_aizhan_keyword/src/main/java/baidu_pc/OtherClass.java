package baidu_pc;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;

/*import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
*/
@Slf4j
public class OtherClass {
	
	//random num
	public static int randomNum(int maxNum){
		Random random = new Random();
		return random.nextInt(maxNum);
	}
	
	//get random num between numbers
	public static int getRandomBetweenNumbers(int m,int n){     
	    return (int)(m + Math.random() * (n - m + 1));
	}
	
	//timesleep
	public static void timeSleep(int num) {
		try {
			Thread.sleep(num * 1000);
		} catch (InterruptedException e) {	
			e.printStackTrace();
		}
	}
	

	
	
	//getMatchers
	public static List<String> getMatchers(String regex, String source){
		Pattern pat = Pattern.compile(regex);
		Matcher mat = pat.matcher(source);
		List<String> list = new ArrayList<String>();
		while(mat.find()) {
			list.add(mat.group());
		}
		return list;
	}
	
	//getMatcher
	public static Boolean getMatcher(String regex, String source){
		Pattern pat = Pattern.compile(regex);
		Matcher mat = pat.matcher(source);
		List<String> list = new ArrayList<String>();
		while(mat.find()) {
			list.add(mat.group());
		}
		if(list.size() > 0) {
			return true;
		}else {
			return false;
		}
		
	}
	//url mat.group(1) href=\"/Win10/2019-02-19/12726.html\"
	//mat.group()  href="/Win10/2019-02-19/12726.html"
	//mat.group(1) /Win10/2019-02-19/12726.html"
	public static List<String> findAllLinkurl (String regex, String source){
		Pattern pat = Pattern.compile(regex);
		Matcher mat = pat.matcher(source);
		List<String> list = new ArrayList<String>();
		while(mat.find()) {
			list.add(mat.group(1));
		}
		return list;
	}
	
	
	//将相对url匹配成绝对url
	public static String absoluteURL(String absolutePath, String relativePath){
		URL absoluteUrl = null;
		try {
			absoluteUrl = new URL(absolutePath);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}

		URL aUrl = null;
		try {
			aUrl = new URL(absoluteUrl,relativePath);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return aUrl.toString();
		

	}
	
	
	//use cn.hutool.core.io.file.FileReader; readLines()
	public static List<String> readLines(String filePath) {
		List<String> stringList = new ArrayList<String>();
		
		File file = new File(filePath);
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			
			String line = null;
			while (( line = br.readLine()) != null) {
				String cleanLine = OtherClass.cleanLine(line);
				stringList.add(cleanLine);
			}
			br.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return stringList;
		
	}

	
	/*
	 * public static String httpGet(String url) { RestTemplate template = new
	 * RestTemplate(); ResponseEntity<String> entity = template.getForEntity(url,
	 * String.class); return entity.getBody(); }
	 */
	 

	
	public static String cleanLine(String str) {
		String line = str.replaceAll("[\r|\n]", "");
		return line;
	}
	
	public static String cleanHtml(String str) {
		String line = str.replaceAll("\\s+", "");
		return line;
	}

	public static final byte[] readInputStream(InputStream inputStream) {
		ByteArrayOutputStream byteData = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int readBytesLength;
        try {
            while ((readBytesLength = inputStream.read(buffer)) != -1) {
                byteData.write(buffer, 0, readBytesLength);
            }
        } catch (Exception e) {
            log.warn("read inputstream", e);
            return null;
        }
        return byteData.toByteArray();
	}
	
	//加载隐藏selenium指纹js
	public static String readStealthMinJs() {
		String classPathJs = Settings.classPathJsPath;
		try {
			File jsFile = new File(classPathJs);
			FileInputStream fin = new FileInputStream(jsFile);
			byte[] data = OtherClass.readInputStream(fin);
			return new String(data);
		} catch (Exception e) {
			System.out.println(">> readSteadthMinJs error:"+e);
		}
		return null;
	}

}
