package k35_ch03;

/**
 * 소프트웨어코딩_3강 - p19 실습 코드 실행해보기
 * 
 * # 수수료 없이 환전 계산(1)
 * 
 * @author KOPO35
 */

public class K35_ex09 {
	
	public static void main(String[] args) {
		int k35_myWon = 1000000;				// 환전할 내 돈
		double k35_moneyEx = 1238.21;			// 미화 환율
		double k35_commision = 0.003;			// 환전 수수료 비율
		
		int k35_usd = (int) (k35_myWon / k35_moneyEx);				// 환전되는 달러 지폐 = 내 돈 / 미화 환율 의 몫
		int k35_remain = (int) (k35_myWon - k35_usd * k35_moneyEx);	// 환전 후 남는 잔액 = 내 돈 / 미화 환율 의 나머지

		// -----------------------------------------------------
		
		double k35_comPerOne = k35_moneyEx * k35_commision;			// 1달러 당 수수료 금액 = 1달러 환율 * 수수료 비율
		double k35_totalcom = k35_usd * k35_comPerOne;				// 환전 총 수수료 = 총 환전 달러 금액 * 1달러당 수수료 금액
		
		System.out.printf("*****************************************************\n");
		System.out.printf("*                 수수료 없이 계산                  *\n");
		//System.out.printf("총 한화환전금액 : %d => 미화 %d달러, 잔돈 : %d원\n", k35_myWon, k35_usd, k35_remain);
		System.out.printf("총 수수료 : %f원 => 미화 : %d달러, 달러 당 수수료 : %f원\n", k35_totalcom, k35_usd, k35_comPerOne);
		System.out.printf("*****************************************************\n");
	}
	
}