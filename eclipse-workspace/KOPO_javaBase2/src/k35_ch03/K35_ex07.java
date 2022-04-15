package k35_ch03;

/**
 * 소프트웨어코딩_3강 - p16 실습 코드 실행해보기
 * 
 * # 소비자 세금 계산
 * 
 * @author KOPO35
 */

public class K35_ex07 {
	
	public static void main(String[] args) {
		String[] k35_item = {"맛동산", "웨하스", "롯데센드", "오땅", "사브레", "촉촉한 초코칩"};	// 구매한 과자 이름 목록	, 최대 10글자
	    int[] k35_price = {1000, 20000, 3000, 2500000, 1451, 12345};					// 각 과자의 가격 - 최대 999,999,999 원
	    int[] k35_amount = {10000, 2, 1, 20, 5, 1};										// 각 과자의 구매 수량 - 최대 9,999,999 개 
	    double k35_tax_rate= 0.1;														// 세율
	    int k35_total_sum = 0;															// 누적될 지불 금액
	    int k35_total_net_price = 0;													// 과제 금액
	    int k35_width_size_item = 20;													// 과자 이름이 출력되는 항목 칸의 크기, 한글은 크기 2, 숫자와 공백은 크기 1
	    
	    
	    System.out.printf("**********************************************\n");
	    //System.out.printf("\t\t너가 지른 과자들\t\t\n");
	    k35_auto_blank(8*2);
	    System.out.printf("너가 지른 과자들");
	    k35_auto_blank(8*2);
	    System.out.printf("\n");
	    //System.out.printf(" 항 목\t\t\t단 가\t수 량\t합  계 \n");
	    System.out.printf("항 목");
	    k35_auto_blank(15);
	    System.out.printf("단 가");
	    k35_auto_blank(3);
	    System.out.printf("수 량");
	    k35_auto_blank(3);
	    System.out.printf("합 계");
	    System.out.printf("\n");
	    
	    
	    for (int i = 0; i < k35_item.length; i++) {										// 구매한 각 과자에 대해서 반복
	       int k35_sum = k35_price[i] * k35_amount[i];									// 과자 총 가격 = 과자 세전 가격 * 구매 수량, 최대 9,999,999,999원
	       k35_total_sum = k35_total_sum + k35_sum;										// 반복문을 돌면서 과자 총 가격 누적
	       
	       k35_auto_format_item(k35_width_size_item, k35_item[i]);						// 과자 이름 출력
	       System.out.printf("%9d", k35_price[i]);										// 가격 출력
	       System.out.printf("%7d", k35_amount[i]);										// 수량 출력
	       System.out.printf("%10d\n", k35_sum);										// 합계 출력
	    }
	    
	    System.out.printf("**********************************************\n");
	    System.out.printf(" 지불 금액 : %30d 원\n", k35_total_sum);							
	    
		if (k35_total_sum / (1 + k35_tax_rate) % 1 != 0) {							// 소수점이 존재할 경우
			k35_total_net_price = (int)(k35_total_sum / (1 + k35_tax_rate)) + 1;	//	올림 처리해서 세금 계산
		} else {																	// 소수점이 없는 경우
			k35_total_net_price = (int)(k35_total_sum / (1 + k35_tax_rate));		//  그대로 세금 계산
		}
	    System.out.printf(" 과세 금액 : %30d 원\n", k35_total_net_price);				// 계산된 과세 금액 출력
	    
	    int tax = k35_total_sum - k35_total_net_price;								// 세금 = 지불 금액 - 과세 금액
	    System.out.printf(" 세     액 : %30d 원\n", tax);								// 계산된 세금 출력
	}
	
	
	/**
	 * 정해진 크기의 항목 칸에 들어갈 과자 이름을 출력하고 나머지 공간에 공백을 채운다.
	 * @param width_size_item : 세팅한 항목 칸의 크기
	 * @param item			  : 항목 칸에 들어갈 과자 이름 문자열
	 */
	public static void k35_auto_format_item(int k35_width_size_item, String k35_item) {
		System.out.printf("%.10s", k35_item);												// 과자 이름 출력
		
		int k35_count_idx = k35_item.length();												// 문자열의 길이 확인하여 변수에 저장
		int k35_count_blank = 0;															// 문자열 속에 공백의 수를 누적 시킬 변수 선언
		while(k35_item.contains(" ")) {														// 공백이 존재하는 동안 반복
			k35_item = k35_item.replace(" ", "");											// 공백을 제거하면서
			k35_count_blank++;																// 공백 횟수 누적
		}
		int k35_time_blank = k35_width_size_item - (k35_count_idx * 2 - k35_count_blank);	// 설정한 '항목'의 크기에서 과자이름이 차지하는 만큼 빼서 출력해야하는 공백 횟수 확인
		for (int i = 0 ; i < k35_time_blank ; i++) {										// 위에서 구한 횟수 만큼 공백 출력
			System.out.printf(" ");
		}
	}
	/**
	 * 출력한 공백 크기를 입력받아 그만큼 공백을 출력한다.
	 * @param size : 출력한 공백 크기
	 */
	public static void k35_auto_blank(int size) {
		for( int i = 0 ; i < size ; i++) {		// 파라미터로 들어온 공백 크기만큼 반복
			System.out.printf(" ");				// 공백 반복 출력
		}
	}
}