package ch02.assignment;

import java.util.Scanner;

public class A4 {
	public static void main(String[] agrs) {
		Scanner sc = new Scanner(System.in);
		
		// input
		System.out.println("input : ");
		int n = sc.nextInt();
		
		// print
		for(int i = 0 ; i < n ; i++) {
			for(int a = n-1 ; a > i ; a--) {
				System.out.print(" ");
			}
			for(int b = 0 ; b < 2 * i + 1 ; b++) {
				System.out.print("*");
			}
			for(int a = n-1 ; a > i ; a--) {
				System.out.print(" ");
			}
			System.out.println("");
		}
	}
}
