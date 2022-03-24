#include <stdio.h>
#include <time.h>

/* �䱸���� �м�
 
	���ǿ� ���� �Ե����� ����� ���� ��� ���α׷�
	
	����1 - �̿�ü�(����) 
		�����̿�� / ��ũ�̿� 
	
	����2 - �̿�ð�(����) 
		���ϱ�(1Day) / ���ı�(After4)
	
	����3 - ����(�ֹι�ȣ �Է�)
		� : �� 19�� �̻� ~ �� 64�� ���� 
		û�ҳ� : �� 13�� �̻� ~ �� 18�� ���� 
		��� : 36�����̻� ~ �� 12�� ����
				 �� 65�� �̻�	
		���̺� : 0~12���� �̸�(��ũ,���� ���̽ü� ����)
				 12���� �̻�~36���� �̸�(��ũ ���� ����, ���� ���̽ü� ����)
				 ��, 12~36������ �ش��ϸ� ���� ���̽ü� �̿��� ���� �ٽ� ����� ��. - �ϴ� ����  => ���̺�� �ϰ� 15000�� ���� 
	
	����4 - ������(��� ��� ����, ����)  
		1 ����� ��� : 50% ����, ���� 1�α��� 
		2 ���������� : 50% ����, ���� 1�α��� 
		3 �ް��庴 : 49% ����, ���� 1�α��� 
		4 �ӻ�� : 50% ���� , ���θ�
		5 �ٵ��� �ູī�� : 30%����, ī�忡 ��õ� ������
		6 ����. 
		
	����5 - ����(���� �Է�) , 1~1000������ ���� & ������ ����� �׿� ���� �ż� ����. 
*/


/* �������� */ 
const int MaxTicketCount = 100;			// �������� ���� �� �ִ� ������ �ִ� �� 
// Ƽ�� ���� - ����, û�ҳ�, ���, ����
const int packageDay[4] = {59000, 52000, 47000, 15000};		// �����̿�� && ���ϱ� 
const int packageAfter4[4] = {48000, 42000, 36000, 15000};	// �����̿�� && after4 
const int onlyParkDay[4] = {56000, 50000, 46000, 15000};	// ��ũ�̿�� && ���ϱ� 
const int onlyParkAfter4[4] = {45000, 40000, 35000, 15000};	// ��ũ�̿�� && after4
// ���� - 1������ ����, ����, û�ҳ�, ���, ���� 
const int ageRange[5][2] = {{150,65},	// ���� �ʴ� ����(�ӽ�),	���� �ּ� ����
							{64,19},	// ���� �ִ� ����, 			�ּ� ����
							{18,13},	// û�� �ִ� ����, 			�ּ� ���� 
							{12,36},	// ��� �ִ� ����, 		�ּ� ���� 
							{35,0}};	// ���� �ִ� ����, 			�ּ� ����					

// ������- �����, ����������, ��������, �ӻ��, ���ڳడ�� 
const float discountRate[5] = {0.5, 0.5, 0.49, 0.5, 0.3};
// �� ���п� �ش��ϴ� ���ڿ� 
const char *KoKindUsageRange[2]	= {"�����̿��", "��ũ�̿��"};
const char *KoKindUsageTime[2]	= {"���ϱ�", "after4"};
const char *KoKindAge[5]		= {"����", "����", "û�ҳ�", "���", "����"};
const char *KoKindDiscount[6]	= {"�����   ", "����������", "�ް��庴", "�ӻ��   ", "���ڳ� ����", "�ش����"};


/* ����� ���� �Լ� */
int 	selectOption0();			// �̿� �ü� �����ϴ� �Լ�, 1�̸� �����̿��, 2�� ��ũ�̿�� 
int 	selectOption1();			//�̿� �ð� �����ϴ� �Լ�, 1�̸� ���ϱ�, 2�� after4 
int 	selectOption2();			// �ֹι�ȣ �Է� �Է¹޾� ���ɱ����� �����ϴ� �Լ�, ���� 1, ���� 2, û�ҳ� 3, ���4, ���� 5 
int 	selectOption3();			// ������ �����ϴ� �Լ�, ����� 1, ���������� 2, �ް��庴 3, �ӻ�� 4, ���ڳ� ���� 5, �ش���� 6 
int 	selectOption4(int option3);	// ���� �Է��ϴ� �Լ� 
int 	checkTotalPrice(int option0, int option1, int option2, int option3, int option4); 	// �����ݾ� ����ϴ� �Լ� 

int 	priceCheck(int option0, int option1, int option2);	// �ɼ�0~2�� �������� ���� Ȯ�� 
float 	checkDiscountRate(int option5);						// �ɼ�3�� �������� ������ Ȯ�� 

void 	printFinalPrice(int option[100][6]);				// ������ ����ϴ� �Լ�
 
int 	checkContinue();			// �߱��� �������� ������� Ȯ�� 
int 	checkNewtickets();			// ���ο� �߱��� �������� Ȯ�� 

int		todayIs();					// ���ó�¥ ��ȯ, 20220321



int main()
{
	FILE *fp = fopen("report.csv","a");
	//fprintf(fp, "��¥, �̿�ü�, �̿�Ⱓ, ���� ����, ����, ����, ������\n");
	
	printf("�Ե����忡 ���� �������� ȯ���մϴ�.\n");
	
	do
	{
		int options[MaxTicketCount][6]={0};	// �̿�ü� ���� , �̿� �ð� ����, �ֹε�Ϲ�ȣ, ������ ����, Ƽ�� ����, �Ѿ� 
		
		for(int i=0;i<100;i++)
		{
			options[i][0] = selectOption0();				// option0, �̿� �ü� ���� , return : 1�̸� �����̿��, 2�� ��ũ�̿�� 
			
			options[i][1] = selectOption1();				// option1, �̿� �ð� ���� , return : 1�̸� ���ϱ�, 2�� after4 
			
			options[i][2] = selectOption2();				// option2, �ֹι�ȣ �Է� �Է¹޾� ���ɱ����� ����, return : ���� 1, ���� 2, û�ҳ� 3, ���4, ���� 5 
										
			options[i][3] = selectOption3();				// option3, ������ ����, return : ����� 1, ���������� 2, �ް��庴 3, �ӻ�� 4, ���ڳ� ���� 5, ���� 6  
			
			options[i][4] = selectOption4(options[i][3]);	// option4, ���� ���� �Է� 
			
			options[i][5] = checkTotalPrice(options[i][0], options[i][1], options[i][2],options[i][3], options[i][4]);	// �� û�� �ݾ�  ��� �� ��� 
			
			fprintf(fp, "%d,%d,%d,%d,%d,%d,%d\n", todayIs(), options[i][0], options[i][1], options[i][2], options[i][3], options[i][4], options[i][5]);
			
			if(!checkContinue()) break;
		}
		
		// ������ ���	
		printFinalPrice(options);
		
	} while(checkNewtickets());
	
	fclose(fp);
	
	return 0;
}




/** 
	�̿�ü� ������ �Է¹޴� �Լ�
	��ȯ : �̿�ü� ���п� �ش��ϴ� ����, 1�̸� �����̿��, 2�� ��ũ�̿�� 
*/
int selectOption0()
{
	int option1=0;
	
	do
	{
		printf("\n*�̿�ü��� �����ϼ���.\n");
		printf("1. �����̿��\n");
		printf("2. ��ũ�̿��\n");
	
		scanf("%d",&option1); 
		//printf("%d\n",option1);
		
		if(!(option1==1||option1==2)) printf("�߸��� �Է��Դϴ�. 1 �Ǵ� 2�� �Է��ϼ���.\n");
	} while(!(option1==1||option1==2));

	return option1;
}

/** 
	�̿� �ð� ������ �Է¹޴� �Լ� 
	��ȯ : �̿�ð� ���п� �ش��ϴ� ����, 1�̸� ���ϱ�, 2�� after4 
*/
int selectOption1()
{
	int option1=0;
	
	do
	{
		printf("\n*������ �����ϼ���.\n");
		printf("1. 1Day \n");
		printf("2. After4 \n");
		
		scanf("%d",&option1); 
		//printf("%d\n",option1);
		
		if(!(option1==1||option1==2)) printf("�߸��� �Է��Դϴ�. 1 �Ǵ� 2�� �Է��ϼ���.\n");
	} while(!(option1==1||option1==2));

	return option1;
}

/**
	�ֹι�ȣ�� �Է¹޾� ���ɱ����� �����ϴ� �Լ� 
	��ȯ : ���� ���п� �ش��ϴ� ����
		���� 1, ���� 2, û�ҳ� 3, ���4, ���� 5 
*/
int selectOption2()
{
	// �ֹι�ȣ �Է�	
	long long ID = 0;
	do
	{
		printf("\n*�ֹι�ȣ�� �Է��ϼ���.(-���� 13�ڸ� �Է�)\n");
		scanf("%lld",&ID);							// 13�ڸ��̹Ƿ� �˳��� long long Ÿ�� ���
		//printf("%lld\n",ID);
		// ��Ȯ�� ����ó�� �ƴ� - 00���1���������� �ϴ� �̷��� �ߴµ�, �����ϱ� ��ٷӴ�. - ���� ���� 
		if(!(1000000000<=ID&&ID<=9999999999999)) printf("�߸��� �Է��Դϴ�. - ���� 13�ڸ��� �Է��ϼ���.\n");
	} while(!(1000000000<=ID&&ID<=9999999999999));
	
	// 13�ڸ� �ֹι�ȣ���� �� ������� ����
	int birth = ID/10000000;					// �� 6�ڸ� ����
	int years =0;
	if((ID/1000000)%10==3||(ID/1000000)%10==4)	// �з��Ͼ�  ���̺� �Ǻ� 
	{
		years = birth/10000+2000;				// �з��Ͼ�  ���̺��� ��� 
	}
	else
	{
		years = birth/10000+1900;
	}
	int months = (birth%10000)/100;
	int days = (birth%100); 		
	
	// �� �� ���� ���
	int today=todayIs();
	
	int countAgeDays=(today%100)-days;
	int countAgeMonths=(today%10000)/100-months;
	int countAgeYears=(today/10000)+2000-years; 
	if(countAgeDays<0)						// ex) 3������ 5���� ���� ��� �����̹Ƿ� ���� ó�� 
	{
		countAgeDays+=30;
		countAgeMonths--;
	} 
	if(countAgeMonths<0)						// ex) 15�Ͽ��� 27���� ���� ��� �����̹Ƿ� ���� ó�� 
	{
		countAgeMonths+=12;
		countAgeYears--;
	} 
	
	// �� ���̰� ��� ���� ���п� �ش��ϴ��� Ȯ��
	int dAge=0;
	if(countAgeYears>=ageRange[0][1])														dAge=1; 	// ����(��65�� �̻�)
	else if(countAgeYears>=ageRange[1][1]&&countAgeYears<=ageRange[1][0])					dAge=2;		// ����(�� 19~64��) 
	else if(countAgeYears>=ageRange[2][1]&&countAgeYears<=ageRange[2][0]) 					dAge=3; 	// û�ҳ�(�� 13~18��) 
	else if(countAgeYears*12+countAgeMonths>=ageRange[3][1]&&countAgeYears<=ageRange[3][0])	dAge=4;		// ���(36����~��12��)
	else if(countAgeYears*12+countAgeMonths<ageRange[4][0])									dAge=5; 	// ����(~36���� �̸�)
	
	return dAge; 
}

/**
	������ ������ �Է¹޴� �Լ� 
	��ȯ : ������ ���п� �ش��ϴ� ����
		1�̸� �����, 2�� ����������, 3�� �ް��庴, 4�� �ӻ��, 5�� ���ڳడ��, 6�� ���� 
*/
int selectOption3()
{
	int option5=0;
	do
	{
		printf("\n*�������� �����ϼ���.\n");
		printf("1. �����\n");
		printf("2. ����������\n");
		printf("3. �ް��庴\n");
		printf("4. �ӻ��\n");
		printf("5. ���ڳడ��\n");
		printf("6. ����\n");
		
		scanf("%d",&option5);
		//printf("%d\n",option5);
		
		if(!(1<=option5&&option5<=6)) printf("�߸��� �Է��Դϴ�. 1~6�� ���ڸ� �Է��ϼ���.\n");
	} while(!(1<=option5&&option5<=6));
	
	return option5; 
}

/** ���� ������ �Է¹޴� �Լ�
	�������� ������� �ʾ����� ������ 1~1000��
	�Ű����� : ������ ���п� �ش��ϴ� ���� 
	��ȯ : ���� ���� 
*/
int selectOption4(int option3)
{
	int amount=0;
	int d1=1;	// �Ϲ����� �ż� ���� : 1~1000�� 				, �̻������ 1, �̻������� 0
	int d2=1;	// ������ ����� �ż� ���� : �ִ� 1 �Ǵ� 2�� 	, �̻������ 1, �̻������� 0 
	
	do
	{
		printf("\n*������ �Է��ϼ���.(���� ���� ���� : 1~1000��)\n");
		scanf("%d",&amount);
		//printf("%d*%d=%d\n",amount,price,price*amount);	
		
		// ������ǿ� ���� Ƽ�� ���� ���� ���� �� �˸� 
		if((option3==1 || option3==2 || option3==3) && amount>2)
		{
			switch(option3)
			{
				case 1:	printf("����������� ����1�α��� ���밡���մϴ�.(�ִ� 2��)\n"); break;
				case 2:	printf("���������� ������ ����1�α��� ���밡���մϴ�.(�ִ� 2��)\n"); break;
				case 3:	printf("�ް� �庴 ������ ����1�α��� ���밡���մϴ�.(�ִ� 2��)\n"); break;
			};
			d2 = 0; 
		}
		else if(option3==4 && amount!=1)
		{
			printf("�ӻ�� ������ ���θ� ������ �����մϴ�.(�ִ� 1��)\n �ٽ� �Է��ϼ���.\n");
			d2 = 0;
		}
		else if(option3==5) 
		{
			do
			{
				int d3 = 0;
				printf("���ڳ� ���� ������ ī�忡 ��õ� ������ ������ �����մϴ�.\n �Է��Ͻ� ������ �½��ϱ�??\n �����Ϸ��� 0, �ٽ� �Է��Ϸ��� 1�� �Է��ϼ���.\n");
				scanf("%d",&d3);
				if(d3 == 0)
				{
					d2 = 1;
					break;
				}
				else if(d3 == 1)			
				{
					d2 = 0;
					break;
				}
				else if(d3 != 0 && d3!=1)	printf("�߸��� �Է��Դϴ�. 1 �Ǵ� 2�� �Է��ϼ���\n");
			}while(1);
			
		}
		
		// �ż� �Է� ������ ���� �˸�, �� �̻������ 0, �̻������� 1 
		if(1>amount && d2==1)
		{
			printf("�ּ� ���ż����� 1���Դϴ�.\n");
			d1=0;	
		}
		else if(amount>1000 && d2==1)
		{
			printf("�ִ� ���� ������ 1000���Դϴ�.\n");
			d1=0;
		}
		else 	d1=1;
	}while(!(d1&&d2));	// �Ϲ����� �ż����Ѱ� ������ ������� �ż����� ��� ������ ���� 
	
	return amount;
}

/** 
	�����ݾ� ��� �� ����ϴ� �Լ�
	�Ű����� :  �̿�ü� ����/�̿�ð� ����/���� ����/������ ���п� �ش��ϴ� ����,  ���� ���� 
	��ȯ :  ��� ������ ����� �Ǹ� �ݾ� 
*/
int checkTotalPrice(int option0, int option1, int option2, int option3, int option4)
{
	int originalPrice = priceCheck(option0, option1, option2);	// �̿� �ü�, �̿�ð�, ���� �������� ���� Ȯ�� 

	float discountRate = checkDiscountRate(option3);						// ���������� ������ Ȯ�� 
	
	int discountedPrice = originalPrice*discountRate;					// ���ε� 1�� �� Ƽ�� ���� 
	
	int totalPrice = discountedPrice * option4;							// �� Ƽ�� ���� 
	
	printf("\n*������ %d �� �Դϴ�.\n",totalPrice);			// 3�ڸ����� ��� ����ϴ� �޼��� ���� ���� ���� 
	printf("�����մϴ�.\n");
	
	return totalPrice; 
}




/**
	Ƽ�� ���� �����ϴ� �Լ� 
	checkTotalPrice()���� ��� 
	�Ű����� : �̿�ü� ����/�̿�ð� ����/���� ����/������ ���п� �ش��ϴ� ���� 
	��ȯ : ���ǿ� �ش��ϴ� Ƽ�� ���� 
*/
int priceCheck(int option0, int option1, int option2)
{
	int price=0;
	if(option2==2)		// ����(�� 19~64��) 
	{
		if(option0==1&&option1==1)		price=packageDay[0];
		else if(option0==1&&option1==2)	price=packageAfter4[0];
		else if(option0==2&&option1==1)	price=onlyParkDay[0];
		else if(option0==2&&option1==2)	price=onlyParkAfter4[0];
	} 
	else if(option2==3) // û�ҳ�(�� 13~18��) 
	{
		if(option0==1&&option1==1)		price=packageDay[1];
		else if(option0==1&&option1==2) price=packageAfter4[1];
		else if(option0==2&&option1==1)	price=onlyParkDay[1];
		else if(option0==2&&option1==2)	price=onlyParkAfter4[1];
	}
	else if(option2==4||option2==1)	// ���(36����~��12��) , ����(��65�� �̻�) 
	{
		if(option0==1&&option1==1)		price=packageDay[2];
		else if(option0==1&&option1==2)	price=packageAfter4[2];
		else if(option0==2&&option1==1)	price=onlyParkDay[2];
		else if(option0==2&&option1==2)	price=onlyParkAfter4[2];
	}
	else if(option2==5)			// ����(~36���� �̸�)
	{
		if(option0==1&&option1==1)		price=packageDay[3];
		else if(option0==1&&option1==2)	price=packageAfter4[3];
		else if(option0==2&&option1==1)	price=onlyParkDay[3];
		else if(option0==2&&option1==2)	price=onlyParkAfter4[3];
	}
	return price; 
}

/**
	�������� �����ϴ� �Լ� 
	checkTotalPrice()���� ��� 
	�Ű����� : ������ ���п� �ش��ϴ� ���� 
	��ȯ : �����׿� ���� ������ 
*/
float checkDiscountRate(int option3)
{
	float dRate=1;

	switch(option3)
	{
		case 1: dRate = 1*(1-discountRate[0]); 	break;
		case 2: dRate = 1*(1-discountRate[1]); 	break;
		case 3: dRate = 1*(1-discountRate[2]); 	break;
		case 4: dRate = 1*(1-discountRate[3]); 	break;
		case 5: dRate = 1*(1-discountRate[4]);	break;
		case 0: dRate = 1; 						break;	
	}
	return dRate;
}
 
/*
	������ ����ϴ� �Լ�  
*/
void printFinalPrice(int options[100][6])
{
	printf("\n\n------------------------�Ե�����------------------------\n\n");
	printf("�̿�ü�\t�̿�ð�\t���ɱ���\t������\t����\t�ݾ�\n\n");
		
	int sumTotalPrice = 0;
	
	for(int i=0;i<100;i++)
	{
		if(options[i][0]==0) break;				// ����� ���� ���� �迭�̸� �ݺ��� ���� 
		
		// �̿�ü�, �̿�ð�, ���ɱ���, ������, ����,���� ��� 
		//printf("%s\t%s\t\t%s\t\t%s\t%d\t%d\n", option0toKo(options[i][0]), option1toKo(options[i][1]), option2toKo(options[i][2]), option3toKo(options[i][3]), options[i][4], options[i][5]);
		printf("%s\t%s\t\t%s\t\t%s\t%d\t%d\n", KoKindUsageRange[options[i][0]-1],KoKindUsageTime[options[i][1]-1], KoKindAge[options[i][2]-1], KoKindDiscount[options[i][3]-1], options[i][4], options[i][5]);
		sumTotalPrice+=options[i][5];
	}
	printf("\n����� �Ѿ��� %d�Դϴ�.\n�����մϴ�.\n\n", sumTotalPrice);
}

/**
	���� ��¥�� Ȯ���ϴ� �Լ� 
	checkTotalPrice()���� ��� 
	��ȯ : ���� ��¥ ������ 6�ڸ� ���� 
*/
int todayIs()
{
	// �ý������� ����  ���� �ð� �������� 
	struct tm *cur_date;						// time.h�� ���ǵǾ� �ִ� ��¥�� �ð��� ��Ÿ���� ����ü 
	
	time_t curTime=time(NULL);					// #include <time.h> �ʿ�, time_t time(time * timer) : Ÿ�̸Ӱ� Null�� �ƴϸ� timer�� ����Ű�� ������ ���� �ð��� ä���. 
	cur_date=localtime(&curTime);				// localtime(const time_t * timer) : Ÿ�̸Ӱ� ����Ű�� ������ UTC�ð� �������� ����ü�� ��ȯ�� �� �ּҸ� ��ȯ 
	
	return ((cur_date->tm_year-100)*10000 + (cur_date->tm_mon+1)*100 + (cur_date->tm_mday));
} 
 
/**
	�߰� ������ �߱��� Ƽ���� �ִ��� ���� �Լ�(������ ����) 
	��ȯ : ������ 0, ������ 1 
*/
int checkContinue()
{
	int d = 1;
	
	do
	{
		printf("\n*��� Ƽ���� �߱��Ͻðڽ��ϱ�?\n");
		printf("0. ���� �� �հ� ����\n");
		printf("1. Ƽ�� �߰� �߱�\n");
		
		scanf("%d",&d);
		
		if(!(d==1||d==0)) printf("�߸��� �Է��Դϴ�. 0 �Ǵ� 1�� �Է��ϼ���.\n");
	} while(!(d==1||d==0));

	return d;
}




/**
	���ο� �߱��� ���� �Ұ��� ���� �Լ�
	��ȯ : ������ 0, ������ 1 
*/
int checkNewtickets()
{
	int d = 1;
	
	do
	{
		printf("\n*���ο� Ƽ���� �߱��Ͻðڽ��ϱ�?\n");
		printf("0. ���α׷� ����\n");
		printf("1. ���ο� Ƽ�� �߱�\n");
		
		scanf("%d",&d);
		
		if(!(d==1||d==0)) printf("�߸��� �Է��Դϴ�. 0 �Ǵ� 1�� �Է��ϼ���.\n");
	} while(!(d==1||d==0));

	return d;
}

