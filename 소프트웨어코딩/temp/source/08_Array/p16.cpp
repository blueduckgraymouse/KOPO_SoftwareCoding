#include <stdio.h>

/*
	n������ n�л��� ������ �Է¹޾� �� �л��� ����, �� �л��� ���, �� ������ ����� ����ϴ� ���α׷�. 
*/
int main()
{
	int numArr[10] = {11, 22, 33, 44, 55, 66, 77, 88, 99, 110};
	int num_l, num_s = 0;		// ���� ��, �л� �� 
	
	// �����, �л��� �Է� 
	printf("����� �Է� : ");
	scanf("%d",&num_l);
	printf("�л��� �Է� : ");
	scanf("%d",&num_s);
	
	// ���ؾ��ϴ� ���� ���� ���� 
	int score[num_s][num_l] = {0};
	int sum_s[num_s] = {0};
	int sum_l[num_l] = {0};
	float avg_s[num_s] = {0};
	float avg_l[num_l] = {0};
	
	// ���� �Է� 
	for(int i=0;i<num_s;i++)
	{
		printf("Koposw%d�л��� ���� %d���� ���ʴ�� �Է��ϼ���:",num_s,num_l);
		for(int j=0;j<num_l;j++)
		{
			scanf("%d",&score[i][j]);
		}
	}
	
	
	// �л��� ���� 
	for(int i=0;i<num_s;i++)
	{
		for(int j=0;j<num_l;j++)
		{
			sum_s[i]+=score[i][j];	
		}
	}
	
	//����  ���� 
	for(int i=0;i<num_l;i++)
	{
		for(int j=0;j<num_s;j++)
		{
			sum_l[i]+=score[j][i];	
		}
	}
	
	// �л��� ���
	for(int i=0;i<num_s;i++)
	{
		avg_s[i] = sum_s[i] / num_l;
	}
	 
	// ���� ���
	for(int i=0;i<num_l;i++)
	{
		avg_l[i] = sum_l[i] / num_s;
	}
	
	
	printf("\n");
	
	
	// �л��� ���հ� ��� ��� 
	for(int i=0;i<num_s;i++)
	{
		printf("Koposw%d �л��� ������%d, ����� %.2f�Դϴ�.\n", i, sum_s[i], avg_s[i]);
	}
	printf("\n");
	
	// ���� ��� ��� 
	for(int i=0;i<num_l;i++)
	{
		printf("���� %d�� ��ü ��� : %.2f\n", i+1, avg_l[i]);
	}
	printf("\n");
	
	return 0;
}
