package ch07.assignment;

import java.util.Scanner;

public class A02 {

	public static void main(String[] args) {
		int[] numbers = {123, 32, 356, 53, 2, 67, 31, 78, 41, 9, 29};

		A02 a02 = new A02();
		
		int inputNumber = a02.inputNumber();
		
		a02.checkFactors(numbers, inputNumber);
	}

	// input Number
	private int inputNumber() {
		System.out.print("input number 0 ~ 9 : ");
		while(true) {
			Scanner sc = new Scanner(System.in);
			try {
				int inputNumber = sc.nextInt();
				if(inputNumber >= 0 && inputNumber < 10)
					return inputNumber;
				else
					System.out.println("wrong input. input a number 0 ~ 9.");
			} catch(Exception e) {
				System.out.println("wrong input. input a number 0 ~ 9.");
			}
		}
	}

	// find an array factor containing inputNumber 
	private void checkFactors(int[] numbers, int inputNumber) {
		for(int i = 0 ; i < numbers.length ; i++) {
			String number = Integer.toString(numbers[i]);
			String iNumber = Integer.toString(inputNumber);
			if(number.contains(iNumber)) {
				System.out.print(number + " ");
			}
		}
	}

}
