package ch02;

import java.util.Scanner;

public class P13 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("input end number : ");
		int endNumber = sc.nextInt();
		
		System.out.println("input start number : ");
		int interval = sc.nextInt();
		
		int result = 0;
		
		for(int i = 1 ; i <= endNumber ; i += interval) {
			result += i;
		}
		System.out.println(result);
	}

}
