#include <stdio.h>

/*
	배열에 들어있는 모든 데이터의 합계 구하기 
*/
int main()
{
	int numArr[10] = {11, 22, 33, 44, 55, 66, 77, 88, 99, 110};
	int sum=0;
	
	for(int i=0;i<10;i++)
	{
		sum += numArr[i];	//sum = sum + numArr[i];
	}
	
	printf("%d\n",sum);
}
