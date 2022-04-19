package k35_ch05;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**	소프트웨어코딩 심화 5강 - 리포팅 연습(영수증 출력 등)
 * 
 *  영수증 출력 2 - p8
 * 
 * @author KOPO
 *
 */
public class K35_Ex06_re_resize {
	
	public static void main(String[] args) {
		
		// 영수증에 출력할 상품 정보
		String[] k35_itemname = {"해피홈 SAFE365 핸드워시(펌프형)", "(G)LH원형유리화병 100ml", "건포도", "오렌지주스", "초코에몽", "abc초콜렛", "서울우유" ,"크래미", "포카칩", "프링글스 오리지날",
								"고등어", "양배추", "고구마", "감자", "비비고 만두", "티코", "하겐다즈", "텀블러", "모니터 clear하고 선명한 그 자체", "삼성 super 울트라 빠른 노트북",
								"선풍기", "프린터", "한우 등심", "한우 안심", "키보드", "마우스", "카누", "대파", "1+ 등급란 10개입 특란", "동원양반볶음김치기획",
								"공기청정기", "스피커"};
		int[] k35_price = 		{10000, 1200, 13000, 3000, 1000, 500, 3800, 3300, 2000, 1700,
								10000, 1500, 9900, 7900, 11900, 4200, 5100, 17000, 213200, 12000000,
								79300, 139000, 32190, 27890, 65900, 54190, 9900, 2500, 19280, 3510,
								342010, 98000};
		int[] k35_num = 		{2, 8, 1, 2, 3, 4, 1, 1, 2, 1,
								1, 2, 1, 1, 2, 2, 1, 1, 1, 1,
								1, 1, 2, 2, 1, 1, 3, 10, 3, 23,
								1, 59};
		boolean[] k35_taxfree = {false, false, true, false, false, false, false, false, false, false,
								true, true, true, true, false, false, false, false, false, false,
								false, false, true, true, false, false, false, true, true, false,
								false, false};
		
		// 금액, 날짜 관련 객체 선언 및 초기화
		DecimalFormat k35_df = new DecimalFormat("###,###,###,###,###");
		Calendar k35_calt = Calendar.getInstance();								// 기본 타임존과 지역에 대해 날짜정보(현재 시간)를 가져와 calt라는 변수에 저장
	 	SimpleDateFormat k35_sdt = new SimpleDateFormat("YYYY-MM-dd HH:mm");	// 연도 4자리, 월 2자리, 일 2자리, 시간 2자리, 분 2자리의 시간 형식을 sdt객라는 객체로 생성
	 	SimpleDateFormat k35_sdt2 = new SimpleDateFormat("YYYYMMdd");			// 연도 4자리, 월 2자리, 일 2자리의 날짜 형식을 sdt객라는 객체로 생성
	 	
		// 영수증 계산에 필요한 변수 선언 및 초기화
		int k35_sum_price_total = 0;											// 모든 물품의 총 합계
		int k35_sum_price_tax = 0;												// 세금이 부과되는 제품의 합계
		int k35_sum_price_taxFree = 0;											// 세금이 부과되지 않는 제품의 합계
		double k35_tax_rate = 0.1;												// 세율 10%로 설정
		int k35_sum_price_before_tax = 0;										// 세금이 부과되는 제품의 합계의 세전 합계이 저장될 변수
		int k35_tax = 0;														// 세금이 부과되는 제품의 합계의 세금이 저장될 변수
		
		/* 영수증 출력
		 * 가로의 총 길이는 46, 한글 2, 그 외 1 => 출력할 영수증에 맞춰 41로 리 사이즈
		 */
		System.out.printf("%3s%5s%s\n", "", "em rt", "  이마트 죽전점 (031)888-1234");			// 41 = 3 + 5 + 23+6 + 4
		System.out.printf("%3s%s%s\n", "", "  a  ", "  206-86-50913 강희석");					// 41 = 3 + 5 + 18+3 + 12
		System.out.printf("%3s%s%s\n", "", "em rt", "  용인 수지구 포은대로 552");				// 41 = 3 + 5 + 17+9 + 7
		System.out.printf("%s\n", "영수증 미지참시 교환/환불 불가");
		System.out.printf("%s\n", "정상상품에 한함, 30일 이내(신선 7일)");
		System.out.printf("%s\n", "※일부 브랜드매장 제외(매장 고지물참조)");
		System.out.printf("%s\n\n", "교환/환불 구매점에서 가능(결제카드 지참)");
		System.out.printf("%s%-22s%12s\n", "[구 매]", k35_sdt.format(k35_calt.getTime()), "POS:001-9861");	// 41 = 5+2 + (6+)16 + 12
		System.out.printf("-----------------------------------------\n");
		System.out.printf("%3s%-12s%4s%4s%8s\n", "", "상 품 명", "단  가", "수량", "금  액");		// 41 = 3 + 5+3(+7) + 4+2 + (2+)4+2 + (2+)4+2 + 1
		System.out.printf("-----------------------------------------\n");
		
		// 모든 상품 정보 출력
		for (int k35_i = 0 ; k35_i < k35_itemname.length ; k35_i++) {						
			if (k35_taxfree[k35_i] == true) {												// 비과세 물품이면
				System.out.printf("%-2s", "*");												//   * 표시
				k35_sum_price_taxFree += k35_price[k35_i] * k35_num[k35_i];					//   세금이 부과되지 않는 제품의 합계에 누적
			} else {																		// 과세 물품이면
				System.out.printf("%2s", "");												//	 * 표시X
				k35_sum_price_tax += k35_price[k35_i] * k35_num[k35_i];						//   세금이 부과되는 제품의 합계에 누적
			}
			k35_auto_format_item(15, k35_itemname[k35_i]);									// 제품명 출력 - 16자리는 상품명의 자리로 할당하여 함수로 처리, 나머지는 22자리를 나눠 할당하여 가격, 수량, 물품 합계 출력
			System.out.printf("%10s", k35_df.format(k35_price[k35_i]));						// 제품 가격 출력
			System.out.printf("%3s", k35_num[k35_i]);										// 제품 수량 출력
			System.out.printf("%11s\n", k35_df.format(k35_price[k35_i] * k35_num[k35_i]));	// 제품의 구매 총액 출력
			if (k35_i % 5 == 4)																// 제품 5개마다
				System.out.printf("-----------------------------------------\n");			//    구분 출력
		}
		
		// 과세 물품 총액, 면세 물품 총액, 부가세, 합계 계산
		k35_sum_price_total = k35_sum_price_taxFree + k35_sum_price_tax;					// 영수증 합계 = 과세물품 총액 + 비과세물품 총액
		if (k35_sum_price_tax / (1 + k35_tax_rate) / 10 % 1 != 0)							// 과세 물품 총액의 세금을 계산(과세물품 총액 / 11)했을 때 소수점이 존재하면
			k35_tax = (int)(k35_sum_price_tax / (1 + k35_tax_rate) / 10) + 1;				//	올림 처리해서 세금 계산
		else																				// 과세 물품 총액의 세금을 계산했을 때 소수점이 없는 경우
			k35_tax = (int)(k35_sum_price_tax / (1 + k35_tax_rate) / 10);					//  그대로 세금 계산
		k35_sum_price_before_tax = k35_sum_price_tax - k35_tax; 							// 과세물품 세전 총액 = 과세물품 총액 - 세금
		
		System.out.printf("\n");
		System.out.printf("%22s%14s\n", "총 품목 수량", k35_itemname.length);								// 41 = 22+5 + 14
		System.out.printf("%23s%14s\n", "(*)면 세  물 품", k35_df.format(k35_sum_price_taxFree));			// 41 = 23+4 + 14
		System.out.printf("%23s%14s\n", "과 세  물 품", k35_df.format(k35_sum_price_before_tax));			// 41 = 23+4 + 14
		System.out.printf("%24s%14s\n", "부   가   세", k35_df.format(k35_tax));							// 41 = 24+3 + 14
		System.out.printf("%25s%14s\n", "합        계", k35_df.format(k35_sum_price_total));				// 41 = 25+2 + 14
		
		System.out.printf("%11s%24s\n", "결 제 대 상 금 액", k35_df.format(k35_sum_price_total));			// 41 = 11+6 + 24
		System.out.printf("-----------------------------------------\n");
		
		System.out.printf("%11s%28s\n", "0012 KEB 하나", "541707**0484/35860658");						// 41 = 11+2 + 28
		System.out.printf("%8s%26s\n", "카드결제(IC)", "일시불 / " + k35_df.format(k35_sum_price_total));	// 41 = 8+4 + 26+3
		
		
		System.out.printf("-----------------------------------------\n");
		System.out.printf("%11s%11s\n", "", "[신세계포인트 적립]");											// 41 = 11 + 11+8 + 11			
		System.out.printf("%s\n", "홍*두 고객님의 포인트 현황입니다.");
		System.out.printf("%7s%17s%10s\n", "금회발생포인트", "9350**9995", k35_df.format(k35_sum_price_total / 1000));				// 적립 포인트 = 합계 / 1000,	41 = 7+7 + 17 + 10
		System.out.printf("%9s%15s%10s\n", "누계(가용)포인트", k35_df.format(5473 + k35_sum_price_total / 1000) + "(", "5,473)");	// 누적 포인트 = 적립 포인트 + 기존 포인트,	 41 = 9+7 + 15 + 10 
		System.out.printf("%s\n", "*신세계포인트 유효기간은 2년입니다."); 
		
		System.out.printf("-----------------------------------------\n");
		System.out.printf("%3s%s\n", "", "구매금액기준 무료주차시간 자동부여");									// 41 = 3 + 18+16 + 4
		System.out.printf("%6s%30s\n", "차량번호 :", "34저****");											// 41 = 6+4 + 30+1
		System.out.printf("%6s%31s\n", "입차시간 :", "2021-03-03 20:20:04");								// 41 = 6+4 + 31
		
		System.out.printf("-----------------------------------------\n");
		System.out.printf("%3s%10s%25s\n", "캐셔:", "084599 양OO", "1150");								// 41 = 3+2 + 10+3 + 25
		System.out.printf("%3s%s\n", "", "|||||||||||||||||||||||||||||||||||");						// 41 = 3 + 35 + 3		
		System.out.printf("%6s%s\n", "",  k35_sdt2.format(k35_calt.getTime()) + "/00119861/00164980/31");	// 41 = 6 + 29 + 6
	}
	
	/**
	 * 정해진 크기의 항목 칸에 들어갈 상품 이름을 출력하고 나머지 공간에 공백을 채운다.
	 * @param width_size_item : 세팅한 항목 칸의 크기
	 * @param item			  : 항목 칸에 들어갈 상품 이름 문자열
	 */
	public static void k35_auto_format_item(int k35_max_width_item, String k35_item) {
		// 계산에 필요한 변수 선언
		int k35_sum_length = 0;																		// 한글2, 나머지 1로 앞글자부터 차례로 카운트할 때 누적 길이가 저장될 값 [byte 단위]
		int k35_i_Chr = 0;																			// 상품명에서 현재 확인하고 있는 글자의 인덱스 번호, 반복문이 끝나면 영수증에 찍히는 마지막 글자의 인덱스가 저장되어 있다.
																									// 즉 0~i번째 글자까지의 byte가 k35_max_width_item 혹은 k35_max_width_item + 1
		// 한글자씩 확인하며 상품명의 총 길이 확인(영수증 출력 범위 내, 출력 가능 길이를 초과하면 영수증에 출력되는 마지막 글자까지) & 영수증에 출력되는 마지막 글자의 인덱스 번호 확인
		for (k35_i_Chr = 0 ; k35_sum_length < k35_max_width_item && k35_i_Chr < k35_item.length() ; k35_i_Chr++) {	// 총 길이가 26(영수증 상품명이 들어갈 수 있는 최대 길이)를 넘거나 마지막 글자까지 다 확인할 때까지 반복
			String k35_chr = k35_item.substring(k35_i_Chr, k35_i_Chr + 1);							// 확인할 글자 추출
			if (k35_chr.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*"))												// 한글이면
				k35_sum_length += 2;																//	 누적 길이 +2
			 else																					// 아니면
				k35_sum_length++;																	//	 누적 길이 +1
		}
		
		// 상품명 출력
		if ((k35_sum_length > k35_max_width_item) && k35_item.substring(k35_i_Chr-1, k35_i_Chr).matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*"))	// 출력할 상품명 길이가 초과하는데 마지막 글자의 자리가 1byte크기일 경우 영수증 출력 글자가 깨져서 나오므로
			System.out.printf("%s ", k35_item.substring(0, k35_i_Chr - 1));							//   마지막 한글 글자 빼고 출력
		else 																						// 상관없으면
			System.out.printf("%s", k35_item.substring(0, k35_i_Chr));								//   그냥 출력 출력
		
		// 남은 공백(제품명 끝 ~ 가격) 채우기
		for (int i = 0 ; i < k35_max_width_item - k35_sum_length ; i++) {							// 할당 받은 26자리 중 남은 자리가 있으면 공백으로 채운다.
			System.out.printf(" ");
		}
	}
	
}
