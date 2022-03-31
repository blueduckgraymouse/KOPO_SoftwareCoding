package ch01;

import java.util.Scanner;

public class P12 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Input the 1st number!");
		int num1 = sc.nextInt();
		
		System.out.println("Input the 1st number!");
		int num2 = sc.nextInt();
				
		System.out.println("Result : " + num1 + " + " + num2 + " = " + (num1+num2));
		
		sc.close();
	}
}

