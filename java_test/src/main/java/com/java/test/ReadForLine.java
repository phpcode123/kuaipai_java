package com.java.test;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class ReadForLine {
	
//	// 读取文件内容
//	public String readFile(){
//	       String path = "";
//	        File file = new File(path);
//	        StringBuilder result = new StringBuilder();
//	        try{
//	            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));//构造一个BufferedReader类来读取文件
//
//	            String s = null;
//	            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
//	                result.append( System.lineSeparator() + s);
//	            }
//	            br.close();
//	        }catch(Exception e){
//	            e.printStackTrace();
//	        }
//	        return result.toString();
//	 }

	//将关键词文件里面的关键词全部读取到ArrayList中，然后程序遍历ArrayList
	//正常的作用域，将keywordStringList放在try外面即可
	public static List<String> readLineToArrayList(String filePath) {
		File file = new File(filePath);
		
		List<String> keywordStringList = new ArrayList<String>();
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			
			String line = null;
			while (( line = br.readLine()) != null) {// 使用readLine方法，一次读一行
				keywordStringList.add(line);
			}
			br.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return keywordStringList;
		
	}
	
	
	
	
	//将关键词文件里面的关键词全部读取到ArrayList中，然后程序遍历ArrayList
	//作用域错误
	public static List<String> readLineToArrayList01(String filePath) {
		File file = new File(filePath);
		
		
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			List<String> keywordStringList = new ArrayList<String>();
			String line = null;
			while (( line = br.readLine()) != null) {// 使用readLine方法，一次读一行
				keywordStringList.add(line);
			}
			br.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return keywordStringList;
		
	}
	
	
	//将关键词文件里面的关键词全部读取到ArrayList中，然后程序遍历ArrayList
	//异常类错误
	public static List<String> readLineToArrayList02(String filePath) throws Exception {
		File file = new File(filePath);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
		List<String> keywordStringList = new ArrayList<String>();
		String line = null;
		while (( line = br.readLine()) != null) {// 使用readLine方法，一次读一行
			keywordStringList.add(line);
		}
		br.close();

		return keywordStringList;
		
	}

	//将关键词文件里面的关键词全部读取到ArrayList中，然后程序遍历ArrayList
	//异常类错误
	public static List<String> readLineToArrayList03(String filePath) throws IOException {
		File file = new File(filePath);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
		List<String> keywordStringList = new ArrayList<String>();
		String line = null;
		while (( line = br.readLine()) != null) {// 使用readLine方法，一次读一行
			keywordStringList.add(line);
		}
		br.close();

		return keywordStringList;
		
	}
	


	public static void main(String[] args) {

		

	}

}
