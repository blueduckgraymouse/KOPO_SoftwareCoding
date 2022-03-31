package ch04.assignment;

import java.util.Scanner;

public class P01_re2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// input
		System.out.println("input N : ");
		int N = sc.nextInt();
		System.out.println("input M : ");
		int M = sc.nextInt();
		
		if(6 * N < M || M < N) 
			System.out.println("wrong input.");
		else {
			function(0, 0, M, N, "");
		}
	}
	
	public static void function(int sum1, int count1, int M, int N, String nums) {
		for(int i = 1 ; i <= 6 ; i++) {
			int count2 = count1 + 1;
			if(count2 == 1)
				nums = "";
			int sum2 = sum1 + i;
			if(count2 < N)
				function(sum2, count2, M, N, nums + Integer.toString(i));
			else if(count2 == N && sum2 == M) {
				nums = nums + Integer.toString(i);
				char[] numArr = nums.toCharArray();
				System.out.print("(");
				for(int j = 0 ; j < numArr.length ; j++) {
					System.out.print(numArr[j]);
					if(j != numArr.length - 1) 
						System.out.print(", ");
				}
				System.out.println(")");
			}
		}
	}
}

