package k35_ch04;

/** 소프트웨어 코딩 심화 4강 - p19 실습
 * 
 *  비정형 비교2
 * 
 * @author KOPO35
 *
 */
public class K35_ex06 {
	public static void main(String[] args) {
		
		for (int k35_i = 1 ; k35_i < 13 ; k35_i++) {	// 1월에서 12월까지 출력하기 위해 i변수를 1~12까지 반복
			System.out.printf("%2d월 => ", k35_i);		// 현재에 해당하는 i월 출력
			
			for (int k35_j = 1; k35_j < 32 ; k35_j++) {	// i의 해당 달의 모든 날짜를 출력하기 위해서 j를 1~31까지 반복, 한 달에 최대 31일까지 있기 때문
				System.out.printf("%d, ", k35_j);		// 일에 해당하는 j를 출력
				
				if (k35_i == 4 || k35_i == 6 || k35_i == 7 || k35_i == 9 || k35_i == 11) {
					if (k35_j == 30)					// 4, 6, 9, 11월은 30일까지 있으므로 j가 30이면 반복문 종료
						break;
				}
				
				if (k35_i == 2) {		// 2월은 28일까지 있으므로 j가 28이면 반복문 종료
					if (k35_j == 28)
						break;
				}
			}
			
			System.out.printf("\n");
		}
	}
}
