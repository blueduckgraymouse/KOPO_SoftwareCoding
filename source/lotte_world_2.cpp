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
				 ��, 12~36������ �ش��ϸ� ���� ���̽ü� �̿��� ���� �ٽ� ����� ��.
	
	����4 - ����(���� �Է�) 
	
	����5 - ������(��� ��� ����, ����)
		����
		����� ��� : 50% ����, ���� 1�α��� 
		���������� : 50% ����, ���� 1�α��� 
		�ް��庴 : 49% ����, ���� 1�α��� 
		�ӻ�� : 50% ���� , ���θ�
		�ٵ��� �ູī�� : 30%����, ī�忡 ��õ� ������
*/

// ���� 
const int Package_Day_Adult_Price = 59000;
const int Package_Day_Youth_Price = 52000;
const int Package_Day_Child_Price = 47000;
const int Package_Day_Baby_Price = 15000;

const int Package_After4_Adult_Price = 48000;
const int Package_After4_Youth_Price = 42000;
const int Package_After4_Child_Price = 36000;
const int Package_After4_Baby_Price = 15000;

const int OnlyPark_Day_Adult_Price = 56000;
const int OnlyPark_Day_Youth_Price = 50000;
const int OnlyPark_Day_Child_Price = 46000;
const int OnlyPark_Day_Baby_Price = 15000;

const int OnlyPark_After4_Adult_Price = 45000;
const int OnlyPark_After4_Youth_Price = 40000;
const int OnlyPark_After4_Child_Price = 35000;
const int OnlyPark_After4_Baby_Price = 15000;

// ���� 
const int Adult_Max_Age = 64;
const int Adult_Min_Age = 19;
const int Youth_Max_Age = 18;
const int Youth_Min_Age = 13;
const int Child_Max_Age = 12;
const int Child_Min_Month = 36;
const int Baby1_Max_Month = 35; 
const int Baby1_Min_Month = 12;
const int Baby2_Max_Month = 11;
const int Older_Min_Age = 65;

// ������
const float Disabled_DiscountRate = 0.5;
const float NationalMerit_DiscountRate = 0.5;
const float Soldier_DiscountRate = 0.49;
const float Pregnant_DiscountRate = 0.5;
const float MultipleChildren_DiscountRate = 0.3;

// ����� ���� �Լ� 
int 	selectOption1();
int 	selectOption2();
long long insertID();
int 	checkPrice(int option1, int option2, long long ID);
int 	selectOption4();
int 	selectOption5();
float 	checkDiscountRate(int option5);
int 	calAndPrintTotalPrice(int total_price, float total_discount_rate); 
int		today();			// ���ó�¥ ��ȯ, 20220321
const char* 	ticketKinds1(int option1);
const char* 	ticketKinds2(int option2);
const char* 	discountKinds(int option5);
int 	checkContinue();



int main()
{
	int final_price=0;		// ��������
	int d=1;
	
	/*
		�ݺ��� ��� ���� �����ϴ� ��
		 -> ��� �ݺ����� �𸥴�?
		 	���̵�� 
		  	1) �迭 �����Ҵ�?
			2) ������ ���Ϸ� �����ؾ��ϴ°� �ݺ��� ������ ���� ����Ǹ� �����ϰ� �װ� �ٽ� �о���� ���?
	*/
	
	FILE *fp;
	fp = fopen("report.csv","a");
	fprintf(fp, "��¥, �̿�ü�, �̿�Ⱓ, ���� ����, ����, ����, ������\n");
	while(d)
	{
		//�ɼǰ� 
		int 	option1=0;		// �̿�ü� ����(1, 2)
		int 	option2=0;		// �̿�ð� ����(1, 2)
		long long ID=0;			// �ֹι�ȣ(13�ڸ�) 
		int 	amount=0; 		// ����(1~1000)
		int 	option5=0;		// ������ ���� (1~6)
		
		// ���� 
		int 	price=0;		// ���̿� ���� Ƽ�� �� ���� ���� 
		float 	total_discount_rate=1;	// �����׿� ���� ����� ������
		int 	sum_price=0; 	// �� ������ ���� ����, price * amount 
		int 	total_price=0;	// ���ΰ� ������ ����� û�� �ݾ�


		printf("�Ե����忡 ���� �������� ȯ���մϴ�.\n");
		
		option1=selectOption1();	// �̿�ü� ���� 
		
		option2=selectOption2();	// �̿�ð� ���� 
		
		ID=insertID();				// �ֹι�ȣ �Է� 
		
		price=checkPrice(option1,option2,ID);	// �ֹι�ȣ�� ǥ ���� ���� 
				
		amount=selectOption4();		// ���� ���� �Է� 
		
		sum_price=price * amount; 	// ���� ���� �Է� �� ������ ���� ����

		option5=selectOption5();	// ������ ���� 
		
		total_discount_rate=checkDiscountRate(option5);	// �����׿� ���� ������ ���� 

		total_price=calAndPrintTotalPrice(sum_price,total_discount_rate);	// �� û�� �ݾ�  ��� �� ��� 
		
		fprintf(fp,"%d,%s,%s,%d,%d,%s\n",today(),ticketKinds1(option1),ticketKinds2(option2),"���ɱ���",amount,total_price,discountKinds(option5));// ���� ������ ���� �����ϴ� �Լ� �߰� ����
		
		d=checkContinue();
	}
	
	fclose(fp);
	return 0;
}



// �̿�ü� ���� 
int selectOption1()
{
	int option1=0;
	
	do
	{
		printf("�̿�ü��� �����ϼ���.\n");
		printf("1. �����̿��\n");
		printf("2. ��ũ�̿��\n");
	
		scanf("%d",&option1); 
		//printf("%d\n",option1);
		
		if(!(option1==1||option1==2)) printf("�߸��� �Է��Դϴ�. 1 �Ǵ� 2�� �Է��ϼ���.\n");
	} while(!(option1==1||option1==2));

	return option1;
}


// �̿� �ð� ���� 
int selectOption2()
{
	int option2=0;
	
	do
	{
		printf("������ �����ϼ���.\n");
		printf("1. 1Day \n");
		printf("2. After4 \n");
		
		scanf("%d",&option2); 
		//printf("%d\n",option2);
		
		if(!(option2==1||option2==2)) printf("�߸��� �Է��Դϴ�. 1 �Ǵ� 2�� �Է��ϼ���.\n");
	} while(!(option2==1||option2==2));

	return option2;
}


// �ֹι�ȣ �Է� 
long long insertID()
{
	long long ID = 0;
	
	do
	{
		printf("�ֹι�ȣ�� �Է��ϼ���.(-���� 13�ڸ� �Է�)\n");

		scanf("%lld",&ID);							// 13�ڸ��̹Ƿ� �˳��� long long Ÿ�� ���
		//printf("%lld\n",ID);
		
		if(!(1000000000000<=ID&&ID<=9999999999999)) printf("�߸��� �Է��Դϴ�. - ���� 13�ڸ��� �Է��ϼ���.\n");
	} while(!(1000000000000<=ID&&ID<=9999999999999));
	
	return ID;
}


// �ֹι�ȣ�� ǥ ���� ���� 
int checkPrice(int option1, int option2, long long ID)
{
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
												

	
	// �ý������� ����  ���� �ð� �������� 
	struct tm *cur_date;						// time.h�� ���ǵǾ� �ִ� ��¥�� �ð��� ��Ÿ���� ����ü 
	
	time_t curTime=time(NULL);					// #include <time.h> �ʿ�, time_t time(time * timer) : Ÿ�̸Ӱ� Null�� �ƴϸ� timer�� ����Ű�� ������ ���� �ð��� ä���. 
	cur_date=localtime(&curTime);				// localtime(const time_t * timer) : Ÿ�̸Ӱ� ����Ű�� ������ UTC�ð� �������� ����ü�� ��ȯ�� �� �ּҸ� ��ȯ 
	
	 // �� �� ���� ��� 
	int count_age_days=cur_date->tm_mday-days;
	int count_age_months=(cur_date->tm_mon+1)-months;
	int count_age_years=cur_date->tm_year+1900-years; 
	
	if(count_age_days<0)						// ex) 3������ 5���� ���� ��� �����̹Ƿ� ���� ó�� 
	{
		count_age_days+=30;
		count_age_months--;
	} 
	
	if(count_age_months<0)						// ex) 15�Ͽ��� 27���� ���� ��� �����̹Ƿ� ���� ó�� 
	{
		count_age_months+=12;
		count_age_years--;
	} 
 
 	//printf("����ð� : %d/%d/%d\n",cur_date->tm_year+1900,cur_date->tm_mon+1,cur_date->tm_mday);
 	//printf("�� �������:%d/%d/%d\n",years,months,days);
 	//printf("��ƿ� ����:%d/%d/%d\n",count_age_years,count_age_months,count_age_days);
 
	// ���̿� ����� ���� ���� Ȯ��
	int price=0;
	
	if(count_age_years>=Adult_Min_Age&&count_age_years<=Adult_Max_Age)		// ����(�� 19~64��) 
	{
		if(option1==1&&option1==1)		price=Package_Day_Adult_Price;
		else if(option1==1&&option1==2)	price=Package_After4_Adult_Price;
		else if(option1==2&&option1==1)	price=OnlyPark_Day_Adult_Price;
		else if(option1==2&&option1==2)	price=OnlyPark_After4_Adult_Price;
	} 
	else if(count_age_years>=Youth_Min_Age&&count_age_years<=Youth_Max_Age) // û�ҳ�(�� 13~18��) 
	{
		if(option1==1&&option1==1)		price=Package_Day_Youth_Price;
		else if(option1==1&&option1==2) price=Package_After4_Youth_Price;
		else if(option1==2&&option1==1)	price=OnlyPark_Day_Youth_Price;
		else if(option1==2&&option1==2)	price=OnlyPark_After4_Youth_Price;
	}
	else if((count_age_years*12+count_age_months>=Child_Min_Month&&count_age_years<=Child_Max_Age)||count_age_years>=65)	// ���(36����~��12��) , ����(��65�� �̻�) 
	{
		if(option1==1&&option1==1)		price=Package_Day_Child_Price;
		else if(option1==1&&option1==2)	price=Package_After4_Child_Price;
		else if(option1==2&&option1==1)	price=OnlyPark_Day_Child_Price;
		else if(option1==2&&option1==2)	price=OnlyPark_After4_Child_Price;
	}
	else if(count_age_years*12+count_age_months<Baby1_Max_Month)			// ����(~36���� �̸�)
	{
		if(option1==1&&option1==1)		price=Package_Day_Baby_Price;
		else if(option1==1&&option1==2)	price=Package_After4_Baby_Price;
		else if(option1==2&&option1==1)	price=OnlyPark_Day_Baby_Price;
		else if(option1==2&&option1==2)	price=OnlyPark_After4_Baby_Price;
	}
	
	//printf("����:%d\n",price);
	
	return price;
}

// ���� ���� �Է� 
int selectOption4()
{
	int amount=0;
	
	do
	{
		printf("������ �Է��ϼ���.(���� ���� ���� : 1~1000��)\n");
		scanf("%d",&amount);
		//printf("%d*%d=%d\n",amount,price,price*amount);	
	
		if(!(1<=amount&&amount<=1000)) printf("�߸��� �Է��Դϴ�. 1~1000�� ���ڸ� �Է��ϼ���.\n");
	}while(!(1<=amount&&amount<=1000));
	
	return amount;
}


// ������ ���� 
int selectOption5()
{
	int option5=0;
	do
	{
		printf("�������� �����ϼ���.\n");
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


// ������ ���� 
float checkDiscountRate(int option5)
{
	float discount_rate=1;

	switch(option5)
	{
		case 1: discount_rate = 1*(1-Disabled_DiscountRate); 		break;
		case 2: discount_rate = 1*(1-NationalMerit_DiscountRate); 	break;
		case 3: discount_rate = 1*(1-Soldier_DiscountRate); 		break;
		case 4: discount_rate = 1*(1-Pregnant_DiscountRate); 		break;
		case 5: discount_rate = 1*(1-MultipleChildren_DiscountRate);break;
		case 0: discount_rate=1; 									break;	
	}
	return discount_rate;
}


// �����ݾ� ��� �� ��� 
int calAndPrintTotalPrice(int total_price, float total_discount_rate)
{
	int final_price=total_price*total_discount_rate;
	
	printf("������ %d �� �Դϴ�.\n",final_price);			// 3�ڸ����� ��� ����ϴ� �޼��� ���� ���� ���� 
	printf("�����մϴ�.\n");
	
	return final_price; 
}
 
// ���� ��¥ ��ȯ
int today()
{
	// �ý������� ����  ���� �ð� �������� 
	struct tm *cur_date;						// time.h�� ���ǵǾ� �ִ� ��¥�� �ð��� ��Ÿ���� ����ü 
	
	time_t curTime=time(NULL);					// #include <time.h> �ʿ�, time_t time(time * timer) : Ÿ�̸Ӱ� Null�� �ƴϸ� timer�� ����Ű�� ������ ���� �ð��� ä���. 
	cur_date=localtime(&curTime);				// localtime(const time_t * timer) : Ÿ�̸Ӱ� ����Ű�� ������ UTC�ð� �������� ����ü�� ��ȯ�� �� �ּҸ� ��ȯ 
	
	return ((cur_date->tm_year-100)*10000 + (cur_date->tm_mon+1)*100 + (cur_date->tm_mday));
} 
 
// �̿� �ü� ���� 
const char* ticketKinds1(int option1)
{
	/*
	char kinds[20] = "" ;
	
	if(option1==1) 	kinds="�����̿��";
	else			kinds="��ũ�̿��";
	
	return kinds; 
	*/
	return "�����̿��";
}
 
// �̿�ð� ���� 
const char* ticketKinds2(int option2)
{
	char[20] kinds = "";
	
	if(option1==1) 	kinds="���ϱ�";
	else			kinds="after4";
	
	return kinds; 
}
 
/* ���� ���� ��ȯ   -> �̰� checkprice()�� ���� Ȯ�� �ϴ� ���μ����� ��ħ. ���� Ȯ���ϴ� ���μ����� �Լ��� ���� ���⼭�� ����ϵ��� �ٲ����. 
char* ageKinds(option2)
{

} */
 
// ���ο�� ���� ��ȯ 
const char* discountKinds(int option5)
{
	char[10] kinds = "";
	
	switch(option5)
	{
		case 1: kind="�����";		break; 
		case 2: kind="����������";	break;
		case 3: kind="����"; 		break;
		case 4: kind="�ӻ��";		break;
		case 5: kind="�ٵ��̰���";	break;
		case 6: kind="����";		break;
	}
	
	return kind;
}
 
// �߰� ������ Ƽ�� �ִ��� Ȯ�� 
int checkContinue()
{
	int d = 1;
	
	do
	{
		printf("��� Ƽ���� �߱��Ͻðڽ��ϱ�?\n");
		printf("0. ���� �� �հ� ����\n");
		printf("1. Ƽ�� �߰� �߱�\n");
		
		scanf("%d",&d);
		
		if(!(d==1||d==0)) printf("�߸��� �Է��Դϴ�. 0 �Ǵ� 1�� �Է��ϼ���.\n");
	} while(!(d==1||d==0));

	return d;
}

