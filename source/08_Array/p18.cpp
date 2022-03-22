#include <stdio.h>

/*
	10진수를 2진수로 바꾸기(13 -> 1101)
*/

int main()
{
	int decimal = 13;
	int binary[20] = {0,};
	
	int position = 0;
	
	while(decimal!=0)
	{
		binary[position] = decimal % 2;
		decimal = decimal / 2;
		
		position++;
	}
	
	for(int i=position-1;i>=0;i--)
	{
		printf("%d",binary[i]);	// 1101
	}
	printf("\n");
	return 0;
}
