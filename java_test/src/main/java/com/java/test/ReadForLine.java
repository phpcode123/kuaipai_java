package com.java.test;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class ReadForLine {
	
//	// ��ȡ�ļ�����
//	public String readFile(){
//	       String path = "";
//	        File file = new File(path);
//	        StringBuilder result = new StringBuilder();
//	        try{
//	            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));//����һ��BufferedReader������ȡ�ļ�
//
//	            String s = null;
//	            while((s = br.readLine())!=null){//ʹ��readLine������һ�ζ�һ��
//	                result.append( System.lineSeparator() + s);
//	            }
//	            br.close();
//	        }catch(Exception e){
//	            e.printStackTrace();
//	        }
//	        return result.toString();
//	 }

	//���ؼ����ļ�����Ĺؼ���ȫ����ȡ��ArrayList�У�Ȼ��������ArrayList
	//�����������򣬽�keywordStringList����try���漴��
	public static List<String> readLineToArrayList(String filePath) {
		File file = new File(filePath);
		
		List<String> keywordStringList = new ArrayList<String>();
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			
			String line = null;
			while (( line = br.readLine()) != null) {// ʹ��readLine������һ�ζ�һ��
				keywordStringList.add(line);
			}
			br.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return keywordStringList;
		
	}
	
	
	
	
	//���ؼ����ļ�����Ĺؼ���ȫ����ȡ��ArrayList�У�Ȼ��������ArrayList
	//���������
	public static List<String> readLineToArrayList01(String filePath) {
		File file = new File(filePath);
		
		
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			List<String> keywordStringList = new ArrayList<String>();
			String line = null;
			while (( line = br.readLine()) != null) {// ʹ��readLine������һ�ζ�һ��
				keywordStringList.add(line);
			}
			br.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return keywordStringList;
		
	}
	
	
	//���ؼ����ļ�����Ĺؼ���ȫ����ȡ��ArrayList�У�Ȼ��������ArrayList
	//�쳣�����
	public static List<String> readLineToArrayList02(String filePath) throws Exception {
		File file = new File(filePath);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
		List<String> keywordStringList = new ArrayList<String>();
		String line = null;
		while (( line = br.readLine()) != null) {// ʹ��readLine������һ�ζ�һ��
			keywordStringList.add(line);
		}
		br.close();

		return keywordStringList;
		
	}

	//���ؼ����ļ�����Ĺؼ���ȫ����ȡ��ArrayList�У�Ȼ��������ArrayList
	//�쳣�����
	public static List<String> readLineToArrayList03(String filePath) throws IOException {
		File file = new File(filePath);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
		List<String> keywordStringList = new ArrayList<String>();
		String line = null;
		while (( line = br.readLine()) != null) {// ʹ��readLine������һ�ζ�һ��
			keywordStringList.add(line);
		}
		br.close();

		return keywordStringList;
		
	}
	


	public static void main(String[] args) {

		

	}

}
