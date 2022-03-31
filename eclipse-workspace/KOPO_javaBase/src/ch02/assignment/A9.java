package ch02.assignment;

import java.util.Scanner;

public class A9 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("input : ");
		
		int input = sc.nextInt();
		
		for(int i = 2 ; i <= input ; i++) {
			int d = 0;
			
			for(int j = 2 ; j < i ; j++) {
				if(i % j == 0) {
					d = 1;
					break;
				}
			}
			if(d == 0) System.out.println(i);
		}
	}

}
