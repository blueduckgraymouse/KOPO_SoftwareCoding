#include <stdio.h>

/**
	��������� ���� ��� ����	
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
		printf("\n���� ���α׷� - �����ϼ���\n");
		printf("1. ����\n");
		printf("2. ����\n");
		printf("3. ����\n");
		printf("4. ������\n");
		printf("5. ����\n");
		printf("6. ����\n");
		
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
	
	printf("ù��° ���� �Է� : ");
	scanf("%d",&num1);
	printf("��° ���� �Է� : ");
	scanf("%d",&num2);
	printf("\n��� : %d\n\n",num1+num2);
}

void fSub()
{
	int num1 = 0; 
	int num2 = 0; 
	
	printf("ù��° ���� �Է� : ");
	scanf("%d",&num1);
	printf("��° ���� �Է� : ");
	scanf("%d",&num2);
	printf("\n��� : %d\n\n",num1-num2);
}

void fMul()
{
	int num1 = 0; 
	int num2 = 0; 
	
	printf("ù��° ���� �Է� : ");
	scanf("%d",&num1);
	printf("��° ���� �Է� : ");
	scanf("%d",&num2);
	printf("\n��� : %d\n\n",num1*num2);
}

void fDiv()
{
	int num1 = 0; 
	int num2 = 0; 
	
	printf("ù��° ���� �Է� : ");
	scanf("%d",&num1);
	printf("��° ���� �Է� : ");
	scanf("%d",&num2);
	printf("\n��� : %.2f / �� : %d, ������ : %d\n\n",(float)num1/(float)num2, num1/num2, num1%num2);
}

void fPow()
{
	int num1 = 0; 
	int num2 = 0; 
	
	printf("��� �Է� : ");
	scanf("%d",&num1);
	printf("���� �Է� : ");
	scanf("%d",&num2);
	
	printf("\n��� : %d\n\n",Pow(num1, num2));
}

int Pow(int num1, int num2)
{
	if(num2==0) return 1;
	return num1 * Pow(num1, --num2);
}
