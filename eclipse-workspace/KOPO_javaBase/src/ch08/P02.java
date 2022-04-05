package ch08;

import java.util.Scanner;

public class P02 {

	public static void main(String[] args) {
		P02 p02 = new P02();
		
		String inputString = p02.getString();
		
		p02.printMiddleChars(inputString);
	}
	
	/* input a String
	 * return : inputed String
	 */
	private String getString() {
		System.out.print("input a String : ");
		
		Scanner sc = new Scanner(System.in);
		String inputString = sc.nextLine();
		
		return inputString;
	}
	
	/* check that length of the string parameter is odd or even 
	 * AND print one or two characters of middle
	 * parameter : inputed String
	 */
	private void printMiddleChars(String inputString) {
		int length = inputString.length();
		String middleChars = "";
		
		if (length % 2 == 0) {	// even
			middleChars = inputString.substring(length / 2 - 1, length / 2 + 1);
		} else {				// odd
			middleChars = inputString.substring(length / 2, length / 2 + 1);
		}
		
		System.out.println(middleChars);
	}
}
