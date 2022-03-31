package ch02.assignment;

import java.util.Scanner;

public class A3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// input
		System.out.println("input : ");
		int n = sc.nextInt();
		
		// print
		for(int i = 0 ; i < n ; i++) {
			for(int j=0 ; j <= i ; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

}
