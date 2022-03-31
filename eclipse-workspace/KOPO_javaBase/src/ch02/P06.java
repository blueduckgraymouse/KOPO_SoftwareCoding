package ch02;

import java.util.Scanner;

public class P06 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("What operation do you want?");
		System.out.println("1. +");
		System.out.println("2. -");
		System.out.println("3. *");
		System.out.println("4. /");
		int operation = sc.nextInt();

		System.out.println("input number1 : ");
		int n1 = sc.nextInt();

		System.out.println("input number2 : ");
		int n2 = sc.nextInt();

		System.out.println("\n--->\n");

		switch (operation) {
			case 1:
				System.out.println(n1 + " + " + n2 + " = " + (n1 + n2));
				break;
			case 2:
				System.out.println(n1 + " - " + n2 + " = " + (n1 - n2));
				break;
			case 3:
				System.out.println(n1 + " * " + n2 + " = " + (n1 * n2));
				break;
			case 4:
				if(n2 != 0) {
					System.out.println(n1 + " / " + n2 + " = " + (n1 / n2));
					break;
				}
			default:
				System.out.println("error");
				break;
		};
	}

}
