package alipay_chrome;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.Cookie;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;



public class OtherClass {
	
	//random num 取值范围为0-maxNum，包括0但是不包括maxNum。如：maxNum=10，取值就为0-9
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
	
	
	//getMatcherString
	public static String getMatcherString(String regex, String source){
		Pattern pat = Pattern.compile(regex);
		Matcher mat = pat.matcher(source);
		String str = "";
		if(mat.find()){
			str = mat.group(1);
		}
		return str;
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
	
	
	//use cn.hutool.core.io.file.FileReader; readLines()
	//关键词使用HashSet模式读入可以去重，可以乱序，这样所有的关键词就都有机会点击到
	public static HashSet<String> readLinesToHashSet(String filePath) {
		HashSet<String> stringList = new HashSet<String>();
		
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
	
	

	public static final byte[] readInputStream(InputStream inputStream) {
		ByteArrayOutputStream byteData = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int readBytesLength;
        try {
            while ((readBytesLength = inputStream.read(buffer)) != -1) {
                byteData.write(buffer, 0, readBytesLength);
            }
        } catch (Exception e) {
            
            return null;
        }
        return byteData.toByteArray();
	}
	
//	//加载隐藏selenium指纹js
//	public static String readStealthMinJs() {
//		String classPathJs = Settings.classPathJsPath;
//		try {
//			File jsFile = new File(classPathJs);
//			FileInputStream fin = new FileInputStream(jsFile);
//			byte[] data = OtherClass.readInputStream(fin);
//			return new String(data);
//		} catch (Exception e) {
//			System.out.println(">> readSteadthMinJs error:"+e);
//		}
//		return null;
//	}
	
	public static Long getTimestamp() {
		Date date = new Date();
		Long timestamp = (Long)(date.getTime()/1000);
		return timestamp;
	}
	
	public static void getRandomTimeSleep(String errorStr) {
		int timeSleep = OtherClass.getRandomBetweenNumbers(1, 3);
		System.out.println(errorStr+".timeSleep("+timeSleep+")");
		OtherClass.timeSleep(timeSleep);
	}
	
	
	public static boolean isLinux() {
		if(System.getProperty("os.name").toLowerCase().contains("linux")) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean isWindows() {
		if(System.getProperty("os.name").toLowerCase().contains("win")) {
			return true;
		}else {
			return false;
		}
	}
	
	
	
	public static void killChrome() {
		//如果是linux系统，在运行时先执行就killChrome方法，避免之前因为种种异常导致Chrome进程没有正常退出
		if(OtherClass.isLinux()) {
			 ProcessBuilder processBuilder = new ProcessBuilder();
		    // -- Linux --
		    // Run a shell command
		    processBuilder.command("bash", "-c", "kill -9 `ps -ef|grep \"chrome\" | grep -v \"grep\"|awk '{print $2}'`");
		    // Run a shell script
		    //processBuilder.command("path/to/hello.sh");
	
		    // -- Windows --
		    // Run a command
		    //processBuilder.command("cmd.exe", "/c", "dir C:\\Users\\mkyong");
		    // Run a bat file
		    //processBuilder.command("C:\\Users\\mkyong\\hello.bat");
	
		    try {
	
		        Process process = processBuilder.start();
		        StringBuilder output = new StringBuilder();
		        BufferedReader reader = new BufferedReader(
		        new InputStreamReader(process.getInputStream()));
	
		        String line;
		        while ((line = reader.readLine()) != null) {
		            output.append(">> "+line);
		        }
	
		        int exitVal = process.waitFor();
		        if (exitVal == 0) {
		            System.out.println(">> Stop chrome process success!");
		            //System.out.println(output);
		            //System.exit(0);
		        } else {
		            //abnormal...
		        }
	
		    } catch (IOException e) {
		        e.printStackTrace();
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
		}
	}
	
	
	//之前使用的是下标随机算法，这种算法越到后面越低效，比如只有一个下标为2的没有调用，系统会一直无限循环下去，直至随机数值是2
	//改进算法：将所有的keyword长度下标数字加进一个数组中，随机调用这个数组中的下标数字，调用一次就删除，然后用获得的下标数字去调用系统关键词中的数组。
	//注意，下标数字数组只能是String类型，如果是int类型，使用List.remove()时会打架。
	
	public static ArrayList<String> keywordIndexNumSet(int arrayListSize){
		ArrayList<String> numSet = new ArrayList<String>();
		int countNum = 0;
		while(countNum < arrayListSize) {
			numSet.add(String.valueOf(countNum));
			countNum += 1;
		}
		return numSet;
	}
	
	
	public static String getSearchKeywordFromList(ArrayList<String> keywordIndexNumList, ArrayList<String> keywordList) {
		//keywordIndexNumList 随机获取一个下标
		int keywordIndexListIndexNum = OtherClass.randomNum(keywordIndexNumList.size());
		//keywordIndexNumList 通过下标取String类型的num值
		String keywordIndexNum = keywordIndexNumList.get(keywordIndexListIndexNum);
		//keywordIndexNumList 将此String类型的num值从list中移除
		keywordIndexNumList.remove(keywordIndexNum);
		//通过将String 类型的索引值转为int类型去调用keywordList中的值
		String searchKeyword = keywordList.get(Integer.valueOf(keywordIndexNum));
		
		return searchKeyword;
		
	}

	
	public static void getSearchKeywordFromListTest(ArrayList<String> keywordIndexNumList, ArrayList<String> keywordList) {
		//实测此算法有效
		while(keywordIndexNumList.size() > 0) {
			//keywordIndexNumList 随机获取一个下标
			int keywordIndexListIndexNum = OtherClass.randomNum(keywordIndexNumList.size());
			//keywordIndexNumList 通过下标取String类型的num值
			String keywordIndexNum = keywordIndexNumList.get(keywordIndexListIndexNum);
			//keywordIndexNumList 将此String类型的num值从list中移除
			keywordIndexNumList.remove(keywordIndexNum);
			//通过将String 类型的索引值转为int类型去调用keywordList中的值
			String searchKeyword1 = keywordList.get(Integer.valueOf(keywordIndexNum));
			System.out.println(">> keywordIndexListIndexNum:"+keywordIndexListIndexNum);
			System.out.println(">> keywordIndexNum:"+keywordIndexNum);
			System.out.println(">> keywordIndexNumList:"+keywordIndexNumList);
			System.out.println(">> seacrchkeyword1:"+searchKeyword1);
		}
		
	}
	
	
	public static String getDateTime() {
		Date dNow = new Date( );
	    SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
	 
	    return ft.format(dNow);
	}
	
	
	public static String stringReplaceAll(String regex, String subStr, String str) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		String newStr = m.replaceAll((subStr));
		
		return newStr;
	}
	
	
	//返回cookis的String参数
	public static String getCookieString(Set<Cookie> cookies) {
		String a = "";
		for(Cookie i : cookies) {	
			String b = OtherClass.stringReplaceAll("path.*?;", "",i.toString());
			//注意com,后面有一个逗号，此逗号是数组的分割符，并不是连在一起的字符串，这也是为什么总是匹配不到带逗号运算符的原因。
			b = OtherClass.stringReplaceAll("domain.*?com", "",b);
			b = OtherClass.stringReplaceAll("expires.*?;", "",b);
			b = OtherClass.stringReplaceAll("\s+","",b);
			
			a += b;
		}
		
		return a;
	}
	
	
	public static Map<String, Object> jsonObjectToMap(Object data){
		JSONObject resultObject = JSONUtil.parseObj(data);
		Map<String, Object>  resultMap = JSONUtil.toBean(resultObject, Map.class, false);
		
		return resultMap;
	}

	
}
