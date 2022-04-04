package ch07.assignment;

import java.util.Scanner;

// example : South Korea has a earned a reputation as a leading global ICT center

public class A01 {

	public static void main(String[] args) {
		A01 a01 = new A01();
		
		String inputString = a01.getString();
		char inputChar = a01.getChar();
		
		int result = checkIndex(inputString, inputChar);
		
		System.out.println(result);
	}
	
	// input a String
	private String getString() {
		System.out.print("input a String : ");
		Scanner sc = new Scanner(System.in);
		String inputString = sc.nextLine();
		return inputString;
	}
	
	// input a character
	private char getChar() {
		System.out.print("input a character : ");
		while(true) {
			Scanner sc = new Scanner(System.in);
			try {
				String inputChar = sc.next();
				if(inputChar.length() != 1) {
					System.out.println("wrong input. input a character.");
					continue;
				} else {
					return inputChar.charAt(0);
				}
			} catch(Exception e) {
				System.out.println("wrong input. input a character.");
			}
		}
	}
	
	// trace index in the String
	private static int checkIndex(String inputString, char inputChar) {
		int index = inputString.indexOf(inputChar);
		return index + 1;
	}
	
}
