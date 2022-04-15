package k35_ch04;

/** 소프트웨어 코딩 심화 4강 - p22 실습
 * 
 *  숫자 한글로 읽기
 * 
 * @author KOPO35
 *
 */
public class K35_ex10 {
	public static void main(String[] args) {
		
		String[] k35_units = {"영", "일", "이", "삼", "사", "오", "육", "칠", "팔", "구"};					// 0~9까지 숫자 한글을 배열 units으로 초기화
		
		for (int k35_i = 0 ; k35_i < 101 ; k35_i++) {												// 숫자 한글로 변환할 i를 0~100까지 반복
			if (k35_i >= 0 && k35_i < 10) 															// i가 한자리 숫자일 경우
				System.out.printf("일의 자리 : %s\n", k35_units[k35_i]);								//	 units의 한글 그대로 출력
			else if (k35_i >= 10 && k35_i < 100) {													// i가 두자리 숫자일 경우
				int k35_i10, k35_i0;																//	 i를 10으로 나눈 몫을 변수 k35_i10, 나머지를 변수 k35_i0에 저장
				k35_i10 = k35_i / 10;
				k35_i0 = k35_i % 10;																
				if (k35_i0 == 0)																	//   k35_i0가 0일 경우
					System.out.printf("십의 자리 : %s십\n", k35_units[k35_i10]);						//	   일의 자리는 0이므로 십의 자리만 출력
				else																				//   k35_i0가 0이 아닐 경우
					System.out.printf("십의 자리 : %s십%s\n", k35_units[k35_i10], k35_units[k35_i0]);	//     일의 자리가 존재하므로 십의 자리와 일의 자리를 같이 출력
			}
		}
	}
}
