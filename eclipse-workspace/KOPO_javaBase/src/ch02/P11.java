package ch02;

import java.util.Scanner;

public class P11 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int input = sc.nextInt();
		
		for(int i = 2 ; i < 10 ; i++) {
			System.out.println(input + " X " + i + " = " + (input * i));
		}
	}
}
