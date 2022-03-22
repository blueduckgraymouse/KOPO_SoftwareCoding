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
				 ��, 12~36������ �ش��ϸ� ���� ���̽ü� �̿��� ���� �ٽ� ����� ��. - �ϴ� ����. 
	
	����4 - ������(��� ��� ����, ����)  
		1 ����� ��� : 50% ����, ���� 1�α��� 
		2 ���������� : 50% ����, ���� 1�α��� 
		3 �ް��庴 : 49% ����, ���� 1�α��� 
		4 �ӻ�� : 50% ���� , ���θ�
		5 �ٵ��� �ູī�� : 30%����, ī�忡 ��õ� ������
		6 ����. 
		
	����5 - ����(���� �Է�) , 1~1000������ ���� & ������ ����� �׿� ���� �ż� ����. 
*/

// �迭�� �� - �������� ���� �� �ִ� ������ �ִ� �� 
const int MaxTicketCount = 100;

// ���� 
const int PackageDayAdultPrice = 59000;
const int PackageDayYouthPrice = 52000;
const int PackageDayChildPrice = 47000;
const int PackageDayBabyPrice = 15000;

const int PackageAfter4AdultPrice = 48000;
const int PackageAfter4YouthPrice = 42000;
const int PackageAfter4ChildPrice = 36000;
const int PackageAfter4BabyPrice = 15000;

const int OnlyParkDayAdultPrice = 56000;
const int OnlyParkDayYouthPrice = 50000;
const int OnlyParkDayChildPrice = 46000;
const int OnlyParkDayBabyPrice = 15000;

const int OnlyParkAfter4AdultPrice = 45000;
const int OnlyParkAfter4YouthPrice = 40000;
const int OnlyParkAfter4ChildPrice = 35000;
const int OnlyParkAfter4BabyPrice = 15000;

// ���� 
const int AdultMaxAge = 64;
const int AdultMinAge = 19;
const int YouthMaxAge = 18;
const int YouthMinAge = 13;
const int ChildMaxAge = 12;
const int ChildMinMonth = 36;
const int Baby1MaxMonth = 35; 
const int Baby1MinMonth = 12;
const int Baby2MaxMonth = 11;
const int OlderMinAge = 65;

// ������
const float DisabledDiscountRate = 0.5;
const float NationalMeritDiscountRate = 0.5;
const float SoldierDiscountRate = 0.49;
const float PregnantDiscountRate = 0.5;
const float MultipleChildrenDiscountRate = 0.3;

// ����� ���� �Լ� 
int 	selectOption0();			// option0, �̿� �ü� ���� , return : ������ �ش��ϴ� ���� - 1�̸� �����̿��, 2�� ��ũ�̿�� 
int 	selectOption1();			// option1, �̿� �ð� ���� , return : ������ �ش��ϴ� ���� - 1�̸� ���ϱ�, 2�� after4 
int 	selectOption2();			// option2, �ֹι�ȣ �Է� �Է¹޾� ���ɱ����� ����, return : ���� ���п� �ش��ϴ� ����, ���� 1, ���� 2, û�ҳ� 3, ���4, ���� 5 
int 	selectOption3();			// option3, ������ ����
int 	selectOption4(int option3);	// option4, ���� �Է� 
int 	checkTotalPrice(int option0, int option1, int option2, int option3, int option4); 	// PriceCheck(), checkDiscountRate()�� �̿��Ͽ� Ȯ���� ������ ������,������ ���� �Ѿ� ��� 

int 	PriceCheck(int option0, int option1, int option2);	// �ɼ�0~2�� �������� ���� Ȯ�� 
float 	checkDiscountRate(int option5);						// �ɼ�3�� �������� ������ Ȯ�� 

int		todayIs();					// ���ó�¥ ��ȯ, 20220321

char 	*option0toKo(int option0);	// �̿�ü� ���� ��ȣ�� �ش��ϴ� �ѱ۷� ��ȯ 
char 	*option1toKo(int option1);	// �̿�ð� ���� ��ȣ�� �ش��ϴ� �ѱ۷� ��ȯ 
char 	*option2toKo(int option2);	// ���� 	���� ��ȣ�� �ش��ϴ� �ѱ۷� ��ȯ 
char 	*option3toKo(int option3);	// ������ ���� ��ȣ�� �ش��ϴ� �ѱ۷� ��ȯ 

int 	checkContinue();			// �߱��� �������� ������� Ȯ�� 




int main()
{
	int options[MaxTicketCount][6]={0};	// �̿�ü� ���� , �̿� �ð� ����, �ֹε�Ϲ�ȣ, ������ ����, Ƽ�� ����, �Ѿ� 
	
	printf("�Ե����忡 ���� �������� ȯ���մϴ�.\n");
	
	for(int i=0;i<100;i++)
	{
		options[i][0]=selectOption0();				// option0, �̿� �ü� ���� , return : 1�̸� �����̿��, 2�� ��ũ�̿�� 
		
		options[i][1]=selectOption1();				// option1, �̿� �ð� ���� , return : 1�̸� ���ϱ�, 2�� after4 
		
		options[i][2]=selectOption2();				// option2, �ֹι�ȣ �Է� �Է¹޾� ���ɱ����� ����, return : ���� 1, ���� 2, û�ҳ� 3, ���4, ���� 5 
									
		options[i][3]=selectOption3();				// option3, ������ ����, return : ����� 1, ���������� 2, �ް��庴 3, �ӻ�� 4, ���ڳ� ���� 5, ���� 6  
		
		options[i][4]=selectOption4(options[i][3]);	// option4, ���� ���� �Է� 
		
		options[i][5]=checkTotalPrice(options[i][0], options[i][1], options[i][2],options[i][3], options[i][4]);	// �� û�� �ݾ�  ��� �� ��� 
		
		if(!checkContinue()) break;
	}
	
	// ������ ���	
	printf("\n\n------------------------�Ե�����------------------------\n\n");
	printf("�̿�ü�\t�̿�ð�\t���ɱ���\t������\t����\t�ݾ�\n\n");
	int sumTotalPrice=0;
	for(int i=0;i<100;i++)
	{
		if(options[i][0]==0) break;				// ����� ���� ���� �迭�̸� �ݺ��� ���� 
		
		// �̿�ü�, �̿�ð�, ���ɱ���, ������,���� ���� 
		printf("%s\t%s\t\t%s\t\t%s\t%d\t%d\n", option0toKo(options[i][0]), option1toKo(options[i][1]), option2toKo(options[i][2]), option3toKo(options[i][3]), options[i][4], options[i][5]);
		sumTotalPrice+=options[i][5];
	}
	printf("\n����� �Ѿ��� %d�Դϴ�.\n�����մϴ�.\n\n", sumTotalPrice);
	
	return 0;
}

// �̿�ü� ���� , return : 1�̸� �����̿��, 2�� ��ũ�̿�� 
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

// �̿� �ð� ���� , return : 1�̸� ���ϱ�, 2�� after4 
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

// �ֹι�ȣ �Է� �Է¹޾� ���ɱ����� ����, return : ���� ���п� �ش��ϴ� ����, ���� 1, ���� 2, û�ҳ� 3, ���4, ���� 5 
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
	if(countAgeYears>=65)																	dAge=1; 	// ����(��65�� �̻�)
	else if(countAgeYears>=AdultMinAge&&countAgeYears<=AdultMaxAge)							dAge=2;		// ����(�� 19~64��) 
	else if(countAgeYears>=YouthMinAge&&countAgeYears<=YouthMaxAge) 						dAge=3; 	// û�ҳ�(�� 13~18��) 
	else if(countAgeYears*12+countAgeMonths>=ChildMinMonth&&countAgeYears<=ChildMaxAge)		dAge=4;		// ���(36����~��12��)
	else if(countAgeYears*12+countAgeMonths<Baby1MaxMonth)									dAge=5; 	// ����(~36���� �̸�)
	
	return dAge; 
}

// ������ ���� , return : 1�̸� �����, 2�� ����������, 3�� �ް��庴, 4�� �ӻ��, 5�� ���ڳడ��, 6�� ���� 
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

// ���� ���� �Է� 
int selectOption4(int option3)
{
	int amount=0;
	int d=1;
	do
	{
		printf("\n*������ �Է��ϼ���.(���� ���� ���� : 1~1000��)\n");
		scanf("%d",&amount);
		//printf("%d*%d=%d\n",amount,price,price*amount);	
		
		// ������ǿ� ���� Ƽ�� ���� ���� ���� 
		if((option3==1 || option3==2 || option3==3) && amount>2)
		{
			switch(option3)
			{
				case 1:	printf("����������� ����1�α��� ���밡���մϴ�.(�ִ� 2��)\n"); break;
				case 2:	printf("���������� ������ ����1�α��� ���밡���մϴ�.(�ִ� 2��)\n"); break;
				case 3:	printf("�ް� �庴 ������ ����1�α��� ���밡���մϴ�.(�ִ� 2��)\n"); break;
			};
		}
		else if(option3==4 && amount!=1)
		{
			printf("�ӻ�� ������ ���θ� ������ �����մϴ�.(�ִ� 1��)\n �ٽ� �Է��ϼ���.\n");
		}
		else if(option3==5)
		{
			int d=1;
			while(d==1)
			{
				printf("���ڳ� ���� ������ ī�忡 ��õ� ������ ������ �����մϴ�. �Է��Ͻ� ������ �½��ϱ�??  ������ 1, �ٽ� �Է��Ͻ÷��� 2�� �Է��ϼ���.\n");
				scanf("%d",&d);
				if(d==1) 					return amount;
				else if(d != 1 && d!=2)		printf("�߸��� �Է��Դϴ�. 1 �Ǵ� 2�� �Է��ϼ���\n");
			}
		} 
		else if(!(1<=amount&&amount<=1000)) printf("�ּ� ���ż����� 1, �ִ� ���� ������ 1000���Դϴ�.\n");
		else 		d=0;
	}while(d);
	
	return amount;
}

// �����ݾ� ��� �� ��� 
int checkTotalPrice(int option0, int option1, int option2, int option3, int option4)
{
	int originalPrice = PriceCheck(option0, option1, option2);	// �̿� �ü�, �̿�ð�, ���� �������� ���� Ȯ�� 

	float discountRate = checkDiscountRate(option3);						// ���������� ������ Ȯ�� 
	
	int discountedPrice = originalPrice*discountRate;					// ���ε� 1�� �� Ƽ�� ���� 
	
	int totalPrice = discountedPrice * option4;							// �� Ƽ�� ���� 
	
	printf("\n*������ %d �� �Դϴ�.\n",totalPrice);			// 3�ڸ����� ��� ����ϴ� �޼��� ���� ���� ���� 
	printf("�����մϴ�.\n");
	
	return totalPrice; 
}

// Ƽ�� ���� ����, return : ���ǿ� ���� ���� ���� 
int PriceCheck(int option0, int option1, int option2)
{
	int price=0;
	if(option2==2)		// ����(�� 19~64��) 
	{
		if(option0==1&&option1==1)		price=PackageDayAdultPrice;
		else if(option0==1&&option1==2)	price=PackageAfter4AdultPrice;
		else if(option0==2&&option1==1)	price=OnlyParkDayAdultPrice;
		else if(option0==2&&option1==2)	price=OnlyParkAfter4AdultPrice;
	} 
	else if(option2==3) // û�ҳ�(�� 13~18��) 
	{
		if(option0==1&&option1==1)		price=PackageDayYouthPrice;
		else if(option0==1&&option1==2) price=PackageAfter4YouthPrice;
		else if(option0==2&&option1==1)	price=OnlyParkDayYouthPrice;
		else if(option0==2&&option1==2)	price=OnlyParkAfter4YouthPrice;
	}
	else if(option2==4||option2==1)	// ���(36����~��12��) , ����(��65�� �̻�) 
	{
		if(option0==1&&option1==1)		price=PackageDayChildPrice;
		else if(option0==1&&option1==2)	price=PackageAfter4ChildPrice;
		else if(option0==2&&option1==1)	price=OnlyParkDayChildPrice;
		else if(option0==2&&option1==2)	price=OnlyParkAfter4ChildPrice;
	}
	else if(option2==5)			// ����(~36���� �̸�)
	{
		if(option0==1&&option1==1)		price=PackageDayBabyPrice;
		else if(option0==1&&option1==2)	price=PackageAfter4BabyPrice;
		else if(option0==2&&option1==1)	price=OnlyParkDayBabyPrice;
		else if(option0==2&&option1==2)	price=OnlyParkAfter4BabyPrice;
	}
	return price; 
}

// ������ ���� , return : �����׿� ���� ������ 
float checkDiscountRate(int option3)
{
	float discountRate=1;

	switch(option3)
	{
		case 1: discountRate = 1*(1-DisabledDiscountRate); 		break;
		case 2: discountRate = 1*(1-NationalMeritDiscountRate); 	break;
		case 3: discountRate = 1*(1-SoldierDiscountRate); 		break;
		case 4: discountRate = 1*(1-PregnantDiscountRate); 		break;
		case 5: discountRate = 1*(1-MultipleChildrenDiscountRate);break;
		case 0: discountRate=1; 									break;	
	}
	return discountRate;
}
 
// ���� ��¥ ��ȯ , return : ���� ��¥ 6�ڸ� ex) 220322 
int todayIs()
{
	// �ý������� ����  ���� �ð� �������� 
	struct tm *cur_date;						// time.h�� ���ǵǾ� �ִ� ��¥�� �ð��� ��Ÿ���� ����ü 
	
	time_t curTime=time(NULL);					// #include <time.h> �ʿ�, time_t time(time * timer) : Ÿ�̸Ӱ� Null�� �ƴϸ� timer�� ����Ű�� ������ ���� �ð��� ä���. 
	cur_date=localtime(&curTime);				// localtime(const time_t * timer) : Ÿ�̸Ӱ� ����Ű�� ������ UTC�ð� �������� ����ü�� ��ȯ�� �� �ּҸ� ��ȯ 
	
	return ((cur_date->tm_year-100)*10000 + (cur_date->tm_mon+1)*100 + (cur_date->tm_mday));
} 
 
// �߰� ������ Ƽ�� �ִ��� Ȯ�� 
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

// ���� ������ �ѱ۷� ��ȯ 
char *option0toKo(int option0)
{
	char *kinds=" ";
	
	if(option0==1) 	kinds="�����̿��";	
	else			kinds="��ũ�̿��";
	
	return kinds;
}
char *option1toKo(int option1)
{
	char *kinds = " ";
	
	if(option1==1) 		kinds="���ϱ�";
	else if(option1==2)	kinds="after4";
	
	return kinds; 
}
char *option2toKo(int option2)
{
	char *kinds = " ";
	
	if(option2==1) 	kinds="����";
	else if(option2==2) 	kinds="����";
	else if(option2==3) 	kinds="û�ҳ�";
	else if(option2==4) 	kinds="���";
	else if(option2==5) 	kinds="����";
	else if(option2==6)		kinds="����";
	
	return kinds; 
}
char *option3toKo(int option3)
{
	char *kinds = " ";
	
	switch(option3)
	{
		case 1: kinds="�����    ";	break; 
		case 2: kinds="����������";	break;
		case 3: kinds="����      "; break;
		case 4: kinds="�ӻ��    ";	break;
		case 5: kinds="�ٵ��̰���";	break;
		case 6: kinds="����      ";	break;
	}
	
	return kinds;
}





