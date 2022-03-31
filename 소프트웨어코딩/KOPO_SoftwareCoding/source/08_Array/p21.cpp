#include <stdio.h>
#include <math.h>

/*
	2진수를 10진수로 변환(1101 -> 13)
*/

int main()
{
	int decimal = 0;
	int binary[4] = {1,1,0,1};

	int size = sizeof(binary) / sizeof(int); // 4
	
	/*
	for(int i=0;i<size;i++)
	{
		decimal += binary[i]*pow(2,size-(i+1));
	}	
	*/
	
	for(int i=0;i<size;i++)
	{
		int n=1;
		for(int j=3;j>i;j--)
		{
			n*=2;
		}
		decimal += binary[i]*n;
	}	
	
	
	printf("%d\n",decimal);
} 
