package ch05;

import java.util.Scanner;


public class P07 {
	// P07 p07 = new P07(); - 이거 왜 main이랑 prtintFactorial에서 못 쓰지?
	
	public static void main(String[] args) {
		P07 p07 = new P07();
		
		int N = p07.input();
		
		p07.printFactorial(N);
	}


	public int input() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("input N : ");
		int N = sc.nextInt();
		return N;
	}
	
	public int factorial(int n) {
		if(n == 1)
			return 1;
		else
			return n * factorial(--n);
	}
	
	private void printFactorial(int n) {
		P07 p07 = new P07();
		
		System.out.print(p07.factorial(n));
		System.out.print(" = ");
		for(int i = n ; i > 0 ; i--) {
			System.out.print(i);
			if(i != 1)
				System.out.print(" X ");
		}
	}
}
