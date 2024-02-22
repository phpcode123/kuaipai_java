package com.java.test;


import java.util.ArrayList;
import java.util.zip.*;
import java.io.*;


public class ZipTest {

	//快代理的http代理接口用户名和密码
	private static String proxyHost = "tps132.kdlapi.com";
	private static String proxyPort = "15818";
	private static String proxyUser = "t11620533622167";
	private static String proxyPass = "klo66i77";
	//-------------------------------- Setting end ---------------------------------------------

	
	public static void fileWriter(String str, String fileName) throws IOException{
		File f = new File(fileName);
		Writer out = new FileWriter(f,false);
		out.write(str);
		out.close();
	}

	
	//压缩zip文件
	public static void zipOutput(String zipFileName, ArrayList<String> filePathList) throws IOException {
		
		File zipFile = new File(zipFileName);
		//设置压缩流对象
		ZipOutputStream zipOut =  null;
		zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
		InputStream input  = null;
		
		for(String fileName : filePathList) {
			File file = new File(fileName);
			input = new FileInputStream(fileName);
			zipOut.putNextEntry(new ZipEntry(file.getName()));
			int temp = 0;
			while((temp = input.read()) != -1) {
				zipOut.write(temp);
			}
			input.close();
		}
		zipOut.close();
		
		System.out.println("zipOutPut Success!");
	}

	
	public static void createProxyAuthExtension(String proxyHost, String proxyPort, String proxyUsername, String proxyPassword) throws IOException {
		
		String scheme = "http";
		String pluginPath = "d:\\test\\"+proxyUsername+"_"+proxyPassword+"@chrome_proxyauth_plugin.zip";
		
		String manifestJson  = new StringBuilder()
								.append("{\r\n"
										+ "            \"version\": \"1.0.0\",\r\n"
										+ "            \"manifest_version\": 2,\r\n"
										+ "            \"name\": \"Proxy\",\r\n"
										+ "            \"permissions\": [\r\n"
										+ "                \"proxy\",\r\n"
										+ "                \"tabs\",\r\n"
										+ "                \"unlimitedStorage\",\r\n"
										+ "                \"storage\",\r\n"
										+ "                \"<all_urls>\",\r\n"
										+ "                \"webRequest\",\r\n"
										+ "                \"webRequestBlocking\"\r\n"
										+ "            ],\r\n"
										+ "            \"background\": {\r\n"
										+ "                \"scripts\": [\"background.js\"]\r\n"
										+ "            },\r\n"
										+ "            \"minimum_chrome_version\":\"22.0.0\"\r\n"
										+ "        })\r\n"
										+ "		").toString();
		
		String backgroundJs = new StringBuilder()
								.append("var config = {\r\n"
										+ "            mode: \"fixed_servers\",\r\n"
										+ "            rules: {\r\n"
										+ "                singleProxy: {\r\n"
										+ "                    scheme: \""+scheme+"\",\r\n"
										+ "                    host: \""+proxyHost+"\",\r\n"
										+ "                    port: parseInt("+proxyPort+")\r\n"
										+ "                },\r\n"
										+ "                bypassList: [\"foobar.com\"]\r\n"
										+ "            }\r\n"
										+ "          };\r\n"
										+ "\r\n"
										+ "        chrome.proxy.settings.set({value: config, scope: \"regular\"}, function() {});\r\n"
										+ "\r\n"
										+ "        function callbackFn(details) {\r\n"
										+ "            return {\r\n"
										+ "                authCredentials: {\r\n"
										+ "                    username: \""+proxyUsername+"\",\r\n"
										+ "                    password: \""+proxyPassword+"\"\r\n"
										+ "                }\r\n"
										+ "            };\r\n"
										+ "        }\r\n"
										+ "\r\n"
										+ "        chrome.webRequest.onAuthRequired.addListener(\r\n"
										+ "            callbackFn,\r\n"
										+ "            {urls: [\"<all_urls>\"]},\r\n"
										+ "            ['blocking']\r\n"
										+ "        );").toString();
		
		//创建两个压缩文件
		//System.out.println(">>> manifestJson:"+manifestJson);
		//System.out.println(">>> backgroundJs:"+backgroundJs);
		ZipTest.fileWriter(manifestJson, "manifest.json");
		ZipTest.fileWriter(backgroundJs, "background.js");
		
		ArrayList<String> filePathList = new ArrayList<String> ();
		filePathList.add("manifest.json");
		filePathList.add("background.js");
		
		ZipTest.zipOutput(pluginPath, filePathList);
		
		
		//删除json和js文件
		for(String fileName : filePathList) {
			File file = new File(fileName);
			if(file.exists()) {
				file.delete();
				System.out.println(">>> File delete:"+fileName);
			}
		}
		
	}
	
	
	public static void main(String[] args) {
		try {
			ZipTest.createProxyAuthExtension(ZipTest.proxyHost, ZipTest.proxyPass, ZipTest.proxyPort, ZipTest.proxyUser);
		}catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println(">>> Running success!!");
		
		
	}

}
