package ch04.assignment;

// 메서드 사용 - 재귀함수, 효율 안 따지고 구현

import java.util.Scanner;

public class P01_re {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("input N : ");
		int N = sc.nextInt();
		
		System.out.println("input M : ");
		int M = sc.nextInt();
		
		if(6 * N < M || M < N) System.out.println("wrong input.");
		else {
			int[] cases = new int[N];  
			
			
			function(M, N, 1, 0, "");
		}
	}
	
	public static void function(int M, int N, int now, int sum1, String nums) {
		for(int i = 1 ; i <= 6 ; i++) {
			System.out.println(sum1 + " - " + i);
			if(sum1 + i == M && now == N) {
				char[] line = nums.toCharArray();
				System.out.println("(");
				for(int j = 0; i < line.length ; j++) {
					System.out.println(line[i]);
					if(i != line.length - 1) System.out.println(", ");
				}
				System.out.println(")");
				break;
			}
			int sum2 = sum1 + i;
			if(now == N) {
				System.out.println("now");
				break;
				
			}
			function(M, N, now+1, sum1, nums);
		}
		
	}
}

