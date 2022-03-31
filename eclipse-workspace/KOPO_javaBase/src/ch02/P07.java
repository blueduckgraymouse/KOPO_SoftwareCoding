package ch02;

import java.util.Scanner;

public class P07 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("What number do you want?");
		
		int input = sc.nextInt();
		
		for(int i = 2 ; i < 10 ; i++) {
			System.out.println(input + " X " + i + " = " + (input * i));
		}
	}

}
