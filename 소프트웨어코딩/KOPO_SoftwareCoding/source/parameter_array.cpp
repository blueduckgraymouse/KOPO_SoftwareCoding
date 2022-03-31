#include <stdio.h>

void tr2(int arr[])
{
	printf("tr2 - %d\n",arr[0]);
}

int *tr1()
{
	int a[3]={1,2,3};
	
	return a;
}

int main()
{
	int* c = tr1();
	printf("main - %d\n",c[0]);
	
	int b[3] = {4,5,6};
	tr2(b);
	
	return 0;
}

