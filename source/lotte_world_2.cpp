#include <stdio.h>
#include <time.h>

/* 요구사항 분석
 
	조건에 따른 롯데월드 입장권 가격 계산 프로그램
	
	조건1 - 이용시설(택일) 
		종합이용권 / 파크이용 
	
	조건2 - 이용시간(택일) 
		종일권(1Day) / 오후권(After4)
	
	조건3 - 나이(주민번호 입력)
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
const float Soldier_DiscountRate = 0.49;
const float Pregnant_DiscountRate = 0.5;
const float MultipleChildren_DiscountRate = 0.3;

// 사용자 정의 함수 
int 	selectOption1();
int 	selectOption2();
long long insertID();
int 	checkPrice(int option1, int option2, long long ID);
int 	selectOption4();
int 	selectOption5();
float 	checkDiscountRate(int option5);
int 	calAndPrintTotalPrice(int total_price, float total_discount_rate); 
int		today();			// 오늘날짜 반환, 20220321
const char* 	ticketKinds1(int option1);
const char* 	ticketKinds2(int option2);
const char* 	discountKinds(int option5);
int 	checkContinue();



int main()
{
	int final_price=0;		// 최종가격
	int d=1;
	
	/*
		반복된 결과 값을 저장하는 것
		 -> 몇번 반복할지 모른다?
		 	아이디어 
		  	1) 배열 동적할당?
			2) 어차피 파일로 저장해야하는거 반복할 때마다 쓰고 종료되면 저장하고 그걸 다시 읽어오는 방법?
	*/
	
	FILE *fp;
	fp = fopen("report.csv","a");
	fprintf(fp, "날짜, 이용시설, 이용기간, 연령 구분, 수량, 가격, 우대사항\n");
	while(d)
	{
		//옵션값 
		int 	option1=0;		// 이용시설 구분(1, 2)
		int 	option2=0;		// 이용시간 구분(1, 2)
		long long ID=0;			// 주민번호(13자리) 
		int 	amount=0; 		// 수량(1~1000)
		int 	option5=0;		// 우대사항 구분 (1~6)
		
		// 가격 
		int 	price=0;		// 나이에 따른 티켓 한 장의 정가 
		float 	total_discount_rate=1;	// 우대사항에 따른 적용된 할인율
		int 	sum_price=0; 	// 총 수량에 따른 정가, price * amount 
		int 	total_price=0;	// 할인과 수량이 적용된 청구 금액


		printf("롯데월드에 오신 여러분을 환영합니다.\n");
		
		option1=selectOption1();	// 이용시설 선택 
		
		option2=selectOption2();	// 이용시간 선택 
		
		ID=insertID();				// 주민번호 입력 
		
		price=checkPrice(option1,option2,ID);	// 주민번호로 표 정가 산출 
				
		amount=selectOption4();		// 구매 수량 입력 
		
		sum_price=price * amount; 	// 구매 수량 입력 후 수량에 따른 정가

		option5=selectOption5();	// 우대사항 선택 
		
		total_discount_rate=checkDiscountRate(option5);	// 우대사항에 따른 할인율 산출 

		total_price=calAndPrintTotalPrice(sum_price,total_discount_rate);	// 총 청구 금액  계산 및 출력 
		
		fprintf(fp,"%d,%s,%s,%d,%d,%s\n",today(),ticketKinds1(option1),ticketKinds2(option2),"연령구분",amount,total_price,discountKinds(option5));// 현재 루프의 정보 저장하는 함수 추가 예정
		
		d=checkContinue();
	}
	
	fclose(fp);
	return 0;
}



// 이용시설 선택 
int selectOption1()
{
	int option1=0;
	
	do
	{
		printf("이용시설을 선택하세요.\n");
		printf("1. 종합이용권\n");
		printf("2. 파크이용권\n");
	
		scanf("%d",&option1); 
		//printf("%d\n",option1);
		
		if(!(option1==1||option1==2)) printf("잘못된 입력입니다. 1 또는 2만 입력하세요.\n");
	} while(!(option1==1||option1==2));

	return option1;
}


// 이용 시간 선택 
int selectOption2()
{
	int option2=0;
	
	do
	{
		printf("권종을 선택하세요.\n");
		printf("1. 1Day \n");
		printf("2. After4 \n");
		
		scanf("%d",&option2); 
		//printf("%d\n",option2);
		
		if(!(option2==1||option2==2)) printf("잘못된 입력입니다. 1 또는 2만 입력하세요.\n");
	} while(!(option2==1||option2==2));

	return option2;
}


// 주민번호 입력 
long long insertID()
{
	long long ID = 0;
	
	do
	{
		printf("주민번호를 입력하세요.(-없이 13자리 입력)\n");

		scanf("%lld",&ID);							// 13자리이므로 넉넉히 long long 타입 사용
		//printf("%lld\n",ID);
		
		if(!(1000000000000<=ID&&ID<=9999999999999)) printf("잘못된 입력입니다. - 없이 13자리를 입력하세요.\n");
	} while(!(1000000000000<=ID&&ID<=9999999999999));
	
	return ID;
}


// 주민번호로 표 정가 산출 
int checkPrice(int option1, int option2, long long ID)
{
	// 13자리 주민번호에서 고객 생년월일 추출
	int birth = ID/10000000;					// 앞 6자리 추출
	int years =0;
	if((ID/1000000)%10==3||(ID/1000000)%10==4)	// 밀레니엄  베이비 판별 
	{
		years = birth/10000+2000;				// 밀레니엄  베이비인 경우 
	}
	else
	{
		years = birth/10000+1900;
	}
	int months = (birth%10000)/100;
	int days = (birth%100); 					
												

	
	// 시스템으로 부터  현재 시간 가져오기 
	struct tm *cur_date;						// time.h에 정의되어 있는 날짜와 시간을 나타내는 구조체 
	
	time_t curTime=time(NULL);					// #include <time.h> 필요, time_t time(time * timer) : 타이머가 Null이 아니면 timer가 가르키는 변수에 현재 시간을 채운다. 
	cur_date=localtime(&curTime);				// localtime(const time_t * timer) : 타이머가 가르키는 변수를 UTC시간 기준으로 구조체로 변환해 그 주소를 반환 
	
	 // 고객 만 나이 계산 
	int count_age_days=cur_date->tm_mday-days;
	int count_age_months=(cur_date->tm_mon+1)-months;
	int count_age_years=cur_date->tm_year+1900-years; 
	
	if(count_age_days<0)						// ex) 3월에서 5월을 뺐을 경우 음수이므로 예외 처리 
	{
		count_age_days+=30;
		count_age_months--;
	} 
	
	if(count_age_months<0)						// ex) 15일에서 27일을 뺐을 경우 음수이므로 예외 처리 
	{
		count_age_months+=12;
		count_age_years--;
	} 
 
 	//printf("현재시간 : %d/%d/%d\n",cur_date->tm_year+1900,cur_date->tm_mon+1,cur_date->tm_mday);
 	//printf("고객 생년월일:%d/%d/%d\n",years,months,days);
 	//printf("살아온 날수:%d/%d/%d\n",count_age_years,count_age_months,count_age_days);
 
	// 나이에 기반한 정가 가격 확인
	int price=0;
	
	if(count_age_years>=Adult_Min_Age&&count_age_years<=Adult_Max_Age)		// 성인(만 19~64세) 
	{
		if(option1==1&&option1==1)		price=Package_Day_Adult_Price;
		else if(option1==1&&option1==2)	price=Package_After4_Adult_Price;
		else if(option1==2&&option1==1)	price=OnlyPark_Day_Adult_Price;
		else if(option1==2&&option1==2)	price=OnlyPark_After4_Adult_Price;
	} 
	else if(count_age_years>=Youth_Min_Age&&count_age_years<=Youth_Max_Age) // 청소년(만 13~18세) 
	{
		if(option1==1&&option1==1)		price=Package_Day_Youth_Price;
		else if(option1==1&&option1==2) price=Package_After4_Youth_Price;
		else if(option1==2&&option1==1)	price=OnlyPark_Day_Youth_Price;
		else if(option1==2&&option1==2)	price=OnlyPark_After4_Youth_Price;
	}
	else if((count_age_years*12+count_age_months>=Child_Min_Month&&count_age_years<=Child_Max_Age)||count_age_years>=65)	// 어린이(36개월~만12세) , 노인(만65세 이상) 
	{
		if(option1==1&&option1==1)		price=Package_Day_Child_Price;
		else if(option1==1&&option1==2)	price=Package_After4_Child_Price;
		else if(option1==2&&option1==1)	price=OnlyPark_Day_Child_Price;
		else if(option1==2&&option1==2)	price=OnlyPark_After4_Child_Price;
	}
	else if(count_age_years*12+count_age_months<Baby1_Max_Month)			// 유아(~36개월 미만)
	{
		if(option1==1&&option1==1)		price=Package_Day_Baby_Price;
		else if(option1==1&&option1==2)	price=Package_After4_Baby_Price;
		else if(option1==2&&option1==1)	price=OnlyPark_Day_Baby_Price;
		else if(option1==2&&option1==2)	price=OnlyPark_After4_Baby_Price;
	}
	
	//printf("가격:%d\n",price);
	
	return price;
}

// 구매 수량 입력 
int selectOption4()
{
	int amount=0;
	
	do
	{
		printf("수량을 입력하세요.(구매 가능 수량 : 1~1000장)\n");
		scanf("%d",&amount);
		//printf("%d*%d=%d\n",amount,price,price*amount);	
	
		if(!(1<=amount&&amount<=1000)) printf("잘못된 입력입니다. 1~1000의 숫자를 입력하세요.\n");
	}while(!(1<=amount&&amount<=1000));
	
	return amount;
}


// 우대사항 선택 
int selectOption5()
{
	int option5=0;
	do
	{
		printf("우대사항을 선택하세요.\n");
		printf("1. 장애인\n");
		printf("2. 국가유공자\n");
		printf("3. 휴가장병\n");
		printf("4. 임산부\n");
		printf("5. 다자녀가족\n");
		printf("6. 없음\n");
		
		scanf("%d",&option5);
		//printf("%d\n",option5);
		
		if(!(1<=option5&&option5<=6)) printf("잘못된 입력입니다. 1~6의 숫자를 입력하세요.\n");
	} while(!(1<=option5&&option5<=6));
	
	return option5; 
}


// 할인율 산출 
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


// 최종금액 계산 밀 출력 
int calAndPrintTotalPrice(int total_price, float total_discount_rate)
{
	int final_price=total_price*total_discount_rate;
	
	printf("가격은 %d 원 입니다.\n",final_price);			// 3자리마다 끊어서 출력하는 메서드 만들어서 적용 예정 
	printf("감사합니다.\n");
	
	return final_price; 
}
 
// 오늘 날짜 반환
int today()
{
	// 시스템으로 부터  현재 시간 가져오기 
	struct tm *cur_date;						// time.h에 정의되어 있는 날짜와 시간을 나타내는 구조체 
	
	time_t curTime=time(NULL);					// #include <time.h> 필요, time_t time(time * timer) : 타이머가 Null이 아니면 timer가 가르키는 변수에 현재 시간을 채운다. 
	cur_date=localtime(&curTime);				// localtime(const time_t * timer) : 타이머가 가르키는 변수를 UTC시간 기준으로 구조체로 변환해 그 주소를 반환 
	
	return ((cur_date->tm_year-100)*10000 + (cur_date->tm_mon+1)*100 + (cur_date->tm_mday));
} 
 
// 이용 시설 구분 
const char* ticketKinds1(int option1)
{
	/*
	char kinds[20] = "" ;
	
	if(option1==1) 	kinds="종합이용권";
	else			kinds="파크이용권";
	
	return kinds; 
	*/
	return "종합이용권";
}
 
// 이용시간 구분 
const char* ticketKinds2(int option2)
{
	char[20] kinds = "";
	
	if(option1==1) 	kinds="종일권";
	else			kinds="after4";
	
	return kinds; 
}
 
/* 연령 구분 반환   -> 이거 checkprice()의 나이 확인 하는 프로세스랑 겹침. 나이 확인하는 프로세스만 함수로 빼서 여기서도 사용하도록 바꿔야함. 
char* ageKinds(option2)
{

} */
 
// 할인우대 구분 반환 
const char* discountKinds(int option5)
{
	char[10] kinds = "";
	
	switch(option5)
	{
		case 1: kind="장애인";		break; 
		case 2: kind="국가유공자";	break;
		case 3: kind="군인"; 		break;
		case 4: kind="임산부";		break;
		case 5: kind="다둥이가족";	break;
		case 6: kind="없음";		break;
	}
	
	return kind;
}
 
// 추가 구매할 티켓 있는지 확인 
int checkContinue()
{
	int d = 1;
	
	do
	{
		printf("계속 티켓을 발권하시겠습니까?\n");
		printf("0. 종료 후 합계 보기\n");
		printf("1. 티켓 추가 발권\n");
		
		scanf("%d",&d);
		
		if(!(d==1||d==0)) printf("잘못된 입력입니다. 0 또는 1을 입력하세요.\n");
	} while(!(d==1||d==0));

	return d;
}

