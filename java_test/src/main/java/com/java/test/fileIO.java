package com.java.test;

import java.io.File;
import java.io.Writer;
import java.io.FileWriter;
import java.io.IOException;

import java.io.Reader;
import java.io.FileReader;

public class fileIO {

	public static void main(String[] args) throws IOException {
		//write
		File f = new File("./test.txt");
		Writer out = new FileWriter(f,false);
		String str = "Hello World!";
		out.write(str);
		out.close();
		System.out.println(">>> Running success!");
		
		//Read
		File f_01 = new File("./test.txt");
		Reader input = new FileReader(f_01);
		char c[] = new char[(int)f_01.length()];
		input.read(c);
		input.close();
		System.out.println(c);
		System.out.println(">>> fileContent:"+String.valueOf(c));

	}

}
