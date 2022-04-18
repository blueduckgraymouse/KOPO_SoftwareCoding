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
public class K35_Ex05_re {
	
	public static void main(String[] args) {
		
		// 영수증에 출력할 상품 정보
		String k35_itemname1 = "퓨어에어 비말차단용마스크(최고급형)";
		String k35_itemcode1 = "1031615";
		int k35_price1 = 3000;
		int k35_amount1 = 1;
		String k35_itemname2 = "슬라이드식명찰(가로형)(100호)";
		String k35_itemcode2 = "11008152";
		int k35_price2 = 1000;
		int k35_amount2 = 1;
		String k35_itemname3 = "매직크(알루미늄타입)";
		String k35_itemcode3 = "1020800";
		int k35_price3 = 1000;
		int k35_amount3 = 1;
		
		// 금액, 날짜 관련 객체 선언 및 초기화
		DecimalFormat k35_df = new DecimalFormat("###,###,###,###,###");
		Calendar k35_calt = Calendar.getInstance();								// 기본 타임존과 지역에 대해 날짜정보(현재 시간)를 가져와 calt라는 변수에 저장
		Calendar k35_calt_refund = k35_calt;									// 환불가능 마감일이 저장될 calt_refund라는 변수에 현재 시간 저장
	 	SimpleDateFormat k35_sdt = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");	// 연도4자리 월 2자리 일 2자리 시간 2자리 분 2자리 초 2자리의 시간 형식을 sdt객라는 객체로 생성
		SimpleDateFormat k35_sdt_refund = new SimpleDateFormat("MM월dd일");		// 환불 마감일 출력에는 월,일만 필요하므로 그에 맞는 형식의 객체 k35_sdt_refund 생성
		k35_calt_refund.add(Calendar.DATE, 14);									// k35_sdt_refund에 저장된 현재 날짜에 14일을 추가 <- 최대 14일 이내 환불 가능
		
		// 영수증 계산에 필요한 변수 선언 및 초기화
		int k35_sumPrice = k35_price1 + k35_price2 + k35_price3;				// 판매 합계 계산
		double k35_tax_rate = 0.1;												// 세율 10%로 설정
		int k35_price_Before = 0;												// 판매 합계의 세전 금액이 저장될 변수
		int k35_tax = 0;														// 판매 합게의 세금이 저장될 변수
		
		// 판매합계의 세전 금액와 세금 계산 
		if (k35_sumPrice / (1 + k35_tax_rate) % 1 != 0)							// 소수점이 존재할 경우
			k35_price_Before = (int)(k35_sumPrice / (1 + k35_tax_rate)) + 1;	//	올림 처리해서 세금 계산
		else																	// 소수점이 없는 경우
			k35_price_Before = (int)(k35_sumPrice / (1 + k35_tax_rate));		//  그대로 세금 계산
		k35_tax = k35_sumPrice - k35_price_Before; 
		
		/* 영수증 출력
		 * 가로의 총 길이는 48, 한글은 2자리, 그 외는 1자리 차지.
		 */
		System.out.printf("%16s%s\n", "", "\"국민가계, 다이소\"");																// 48 = [16] + 16 + [16]
		System.out.printf("%s\n", "(주) 아성다이소_분당서현점");			
		System.out.printf("%s\n", "전화 : 031-702-6016");
		System.out.printf("%s\n", "본사 : 서울 강서구 남부순환로 2748");
		System.out.printf("%s\n", "대표 : 박정부, 신호섭 213-81-52063");
		System.out.printf("%s\n", "매장 : 경기도 성남시 분당구 분당로 53번길 11 (서현동)");
		System.out.printf("================================================\n");
		
		System.out.printf("%10s%s\n", "", "소비자중심경영(CCM) 인증기업");															// 48 = [10] + 14 + 6 + 8 + [10]
		System.out.printf("%8s%s\n", "", "ISO 9001 품질경영시스템 인증기업");														// 48 = [8] + 9 + 14 + 9 + [8]
		System.out.printf("%9s%s%s%s\n", "" , "교환/환불 14일(", k35_sdt_refund.format(k35_calt_refund.getTime()), ")이내,");	// 48 = [9] + 15 + 8 + 6 + [10] 
		System.out.printf("%s\n", "(전자)영수증, 결제카드 지참 후 구입매장에서 가능");													// 48 = 48
		System.out.printf("%7s%s\n", "", "포장/가격 택 훼손시 교환/환불 불가");														// 48 = [7] + 34 + [7]
		System.out.printf("================================================\n");
		
		System.out.printf("%s%35s\n", "[POS 1058231]", k35_sdt.format(k35_calt.getTime()));									// 48 = 13 + [26] + 19
		System.out.printf("================================================\n");
		
		k35_auto_format_item(26, k35_itemname1);																			// 한글은 2자리를 차지하는 까다로움 때문에 26자리는 상품명의 자리로 할당하여 함수로 처리하고
		System.out.printf("%9s%4d%9s\n", k35_df.format(k35_price1), 														// 나머지는 22자리 할당하여 출력
										 k35_amount1,
										 k35_df.format(k35_price1 * k35_amount1));
		System.out.printf("%s\n", "["+ k35_itemcode1 + "]");
		
		k35_auto_format_item(26, k35_itemname2);
		System.out.printf("%9s%4d%9s\n", k35_df.format(k35_price2), 
										 k35_amount2, 
										 k35_df.format(k35_price2 * k35_amount2));
		System.out.printf("%s\n", "["+ k35_itemcode2 + "]");
		
		k35_auto_format_item(26, k35_itemname3);
		System.out.printf("%9s%4d%9s\n", k35_df.format(k35_price3), 
										 k35_amount3, 
										 k35_df.format(k35_price3 * k35_amount3));
		System.out.printf("%s\n", "["+ k35_itemcode3 + "]");

		System.out.printf("%14s%4s%26s\n", "", "과세합계",  k35_df.format(k35_price_Before));
		System.out.printf("%14s%5s%26s\n", "", "부과세",  k35_df.format(k35_tax));
		System.out.printf("------------------------------------------------\n");
		
		System.out.printf("%s%40s\n", "판매합계", k35_df.format(k35_sumPrice));
		System.out.printf("================================================\n");
		
		System.out.printf("%s%40s\n", "신용카드", k35_df.format(k35_sumPrice));
		System.out.printf("------------------------------------------------\n");
		
		System.out.printf("%s%40s\n", "우리카드", "538720**********");
		System.out.printf("%s%s%24s\n", "승인번호", " 77982843(0)", "승인금액 " + k35_df.format(k35_sumPrice));
		System.out.printf("================================================\n");
		
		System.out.printf("%9s%s%s\n", "", k35_sdt.format(k35_calt.getTime()), " 분당서현점");									// 48 = [9] + 30 + [9]
		System.out.printf("%s\n", "상품 및 기타 문의 : 1522-4400");
		System.out.printf("%s\n", "멥버십 및  샵다이소 관련 문의 : 1599-2211");
		System.out.printf("%14s%s\n", "", "||||||||||||||||||||");															// 48 = [14] + 20 + [14]
		System.out.printf("%16s%s\n", "", "2112820610158231");																// 48 = [16] + 16 + [16]
		System.out.printf("------------------------------------------------\n");
		
		System.out.printf("%s\n%s", " ◈ 다이소 맴버쉽 앱 또는 홈페이지에 접속하셔서 ",
									"   회원가입 후 다양한 혜택을 누려보세요! ◈");
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
		
		// 한글자씩 확인하며 상품명의 총 길이 확인(영수증 출력 범위 내, 출력 가능 길이를 초과하면 영수증에 출력되는 마지막 글자까지) & 영수증에 출력되는 마지막 글자의 인덱스 번호 확인
		for (k35_i_Chr = 0 ; k35_sum_length < k35_max_width_item && k35_i_Chr < k35_item.length() ; k35_i_Chr++) {	// 총 길이가 26(영수증 상품명이 들어갈 수 있는 최대 길이)를 넘거나 마지막 글자까지 다 확인할 때까지 반복
			String k35_chr = k35_item.substring(k35_i_Chr, k35_i_Chr + 1);							// 확인할 글자 추출
			if (k35_chr.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*"))												// 한글이면
				k35_sum_length += 2;																//	 누적 길이 +2
			 else																					// 아니면
				k35_sum_length++;																	//	 누적 길이 +1
		}
		
		System.out.printf("%s", k35_item.substring(0, k35_i_Chr));									// 상품명 출력 - > 추가작업 필요: 마지막 자리가 한글인데 출력 여유 길이가 1이면 버림처리 - 안그러면 출력시 깨진다. 
		
		for (int i = 0 ; i < 26 - k35_sum_length ; i++) {											// 할당 받은 26자리 중 남은 자리가 있으면 공백으로 채운다.
			System.out.printf(" ");
		}
	}
	
}
