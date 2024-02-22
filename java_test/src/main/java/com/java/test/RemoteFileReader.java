package com.java.test;



import cn.hutool.core.io.file.FileReader;
import java.util.List;
public class RemoteFileReader {

	public static void main(String[] args) {
		FileReader remoteFile = new FileReader("http://192.168.0.101:8083/win10.html");
		List<String> fileList = remoteFile.readLines();
		for(String i : fileList) {
			System.out.println(">>> i:" + i);
		}

	}

}
