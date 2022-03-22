#include <stdio.h>

/*
	n과목의 n학생의 성적을 입력받아 각 학생의 총점, 각 학생의 평균, 각 과목의 평균을 계산하는 프로그램. 
*/
int main()
{
	int numArr[10] = {11, 22, 33, 44, 55, 66, 77, 88, 99, 110};
	int num_l, num_s = 0;		// 과목 수, 학생 수 
	
	// 과목수, 학생수 입력 
	printf("과목수 입력 : ");
	scanf("%d",&num_l);
	printf("학생수 입력 : ");
	scanf("%d",&num_s);
	
	// 구해야하는 값의 변수 선언 
	int score[num_s][num_l] = {0};
	int sum_s[num_s] = {0};
	int sum_l[num_l] = {0};
	float avg_s[num_s] = {0};
	float avg_l[num_l] = {0};
	
	// 성적 입력 
	for(int i=0;i<num_s;i++)
	{
		printf("Koposw%d학생의 점수 %d개를 차례대로 입력하세요:",num_s,num_l);
		for(int j=0;j<num_l;j++)
		{
			scanf("%d",&score[i][j]);
		}
	}
	
	
	// 학생별 총합 
	for(int i=0;i<num_s;i++)
	{
		for(int j=0;j<num_l;j++)
		{
			sum_s[i]+=score[i][j];	
		}
	}
	
	//과목별  총합 
	for(int i=0;i<num_l;i++)
	{
		for(int j=0;j<num_s;j++)
		{
			sum_l[i]+=score[j][i];	
		}
	}
	
	// 학생별 평균
	for(int i=0;i<num_s;i++)
	{
		avg_s[i] = sum_s[i] / num_l;
	}
	 
	// 과목별 평균
	for(int i=0;i<num_l;i++)
	{
		avg_l[i] = sum_l[i] / num_s;
	}
	
	
	printf("\n");
	
	
	// 학생별 총합과 평균 출력 
	for(int i=0;i<num_s;i++)
	{
		printf("Koposw%d 학생의 총점은%d, 평균은 %.2f입니다.\n", i, sum_s[i], avg_s[i]);
	}
	printf("\n");
	
	// 과목별 평균 출력 
	for(int i=0;i<num_l;i++)
	{
		printf("과목 %d의 전체 평균 : %.2f\n", i+1, avg_l[i]);
	}
	printf("\n");
	
	return 0;
}
