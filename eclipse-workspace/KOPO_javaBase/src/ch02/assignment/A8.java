package ch02.assignment;

import java.util.Scanner;

public class A8 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("input number1 : ");
		int n1 = sc.nextInt();

		System.out.println("input number2 : ");
		int n2 = sc.nextInt();
		
		int maxNumber = 0;
		
		for(int i = 1 ; i < n1 ; i++) {
			if(n1 % i == 0) {
				for(int j = 1 ; j < n2 ; j++) {
					if(n2 % j == 0) {
						if(i == j) maxNumber = i;
					}
				}
			}
		}
		System.out.println(maxNumber);
	}

}
