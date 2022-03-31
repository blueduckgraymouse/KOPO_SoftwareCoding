package ch02;

import java.util.Scanner;

public class P08 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("What number do you want?");
		
		int input = sc.nextInt();
		
		switch(input) {
			case 2:
				for(int i = 2 ; i < 10 ; i++) {
					System.out.println("2 X " + i + " = " + (2 * i));
				}
				break;
			case 3:
				for(int i = 2 ; i < 10 ; i++) {
					System.out.println("3 X " + i + " = " + (3 * i));
				}
				break;
			case 4:
				for(int i = 2 ; i < 10 ; i++) {
					System.out.println("4 X " + i + " = " + (4 * i));
				}
				break;
			case 5:
				for(int i = 2 ; i < 10 ; i++) {
					System.out.println("5 X " + i + " = " + (5 * i));
				}
				break;
			case 6:
				for(int i = 2 ; i < 10 ; i++) {
					System.out.println("6 X " + i + " = " + (6 * i));
				}
				break;
			case 7:
				for(int i = 2 ; i < 10 ; i++) {
					System.out.println("7 X " + i + " = " + (7 * i));
				}
				break;
			case 8:
				for(int i = 2 ; i < 10 ; i++) {
					System.out.println("8 X " + i + " = " + (8 * i));
				}
				break;
			case 9:
				for(int i = 2 ; i < 10 ; i++) {
					System.out.println("9 X " + i + " = " + (9 * i));
				}
				break;
			default:
				for(int i = 2 ; i < 10 ; i++) {
					System.out.println(input + " X " + i + " = " + (input * i));
				}
				break;
		}
		
	}

}
