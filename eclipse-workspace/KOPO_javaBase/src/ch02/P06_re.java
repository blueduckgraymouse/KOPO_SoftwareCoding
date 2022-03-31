package ch02;

import java.util.Scanner;

public class P06_re {

	public static void main(String[] args) {
		int operation = 0;
		int result = 0;
		
		while(true) {
			try {
				Scanner sc = new Scanner(System.in);
				System.out.println("What operation do you want?");
				System.out.println("1. +");
				System.out.println("2. -");
				System.out.println("3. *");
				System.out.println("4. /");
				operation = sc.nextInt();
				if(operation>0 && operation<5) break;
			} catch(Exception e) {
				System.out.println("wrong input. try again.");
			}
		}
		
		while(true) {
			try {
				Scanner sc = new Scanner(System.in);
				
				System.out.println("input number1 : ");
				int n1 = sc.nextInt();
				
				System.out.println("input number2 : ");
				int n2 = sc.nextInt();
				
				System.out.println("\n--->\n");
				
				switch(operation) {
				case 1:
					result = n1 + n2;
					break;
				case 2:
					result = n1 - n2;
					break;
				case 3:
					result = n1 * n2;
					break;
				case 4:
					result = n1 / n2;
					break;
				};
				
				System.out.println(result);
				break;
			} catch(Exception e) {
				System.out.println("wrong input. try again.");
			}
		}
		
	}

}
