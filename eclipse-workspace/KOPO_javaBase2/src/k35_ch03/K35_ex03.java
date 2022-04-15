package k35_ch03;
/**
 * 소프트웨어코딩_3강 - P8 실습 코드 실행해보기
 * 
 * #3 정수형 변수의 나눗셈은 정수범위에서 버림처리
 *  
 * @author KOPO35
 */

public class K35_ex03 {

	public static void main(String[] args) {
		int k35_ii;										// 정수형 변수 ii 선언
		
		k35_ii = 1000 / 3;								// 1000 / 3의 몫 연산값 333을 ii에 할당
		System.out.printf("#3-1 : %d\n", k35_ii);		// ii에 할당된 값 출력 - 버림 확인
		
		k35_ii = 1000 % 3;								// 1000 % 3의 나머지 연산값 1을 ii에 할당
		System.out.printf("#3-2 : %d\n", k35_ii);		// ii에 할당된 값 출력
		
		for (int k35_i = 0 ; k35_i < 20 ; k35_i++) {	// 0~19까지 i 반복
			System.out.printf("#3-3 : %d ", k35_i);		// 현재 루프의 i 값 출력
			
			if (((k35_i + 1) % 5) == 0) {				// 5개씩 한 줄에 출력 후 줄바꿈
				System.out.printf("\n");
			}
		}
	}

}
