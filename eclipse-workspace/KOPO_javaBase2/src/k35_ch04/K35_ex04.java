package k35_ch04;

/** 소프트웨어 코딩 심화 4강 - p17 실습
 * 
 *  범위를 주어 비교(찾기)
 * 
 * @author KOPO35
 *
 */
public class K35_ex04 {
	public static void main(String[] args) {
		
		int k35_iVal;										// 5의 배수가 저장될 변수 선언
		
		for (int k35_i = 0 ; k35_i < 300 ; k35_i++) {		// i를 0 ~ 299까지 반복
			k35_iVal = 5 * k35_i;							// iVal변수에 5 * i를 통해서 반복의 루프가 돌 때마다 증가하는 5의 배수가 되도록 설정
			
			if (k35_iVal >= 0 && k35_iVal < 10)				// 5의 배수 값이 한자리 숫자이면
				System.out.printf("일 %d\n",k35_iVal);		// 	 앞에 "일"을 붙여서 출력
			else if (k35_iVal >= 10 && k35_iVal < 100)		// 5의 배수 값이 두자리 숫자이면
				System.out.printf("십 %d\n",k35_iVal);		// 	 앞에 "십"을 붙여서 출력
			else if (k35_iVal >= 100 && k35_iVal < 1000)	// 5의 배수 값이 세자리 숫자이면
				System.out.printf("백 %d\n",k35_iVal);		// 	 앞에 "백"을 붙여서 출력
		}
	}
}
