package k35_ch03;

import java.text.DecimalFormat;

/**
 * 소프트웨어코딩_3강 - p23 실습 코드 실행해보기
 * 
 * # 콤마찍기, 날짜 적용
 * 
 * @author KOPO35
 */

public class K35_ex12 {
	
	public static void main(String[] args) {
		int k35_myWon = 1000000;				// 환전할 내 돈
		double k35_moneyEx = 1238.21;			// 미화 환율
		double k35_commision = 0.003;			// 환전 수수료 비율
		
		double k35_comPerOne = k35_moneyEx * k35_commision;					// 1달러 당 수수료 금액 = 1달러 환율 * 수수료 비율
		
		int k35_usd = (int) (k35_myWon / (k35_moneyEx + k35_comPerOne));	// 환전되는 달러 지폐 = 내 돈 / (미화 환율 + 수수료) 의 몫
		//int k35_remain = (int) (k35_myWon - k35_usd * k35_moneyEx);			// 환전 후 남는 잔액 = 내 돈 / 미화 환율 의 나머지
		
		double k35_totalcom = k35_usd * k35_comPerOne;						// 환전 총 수수료 = 총 환전 달러 금액 * 1달러당 수수료 금액
		
		int k35_i_totalcom;													// 환전 총 수수료의 소수점이 존재했는데 버림을 하면 은행손해니까 확인 후 최종 총 수수료가 저장될 변수
		if(k35_totalcom != (double)((int)k35_totalcom)) {						// 소수점이 존재하면
			k35_i_totalcom = (int) k35_totalcom + 1;							// 		환전 총 수수료 + 1원
			//k35_remain = (int) (k35_myWon % (k35_moneyEx + k35_comPerOne)) - 1;	// 	환전 총 수수료가 +1원 됬으면 잔돈에서는 -1원
		} else {																// 소수점이 존재하지 않으면
			k35_i_totalcom = (int) k35_totalcom;								// 		환전 총 수수료는 그대로
			//k35_remain = (int) (k35_myWon % (k35_moneyEx + k35_comPerOne));	// 		환전 총 수수료가 그대로면 잔돈도 그대로
		}
		
		int k35_remain = (int) (k35_myWon - k35_usd * k35_moneyEx - k35_i_totalcom)	;
		
		DecimalFormat k35_df = new DecimalFormat("###,###,###,###,###");		// 세 자리수 마다 콤마를 찍는 숫자 형식을 df라고 생성
		
 		System.out.printf("*****************************************************\n");
		System.out.printf("*                 콤마 찍기, 날짜 적용                   *\n");
		System.out.printf("총 수수료 : %s원 => 미화 : %s달러, 달러 당 수수료 : %f원\n",	// decimalFormat객체의 format 메서드의 리턴 타입은 String이므로 %s를 사용해야 한다.
				k35_df.format(k35_i_totalcom), 									// 출력해야하는 금액 데이터는 모두 df객체의 format메서드를 통해 정의한 형식의 String으로
				k35_df.format(k35_usd), 										// 반환받아 출력한다.
				k35_comPerOne);
		System.out.printf("총 한화환전금액 : %s원 => 미화 : %s달러, 수수료 징수 : %s원, 잔돈 : %s원\n", 	// decimalFormat객체의 format 메서드의 리턴 타입은 String이므로 %s를 사용해야 한다.
				k35_df.format(k35_myWon), 													// 출력해야하는 금액 데이터는 모두 df객체의 format메서드를 통해 정의한 형식의 String으로
				k35_df.format(k35_usd), 													// 반환받아 출력한다.
				k35_df.format(k35_i_totalcom), 
				k35_df.format(k35_remain)); 
		System.out.printf("*****************************************************\n");
	}
	
}