package ch11;

import java.util.Scanner;

public class Ex01 {

	public static void main(String[] args) {
		Ex01 ex01 = new Ex01();
		
		int num1 = ex01.inputNumber();
		int num2 = ex01.inputNumber();
		
		int Bigger = num1;
		
		if (num1 < num2) {
			Bigger = num2;
		}
		System.out.println(Bigger);
	}

	private int inputNumber() {
		Scanner sc = new Scanner(System.in);
		return sc.nextInt();
	}

}
