package ch04.assignment;

import java.util.Scanner;

public class P03 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// input
		System.out.print("input M : ");
		int M = sc.nextInt();
		System.out.print("input N : ");
		int N = sc.nextInt();

		int sum = 0;
		int multi = N;
		int factor[] = new int[M / N];
		
		// process
		for (int i = 0 ; i < factor.length ; i++) {
			factor[i] = multi;
			sum += multi;
			multi += N;
		}
		
		// output
		System.out.print("SUM : " + sum + "(");
		
		for (int i = 0 ; i < factor.length ; i++) {
			System.out.print(factor[i]);
			if (i != factor.length - 1)
				System.out.print(", ");
		}
		
		System.out.println(")");
		
		
	}

}
