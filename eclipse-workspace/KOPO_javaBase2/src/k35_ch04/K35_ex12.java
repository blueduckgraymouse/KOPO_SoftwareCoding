package k35_ch04;

/** 소프트웨어 코딩 심화 4강 - p24 실습
 * 
 *  띄어쓰기
 * 
 * @author KOPO35
 *
 */
public class K35_ex12 {
	public static void main(String[] args) {
		for (int k35_i = 0 ; k35_i < 10 ; k35_i++) {		// 변수 i를 0에서 10까지 반복 - 줄 반복
			for (int k35_j = 0 ; k35_j < k35_i ; k35_j++)	//   변수 j를 0에서 i까지 반복	- 칸 반복
				System.out.printf(" ");						// 	   i크기 만큼 공백 출력
			System.out.printf("%d\n", k35_i);				//   줄 번호에 해당한는 숫자 i 출력 후 줄바꿈
		}
	}
}
