#include <stdio.h>

/*
	배열의 선언
	자료형 배열이름[크기];
	자료형 배열이름[크기] ={값, .. ,값}; 
	
	인덱스는 0부터 시작. - 메모리 주소가 0부터 접근하므로.
	배열도 포인터로 취급되므로, 인덱스가 0부터 시작하면 요소 접근과 포인터 연산이 일치하게 됨. 
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
