#include <stdio.h>
#include <time.h>

/* 요구사항
	lotte_world 티켓 프로그램을 통해 저장된 report.csv파일을 분석하는 프로그램
	
	1. 파일 내용 전체 출력.
		추가) 마지막에 판매 총 수량, 총 매출 출력 
	
	2. 분석 - 권종 기준 판매 티켓 분석
		: 연령구분별 판매 티켓 수, 권종별 총 매출 출력 
		1) 이용시설
		2) 이용시간
	
	3. 분석 - 일자 별 매출 분석
		: 일자별 총 매출
	
	4. 분석 - 우대사항별 판매 티켓 분석
		: 우대사항 별 판매 티켓 수 출력 
	
	5. 분석 결과 파일로 저장. 
*/


/* 사용자정의 함수 */
void fCountDate(int date, int amount, int price);					// 날짜에 따른 티켓 수 누적하는 함수 
void fCountUsageRage(int countUsageRage, int kindAge, int amount);	// 이용시설 별 티켓, 연령구분에 따라 누적하는 함수 
void fCountUsageTime(int KindUsageTime, int kindAge, int amount);	// 이용시간 별 티켓, 연령구분에 따라 누적하는 함수 
void fCountAge(int kindAge, int amount);							// 연령구분 별 티켓 누적하는 함수 
void fCountDiscount(int kindDiscount, int amount);					// 우대사항 별 티켓 누적하는 함수 
void fCountTickets(int amount);										// 총 티켓 수 누적하는 함수 
void fSumPrice(int price);											// 매출 누적하는 함수 

void printCountDate();						// 날짜에 따라 누적된 티켓 수 출력하는 함수 
void printCountUsageTime();					// 이용시설 기준으로 연령 구분에 따라 누적된 티켓 수 출력하는 함수  
void printCountUsageRange();				// 이용시간 기준으로 연령 구분에 따라 누적된 티켓 수 출력하는 함수 
void printCountKindAge();					// 연령 구분에 따라 누적된 티켓 수 출력하는 함수 
void printCountKindDiscount();				// 우대사항에 따라 누적된 티켓 수 출력하는 함수 

int *chageDateFmt(int date);				// 날짜 6자리를 2자리씩 끊어 크기3인 배열로 반환하는 함수 


/* 전역변수 */
const int maxDataCount = 1000; 				// 분석가능한 발권 총 횟수, 파일의 최대 줄 수 
// 분석을 위해 누적값을 저장하는 변수 
int countDate[maxDataCount][3]  = {0};		// 날짜별 누적 [n][0]은 날짜, [n][1]은 누적 티켓 수, [n][2]는 누적 매출
											// report.csv를 1000줄 읽을 수 있고 모든 줄의 데이터 날짜가 다를 수 있으므로 똑같은 맥시멈으로 설정. 
int countKindUsageRange[2][5] 	= {0};		// [0]은 총 종합이용권 	개수, [1]은 파크이용권 	개수 
int countKindUsageTime[2][5] 	= {0};		// [0]은 총 종일권 		개수, [1]은 after4 		개수 
int countKindAge[5] 			= {0};		// [0]은 노인, 	[1]은 성인, 		[2]는 청소년, 	[3]은 어린이, 	[4]는 유아  티켓 총 개수
int countKindDiscount[6]		= {0};		// [0]은 장애인,[1]은 국가유공자,	[2]는 휴가장병, [3]은 임산부, 	[4]는 다자녀 가족, [5]는 해당없음  총 개수
int countTickets 				= 0;		// 판매된 티켓의 총 개수
int sumPrice 					= 0; 		// 누적 매출액
// 각 구분에 해당하는 한글 문자열 배열 
const char *KoKindUsageRange[2]	= {"종합이용권", "파크이용권"};
const char *KoKindUsageTime[2]	= {"종일권", "after4"};
const char *KoKindAge[5]		= {"노인", "성인", "청소년", "어린이", "유아"};
const char *KoKindDiscount[6]	= {"장애인   ", "국가유공자", "휴가장병", "임산부   ", "다자녀 가족", "해당없음"};



int main()
{
	int i = 0;									// 0부터 report.csv의 줄 수 만큼 증가 
	
	FILE *report = fopen("report.csv", "r");	// 분석할 파일 report.csv 열기 
	
	printf("\n------------------------------------------- report.csv -------------------------------------------\n\n"); 
	printf("날짜\t이용시설\t이용시간\t연령구분\t우대사항\t수량\t금액\n\n");
	
	// 파일의 한 줄씩 파일의 마지막까지 반복 실행 
	while(feof(report)==0)					// feof(report) : report파일의 끝이면 EOF플래그 출력, 아니면 0
	{
		// report.csv의 한줄의 내용을 저장할 변수, 반복문 안에 넣어주어 라이프사이틀을 루프 1회로 한정. 
		int date[maxDataCount] 				= {0};	// 판매 날짜 
		int KindUsageRange[maxDataCount]	= {0};	// 이용시설 구분, 1은 종합이용권, 2는 파크이용권 
		int KindUsageTime[maxDataCount] 	= {0};	// 이용시간 구분, 1은 종일권, 2는 after4 
		int kindAge[maxDataCount] 			= {0};	// 연령 구분	, 1은 노인, 2는 성인, 3은 청소년, 4는 어린이, 5는 유아 
		int kindDiscount[maxDataCount]		= {0};	// 우대사항 구분, 1은 장애인, 2는 국가유공자, 3은 휴가장병, 4는 임산부, 5는 다자녀 가족, 6는 없음 
		int amount[maxDataCount]			= {0};	// 티켓 판매 수량 
		int price[maxDataCount]				= {0};	// 총액 
		
		// 파일 한 줄 읽어 변수에 저장 
		fscanf(report, "%d, %d, %d, %d, %d, %d, %d\n", &date[i], &KindUsageRange[i], &KindUsageTime[i], &kindAge[i], &kindDiscount[i], &amount[i], &price[i]);
		
		// 읽은 한 줄 출력 
		printf("%d\t%s\t%s\t\t%s\t\t%s\t%d\t%d\n", date[i], KoKindUsageRange[KindUsageRange[i]-1], KoKindUsageTime[KindUsageTime[i]-1], KoKindAge[kindAge[i]-1], KoKindDiscount[kindDiscount[i]-1], amount[i], price[i]);
		
		// 읽은 한 줄에 대하여 이용시설, 이용시간, 유대사항 별 총 티켓 수 누적 
		fCountDate(date[i], amount[i], price[i]);					// 요구사항 3번 : 분석) 날짜에 따른 티켓 카운트 
		fCountUsageRage(KindUsageRange[i], kindAge[i], amount[i]); 	// 요구사항 2-1번 : 분석) 이용시설 별 티켓, 연령구분에 따라 카운트. 
		fCountUsageTime(KindUsageTime[i], kindAge[i], amount[i]);	// 요구사항 2-2번 : 분석) 이용시간 별 티켓, 연령구분에 따라 카운트.
		fCountAge(kindAge[i], amount[i]); 							// 요구사항은 아님. 
		fCountDiscount(kindDiscount[i], amount[i]);					// 요구사항  4번 : 분석) 우대사항 별 티켓 카운트 
		
		// 읽은 한 줄에 대하여 누적 판매 티켓 수, 매출액 누적
		fCountTickets(amount[i]);
		fSumPrice(price[i]);
		
		i++;		// report파일의 다음 줄 접근을 위해 증가
	}
	
	
	//FILE *analysis = fopen("analysis.csv", "a");		// 분석 데이터를 저장할 파일 열기 
	
	// 완료된 분석 출력
	printCountDate();			// 날짜에 따라 누적된 티켓 수 출력
	printCountUsageRange();		// 이용시설 기준으로 연령 구분에 따라 누적된 티켓 수 출력 
	printCountUsageTime();		// 이용시간 기준으로 연령 구분에 따라 누적된 티켓 수 출력
	printCountKindAge();		// 연령 구분에 따라 누적된 티켓 수 출력
	printCountKindDiscount();	// 우대사항에 따라 누적된 티켓 수 출력 
	
	fclose(report);			// report파일 닫기 
	//fclose(analysis);		// analysis파일 닫기
	
	return 0;
}


/**
	날짜 기준으로 티켓 수 누적하는 함수
	매개변수 : 판매 날짜, 판매 수량, 판매 총액
	누적값이 저장될 변수를 전역변수로 선언하였으므로 return값 없다. 
*/
 void fCountDate(int date, int amount, int price)
 {
	for(int i=0;i<maxDataCount;i++)	// 저장된 날짜-누적티켓 배열에 인덱스 0부터 접근 
	{
		if(countDate[i][0] == date)		// 접근한 날짜가 현재 날짜와 일치하면 -> 기존에 있는 날짜
		{
			countDate[i][1] += amount;	// 누적 티켓 수, 기존 값에 누적 
			countDate[i][2] += price;	// 누적 매출, 기존 값에 누적  
			break; 
		}
		else if(countDate[i][0] == 0)	// 접근한 날짜가 저장된 날짜를 다 지나서 비어있는 배열이면 -> 기존에 없는 날짜 
		{
			countDate[i][0] = date;		// 날짜를 배열에 저장 
			countDate[i][1] = amount;	// 누적 티켓 수, 처음으로 값 저장
			countDate[i][2] = price;	// 누적 매출, 처음으로 값 저장
			break;
		}
	}
 }

/**
	용시설 기준으로 연령 구분에 따라 티켓 수 누적하는 함수 
	매개변수 : 이용시설 구분/연령 구분에 해당하는 숫자, 판매 수량 
*/
void fCountUsageRage(int kindUsageRange, int kindAge, int amount)
{
	if(kindUsageRange==1)		// 종합이용권 
	{
		switch(kindAge)
		{
			case 1: countKindUsageRange[0][0] += amount; break;	// 노인 
			case 2: countKindUsageRange[0][1] += amount; break;	// 성인 
			case 3: countKindUsageRange[0][2] += amount; break;	// 청소년 
			case 4: countKindUsageRange[0][3] += amount; break;	// 어린이 
			case 5: countKindUsageRange[0][4] += amount; break;	// 유아 
		};
	}
	else if(kindUsageRange==2)	// 시설이용권 
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
	이용시간 기준으로 연령 구분에 따라 티켓 수 누적하는 함수 
	매개변수 : 이용시간 구분/연령 구분에 해당하는 숫자, 판매 수량 
*/
void fCountUsageTime(int kindUsageTime,  int kindAge, int amount)
{
	if(kindUsageTime==1)		// 종일권 
	{
		switch(kindAge)
		{
			case 1: countKindUsageTime[0][0] += amount; break;	// 노인 
			case 2: countKindUsageTime[0][1] += amount; break;	// 성인 
			case 3: countKindUsageTime[0][2] += amount; break;	// 청소년 
			case 4: countKindUsageTime[0][3] += amount; break;	// 어린이 
			case 5: countKindUsageTime[0][4] += amount; break;	// 유아 
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
	연령구분 기준으로 티켓 수 누적하는 함수 
	매개변수 : 연령 구분에 해당하는 숫자, 판매 수량 
*/
void fCountAge(int kindAge, int amount)
{
	switch(kindAge)
	{
		case 1: countKindAge[0] += amount;	break;	// 노인 
		case 2: countKindAge[1] += amount;	break;	// 성인 
		case 3: countKindAge[2] += amount;	break;	// 청소년 
		case 4: countKindAge[3] += amount;	break;	// 어린이 
		case 5: countKindAge[4] += amount;	break;	// 유아 
	};
}

/**
	우대사항 기준으로 티켓 수 누적하는 함수 
	매개변수 : 우대사항 구분에 해당하는 숫자, 판매 수량 
*/
void fCountDiscount(int kindDiscount, int amount)
{
	switch(kindDiscount)
	{
		case 1: countKindDiscount[0] += amount;	break;	// 장애인 
		case 2: countKindDiscount[1] += amount;	break;	// 국가유공자 
		case 3: countKindDiscount[2] += amount;	break;	// 휴가장병 
		case 4: countKindDiscount[3] += amount;	break;	// 임산부 
		case 5: countKindDiscount[4] += amount;	break;	// 다자녀 가족 
		case 6: countKindDiscount[5] += amount;	break;	// 해당없음 
	};
}

/**
	총 매출액 누적하는 함수 
	매개변수 : 판매 금액
*/
void fSumPrice(int price)
{
	sumPrice += price;
}

/**
	총 티켓 수 누적하는 함수 
	매개변수 : 판매 금액
*/
void fCountTickets(int amount)
{
	countTickets += amount;
}




/*	날짜에 따른 누적 티켓 수 출력하는 함수 */
void printCountDate()
{
	printf("\n\n------------------------------------- 날짜에 따른 판매현황 -------------------------------------\n\n");

	for(int i=0;i<maxDataCount;i++)
	{
		if(countDate[i][0]==0) break; // 마지막 데이터가 저장된 인덱스를 지났으면 종료 
		
		printf("%d년 %d월 %d일\t: 총 %d 장 \t 총액 %d원\n", chageDateFmt(countDate[i][0])[0], chageDateFmt(countDate[i][0])[1], chageDateFmt(countDate[i][0])[2], countDate[i][1], countDate[i][2]);
	}

	printf(" => 누적 %d 장\n\n", countTickets);
}

/*	이용시설에 따른 연령구분 별 누적 티켓 수 출력하는 함수 */
void printCountUsageRange()
{
	int SumCountUsageRange[sizeof(countKindUsageRange)/sizeof(countKindUsageRange[0])]  = {0};
	
	printf("\n\n------------------------------------- 이용시설에 따른 판매현황 -------------------------------------\n\n");
	
	for(int i=0 ; i<sizeof(countKindUsageRange)/sizeof(countKindUsageRange[0]) ; i++)
	{
		printf("*%s\n", KoKindUsageRange[i]);
		
		for(int j=0 ; j<sizeof(countKindUsageRange[0])/sizeof(int) ; j++)
		{
			SumCountUsageRange[i] = SumCountUsageRange[i] + countKindUsageRange[i][j];
			printf("%s\t : %d 장\n", KoKindAge[j], countKindUsageRange[i][j]);
		}
		
		printf(" => 누적 %s : %d 장\n\n", KoKindUsageRange[i], SumCountUsageRange[i]);
	}
}

/*	이용시간에 따른 연령구분 별 누적 티켓 수 출력하는 함수 */
void printCountUsageTime()
{
	int SumCountUsageTime[sizeof(countKindUsageTime)/sizeof(countKindUsageTime[0])]  = {0};
	
	printf("\n\n------------------------------------- 이용시간에 따른 판매현황 -------------------------------------\n\n");
	
	for(int i=0 ; i<sizeof(countKindUsageTime)/sizeof(countKindUsageTime[0]) ; i++)
	{
		printf("*%s\n", KoKindUsageTime[i]);
		
		for(int j=0 ; j<sizeof(countKindUsageTime[0])/sizeof(int) ; j++)
		{
			SumCountUsageTime[i] = SumCountUsageTime[i] + countKindUsageTime[i][j];
			printf("%s\t : %d 장\n", KoKindAge[j], countKindUsageTime[i][j]);
		}
		
		printf(" => 누적 %s : %d 장\n\n", KoKindUsageTime[i], SumCountUsageTime[i]);
	}
}

/*	연령구분에 따른 누적 티켓 수  출력하는 함수 */ 
void printCountKindAge()
{
	printf("\n\n------------------------------------- 연령 구분에 따른 판매현황 -------------------------------------\n\n");
	
	for(int i=0 ; i<sizeof(countKindAge)/sizeof(int) ; i++)
	{
		printf("%s\t : %d 장\n", KoKindAge[i], countKindAge[i]);
	}
	
	printf(" => 누적 %d 장\n\n", countTickets);
}

/*	우대사항에 따른 누적 티켓 수 출력하는 함수 */ 
void printCountKindDiscount()
{
	printf("\n\n------------------------------------- 우대사항에 따른 판매현황 -------------------------------------\n\n");
	
	for(int i=0 ; i<sizeof(countKindDiscount)/sizeof(int) ; i++)
	{
		printf("%s\t : %d 장\n", KoKindDiscount[i], countKindDiscount[i]);
	}
	printf(" => 누적 %d 장\n\n", countTickets);
} 



/*	
	날짜 6자리를 연,월,일 2자리 씩 자르는 함수
	매개변수 : 연월일 6자리 숫자
	반환 : 연, 월, 일 2자리씩 저장된 크기 3인 '배열 
*/ 
int* chageDateFmt(int date)
{
	// 현재시간 가져오기 
	struct tm *cur_date;						// time.h에 정의되어 있는 날짜와 시간을 나타내는 구조체 
	
	time_t curTime=time(NULL);					// #include <time.h> 필요, time_t time(time * timer) : 타이머가 Null이 아니면 timer가 가르키는 변수에 현재 시간을 채운다. 
	cur_date=localtime(&curTime);				// localtime(const time_t * timer) : 타이머가 가르키는 변수를 UTC시간 기준으로 구조체로 변환해 그 주소를 반환 
	
	// return될 배열 선언, [0]은 년도, [1]은 월, [2]는 날짜 
	int Ddate[3];
	
	// 연도가 올해보다 크면 1900년대로 인식, ex) 230101 => 1923.01.01 , 220101 => 2022.01.01
	if((cur_date->tm_year-100) >= date/10000)	Ddate[0] = 2000 + date/10000;
	else										Ddate[0] = 1900 + date/10000;
	Ddate[1] = (date%10000)/100;
	Ddate[2] = date%100;
	
	return Ddate;
}

