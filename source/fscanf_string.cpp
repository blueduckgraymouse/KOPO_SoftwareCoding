#include <stdio.h>

int main()
{
	FILE *fp = fopen("string.csv", "r"); 
	
	int num[100][2] = {0};
	char* data[100][2][100] = {" "};
	
	int i = 0;
	
	while(feof(fp)==0)	// feof(report) : report������ ���̸� EOF�÷��� ���, �ƴϸ� 0
	{
		fscanf(fp, "%d, %d, %s, %s\n", &num[i][0], &num[i][0], &data[i][0], &data[i][0]);
		
		printf("%d, %d, %s, %s\n", num[i][0], num[i][0], data[i][0], data[i][0]);
		
		i++;
	}
	
	return 0;
 } 
