package ch02.assignment;

import java.util.Scanner;

public class A5 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// input
		System.out.println("input : ");
		int n = sc.nextInt();
		
		// print
		for(int i = 0 ; i < n ; i++) {
			for(int a = 0 ; a < i ; a++) {
				System.out.print(" ");
			}
			for(int b = (n-i)*2-1 ; b > 0 ; b--) {
				System.out.print("*");
			}
			for(int a = 0 ; a < i ; a++) {
				System.out.print(" ");
			}
			System.out.println("\n");
		}
	}

}
