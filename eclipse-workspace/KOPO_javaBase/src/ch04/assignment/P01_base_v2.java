package ch04.assignment;

// 메서드 사용 - 재귀함수, 효율 안 따지고 구현

import java.util.Scanner;

public class P01_base_v2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("input N : ");
		int N = sc.nextInt();
		
		System.out.println("input M : ");
		int M = sc.nextInt();
		
		if(6 * N < M || M < N) System.out.println("wrong input.");
		else {
			int sum0 = 0;
			int count0 = 0;
			for(int i = 1 ; i <= 6 ; i++) {
				int count1 = count0 + 1;
				int sum1 = sum0 + i;
				if(count1 < N) {
//					for(int j = 1 ; j <=6 ; j++) {
//						int count2 = count1 + 1;
//						int sum2 = sum1 + j;
//						if(count2 < N) {
//							for(int k = 1 ; k <=6 ; k++)
//							{
//								int count3 = count2 + 1;
//								int sum3 = sum2 + k;
//								if(sum3==M) {
//									System.out.println(i+"+" + j + "+" + k);
//									//System.out.println(sum2);
//								}
//							}
//						} else if(count2 == N) {
//							if(sum2 == M) {
//								System.out.println(i + "+" + j);
//								//System.out.println(sum2);
//							}
//						}
//					}
				} else if(count1 == N) {
					if(sum1==M) {
						System.out.println(i);
						//System.out.println(sum2);
					}
				}
			}
		}
	}
}

