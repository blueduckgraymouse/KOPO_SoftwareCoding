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
public class K35_Ex05 {
	
	public static void main(String[] args) {
		
		String k35_itemname1 = "퓨어에어 비말차단용마스크(최고급형)";
		String k35_itemcode1 = "1031615";
		int k35_price1 = 3000;
		int k35_amount1 = 1;
		String k35_itemname2 = "슬라이드식명찰(가로형)(100호)";
		String k35_itemcode2 = "11008152";
		int k35_price2 = 1000;
		int k35_amount2 = 1;
		String k35_itemname3 = "매직흡착 인테리어후크(알루미늄타입)";
		String k35_itemcode3 = "1020800";
		int k35_price3 = 1000;
		int k35_amount3 = 1;
		
		DecimalFormat k35_df = new DecimalFormat("###,###,###,###,###");
		
		Calendar k35_calt = Calendar.getInstance();								// 기본 타임존과 지역에 대해 날짜정보를 가져와 calt라는 변수에 저장
		Calendar k35_calt_refund = k35_calt;		
		SimpleDateFormat k35_sdt = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");	// 연도4자리 월 2자리 일 2자리 시간 2자리 분 2자리 초 2자리의 시간 형식을 sdt객라는 객체로 생성
		SimpleDateFormat k35_sdt_refund = new SimpleDateFormat("MM월dd일");		
		
		k35_calt_refund.add(Calendar.DATE, 14);
		
		
		int k35_sumPrice = k35_price1 + k35_price2 + k35_price3;
		double k35_tax_rate = 0.1;
		int k35_price_Before = 0;
		int k35_tax = 0;
		
		if (k35_sumPrice / (1 + k35_tax_rate) % 1 != 0) {							// 소수점이 존재할 경우
			k35_price_Before = (int)(k35_sumPrice / (1 + k35_tax_rate)) + 1;	//	올림 처리해서 세금 계산
		} else {																// 소수점이 없는 경우
			k35_price_Before = (int)(k35_sumPrice / (1 + k35_tax_rate));		//  그대로 세금 계산
		}
		
		k35_tax = k35_sumPrice - k35_price_Before; 
		
		// 가로 size = 48, 한글은 2, 영어,공백,특수문자는 1차지.
		
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
		
		System.out.printf("%14.14s%s%9s%4d%9s\n", k35_itemname1, "",
													k35_df.format(k35_price1), 
													k35_amount1, 
													k35_df.format(k35_price1 * k35_amount1));
		System.out.printf("%s\n", "["+ k35_itemcode1 + "]");
		System.out.printf("%14.14s%s%9s%4d%9s\n", k35_itemname2, "",
													k35_df.format(k35_price2), 
													k35_amount2, 
													k35_df.format(k35_price2 * k35_amount2));
		System.out.printf("%s\n", "["+ k35_itemcode2 + "]");
		System.out.printf("%14.14s%s%9s%4d%9s\n", k35_itemname3, "",
													k35_df.format(k35_price3), 
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
		
		System.out.printf("%9s%s%s\n", "", k35_sdt.format(k35_calt.getTime()), " 분당서현점");	// 48 = [9] + 30 + [9]
		System.out.printf("%s\n", "상품 및 기타 문의 : 1522-4400");
		System.out.printf("%s\n", "멥버십 및  샵다이소 관련 문의 : 1599-2211");
		System.out.printf("%14s%s\n", "", "||||||||||||||||||||");			// 48 = [14] + 20 + [14]
		System.out.printf("%16s%s\n", "", "2112820610158231");			// 48 = [16] + 16 + [16]
		System.out.printf("------------------------------------------------\n");
		
		System.out.printf("%s\n%s", " ◈ 다이소 맴버쉽 앱 또는 홈페이지에 접속하셔서 ",
									"   회원가입 후 다양한 혜택을 누려보세요! ◈");
	}
	
	/**
	 * 정해진 크기의 항목 칸에 들어갈 과자 이름을 출력하고 나머지 공간에 공백을 채운다.
	 * @param width_size_item : 세팅한 항목 칸의 크기
	 * @param item			  : 항목 칸에 들어갈 과자 이름 문자열
	 */
	public static void k35_auto_format_item(int k35_width_size_item, String k35_item) {
		System.out.printf("%.10s", k35_item);												// 상품 이름 출력
		
		int k35_count_idx = k35_item.length();												// 문자열의 길이 확인하여 변수에 저장
		int k35_count_blank = 0;															// 문자열 속에 공백의 수를 누적 시킬 변수 선언
		while(k35_item.contains(" ")) {														// 공백이 존재하는 동안 반복
			k35_item = k35_item.replace(" ", "");											// 공백을 제거하면서
			k35_count_blank++;																// 공백 횟수 누적
		}
		int k35_time_blank = k35_width_size_item - (k35_count_idx * 2 - k35_count_blank);	// 설정한 '항목'의 크기에서 상품이름이 차지하는 만큼 빼서 출력해야하는 공백 횟수 확인
		for (int i = 0 ; i < k35_time_blank ; i++) {										// 위에서 구한 횟수 만큼 공백 출력
			System.out.printf(" ");
		}
	}
	
}
