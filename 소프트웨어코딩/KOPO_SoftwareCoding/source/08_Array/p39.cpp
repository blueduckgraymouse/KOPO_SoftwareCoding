#include <stdio.h>

/*
	임의의 행렬A,B를 *연산한 AB행렬을 출력하는 프로그램 
*/

int main()
{
	// 행렬 A, B 
	int A[2][2] = {
		{3, 4},
		{5, 6}
	};
	int B[2][2] = {
		{1, 5},
		{3, 2}
	};
	
	// A, B의 연산이 저장될 행렬 AB 
	int AB[2][2] = {0,};
	
	// --------------------------------------------------
	
	// A,B행렬의 row(행,가로), column(열, 세로)  크기 구하기 
	int size_A_i = sizeof(A) / sizeof(A[0]);
	int size_A_j = sizeof(A[0]) / sizeof(int);
	int size_B_i = sizeof(B) / sizeof(B[0]);
	int size_B_j = sizeof(B[0]) / sizeof(int);
	
	// 연산은 A의 j크기와 B의 i크기가 동일해야 연산 가능.
	if(size_A_j != size_B_i) 
	{
		printf("연산 불가\n");
	}
	else													// ex) 2*3 3*4 라면 
	{
		for(int i=0;i<size_A_i;i++) 						// A의 [2] => 2 번 반복 
		{
			for(int j=0;j<size_B_j;j++) 					// B의 [4] => 4 번 반복 , 즉 AB는 2*4 행렬
			{
				for(int k=0;k<size_A_j;k++)					// A의[j] 크기  (= B의 [i] 크기)
				{
					AB[i][j] += A[i][k]*B[k][j];			// i,j는 일정, k는 증가하며 A*B에 대해 누적. 
				}
			}
		}
		
		// 행렬 출력
		for(int i=0;i<size_A_i;i++) 	
		{
			for(int j=0;j<size_B_j;j++) 
			{
				printf("%d ",AB[i][j]);
			}
			printf("\n");
		}
	}
	
} 
