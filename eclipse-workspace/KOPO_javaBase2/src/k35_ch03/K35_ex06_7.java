package k35_ch03;

/**
 * 소프트웨어코딩_3강 - P15 실습 코드 실행해보기
 * 
 * # 소비자 세금 계산
 * 
 * @author KOPO35
 */

public class K35_ex06_7 {
	public static void main(String[] args) {
		
		int k35_price = 1234;											// 물건 가격값 1234를 price변수에 초기화
		double k35_tax_rate = 0.1;										// 물건 세율 0.1을 tax_rate 변수에 초기화
		
		int k35_netprice = k35_netprice(k35_price, k35_tax_rate);		// 세율 계산을 하는 netprice함수에 파라미터로 물건가격과 세율 전달하여 반환값을 netprice 변수에 저장
		int k35_tax = k35_price - k35_netprice;							// 세금 = 소비자 가격 - 세전 가격 계산하여 tax변수에 저장
		
		System.out.printf("***********************************\n");
		System.out.printf("* 소비자가, 세전 가격, 세금 계산  *\n");
		System.out.printf(" 소비자 가격 :%d, 세전 가격 : %d, 세금 : %d\n", k35_price, k35_netprice, k35_tax);
		System.out.printf("***********************************\n");
	}

	/**
	 * 소비자가격을 이용해서 세전금액 계산한다.
	 * @param k35_price		: 소비자가격
	 * @param k35_tax_rate	: 세율
	 * @return				: 계산된 세전 가격
	 */
	private static int k35_netprice(int k35_price, double k35_tax_rate) {
		return (int)(k35_price / (1 + k35_tax_rate));						// 세전 가격 = 소비자가격 * (  1 + 세율) 식을 통해서 세전 가격 계산 후 리턴
	}
}
