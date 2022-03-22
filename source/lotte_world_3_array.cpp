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
				 즉, 12~36개월에 해당하면 유아 놀이시설 이용할 건지 다시 물어야 함. - 일단 생략. 
	
	조건4 - 우대사항(상시 우대 정보, 택일)  
		1 장애인 우대 : 50% 할인, 동반 1인까지 
		2 국가유공자 : 50% 할인, 동반 1인까지 
		3 휴가장병 : 49% 할인, 동반 1인까지 
		4 임산부 : 50% 할인 , 본인만
		5 다둥이 행복카드 : 30%할인, 카드에 명시된 가족만
		6 없음. 
		
	조건5 - 수량(수량 입력) , 1~1000장으로 제한 & 우대사항 적용시 그에 따른 매수 제한. 
*/

// 배열의 수 - 영수증에 찍을 수 있는 권종의 최대 수 
const int MaxTicketCount = 100;

// 가격 
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

// 나이 
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

// 할인율
const float DisabledDiscountRate = 0.5;
const float NationalMeritDiscountRate = 0.5;
const float SoldierDiscountRate = 0.49;
const float PregnantDiscountRate = 0.5;
const float MultipleChildrenDiscountRate = 0.3;

// 사용자 정의 함수 
int 	selectOption0();			// option0, 이용 시설 선택 , return : 권종에 해당하는 숫자 - 1이면 종합이용권, 2면 파크이용권 
int 	selectOption1();			// option1, 이용 시간 선택 , return : 권종에 해당하는 숫자 - 1이면 종일권, 2면 after4 
int 	selectOption2();			// option2, 주민번호 입력 입력받아 연령구분을 산출, return : 연령 구분에 해당하는 숫자, 노인 1, 성인 2, 청소년 3, 어린이4, 유아 5 
int 	selectOption3();			// option3, 우대사항 선택
int 	selectOption4(int option3);	// option4, 수량 입력 
int 	checkTotalPrice(int option0, int option1, int option2, int option3, int option4); 	// PriceCheck(), checkDiscountRate()를 이용하여 확인한 정가와 할인율,수량을 곱해 총액 계산 

int 	PriceCheck(int option0, int option1, int option2);	// 옵션0~2를 기준으로 정가 확인 
float 	checkDiscountRate(int option5);						// 옵션3을 기준으로 할인율 확인 

int		todayIs();					// 오늘날짜 반환, 20220321

char 	*option0toKo(int option0);	// 이용시설 구분 번호를 해당하는 한글로 변환 
char 	*option1toKo(int option1);	// 이용시간 구분 번호를 해당하는 한글로 변환 
char 	*option2toKo(int option2);	// 연령 	구분 번호를 해당하는 한글로 변환 
char 	*option3toKo(int option3);	// 우대사항 구분 번호를 해당하는 한글로 변환 

int 	checkContinue();			// 발권을 종료할지 계속할지 확인 




int main()
{
	int options[MaxTicketCount][6]={0};	// 이용시설 구분 , 이용 시간 구분, 주민등록번호, 우대사항 구분, 티켓 수량, 총액 
	
	printf("롯데월드에 오신 여러분을 환영합니다.\n");
	
	for(int i=0;i<100;i++)
	{
		options[i][0]=selectOption0();				// option0, 이용 시설 선택 , return : 1이면 종합이용권, 2면 파크이용권 
		
		options[i][1]=selectOption1();				// option1, 이용 시간 선택 , return : 1이면 종일권, 2면 after4 
		
		options[i][2]=selectOption2();				// option2, 주민번호 입력 입력받아 연령구분을 산출, return : 노인 1, 성인 2, 청소년 3, 어린이4, 유아 5 
									
		options[i][3]=selectOption3();				// option3, 우대사항 선택, return : 장애인 1, 국가유공자 2, 휴가장병 3, 임산부 4, 다자녀 가족 5, 없음 6  
		
		options[i][4]=selectOption4(options[i][3]);	// option4, 구매 수량 입력 
		
		options[i][5]=checkTotalPrice(options[i][0], options[i][1], options[i][2],options[i][3], options[i][4]);	// 총 청구 금액  계산 및 출력 
		
		if(!checkContinue()) break;
	}
	
	// 영수증 출력	
	printf("\n\n------------------------롯데월드------------------------\n\n");
	printf("이용시설\t이용시간\t연령구분\t우대사항\t수량\t금액\n\n");
	int sumTotalPrice=0;
	for(int i=0;i<100;i++)
	{
		if(options[i][0]==0) break;				// 저장된 값이 없는 배열이면 반복문 종료 
		
		// 이용시설, 이용시간, 연령구분, 우대사항,수량 가격 
		printf("%s\t%s\t\t%s\t\t%s\t%d\t%d\n", option0toKo(options[i][0]), option1toKo(options[i][1]), option2toKo(options[i][2]), option3toKo(options[i][3]), options[i][4], options[i][5]);
		sumTotalPrice+=options[i][5];
	}
	printf("\n입장료 총액은 %d입니다.\n감사합니다.\n\n", sumTotalPrice);
	
	return 0;
}

// 이용시설 선택 , return : 1이면 종합이용권, 2면 파크이용권 
int selectOption0()
{
	int option1=0;
	
	do
	{
		printf("\n*이용시설을 선택하세요.\n");
		printf("1. 종합이용권\n");
		printf("2. 파크이용권\n");
	
		scanf("%d",&option1); 
		//printf("%d\n",option1);
		
		if(!(option1==1||option1==2)) printf("잘못된 입력입니다. 1 또는 2만 입력하세요.\n");
	} while(!(option1==1||option1==2));

	return option1;
}

// 이용 시간 선택 , return : 1이면 종일권, 2면 after4 
int selectOption1()
{
	int option1=0;
	
	do
	{
		printf("\n*권종을 선택하세요.\n");
		printf("1. 1Day \n");
		printf("2. After4 \n");
		
		scanf("%d",&option1); 
		//printf("%d\n",option1);
		
		if(!(option1==1||option1==2)) printf("잘못된 입력입니다. 1 또는 2만 입력하세요.\n");
	} while(!(option1==1||option1==2));

	return option1;
}

// 주민번호 입력 입력받아 연령구분을 산출, return : 연령 구분에 해당하는 숫자, 노인 1, 성인 2, 청소년 3, 어린이4, 유아 5 
int selectOption2()
{
	// 주민번호 입력	
	long long ID = 0;
	do
	{
		printf("\n*주민번호를 입력하세요.(-없이 13자리 입력)\n");
		scanf("%lld",&ID);							// 13자리이므로 넉넉히 long long 타입 사용
		//printf("%lld\n",ID);
		// 정확한 예외처리 아님 - 00년생1월생때문에 일단 이렇게 했는데, 구현하기 까다롭다. - 추후 개선 
		if(!(1000000000<=ID&&ID<=9999999999999)) printf("잘못된 입력입니다. - 없이 13자리를 입력하세요.\n");
	} while(!(1000000000<=ID&&ID<=9999999999999));
	
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
	
	// 고객 만 나이 계산
	int today=todayIs();
	
	int countAgeDays=(today%100)-days;
	int countAgeMonths=(today%10000)/100-months;
	int countAgeYears=(today/10000)+2000-years; 
	if(countAgeDays<0)						// ex) 3월에서 5월을 뺐을 경우 음수이므로 예외 처리 
	{
		countAgeDays+=30;
		countAgeMonths--;
	} 
	if(countAgeMonths<0)						// ex) 15일에서 27일을 뺐을 경우 음수이므로 예외 처리 
	{
		countAgeMonths+=12;
		countAgeYears--;
	} 
	
	// 만 나이가 어느 연령 구분에 해당하는지 확인
	int dAge=0;
	if(countAgeYears>=65)																	dAge=1; 	// 노인(만65세 이상)
	else if(countAgeYears>=AdultMinAge&&countAgeYears<=AdultMaxAge)							dAge=2;		// 성인(만 19~64세) 
	else if(countAgeYears>=YouthMinAge&&countAgeYears<=YouthMaxAge) 						dAge=3; 	// 청소년(만 13~18세) 
	else if(countAgeYears*12+countAgeMonths>=ChildMinMonth&&countAgeYears<=ChildMaxAge)		dAge=4;		// 어린이(36개월~만12세)
	else if(countAgeYears*12+countAgeMonths<Baby1MaxMonth)									dAge=5; 	// 유아(~36개월 미만)
	
	return dAge; 
}

// 우대사항 선택 , return : 1이면 장애인, 2는 국가유공자, 3는 휴가장병, 4는 임산부, 5는 다자녀가족, 6은 없음 
int selectOption3()
{
	int option5=0;
	do
	{
		printf("\n*우대사항을 선택하세요.\n");
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

// 구매 수량 입력 
int selectOption4(int option3)
{
	int amount=0;
	int d=1;
	do
	{
		printf("\n*수량을 입력하세요.(구매 가능 수량 : 1~1000장)\n");
		scanf("%d",&amount);
		//printf("%d*%d=%d\n",amount,price,price*amount);	
		
		// 우대조건에 따른 티켓 구매 수량 제한 
		if((option3==1 || option3==2 || option3==3) && amount>2)
		{
			switch(option3)
			{
				case 1:	printf("장애인할인은 동반1인까지 적용가능합니다.(최대 2매)\n"); break;
				case 2:	printf("국가유공자 할인은 동반1인까지 적용가능합니다.(최대 2매)\n"); break;
				case 3:	printf("휴가 장병 할인은 동반1인까지 적용가능합니다.(최대 2매)\n"); break;
			};
		}
		else if(option3==4 && amount!=1)
		{
			printf("임산부 할인은 본인만 적용이 가능합니다.(최대 1매)\n 다시 입력하세요.\n");
		}
		else if(option3==5)
		{
			int d=1;
			while(d==1)
			{
				printf("다자녀 가족 할인은 카드에 명시된 가족만 적용이 가능합니다. 입력하신 정보가 맞습니까??  맞으면 1, 다시 입력하시려면 2를 입력하세요.\n");
				scanf("%d",&d);
				if(d==1) 					return amount;
				else if(d != 1 && d!=2)		printf("잘못된 입력입니다. 1 또는 2를 입력하세요\n");
			}
		} 
		else if(!(1<=amount&&amount<=1000)) printf("최소 구매수량은 1, 최대 구매 수량은 1000매입니다.\n");
		else 		d=0;
	}while(d);
	
	return amount;
}

// 최종금액 계산 밀 출력 
int checkTotalPrice(int option0, int option1, int option2, int option3, int option4)
{
	int originalPrice = PriceCheck(option0, option1, option2);	// 이용 시설, 이용시간, 연령 구분으로 정가 확인 

	float discountRate = checkDiscountRate(option3);						// 우대사항으로 할인율 확인 
	
	int discountedPrice = originalPrice*discountRate;					// 할인된 1장 당 티켓 가격 
	
	int totalPrice = discountedPrice * option4;							// 총 티켓 가격 
	
	printf("\n*가격은 %d 원 입니다.\n",totalPrice);			// 3자리마다 끊어서 출력하는 메서드 만들어서 적용 예정 
	printf("감사합니다.\n");
	
	return totalPrice; 
}

// 티켓 정가 산출, return : 조건에 따른 정가 숫자 
int PriceCheck(int option0, int option1, int option2)
{
	int price=0;
	if(option2==2)		// 성인(만 19~64세) 
	{
		if(option0==1&&option1==1)		price=PackageDayAdultPrice;
		else if(option0==1&&option1==2)	price=PackageAfter4AdultPrice;
		else if(option0==2&&option1==1)	price=OnlyParkDayAdultPrice;
		else if(option0==2&&option1==2)	price=OnlyParkAfter4AdultPrice;
	} 
	else if(option2==3) // 청소년(만 13~18세) 
	{
		if(option0==1&&option1==1)		price=PackageDayYouthPrice;
		else if(option0==1&&option1==2) price=PackageAfter4YouthPrice;
		else if(option0==2&&option1==1)	price=OnlyParkDayYouthPrice;
		else if(option0==2&&option1==2)	price=OnlyParkAfter4YouthPrice;
	}
	else if(option2==4||option2==1)	// 어린이(36개월~만12세) , 노인(만65세 이상) 
	{
		if(option0==1&&option1==1)		price=PackageDayChildPrice;
		else if(option0==1&&option1==2)	price=PackageAfter4ChildPrice;
		else if(option0==2&&option1==1)	price=OnlyParkDayChildPrice;
		else if(option0==2&&option1==2)	price=OnlyParkAfter4ChildPrice;
	}
	else if(option2==5)			// 유아(~36개월 미만)
	{
		if(option0==1&&option1==1)		price=PackageDayBabyPrice;
		else if(option0==1&&option1==2)	price=PackageAfter4BabyPrice;
		else if(option0==2&&option1==1)	price=OnlyParkDayBabyPrice;
		else if(option0==2&&option1==2)	price=OnlyParkAfter4BabyPrice;
	}
	return price; 
}

// 할인율 산출 , return : 우대사항에 따른 할인율 
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
 
// 오늘 날짜 반환 , return : 오늘 날짜 6자리 ex) 220322 
int todayIs()
{
	// 시스템으로 부터  현재 시간 가져오기 
	struct tm *cur_date;						// time.h에 정의되어 있는 날짜와 시간을 나타내는 구조체 
	
	time_t curTime=time(NULL);					// #include <time.h> 필요, time_t time(time * timer) : 타이머가 Null이 아니면 timer가 가르키는 변수에 현재 시간을 채운다. 
	cur_date=localtime(&curTime);				// localtime(const time_t * timer) : 타이머가 가르키는 변수를 UTC시간 기준으로 구조체로 변환해 그 주소를 반환 
	
	return ((cur_date->tm_year-100)*10000 + (cur_date->tm_mon+1)*100 + (cur_date->tm_mday));
} 
 
// 추가 구매할 티켓 있는지 확인 
int checkContinue()
{
	int d = 1;
	
	do
	{
		printf("\n*계속 티켓을 발권하시겠습니까?\n");
		printf("0. 종료 후 합계 보기\n");
		printf("1. 티켓 추가 발권\n");
		
		scanf("%d",&d);
		
		if(!(d==1||d==0)) printf("잘못된 입력입니다. 0 또는 1을 입력하세요.\n");
	} while(!(d==1||d==0));

	return d;
}

// 숫자 구분을 한글로 변환 
char *option0toKo(int option0)
{
	char *kinds=" ";
	
	if(option0==1) 	kinds="종합이용권";	
	else			kinds="파크이용권";
	
	return kinds;
}
char *option1toKo(int option1)
{
	char *kinds = " ";
	
	if(option1==1) 		kinds="종일권";
	else if(option1==2)	kinds="after4";
	
	return kinds; 
}
char *option2toKo(int option2)
{
	char *kinds = " ";
	
	if(option2==1) 	kinds="노인";
	else if(option2==2) 	kinds="성인";
	else if(option2==3) 	kinds="청소년";
	else if(option2==4) 	kinds="어린이";
	else if(option2==5) 	kinds="유아";
	else if(option2==6)		kinds="없음";
	
	return kinds; 
}
char *option3toKo(int option3)
{
	char *kinds = " ";
	
	switch(option3)
	{
		case 1: kinds="장애인    ";	break; 
		case 2: kinds="국가유공자";	break;
		case 3: kinds="군인      "; break;
		case 4: kinds="임산부    ";	break;
		case 5: kinds="다둥이가족";	break;
		case 6: kinds="없음      ";	break;
	}
	
	return kinds;
}





