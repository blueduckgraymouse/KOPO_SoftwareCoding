package ch06.assignment.A3;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Main main = new Main();
		String str = main.inputString();
		
		ReverseString reverseString = new ReverseString();
		reverseString.reverseString(str);
		reverseString.printReversedString();
	}

	private String inputString() {
		System.out.print("input String : ");
		while (true) {
			Scanner sc = new Scanner(System.in);
			try {
				String str = sc.next();
				return str;
			} catch (Exception e) {
				System.out.println("wrong input. input only String.");
			}
		}
	}
}
