#include <stdio.h>

/*
	�迭�� ����
	�ڷ��� �迭�̸�[ũ��];
	�ڷ��� �迭�̸�[ũ��] ={��, .. ,��}; 
	
	�ε����� 0���� ����. - �޸� �ּҰ� 0���� �����ϹǷ�.
	�迭�� �����ͷ� ��޵ǹǷ�, �ε����� 0���� �����ϸ� ��� ���ٰ� ������ ������ ��ġ�ϰ� ��. 
*/
int main()
{
	int numArr[10] = {11, 22, 33, 44, 55, 66, 77, 88, 99, 110};
	
	printf("%d\n",numArr[0]);
	printf("%d\n",numArr[5]);
	printf("%d\n",numArr[9]);
	
	numArr[0] = 0;
	numArr[5] = 5;
	numArr[9] = 9;
	
	printf("%d\n",numArr[0]);
	printf("%d\n",numArr[5]);
	printf("%d\n",numArr[9]);
	
	// ----------------------
	
	int numArr2[10] = {0,};
	//int numArr2[10] = {1};
	
	for(int i=0;i<10;i++)
	{
		printf("%d\n",numArr2[i]);
	}

	return 0;
} 
