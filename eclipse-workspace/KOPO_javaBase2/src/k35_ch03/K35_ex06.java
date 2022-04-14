package k35_ch03;

/**
 * 소프트웨어코딩_3강 - P14 실습 코드 실행해보기
 * 
 * # 간단한 방정식 - 소비자 세금 계산
 * 
 * @author KOPO35
 */

public class K35_ex06 {
	public static void main(String[] args) {
		int k35_price = 1234;
		int k35_percent = 10;
		double k35_rate = 0;
		int k35_sumPrice = 0;
		int k35_tax = 0;
		
		// 정확한 계산 - 1234 + 123.4 = 1357.4
		
	/* 세율 계산시 버림 적용 */ 
		
		k35_rate = (double)k35_percent / 100;
		k35_tax = (int)(k35_price * k35_rate); 			// 계산된 세금은 소수점 버림이 적용
														// 1234 * 0.1 = 123.4 => 123 
		// #1. 소비자 가격 = 세전 가격 + 세금
		k35_sumPrice = k35_price + k35_tax;				// 1234 + 123 = 1357
		System.out.printf("#1 : %d\n", k35_sumPrice);	
		
		// #2. 소비자 가격 = 세전 가격 + (세전 가격 * 세율)
		k35_sumPrice = k35_price + (int)(k35_price * k35_rate);	// 1234 + 123 = 1357
		System.out.printf("#2 : %d\n", k35_sumPrice);	
		
		// #3. 소비자 가격 = 세전 가격 * (1 + 세율)
		k35_sumPrice = (int)(k35_price * (1 + k35_rate));	// 1234 + 123 = 1357
		System.out.printf("#3 : %d\n", k35_sumPrice);
		
		// #4. 세전 가격 = 소비자가격 / ( 1 + 세율)
		k35_price = (int)(k35_sumPrice / (1 + k35_rate));	// 1357 / (1+0.1) = 1233.64, 근데 버림이므로 1233, 그렇게 되면 세금은 1357-1233=124
		System.out.printf("#4 : %d, %d\n", k35_price, k35_sumPrice - k35_price);
		
		
		System.out.println("-----------------------------------");
		
	/* 세율 계산시 올림 적용 */
		
		k35_price = 1234;
		if(k35_price * k35_rate % 1 != 0) {				// 소수점이 존재할 경우
			k35_tax = (int)(k35_price * k35_rate) + 1;	//	올림 처리해서 세금 계산
		} else {										// 소수점이 없는 경우
			k35_tax = (int)(k35_price * k35_rate);		//  그대로 세금 계산
		}
		
		// #1. 소비자 가격 = 세전 가격 + 세금
		k35_sumPrice = k35_price + k35_tax;				// 1234 + 124 = 1358
		System.out.printf("#1 : %d\n", k35_sumPrice);	
		
		// #4. 세전 가격 = 소비자가격 / ( 1 + 세율)
		k35_price = (int)(k35_sumPrice / (1 + k35_rate));	// 1358 / (1+0.1) = 1234.54, 근데 버림이므로 1234, 그렇게 되면 세금은 1358-1234=124
		System.out.printf("#4 : %d, %d\n", k35_price, k35_sumPrice - k35_price);
		
	}
}
