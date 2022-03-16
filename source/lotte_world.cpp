#include <stdio.h>
#include <time.h>

/* �䱸���� �м�
 
	���ǿ� ���� �Ե����� ����� ���� ��� ���α׷�
	
	����1 - �̿�ü�(����) 
		�����̿�� / ��ũ�̿� 
	
	����2 - �̿�ð�(����) 
		���ϱ�(1Day) / ���ı�(After4)
	
	����3 - ����(�ֹι�ȣ �Է� )
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

int main()
{
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

	struct tm *cur_date;		// time.h�� ���ǵǾ� �ִ� ��¥�� �ð��� ��Ÿ���� ����ü 

	//�ɼ�
	int option1=0;		// �̿�ü� ���� 
	int option2=0;		// �̿�ð� ���� 
	long long id=0;		// �ֹι�ȣ 
	int amount=0; 		// ���� 
	int option5=0;		// ������ ���� 
	
	// ���� 
	int price=0;		// ���̿� ���� ���� 
	float total_discount_rate=1;	// �����׿� ���� ���������� 
	int total_price=0;	// �� ������ ���� ���� 
	int final_price=0;	// �������� 
	
	printf("�Ե����忡 ���� �������� ȯ���մϴ�.\n");

// �ɼ�1 �Է� - �̿�ü� 
	printf("�̿�ü��� �����ϼ���.\n");
	printf("1. �����̿��\n");
	printf("2. ��ũ�̿��\n");
	
	scanf("%d",&option1); 

// �ɼ�2 �Է� - �̿�ð� 
	printf("������ �����ϼ���.\n");
	printf("1. 1Day \n");
	printf("2. After4 \n");
	
	scanf("%d",&option2); 

// �ɼ�3 �Է� - �ֹι�ȣ 
	printf("�ֹι�ȣ�� �Է��ϼ���.(-���� ��ȣ�� �Է�)\n");

	scanf("%lld",&id);							// 13�ڸ��̹Ƿ� �˳��� long long Ÿ�� ��� 
	
	// 13�ڸ� �ֹι�ȣ���� �� ������� ����
	int birth = id/10000000;					// �� 6�ڸ� ����
	int years =0;
	if((id/1000000)%10==3||(id/1000000)%10==4)	// �з��̳� ���̺� �Ǻ� 
	{
		years = birth/10000+2000;				// 2000������ ��� 
	}
	else
	{
		years = birth/10000+1900;
	}
	int months = (birth%10000)/100;
	int days = (birth%100);

	
	 // �ý������� ����  ���� �ð� �������� 
	time_t curTime=time(NULL);					// #include <time.h> �ʿ�, time_t time(time * timer) : Ÿ�̸Ӱ� Null�� �ƴϸ� timer�� ����Ű�� ������ ���� �ð��� ä���. 
	cur_date=localtime(&curTime);				// localtime(const time_t * timer) : Ÿ�̸Ӱ� ����Ű�� ������ UTC�ð� �������� ����ü�� ��ȯ�� �� �ּҸ� ��ȯ 
	
	 // �� �� ���� ��� 
	int count_age_days=cur_date->tm_mday-days;
	int count_age_months=(cur_date->tm_mon+1)-months;
	int count_age_years=cur_date->tm_year+1900-years; 
	if(count_age_days<0)		// ex) 3������ 5���� ���� ��� �����̹Ƿ� ���� ó�� 
	{
		count_age_days+=30;
		count_age_months--;
	} 
	
	if(count_age_months<0)		// ex) 15�Ͽ��� 27���� ���� ��� �����̹Ƿ� ���� ó 
	{
		count_age_months+=12;
		count_age_years--;
	} 

	// ���̿� ����� ���� ���� Ȯ��
	if((count_age_years>=Adult_Min_Age&&count_age_years<=Adult_Max_Age)&&option1==1&&option1==1) // ����,�����̿��,���� 
		price = Package_Day_Adult_Price;
	else if((count_age_years>=Adult_Min_Age&&count_age_years<=Adult_Max_Age)&&option1==1&&option1==2) // ����,�����̿��,���� 
		price = Package_After4_Adult_Price;
	else if((count_age_years>=Adult_Min_Age&&count_age_years<=Adult_Max_Age)&&option1==2&&option1==1) // ����,��ũ�̿��,���� 
		price = OnlyPark_Day_Adult_Price;
	else if((count_age_years>=Adult_Min_Age&&count_age_years<=Adult_Max_Age)&&option1==2&&option1==2) // ����,��ũ�̿��,���� 
		price = OnlyPark_After4_Adult_Price;
		
	else if((count_age_years>=Youth_Min_Age&&count_age_years<=Youth_Max_Age)&&option1==1&&option1==1) // û�ҳ�,�����̿��,���� 
		price = Package_Day_Youth_Price;
	else if((count_age_years>=Youth_Min_Age&&count_age_years<=Youth_Max_Age)&&option1==1&&option1==2) // û�ҳ�,�����̿��,���� 
		price = Package_After4_Youth_Price;
	else if((count_age_years>=Youth_Min_Age&&count_age_years<=Youth_Max_Age)&&option1==2&&option1==1) // û�ҳ�,��ũ�̿��,���� 
		price = OnlyPark_Day_Youth_Price;
	else if((count_age_years>=Youth_Min_Age&&count_age_years<=Youth_Max_Age)&&option1==2&&option1==2) // û�ҳ�,��ũ�̿��,���� 
		price = OnlyPark_After4_Youth_Price;
	
	else if(((count_age_years*12+count_age_months>=Child_Min_Month&&count_age_years<=Child_Max_Age)||count_age_years>=65)&&option1==1&&option1==1) // ���,�����̿��,���� 
		price = Package_Day_Child_Price;
	else if(((count_age_years*12+count_age_months>=Child_Min_Month&&count_age_years<=Child_Max_Age)||count_age_years>=65)&&option1==1&&option1==2) // ���,�����̿��,���� 
		price = Package_After4_Child_Price;
	else if(((count_age_years*12+count_age_months>=Child_Min_Month&&count_age_years<=Child_Max_Age)||count_age_years>=65)&&option1==2&&option1==1) // ���,��ũ�̿��,���� 
		price = OnlyPark_Day_Child_Price;
	else if(((count_age_years*12+count_age_months>=Child_Min_Month&&count_age_years<=Child_Max_Age)||count_age_years>=65)&&option1==2&&option1==2) // ���,��ũ�̿��,���� 
		price = OnlyPark_After4_Child_Price;
	
	else if((count_age_years*12+count_age_months<=Baby1_Max_Month)&&option1==1&&option1==1) // ���,�����̿��,���� 
		price = Package_Day_Baby_Price;
	else if((count_age_years*12+count_age_months<=Baby1_Max_Month)&&option1==1&&option1==2) // ���,�����̿��,���� 
		price = Package_After4_Baby_Price;
	else if((count_age_years*12+count_age_months<=Baby1_Max_Month)&&option1==2&&option1==1) // ���,��ũ�̿��,���� 
		price = OnlyPark_Day_Baby_Price;
	else if((count_age_years*12+count_age_months<=Baby1_Max_Month)&&option1==2&&option1==2) // ���,��ũ�̿��,���� 
		price = OnlyPark_After4_Baby_Price;
	
// �ɼ� 4 �Է�  - ���� 
	printf("������ �Է��ϼ���.\n");
	scanf("%d",&amount);

	total_price = price*amount;
	
// �ɼ�5 �Է� - ��� ����
	printf("�������� �����ϼ���.\n");
	printf("1. �����\n");
	printf("2. ����������\n");
	printf("3. �ް��庴\n");
	printf("4. �ӻ��\n");
	printf("5. ���ڳడ��\n");
	printf("6. ����\n");
	
	scanf("%d",&option5);  
	
	// �����׿� ����  ���� ������ �Ǵ� 
	switch(option5)
	{
		case 1: total_discount_rate = 1*(1-Disabled_DiscountRate); break;
		case 2: total_discount_rate = 1*(1-NationalMerit_DiscountRate); break;
		case 3: total_discount_rate = 1*(1-Soldier_DiscountRate); break;
		case 4: total_discount_rate = 1*(1-Pregnant_DiscountRate); break;
		case 5: total_discount_rate = 1*(1-MultipleChildren_DiscountRate); break;
		case 0: break;	
	}
	
// �������� ��� 
	final_price=total_price*total_discount_rate;
	
	printf("������ %d �� �Դϴ�.\n",final_price);
	printf("�����մϴ�.");
	
	return 0;
}
