#include <stdio.h>

/**
	사직연산과 제곱 계산 구현	
*/

void fAdd();
void fSub();
void fMul();
void fDiv();
void fPow();
int Pow(int num1, int num2);

int main()
{
	while(1)
	{
		printf("\n계산기 프로그램 - 선택하세요\n");
		printf("1. 덧셈\n");
		printf("2. 뺄셈\n");
		printf("3. 곱셈\n");
		printf("4. 나눗셈\n");
		printf("5. 제곱\n");
		printf("6. 종료\n");
		
		int option = 0;
		
		scanf("%d",&option);
		
		switch(option)
		{
			case 1: fAdd(); break;
			case 2: fSub(); break;
			case 3: fMul(); break;
			case 4: fDiv(); break;
			case 5: fPow(); break;
			case 6: return 0;
		}
	}
}

void fAdd()
{
	int num1 = 0; 
	int num2 = 0; 
	
	printf("첫번째 숫자 입력 : ");
	scanf("%d",&num1);
	printf("둘째 숫자 입력 : ");
	scanf("%d",&num2);
	printf("\n결과 : %d\n\n",num1+num2);
}

void fSub()
{
	int num1 = 0; 
	int num2 = 0; 
	
	printf("첫번째 숫자 입력 : ");
	scanf("%d",&num1);
	printf("둘째 숫자 입력 : ");
	scanf("%d",&num2);
	printf("\n결과 : %d\n\n",num1-num2);
}

void fMul()
{
	int num1 = 0; 
	int num2 = 0; 
	
	printf("첫번째 숫자 입력 : ");
	scanf("%d",&num1);
	printf("둘째 숫자 입력 : ");
	scanf("%d",&num2);
	printf("\n결과 : %d\n\n",num1*num2);
}

void fDiv()
{
	int num1 = 0; 
	int num2 = 0; 
	
	printf("첫번째 숫자 입력 : ");
	scanf("%d",&num1);
	printf("둘째 숫자 입력 : ");
	scanf("%d",&num2);
	printf("\n결과 : %.2f / 몫 : %d, 나머지 : %d\n\n",(float)num1/(float)num2, num1/num2, num1%num2);
}

void fPow()
{
	int num1 = 0; 
	int num2 = 0; 
	
	printf("상수 입력 : ");
	scanf("%d",&num1);
	printf("지수 입력 : ");
	scanf("%d",&num2);
	
	printf("\n결과 : %d\n\n",Pow(num1, num2));
}

int Pow(int num1, int num2)
{
	if(num2==0) return 1;
	return num1 * Pow(num1, --num2);
}
