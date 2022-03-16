#include <stdio.h>

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
	const int Disabled_DiscountRate = 0.5;
	const int NationalMerit_DiscountRate = 0.5;
	const int Soldier_DiscountRate = 0.5;
	const int Pregnant_DiscountRate = 0.5;
	const int MultipleChildren_DiscountRate = 0.3;


	return 0;
}
