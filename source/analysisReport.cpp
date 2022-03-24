#include <stdio.h>
#include <time.h>

/* �䱸����
	lotte_world Ƽ�� ���α׷��� ���� ����� report.csv������ �м��ϴ� ���α׷�
	
	1. ���� ���� ��ü ���.
		�߰�) �������� �Ǹ� �� ����, �� ���� ��� 
	
	2. �м� - ���� ���� �Ǹ� Ƽ�� �м�
		: ���ɱ��к� �Ǹ� Ƽ�� ��, ������ �� ���� ��� 
		1) �̿�ü�
		2) �̿�ð�
	
	3. �м� - ���� �� ���� �м�
		: ���ں� �� ����
	
	4. �м� - �����׺� �Ǹ� Ƽ�� �м�
		: ������ �� �Ǹ� Ƽ�� �� ��� 
	
	5. �м� ��� ���Ϸ� ����. 
*/


/* ��������� �Լ� */
void fCountDate(int date, int amount, int price);					// ��¥�� ���� Ƽ�� �� �����ϴ� �Լ� 
void fCountUsageRage(int countUsageRage, int kindAge, int amount);	// �̿�ü� �� Ƽ��, ���ɱ��п� ���� �����ϴ� �Լ� 
void fCountUsageTime(int KindUsageTime, int kindAge, int amount);	// �̿�ð� �� Ƽ��, ���ɱ��п� ���� �����ϴ� �Լ� 
void fCountAge(int kindAge, int amount);							// ���ɱ��� �� Ƽ�� �����ϴ� �Լ� 
void fCountDiscount(int kindDiscount, int amount);					// ������ �� Ƽ�� �����ϴ� �Լ� 
void fCountTickets(int amount);										// �� Ƽ�� �� �����ϴ� �Լ� 
void fSumPrice(int price);											// ���� �����ϴ� �Լ� 

void printCountDate();						// ��¥�� ���� ������ Ƽ�� �� ����ϴ� �Լ� 
void printCountUsageTime();					// �̿�ü� �������� ���� ���п� ���� ������ Ƽ�� �� ����ϴ� �Լ�  
void printCountUsageRange();				// �̿�ð� �������� ���� ���п� ���� ������ Ƽ�� �� ����ϴ� �Լ� 
void printCountKindAge();					// ���� ���п� ���� ������ Ƽ�� �� ����ϴ� �Լ� 
void printCountKindDiscount();				// �����׿� ���� ������ Ƽ�� �� ����ϴ� �Լ� 

int *chageDateFmt(int date);				// ��¥ 6�ڸ��� 2�ڸ��� ���� ũ��3�� �迭�� ��ȯ�ϴ� �Լ� 


/* �������� */
const int maxDataCount = 1000; 				// �м������� �߱� �� Ƚ��, ������ �ִ� �� �� 
// �м��� ���� �������� �����ϴ� ���� 
int countDate[maxDataCount][3]  = {0};		// ��¥�� ���� [n][0]�� ��¥, [n][1]�� ���� Ƽ�� ��, [n][2]�� ���� ����
											// report.csv�� 1000�� ���� �� �ְ� ��� ���� ������ ��¥�� �ٸ� �� �����Ƿ� �Ȱ��� �ƽø����� ����. 
int countKindUsageRange[2][5] 	= {0};		// [0]�� �� �����̿�� 	����, [1]�� ��ũ�̿�� 	���� 
int countKindUsageTime[2][5] 	= {0};		// [0]�� �� ���ϱ� 		����, [1]�� after4 		���� 
int countKindAge[5] 			= {0};		// [0]�� ����, 	[1]�� ����, 		[2]�� û�ҳ�, 	[3]�� ���, 	[4]�� ����  Ƽ�� �� ����
int countKindDiscount[6]		= {0};		// [0]�� �����,[1]�� ����������,	[2]�� �ް��庴, [3]�� �ӻ��, 	[4]�� ���ڳ� ����, [5]�� �ش����  �� ����
int countTickets 				= 0;		// �Ǹŵ� Ƽ���� �� ����
int sumPrice 					= 0; 		// ���� �����
// �� ���п� �ش��ϴ� �ѱ� ���ڿ� �迭 
const char *KoKindUsageRange[2]	= {"�����̿��", "��ũ�̿��"};
const char *KoKindUsageTime[2]	= {"���ϱ�", "after4"};
const char *KoKindAge[5]		= {"����", "����", "û�ҳ�", "���", "����"};
const char *KoKindDiscount[6]	= {"�����   ", "����������", "�ް��庴", "�ӻ��   ", "���ڳ� ����", "�ش����"};



int main()
{
	int i = 0;									// 0���� report.csv�� �� �� ��ŭ ���� 
	
	FILE *report = fopen("report.csv", "r");	// �м��� ���� report.csv ���� 
	
	printf("\n------------------------------------------- report.csv -------------------------------------------\n\n"); 
	printf("��¥\t�̿�ü�\t�̿�ð�\t���ɱ���\t������\t����\t�ݾ�\n\n");
	
	// ������ �� �پ� ������ ���������� �ݺ� ���� 
	while(feof(report)==0)					// feof(report) : report������ ���̸� EOF�÷��� ���, �ƴϸ� 0
	{
		// report.csv�� ������ ������ ������ ����, �ݺ��� �ȿ� �־��־� ����������Ʋ�� ���� 1ȸ�� ����. 
		int date[maxDataCount] 				= {0};	// �Ǹ� ��¥ 
		int KindUsageRange[maxDataCount]	= {0};	// �̿�ü� ����, 1�� �����̿��, 2�� ��ũ�̿�� 
		int KindUsageTime[maxDataCount] 	= {0};	// �̿�ð� ����, 1�� ���ϱ�, 2�� after4 
		int kindAge[maxDataCount] 			= {0};	// ���� ����	, 1�� ����, 2�� ����, 3�� û�ҳ�, 4�� ���, 5�� ���� 
		int kindDiscount[maxDataCount]		= {0};	// ������ ����, 1�� �����, 2�� ����������, 3�� �ް��庴, 4�� �ӻ��, 5�� ���ڳ� ����, 6�� ���� 
		int amount[maxDataCount]			= {0};	// Ƽ�� �Ǹ� ���� 
		int price[maxDataCount]				= {0};	// �Ѿ� 
		
		// ���� �� �� �о� ������ ���� 
		fscanf(report, "%d, %d, %d, %d, %d, %d, %d\n", &date[i], &KindUsageRange[i], &KindUsageTime[i], &kindAge[i], &kindDiscount[i], &amount[i], &price[i]);
		
		// ���� �� �� ��� 
		printf("%d\t%s\t%s\t\t%s\t\t%s\t%d\t%d\n", date[i], KoKindUsageRange[KindUsageRange[i]-1], KoKindUsageTime[KindUsageTime[i]-1], KoKindAge[kindAge[i]-1], KoKindDiscount[kindDiscount[i]-1], amount[i], price[i]);
		
		// ���� �� �ٿ� ���Ͽ� �̿�ü�, �̿�ð�, ������� �� �� Ƽ�� �� ���� 
		fCountDate(date[i], amount[i], price[i]);					// �䱸���� 3�� : �м�) ��¥�� ���� Ƽ�� ī��Ʈ 
		fCountUsageRage(KindUsageRange[i], kindAge[i], amount[i]); 	// �䱸���� 2-1�� : �м�) �̿�ü� �� Ƽ��, ���ɱ��п� ���� ī��Ʈ. 
		fCountUsageTime(KindUsageTime[i], kindAge[i], amount[i]);	// �䱸���� 2-2�� : �м�) �̿�ð� �� Ƽ��, ���ɱ��п� ���� ī��Ʈ.
		fCountAge(kindAge[i], amount[i]); 							// �䱸������ �ƴ�. 
		fCountDiscount(kindDiscount[i], amount[i]);					// �䱸����  4�� : �м�) ������ �� Ƽ�� ī��Ʈ 
		
		// ���� �� �ٿ� ���Ͽ� ���� �Ǹ� Ƽ�� ��, ����� ����
		fCountTickets(amount[i]);
		fSumPrice(price[i]);
		
		i++;		// report������ ���� �� ������ ���� ����
	}
	
	
	//FILE *analysis = fopen("analysis.csv", "a");		// �м� �����͸� ������ ���� ���� 
	
	// �Ϸ�� �м� ���
	printCountDate();			// ��¥�� ���� ������ Ƽ�� �� ���
	printCountUsageRange();		// �̿�ü� �������� ���� ���п� ���� ������ Ƽ�� �� ��� 
	printCountUsageTime();		// �̿�ð� �������� ���� ���п� ���� ������ Ƽ�� �� ���
	printCountKindAge();		// ���� ���п� ���� ������ Ƽ�� �� ���
	printCountKindDiscount();	// �����׿� ���� ������ Ƽ�� �� ��� 
	
	fclose(report);			// report���� �ݱ� 
	//fclose(analysis);		// analysis���� �ݱ�
	
	return 0;
}


/**
	��¥ �������� Ƽ�� �� �����ϴ� �Լ�
	�Ű����� : �Ǹ� ��¥, �Ǹ� ����, �Ǹ� �Ѿ�
	�������� ����� ������ ���������� �����Ͽ����Ƿ� return�� ����. 
*/
 void fCountDate(int date, int amount, int price)
 {
	for(int i=0;i<maxDataCount;i++)	// ����� ��¥-����Ƽ�� �迭�� �ε��� 0���� ���� 
	{
		if(countDate[i][0] == date)		// ������ ��¥�� ���� ��¥�� ��ġ�ϸ� -> ������ �ִ� ��¥
		{
			countDate[i][1] += amount;	// ���� Ƽ�� ��, ���� ���� ���� 
			countDate[i][2] += price;	// ���� ����, ���� ���� ����  
			break; 
		}
		else if(countDate[i][0] == 0)	// ������ ��¥�� ����� ��¥�� �� ������ ����ִ� �迭�̸� -> ������ ���� ��¥ 
		{
			countDate[i][0] = date;		// ��¥�� �迭�� ���� 
			countDate[i][1] = amount;	// ���� Ƽ�� ��, ó������ �� ����
			countDate[i][2] = price;	// ���� ����, ó������ �� ����
			break;
		}
	}
 }

/**
	��ü� �������� ���� ���п� ���� Ƽ�� �� �����ϴ� �Լ� 
	�Ű����� : �̿�ü� ����/���� ���п� �ش��ϴ� ����, �Ǹ� ���� 
*/
void fCountUsageRage(int kindUsageRange, int kindAge, int amount)
{
	if(kindUsageRange==1)		// �����̿�� 
	{
		switch(kindAge)
		{
			case 1: countKindUsageRange[0][0] += amount; break;	// ���� 
			case 2: countKindUsageRange[0][1] += amount; break;	// ���� 
			case 3: countKindUsageRange[0][2] += amount; break;	// û�ҳ� 
			case 4: countKindUsageRange[0][3] += amount; break;	// ��� 
			case 5: countKindUsageRange[0][4] += amount; break;	// ���� 
		};
	}
	else if(kindUsageRange==2)	// �ü��̿�� 
	{
		switch(kindAge)
		{
			case 1: countKindUsageRange[1][0] += amount; break;	
			case 2: countKindUsageRange[1][1] += amount; break;
			case 3: countKindUsageRange[1][2] += amount; break;
			case 4: countKindUsageRange[1][3] += amount; break;
			case 5: countKindUsageRange[1][4] += amount; break;
		};
	}
}

/**
	�̿�ð� �������� ���� ���п� ���� Ƽ�� �� �����ϴ� �Լ� 
	�Ű����� : �̿�ð� ����/���� ���п� �ش��ϴ� ����, �Ǹ� ���� 
*/
void fCountUsageTime(int kindUsageTime,  int kindAge, int amount)
{
	if(kindUsageTime==1)		// ���ϱ� 
	{
		switch(kindAge)
		{
			case 1: countKindUsageTime[0][0] += amount; break;	// ���� 
			case 2: countKindUsageTime[0][1] += amount; break;	// ���� 
			case 3: countKindUsageTime[0][2] += amount; break;	// û�ҳ� 
			case 4: countKindUsageTime[0][3] += amount; break;	// ��� 
			case 5: countKindUsageTime[0][4] += amount; break;	// ���� 
		};
	}
	else if(kindUsageTime==2)	// after4
	{
		switch(kindAge)
		{
			case 1: countKindUsageTime[1][0] += amount; break;	
			case 2: countKindUsageTime[1][1] += amount; break;
			case 3: countKindUsageTime[1][2] += amount; break;
			case 4: countKindUsageTime[1][3] += amount; break;
			case 5: countKindUsageTime[1][4] += amount; break;
		};
	}
}

/**
	���ɱ��� �������� Ƽ�� �� �����ϴ� �Լ� 
	�Ű����� : ���� ���п� �ش��ϴ� ����, �Ǹ� ���� 
*/
void fCountAge(int kindAge, int amount)
{
	switch(kindAge)
	{
		case 1: countKindAge[0] += amount;	break;	// ���� 
		case 2: countKindAge[1] += amount;	break;	// ���� 
		case 3: countKindAge[2] += amount;	break;	// û�ҳ� 
		case 4: countKindAge[3] += amount;	break;	// ��� 
		case 5: countKindAge[4] += amount;	break;	// ���� 
	};
}

/**
	������ �������� Ƽ�� �� �����ϴ� �Լ� 
	�Ű����� : ������ ���п� �ش��ϴ� ����, �Ǹ� ���� 
*/
void fCountDiscount(int kindDiscount, int amount)
{
	switch(kindDiscount)
	{
		case 1: countKindDiscount[0] += amount;	break;	// ����� 
		case 2: countKindDiscount[1] += amount;	break;	// ���������� 
		case 3: countKindDiscount[2] += amount;	break;	// �ް��庴 
		case 4: countKindDiscount[3] += amount;	break;	// �ӻ�� 
		case 5: countKindDiscount[4] += amount;	break;	// ���ڳ� ���� 
		case 6: countKindDiscount[5] += amount;	break;	// �ش���� 
	};
}

/**
	�� ����� �����ϴ� �Լ� 
	�Ű����� : �Ǹ� �ݾ�
*/
void fSumPrice(int price)
{
	sumPrice += price;
}

/**
	�� Ƽ�� �� �����ϴ� �Լ� 
	�Ű����� : �Ǹ� �ݾ�
*/
void fCountTickets(int amount)
{
	countTickets += amount;
}




/*	��¥�� ���� ���� Ƽ�� �� ����ϴ� �Լ� */
void printCountDate()
{
	printf("\n\n------------------------------------- ��¥�� ���� �Ǹ���Ȳ -------------------------------------\n\n");

	for(int i=0;i<maxDataCount;i++)
	{
		if(countDate[i][0]==0) break; // ������ �����Ͱ� ����� �ε����� �������� ���� 
		
		printf("%d�� %d�� %d��\t: �� %d �� \t �Ѿ� %d��\n", chageDateFmt(countDate[i][0])[0], chageDateFmt(countDate[i][0])[1], chageDateFmt(countDate[i][0])[2], countDate[i][1], countDate[i][2]);
	}

	printf(" => ���� %d ��\n\n", countTickets);
}

/*	�̿�ü��� ���� ���ɱ��� �� ���� Ƽ�� �� ����ϴ� �Լ� */
void printCountUsageRange()
{
	int SumCountUsageRange[sizeof(countKindUsageRange)/sizeof(countKindUsageRange[0])]  = {0};
	
	printf("\n\n------------------------------------- �̿�ü��� ���� �Ǹ���Ȳ -------------------------------------\n\n");
	
	for(int i=0 ; i<sizeof(countKindUsageRange)/sizeof(countKindUsageRange[0]) ; i++)
	{
		printf("*%s\n", KoKindUsageRange[i]);
		
		for(int j=0 ; j<sizeof(countKindUsageRange[0])/sizeof(int) ; j++)
		{
			SumCountUsageRange[i] = SumCountUsageRange[i] + countKindUsageRange[i][j];
			printf("%s\t : %d ��\n", KoKindAge[j], countKindUsageRange[i][j]);
		}
		
		printf(" => ���� %s : %d ��\n\n", KoKindUsageRange[i], SumCountUsageRange[i]);
	}
}

/*	�̿�ð��� ���� ���ɱ��� �� ���� Ƽ�� �� ����ϴ� �Լ� */
void printCountUsageTime()
{
	int SumCountUsageTime[sizeof(countKindUsageTime)/sizeof(countKindUsageTime[0])]  = {0};
	
	printf("\n\n------------------------------------- �̿�ð��� ���� �Ǹ���Ȳ -------------------------------------\n\n");
	
	for(int i=0 ; i<sizeof(countKindUsageTime)/sizeof(countKindUsageTime[0]) ; i++)
	{
		printf("*%s\n", KoKindUsageTime[i]);
		
		for(int j=0 ; j<sizeof(countKindUsageTime[0])/sizeof(int) ; j++)
		{
			SumCountUsageTime[i] = SumCountUsageTime[i] + countKindUsageTime[i][j];
			printf("%s\t : %d ��\n", KoKindAge[j], countKindUsageTime[i][j]);
		}
		
		printf(" => ���� %s : %d ��\n\n", KoKindUsageTime[i], SumCountUsageTime[i]);
	}
}

/*	���ɱ��п� ���� ���� Ƽ�� ��  ����ϴ� �Լ� */ 
void printCountKindAge()
{
	printf("\n\n------------------------------------- ���� ���п� ���� �Ǹ���Ȳ -------------------------------------\n\n");
	
	for(int i=0 ; i<sizeof(countKindAge)/sizeof(int) ; i++)
	{
		printf("%s\t : %d ��\n", KoKindAge[i], countKindAge[i]);
	}
	
	printf(" => ���� %d ��\n\n", countTickets);
}

/*	�����׿� ���� ���� Ƽ�� �� ����ϴ� �Լ� */ 
void printCountKindDiscount()
{
	printf("\n\n------------------------------------- �����׿� ���� �Ǹ���Ȳ -------------------------------------\n\n");
	
	for(int i=0 ; i<sizeof(countKindDiscount)/sizeof(int) ; i++)
	{
		printf("%s\t : %d ��\n", KoKindDiscount[i], countKindDiscount[i]);
	}
	printf(" => ���� %d ��\n\n", countTickets);
} 



/*	
	��¥ 6�ڸ��� ��,��,�� 2�ڸ� �� �ڸ��� �Լ�
	�Ű����� : ������ 6�ڸ� ����
	��ȯ : ��, ��, �� 2�ڸ��� ����� ũ�� 3�� '�迭 
*/ 
int* chageDateFmt(int date)
{
	// ����ð� �������� 
	struct tm *cur_date;						// time.h�� ���ǵǾ� �ִ� ��¥�� �ð��� ��Ÿ���� ����ü 
	
	time_t curTime=time(NULL);					// #include <time.h> �ʿ�, time_t time(time * timer) : Ÿ�̸Ӱ� Null�� �ƴϸ� timer�� ����Ű�� ������ ���� �ð��� ä���. 
	cur_date=localtime(&curTime);				// localtime(const time_t * timer) : Ÿ�̸Ӱ� ����Ű�� ������ UTC�ð� �������� ����ü�� ��ȯ�� �� �ּҸ� ��ȯ 
	
	// return�� �迭 ����, [0]�� �⵵, [1]�� ��, [2]�� ��¥ 
	int Ddate[3];
	
	// ������ ���غ��� ũ�� 1900���� �ν�, ex) 230101 => 1923.01.01 , 220101 => 2022.01.01
	if((cur_date->tm_year-100) >= date/10000)	Ddate[0] = 2000 + date/10000;
	else										Ddate[0] = 1900 + date/10000;
	Ddate[1] = (date%10000)/100;
	Ddate[2] = date%100;
	
	return Ddate;
}

