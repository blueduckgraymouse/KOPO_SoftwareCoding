package ch02;

import java.util.Scanner;

public class P12 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("input number : ");
		
		int input = sc.nextInt();
		
		int result = 0;
		for(int i = 1 ; i <= input ; i++) {
			result += i;
		}
		System.out.println(result);
	}

}
