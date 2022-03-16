#include <stdio.h>
#include <time.h>

/*
	조건에 따른 롯데월드 입장권 가격 계산 프로그램
	
	조건1 - 이용시설(택일) 
		종합이용권 / 파크이용 
	
	조건2 - 이용시간(택일) 
		종일권(1Day) / 오후권(After4)
	
	조건3 - 나이(주민번호 입력 )
		어른 : 만 19세 이상 ~ 만 64세 이하 
		청소년 : 만 13세 이상 ~ 만 18세 이하 
		어린이 : 36개월이상 ~ 만 12세 이하
				 만 65세 이상	
		베이비 : 0~12개월 미만(파크,유아 놀이시설 무료)
				 12개월 이상~36개월 미만(파크 입장 무료, 유아 놀이시설 유료)
				 즉, 12~36개월에 해당하면 유아 놀이시설 이용할 건지 다시 물어야 함.
	
	조건4 - 수량(수량 입력) 
	
	조건5 - 우대사항(상시 우대 정보, 택일)
		없음
		장애인 우대 : 50% 할인, 동반 1인까지 
		국가유공자 : 50% 할인, 동반 1인까지 
		휴가장병 : 49% 할인, 동반 1인까지 
		임산부 : 50% 할인 , 본인만
		다둥이 행복카드 : 30%할인, 카드에 명시된 가족만
*/

int main()
{
	// 가격 
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

	// 나이 
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
	
	// 할인율
	const float Disabled_DiscountRate = 0.5;
	const float NationalMerit_DiscountRate = 0.5;
	const float Soldier_DiscountRate = 0.5;
	const float Pregnant_DiscountRate = 0.5;
	const float MultipleChildren_DiscountRate = 0.3;

	struct tm *cur_date;

	//옵션 
	int option1=0;		// 이용시설 구분 
	int option2=0;		// 이용시간 구분 
	long long id=0;		// 주민번호 
	int amount=0; 		// 수량 
	int option5=0;		// 우대사항 구분 
	
	// 가격 
	int price=0;		// 나이에 따른 정가 
	float total_discount_rate=1;	// 우대사항에 따른 최종할인율 
	int total_price=0;	// 최종가격 
	




	printf("롯데월드에 오신 여러분을 환영합니다.\n");

// 옵션1 선택 
	printf("이용시설을 선택하세요.\n");
	printf("1. 종합이용권\n");
	printf("2. 파크이용권\n");
	
	scanf("%d",&option1); 

// 옵션2 선택 
	printf("권종을 선택하세요.\n");
	printf("1. 1Day \n");
	printf("2. After4 \n");
	
	scanf("%d",&option2); 

// 옵션3 입력 
	printf("주민번호를 입력하세요.(-없이 번호만 입력)\n");

	scanf("%lld",&id); 	
	
	// 고객 생년월일 추출
	int birth = id/10000000;					// 앞 6자리 추출
	int years =0;
	if((id/1000000)%10==3||(id/1000000)%10==4)	// 밀레이넘 베이비 판별 
	{
		years = birth/10000+2000;
	}
	else
	{
		years = birth/10000+1900;
	}
	int months = (birth%10000)/100;
	int days = (birth%100);

	
	 // 현재 시간 확인 
	time_t curTime = time(NULL);
	cur_date = localtime(&curTime);
	
	 // 고객 만 나이 계산 
	int count_age_days = cur_date->tm_mday - days;
	int count_age_months = (cur_date->tm_mon+1) - months;
	int count_age_years = cur_date->tm_year+1900 - years; 
	if(count_age_days<0)
	{
		count_age_days+=30;
		count_age_months--;
	} 
	
	if(count_age_months<0)
	{
		count_age_months+=12;
		count_age_years--;
	} 
 
	// 나이에 기반한 정가 가격 확인
	if((count_age_years>=19 || count_age_years<65)&&option1==1&&option1==1) // 성인,종합이용권,종일 
		price = Package_Day_Adult_Price;
	else if((count_age_years>=19 || count_age_years<65)&&option1==1&&option1==2) // 성인,종합이용권,오후 
		price = Package_After4_Adult_Price;
	else if((count_age_years>=19 || count_age_years<65)&&option1==2&&option1==1) // 성인,파크이용권,종일 
		price = OnlyPark_Day_Adult_Price;
	else if((count_age_years>=19 || count_age_years<65)&&option1==2&&option1==2) // 성인,파크이용권,오후 
		price = OnlyPark_After4_Adult_Price;
		
	else if((count_age_years>=13 || count_age_years<=18)&&option1==1&&option1==1) // 청소년,종합이용권,종일 
		price = Package_Day_Youth_Price;
	else if((count_age_years>=13 || count_age_years<=18)&&option1==1&&option1==2) // 청소년,종합이용권,오후 
		price = Package_After4_Youth_Price;
	else if((count_age_years>=13 || count_age_years<=18)&&option1==2&&option1==1) // 청소년,파크이용권,종일 
		price = OnlyPark_Day_Youth_Price;
	else if((count_age_years>=13 || count_age_years<=18)&&option1==2&&option1==2) // 청소년,파크이용권,오후 
		price = OnlyPark_After4_Youth_Price;
	
	else if(((count_age_years*12+count_age_months>=36||count_age_years<=12)||count_age_years>=65)&&option1==1&&option1==1) // 어린이,종합이용권,종일 
		price = Package_Day_Child_Price;
	else if(((count_age_years*12+count_age_months>=36||count_age_years<=12)||count_age_years>=65)&&option1==1&&option1==2) // 어린이,종합이용권,오후 
		price = Package_After4_Child_Price;
	else if(((count_age_years*12+count_age_months>=36||count_age_years<=12)||count_age_years>=65)&&option1==2&&option1==1) // 어린이,파크이용권,종일 
		price = OnlyPark_Day_Child_Price;
	else if(((count_age_years*12+count_age_months>=36||count_age_years<=12)||count_age_years>=65)&&option1==2&&option1==2) // 어린이,파크이용권,오후 
		price = OnlyPark_After4_Child_Price;
	
	else if((count_age_years*12+count_age_months<=35)&&option1==1&&option1==1) // 어린이,종합이용권,종일 
		price = Package_Day_Baby_Price;
	else if((count_age_years*12+count_age_months<=35)&&option1==1&&option1==2) // 어린이,종합이용권,오후 
		price = Package_After4_Baby_Price;
	else if((count_age_years*12+count_age_months<=35)&&option1==2&&option1==1) // 어린이,파크이용권,종일 
		price = OnlyPark_Day_Baby_Price;
	else if((count_age_years*12+count_age_months<=35)&&option1==2&&option1==2) // 어린이,파크이용권,오후 
		price = OnlyPark_After4_Baby_Price;
	
// 옵션 4 - 수량 
	printf("수량을 입력하세요.\n");
	scanf("%d",&amount);

	total_price = price*amount;
	
// 옵션5 - 우대 혜택
	printf("우대사항을 선택하세요.\n");
	printf("1. 장애인\n");
	printf("2. 국가유공자\n");
	printf("3. 휴가장병\n");
	printf("4. 임산부\n");
	printf("5. 다자녀가족\n");
	printf("6. 없음\n");
	
	scanf("%d",&option5);  

	switch(option5)
	{
		case 1: total_discount_rate = 1*(1-Disabled_DiscountRate); break;
		case 2: total_discount_rate = 1*(1-NationalMerit_DiscountRate); break;
		case 3: total_discount_rate = 1*(1-Soldier_DiscountRate); break;
		case 4: total_discount_rate = 1*(1-Pregnant_DiscountRate); break;
		case 5: total_discount_rate = 1*(1-MultipleChildren_DiscountRate); break;
		case 0: break;	
	}
	
// 최종가격 출력 
	total_price = price * total_discount_rate;
	
	printf("가격은 %d 원 입니다.\n",total_price);
	printf("감사합니다.");
	
	return 0;
}
