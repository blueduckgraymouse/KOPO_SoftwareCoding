package ch07;

import java.util.Scanner;

public class P09 {

	public static void main(String[] args) {
		String base = " ";
		
		P09 p09 = new P09();
		
		String inputString = p09.getString();
		String[] splitedString = p09.splitString(inputString, base);
		p09.print(splitedString);
	}

	private String getString() {
		System.out.print("input a String to split : ");
		Scanner sc = new Scanner(System.in);
		String inputString = sc.nextLine();
		return inputString;
	}

	private String[] splitString(String inputString, String base) {
		String[] splitedString = inputString.split(base);
		return splitedString;
	}

	private void print(String[] splitedString) {
		for(int i = 0 ; i < splitedString.length ; i++) {
			System.out.println(splitedString[i]);
		}
	}

}
