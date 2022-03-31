package ch02;

import java.util.Scanner;

public class Ex13 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = 2;
		switch(n) {
			case 1:
				System.out.println("One!");
				break;
			case 2:
				System.out.println("Two!");
				break;
			default:
				System.out.println("Except 1, 2");
				break;
		}
	}

}
