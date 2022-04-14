package k35_ch03;

/**
 * 소프트웨어코딩_3강 - p20 실습 코드 실행해보기
 * 
 * # 수수료 포함 환전 계산(2)
 * 
 * @author KOPO35
 */

public class K35_ex10 {
	
	public static void main(String[] args) {
		int k35_myWon = 1000000;				// 환전할 내 돈
		double k35_moneyEx = 1238.21;			// 미화 환율
		double k35_commision = 0.003;			// 환전 수수료 비율
		
		int k35_usd = (int) (k35_myWon / k35_moneyEx);				// 환전되는 달러 지폐 = 내 돈 / 미화 환율 의 몫
		int k35_remain = (int) (k35_myWon - k35_usd * k35_moneyEx);	// 환전 후 남는 잔액 = 내 돈 / 미화 환율 의 나머지

		// -----------------------------------------------------
		
		double k35_comPerOne = k35_moneyEx * k35_commision;			// 1달러 당 수수료 금액 = 1달러 환율 * 수수료 비율
		double k35_totalcom = k35_usd * k35_comPerOne;				// 환전 총 수수료 = 총 환전 달러 금액 * 1달러당 수수료 금액
		
		// -----------------------------------------------------
		
		int k35_i_totalcom;											// 환전 총 수수료의 소수점이 존재했는데 버림을 하면 은행손해니까 확인 후 최종 총 수수료가 저장될 변수
		if(k35_totalcom != (double)((int)k35_totalcom)) {			// 소수점이 존재하면
			k35_i_totalcom = (int) k35_totalcom + 1;				//  환전 총 수수료 + 1원
		} else {													// 소수점이 존재하지 않으면
			k35_i_totalcom = (int) k35_totalcom;					// 	환전 총 수수료는 그대로
		}
		k35_remain = (int) (k35_myWon - k35_usd * k35_moneyEx - k35_i_totalcom);	// 환전 후 남은 잔액 = 내 현금 - 환전한 달러 * 1달러 당 환율 - 환전 총 수수료
		
 		System.out.printf("*****************************************************\n");
		System.out.printf("*                 수수료 없이 계산                  *\n");
		//System.out.printf("총 한화환전금액 : %d => 미화 %d달러, 잔돈 : %d\n", k35_myWon, k35_usd, k35_remain);
		System.out.printf("총 수수료 : %f원 => 미화 : %d달러, 달러 당 수수료 : %f원\n", k35_totalcom, k35_usd, k35_comPerOne);
		System.out.printf("총 한화환전금액 : %d원 => 미화 : %d달러, 수수료 징수 : %d원, 잔돈 : %d원\n", k35_myWon, k35_usd, k35_i_totalcom, k35_remain);
		System.out.printf("*****************************************************\n");
	}
	
}