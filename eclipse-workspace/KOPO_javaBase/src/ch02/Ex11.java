package ch02;

//p16

import java.util.Scanner;

public class Ex11 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n;
		n = sc.nextInt();
		if(n == 1)		System.out.println("One");
		else if(n == 2)	System.out.println("Two");
		else if(n == 3)	System.out.println("Three");
		else			System.out.println("Except 1, 2, 3");
	}

}
