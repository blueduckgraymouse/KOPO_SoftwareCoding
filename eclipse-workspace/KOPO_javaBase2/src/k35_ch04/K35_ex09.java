package k35_ch04;

/** 소프트웨어 코딩 심화 4강 - p21 실습
 * 
 *  Array 이용 비교
 * 
 * @author KOPO35
 *
 */
public class K35_ex09 {
	public static void main(String[] args) {
		
		int[] k35_iLMD = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};	// 1~12월까지 마지막 일의 날짜를 담은 배열을 초기화
		
		for (int k35_i = 1 ; k35_i < 13 ; k35_i++) {						// 1월에서 12월까지 출력하기 위해 i변수를 1~12까지 반복
			System.out.printf("%2d월 => ", k35_i);							// 현재에 해당하는 i월 출력
			
			for (int k35_j = 1 ; k35_j < 32 ; k35_j++) {					// i의 해당 달의 모든 날짜를 출력하기 위해서 j를 1~31까지 반복, 한 달에 최대 31일까지 있기 때문
				System.out.printf("%d", k35_j);								// 일에 해당하는 j를 출력
				
				if (k35_iLMD[k35_i - 1] == k35_j)							// 일에 해당하는 j를 1~31까지 출력하다가 해당하는 i월의 마지막 일 날짜가 되면
					break;													//   반복문 종료
				
				System.out.printf(".");										// 마지막 일짜에 해당하면 22번째 줄의 break에 의해 반복문이 종료되므로 현재 줄이 실행되지 않는다.
			}																//   즉 이전 실습과 다르게 마지막 일짜 뒤에 점(.)이 찍히지 않는다.
			
			System.out.printf("\n");
		}
	}
}
