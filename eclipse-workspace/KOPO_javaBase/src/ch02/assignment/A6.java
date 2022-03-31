package ch02.assignment;

import java.util.Scanner;

public class A6 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("input : ");
		
		int input = sc.nextInt();
		
		for(int i = 1 ; i < input ; i++) {
			if(input % i == 0) System.out.println(i);
		}
	}

}
