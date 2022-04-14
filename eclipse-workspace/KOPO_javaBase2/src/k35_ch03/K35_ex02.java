package k35_ch03;
/**
 * 소프트웨어코딩_3강 - P7 실습 코드 실행해보기
 * 
 * #2. 누적하기, 합 구하기
 * 
 * @author KOPO35
 */

public class K35_ex02 {

	public static void main(String[] args) {
		int k35_sum;									// 합, 누적 값이 저장될 변수 sum 선언
		
		k35_sum = 0;									// 초기값 0으로 할당
		
		for (int i = 1; i < 101 ; i++) {				// i를 1~100까지 반복
			k35_sum = k35_sum + i;						// i 값을 sum 변수에 누적
		}
		System.out.printf("#2 : %d\n", k35_sum);
		
		System.out.printf("#2-2 : %d\n", k35_sum / 100);
		
		
		int[] k35_v = {1, 2, 3, 4, 5};					// 배열 v 초기화
		int k35_vSum;									// 배열의 누적값이 저장될 변수 vSum 선언
		
		k35_vSum = 0;									// 초기값 0으로 할당
		
		for (int k35_i = 0 ; k35_i < 5 ; k35_i++) {		// 배열의 크기인 5만큼 반복
			k35_vSum = k35_vSum + k35_v[k35_i];			// v의 모든 요소 vSum 변수에 누적
		}
		
		System.out.printf("#2-3 : %d\n", k35_vSum);
	}

}
