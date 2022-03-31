package ch02;

import java.util.Scanner;

public class P07_re {

	public static void main(String[] args) {
		while(true) {
			System.out.println("What number do you want?");
			try {
				Scanner sc = new Scanner(System.in);
				int input = sc.nextInt();
				
				for(int i = 2 ; i < 10 ; i++) {
					System.out.println(input + " X " + i + " = " + (input * i));
				}
				break;
			}
			catch(Exception e) {
				System.out.println("wrong input. try again.");
			}
		}
	}

}
