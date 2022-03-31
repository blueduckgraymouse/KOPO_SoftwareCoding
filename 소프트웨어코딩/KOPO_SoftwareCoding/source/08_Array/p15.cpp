#include <stdio.h>

/*
	배열에 들어있는 각 데이터를 모두 2배로 만들어 놓고 출력하기 
*/
int main()
{
	int numArr[10] = {11, 22, 33, 44, 55, 66, 77, 88, 99, 110};
	int sum=0;
	
	for(int i=0;i<10;i++)
	{
		numArr[i]*=2;
	}
	
	for(int i=0;i<10;i++)
	{
		printf("%d\n",numArr[i]);
	}
	
	return 0;
}
