package k35_ch04;

/** 소프트웨어 코딩 심화 4강 - p20 실습
 * 
 *  case문 비교
 * 
 * @author KOPO35
 *
 */
public class K35_ex08 {
	public static void main(String[] args) {
		
		for (int k35_i = 1 ; k35_i < 13 ; k35_i++) {			// 1월에서 12월까지 출력하기 위해 i변수를 1~12까지 반복
			System.out.printf("%2d월 => ", k35_i);				// 현재에 해당하는 i월 출력
			
			LOOP : for (int k35_j = 1; k35_j < 32 ; k35_j++) {	// i의 해당 달의 모든 날짜를 출력하기 위해서 j를 1~31까지 반복, 한 달에 최대 31일까지 있기 때문
				System.out.printf("%d, ", k35_j);				// 일에 해당하는 j를 출력
				
				switch (k35_i) {
					case 4: case 6: case 7: case 9: case 11:	// 4, 6, 9, 11월은 30일까지 있으므로 j가 30이면
						if (k35_j == 30)						//   j가 30이면
							break LOOP;							//	   반복문 종료 - break 뒤에 LOOP를 사용하여 break할 반복문을 지정하였다.
																//     *break는 보통 현재 루프의 반복문을 종료 시킨다.
																//     *하지만 break 뒤에 LOOP를 명시하고 반복을 종료시킬 for문 앞에 LOOP : 를 붙여줌으로써
																//     *현재 루프의 반복문이 아닌 외부 반복문(다중 중접 반복문에서)의 종료를 명령할 수 있다.
						if (k35_j == 28)						// 2월은 28일까지 있으므로 j가 28이면
							break LOOP;							// 반복문 종료
				}
			}
			
			System.out.printf("\n");
		}
		
	}
}
