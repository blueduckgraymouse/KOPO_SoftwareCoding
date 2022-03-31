package ch05;

import java.util.Scanner;

public class P05 {

	public static void main(String[] args) {
		P05 p05 = new P05();
		int N = p05.inputN();
		p05.printMultiTable(N);
	}

	int inputN() {
		System.out.print("input N : ");
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		return N;
	}
	
	void printMultiTable(int N) {
		for (int i = 0 ; i < 10 ; i++) {
			System.out.println(N + " X " + i + " = " + (N * i));
		}
	}
}

