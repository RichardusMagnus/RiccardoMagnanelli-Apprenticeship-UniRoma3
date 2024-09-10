package app.core;

import java.util.Scanner;

public class IOstream {
	static Scanner scanner;
	
	public static void println(String s) {
		System.out.println(s);
	}
	
	public static void printf(String s) {
		System.out.print(s);
	}
	
	public static String readFromKB() {
		scanner = new Scanner(System.in);
		String s = scanner.nextLine();
		return s;
	}
	
	public static int readInteger() {
		scanner = new Scanner(System.in);
		int x = scanner.nextInt();
		return x;
	}
	
	public static void closeAllScanners() {
		scanner.close();
	}


}
