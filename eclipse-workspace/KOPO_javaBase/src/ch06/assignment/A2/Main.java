package ch06.assignment.A2;

import java.util.Scanner;

// 출제의도는 나누기와 나머지 연산자로 구현.

public class Main {

	public static void main(String[] args) {
		Main main = new Main();
		int number = main.inputNumber();
		
		ReverseNumber reverseNumber = new ReverseNumber();
		reverseNumber.reverseNumber(number);
		reverseNumber.printReversedNumber();
	}

	private int inputNumber() {
		System.out.print("input number : ");
		while (true) {
			Scanner sc = new Scanner(System.in);
			try {
				int input = sc.nextInt();
				return input;
			} catch (Exception e) {
				System.out.println("wrong input. input only number.");
			}
		}
	}
}
