package k35_ch03;
/**
 * 소프트웨어코딩_3강 - P6 실습 코드 실행해보기
 * 
 * #1. 연산 후 대입, 당연히 사칙연산의 순서
 * 
 * @author KOPO35
 */

public class K35_ex01 {

	public static void main(String[] args) {
		int k35_ii;									// 정수형 변수 ii 선언
		k35_ii = 1 + 2;								// 1 + 2의 연산결과를 ii에 할당
		System.out.printf("# 1-1 : %d\n", k35_ii);
		
		k35_ii = 1+ 2 * 3;							// 1 + 2 * 3의 연산결과를 ii에 할당
		System.out.printf("# 1-2 : %d\n", k35_ii);
	}
	
}
