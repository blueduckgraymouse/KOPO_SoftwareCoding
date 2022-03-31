package ch04.assignment;

import java.util.Scanner;

public class P01_f1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// input
		System.out.println("input N : ");
		int N = sc.nextInt();
		System.out.println("input M : ");
		int M = sc.nextInt();
		
		// process
		if (6 * N >= M && M >= N)
			rfunc(0, 0, M, N, "");
		else 						
			System.out.println("wrong input.");
	}
	
	// recursive function
	public static void rfunc(int sumBefore, int diceBefore, int M, int N, String sCase) {
		for (int i = 1 ; i <= 6 ; i++) {
			int diceCurrent = diceBefore + 1;
			if (diceCurrent == 1) 							// in first dice, reset
				sCase = "";
			int sumCurrent = sumBefore + i;
			if (diceCurrent < N) 							// save the value of current dice , access next dice
				rfunc(sumCurrent, diceCurrent, M, N, sCase + Integer.toString(i));
			else if (diceCurrent == N && sumCurrent == M) {	// work in last dice
				sCase = sCase + Integer.toString(i);		// save the value of current dice
				// print a case
				char[] numArr = sCase.toCharArray();
				System.out.print("(");
				for (int j = 0 ; j < numArr.length ; j++) {
					System.out.print(numArr[j]);
					if (j != numArr.length - 1) 	
						System.out.print(", ");
				}
				System.out.println(")");
			}
		}
	}
}

