package k35_ch05;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**	소프트웨어코딩 심화 5강 - 리포팅 연습(영수증 출력 등)
 * 
 *  영수증 출력 1  - p7
 * 
 * @author KOPO
 *
 */
public class K35_Ex04_resize {
	
	public static void main(String[] args) {
		// 필요한 객체 생성 및 변수 초기화 
		DecimalFormat k35_df = new DecimalFormat("###,###,###,###,###");
		Calendar k35_calt = Calendar.getInstance();								// 기본 타임존과 지역에 대해 날짜정보를 가져와 calt라는 변수에 저장
		SimpleDateFormat k35_sdt = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");	// 연도4자리 월 2자리 일 2자리 시간 2자리 분 2자리 초 2자리의 시간 형식을 sdt객라는 객체로 생성
		
		int k35_iPrice = 33000;												// 총 결제 금액 33000원 변수로 초기화
		double k35_tax_rate = 0.1;											// 세율 0.1(10%) 변수로 초기화
		int k35_price_Before = 0;											// 세전 가격이 저장될 변수 0으로 초기화
		int k35_tax = 0;													// 세금이 저장될 변수 0으로 초기화
		
		// 세금 소수점 처리 및 계산
		if (k35_iPrice / (1 + k35_tax_rate) % 1 != 0) 						// 소수점이 존재할 경우
			k35_price_Before = (int)(k35_iPrice / (1 + k35_tax_rate)) + 1;	//	올림 처리해서 세금 계산
		else 																// 소수점이 없는 경우
			k35_price_Before = (int)(k35_iPrice / (1 + k35_tax_rate));		//  그대로 세금 계산
		k35_tax = k35_iPrice - k35_price_Before; 							// 세금 = 합계 - 세전 가격
		
		/* 영수증 출력
		 * 가로 size = 48, 한글은 2, 영어,공백,특수문자는 1차지.  == > 41로 resize
		 */
		System.out.printf("%s\n", "신용승인");
		System.out.printf("%3s%3s%10s", "단말기", " : ", "2N68665898");							
		System.out.printf("%5s%s%3s%6s\n", "", "전표번호", " : ", "041218");						
		System.out.printf("%s\n", "가맹점 : 한양김치찌개");
		System.out.printf("%s\n", "주  소 : 경기 성남시 분당구 황새울로351번\n길 10, 1층");
		System.out.printf("%s\n", "대표자 : 유창신");
		System.out.printf("%s%s%s", "사업자", " : ", "752-53-00558");								
		System.out.printf("%7s%s%s%s\n", "", "TEL", " : ", "7055695");							
		System.out.printf("- - - - - - - - - - - - - - - - - - - - -\n");				
		
		System.out.printf("%s%32s%s\n","금  액", k35_df.format(k35_price_Before), " 원");				
		System.out.printf("%s%32s%s\n", "부가세", k35_df.format(k35_tax), " 원");					
		System.out.printf("%s%32s%s\n", "합  계", k35_df.format(k35_iPrice), " 원");				
		System.out.printf("- - - - - - - - - - - - - - - - - - - - -\n");				
		
		System.out.printf("%s\n", "우리카드");
		System.out.printf("%s%s%s%2s%s\n", "카드번호", " : ", "5387-20**-****-4613(S)", "", "일시불"); 
		System.out.printf("%s%s%s\n", "거래일시", " : ", k35_sdt.format(k35_calt.getTime()));
		System.out.printf("%s%s%s%8s%s%s%s\n", "매입", " : ", "비씨카드사", "", "가맹", " : ", "720068568"); 
		System.out.printf("%s%s%s\n", "알림", " : ", "EDC매출표");
		System.out.printf("%s%s%s\n", "문의", " : ", "TEL)1544-4700");
		System.out.printf("- - - - - - - - - - - - - - - - - - - - -\n");				
		
		System.out.printf("%17s%s\n", "", "* 감사합니다 *");										
		System.out.printf("%23s%s\n", "", "표준V2.08_20220212");									
	}
	
}
