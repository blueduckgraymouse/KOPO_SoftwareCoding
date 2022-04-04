package ch07;

import java.util.Scanner;

public class P07 {

	public static void main(String[] args) {
		char[] original = {'d', 'r', 'w', 'o', 'l', 'e', 'h', ' '};	
		char[] encrypted = {'a', 'b', 'c', 'f', 'g', 'i', 'j', 'k'};	// jiggfkcfbga => hello world
		
		P07 p07 = new P07();
		
		String inputString = p07.getString();
		
		String result = p07.encrypt(inputString, original, encrypted);
		
		System.out.println("encrypted String : " + result);
	}

	private String getString() {
		System.out.print("input a String to encrypt : ");
		Scanner sc = new Scanner(System.in);
		String inputString = sc.nextLine();
		return inputString;
	}
	
	private String encrypt(String inputString, char[] original, char[] encrypted) {
		char[] inputChars = inputString.toCharArray();
		String result = "";
		
		for(int i = 0 ; i < inputChars.length ; i++) {
			for(int j = 0 ; j < original.length ; j++) {
				if(inputChars[i] == original[j])
					result = result + encrypted[j];
			}
		}
		
		return result;
	}
}
