package ch02.assignment;

import java.util.Scanner;

public class A7 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("input number1 : ");
		int n1 = sc.nextInt();

		System.out.println("input number2 : ");
		int n2 = sc.nextInt();
		
		int i = 1;
		
		while(true) {
			if((n1 * i) % n2 == 0) {
				System.out.println(n1 * i);
				break;
			}
			i++;
		}
	}

}
