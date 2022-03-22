#include <stdio.h>

/*
	������ ���A,B�� *������ AB����� ����ϴ� ���α׷� 
*/

int main()
{
	// ��� A, B 
	int A[2][2] = {
		{3, 4},
		{5, 6}
	};
	int B[2][2] = {
		{1, 5},
		{3, 2}
	};
	
	// A, B�� ������ ����� ��� AB 
	int AB[2][2] = {0,};
	
	// --------------------------------------------------
	
	// A,B����� row(��,����), column(��, ����)  ũ�� ���ϱ� 
	int size_A_i = sizeof(A) / sizeof(A[0]);
	int size_A_j = sizeof(A[0]) / sizeof(int);
	int size_B_i = sizeof(B) / sizeof(B[0]);
	int size_B_j = sizeof(B[0]) / sizeof(int);
	
	// ������ A�� jũ��� B�� iũ�Ⱑ �����ؾ� ���� ����.
	if(size_A_j != size_B_i) 
	{
		printf("���� �Ұ�\n");
	}
	else													// ex) 2*3 3*4 ��� 
	{
		for(int i=0;i<size_A_i;i++) 						// A�� [2] => 2 �� �ݺ� 
		{
			for(int j=0;j<size_B_j;j++) 					// B�� [4] => 4 �� �ݺ� , �� AB�� 2*4 ���
			{
				for(int k=0;k<size_A_j;k++)					// A��[j] ũ��  (= B�� [i] ũ��)
				{
					AB[i][j] += A[i][k]*B[k][j];			// i,j�� ����, k�� �����ϸ� A*B�� ���� ����. 
				}
			}
		}
		
		// ��� ���
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
