package ch07;

import java.util.Scanner;

public class P08 {

	public static void main(String[] args) {
		char[] original = {'d', 'r', 'w', 'o', 'l', 'e', 'h', ' '};	
		char[] encrypted = {'a', 'b', 'c', 'f', 'g', 'i', 'j', 'k'};	// jiggfkcfbga => hello world
		
		P08 p07 = new P08();
		
		String inputString = p07.getString();
		
		String result = p07.encrypt(inputString, original, encrypted);
		
		System.out.println("decrypted String : " + result);
	}

	private String getString() {
		System.out.print("input a String to decrypt : ");
		Scanner sc = new Scanner(System.in);
		String inputString = sc.nextLine();
		return inputString;
	}
	
	private String encrypt(String inputString, char[] original, char[] encrypted) {
		char[] inputChars = inputString.toCharArray();
		String result = "";
		for(int i = 0 ; i < inputChars.length ; i++) {
			for(int j = 0 ; j < encrypted.length ; j++) {
				if(inputChars[i] == encrypted[j])
					result = result + original[j];
			}
		}
		
		return result;
	}
}
