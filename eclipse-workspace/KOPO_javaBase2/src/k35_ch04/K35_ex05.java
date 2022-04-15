package k35_ch04;

/** 소프트웨어 코딩 심화 4강 - p18 실습
 * 
 *  비정형 비교1 (깔끔 코딩)
 * 
 * @author KOPO35
 *
 */
public class K35_ex05 {
	public static void main(String[] args) {
		
		for (int k35_i = 1 ; k35_i < 13 ; k35_i++) {	// 1월에서 12월까지 출력하기 위해 i변수를 1~12까지 반복
			System.out.printf("%2d월 => ", k35_i);		// 현재에 해당하는 i월 출력
			
			for (int k35_j = 1; k35_j < 32 ; k35_j++) {	// i의 해당 달의 모든 날짜를 출력하기 위해서 j를 1~31까지 반복, 한 달에 최대 31일까지 있기 때문
				System.out.printf("%d, ", k35_j);		// 일에 해당하는 j를 출력
				
				if (k35_i == 1  && k35_j==31) break;	 
				if (k35_i == 2  && k35_j==28) break;	// 2월은 28일까지 있으므로 j가 28이면 반복문 종료
				if (k35_i == 3  && k35_j==31) break;	// 1, 3, 5, 7, 8, 10, 12월은 31일까지 있으므로 j가 31이면 반복문 종료
				if (k35_i == 4  && k35_j==30) break;	// 4, 6, 9, 11월은 30일까지 있으므로 j가 30이면 반복문 종료
				if (k35_i == 5  && k35_j==31) break;
				if (k35_i == 6  && k35_j==30) break;
				if (k35_i == 7  && k35_j==31) break;
				if (k35_i == 8  && k35_j==31) break;
				if (k35_i == 9  && k35_j==30) break;
				if (k35_i == 10 && k35_j==31) break;
				if (k35_i == 11 && k35_j==30) break;
				if (k35_i == 12 && k35_j==31) break;
			}
			
			System.out.printf("\n");					// i달에 해당하는 모든 일을 출력했으면 줄바꿈 출력
		}
	}
}
