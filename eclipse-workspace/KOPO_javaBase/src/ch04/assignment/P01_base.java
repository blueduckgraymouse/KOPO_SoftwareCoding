package ch04.assignment;

// 메서드 사용 - 재귀함수, 효율 안 따지고 구현

import java.util.Scanner;

public class P01_base {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("input N : ");
		int N = sc.nextInt();
		
		System.out.println("input M : ");
		int M = sc.nextInt();
		
		if(6 * N < M || M < N) System.out.println("wrong input.");
		else {
			for(int i = 1 ; i <= 6 ; i++) {
				int sum1 = i;
				for(int j = 1 ; j <=6 ; j++) {
					int sum2 = sum1 + j;
					for(int k = 1 ; k <=6 ; k++)
					{
						if(sum2+k==M) {
							System.out.println(i+"+" + j + "+" + k);
							//System.out.println(sum2);
						}
					}
				}
			}
		}
	}
}

