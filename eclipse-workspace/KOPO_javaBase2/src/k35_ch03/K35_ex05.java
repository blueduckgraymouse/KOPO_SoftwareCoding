package k35_ch03;
/**
 * 소프트웨어코딩_3강 - P10 실습 코드 실행해보기
 * 
 * #5 소수점 이하에서는 어떻게 할까?
 * 
 * @author KOPO35
 */

public class K35_ex05 {

	public static void main(String[] args) {
		int k35_MyVal = 14 / 5;							// 14/5의 연산결과 2가 myVal에 저장된다
		
		System.out.printf("#5-1 : %d\n", k35_MyVal);
		
		double k35_MyValF;								// 실수형 변수 k35_MyValF 선언
		
		k35_MyValF = 14 / 5;							// 14/5의 연산결과 2가 저장된다.
		System.out.printf("#5-2 : %f\n", k35_MyValF);

		k35_MyValF = 14.0 / 5;							// 14.0/5의 연산은 실수범위 연산이므로 2.8이 저장된다.
		System.out.printf("#5-2 : %f\n", k35_MyValF);
		
		k35_MyValF = 14.0 / 5 + 0.5;					// 14.0/5의 연산 결과 2.8 + 0.5의 결과 3.3이 저장된다.
		System.out.printf("#5-2 : %f\n", k35_MyValF);
		
		k35_MyValF = (int)(14.0 / 5 + 0.5);				// 14.0/5+0.5의 연산결과 3.3을 정수형으로 변환하므로 3이 저장된다.
		System.out.printf("#5-2 : %f\n", k35_MyValF);
	}
	
}
