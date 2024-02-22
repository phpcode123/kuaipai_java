package com.java.test;

import java.util.Scanner;

public class ScannerTest {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		if (scan.hasNext()) {
            String domain = scan.next();
            System.out.println("domian：" + domain);
        }
        scan.close();
		

	}

}
