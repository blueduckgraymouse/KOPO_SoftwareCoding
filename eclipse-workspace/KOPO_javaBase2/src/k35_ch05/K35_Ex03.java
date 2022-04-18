package k35_ch05;

/**	소프트웨어코딩 심화 5강 - 리포팅 연습(영수증 출력 등)
 * 
 *  달력 인쇄 - p6
 * 
 * @author KOPO
 *
 */
public class K35_Ex03 {
	
	public static void main(String[] args) {
		int k35_iWeekDay = 5;												// 해당 달의 1일에 해당하는 요일 변수로 1월 1일의 요일 값인 5로 초기화, 0은 일요일 ~ 6은 토요일
		int[] k35_iEnd = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};	// 1~12월의 마지막 일의 숫자값을 배열로 초기화
		
		for (int k35_iMon = 0; k35_iMon < 12 ; k35_iMon++) {				// 1~12월까지 반복 - 월 반복
			System.out.printf("\n\n         %d 월\n", k35_iMon + 1);			// 월을 나타내는 제목 출력
			System.out.printf("=====================\n");
			System.out.printf(" 일 월 화 수 목 금 토\n");						// 날짜를 나타내는 줄력
			
			for (int k35_i = 1; k35_i <= k35_iEnd[k35_iMon] ; k35_i++) {	// 특정 달(iMon)의 1~마지막 일까지 반복 - 일 반복 
				if (k35_iWeekDay != 0 && k35_i == 1) {						// 달의 1일의 요일 공백 작업 : 1일이 일요일이 아니면
					for (int k35_j = 0 ; k35_j < k35_iWeekDay ; k35_j++) {	//   해당 요일까지
						System.out.printf("   ");							//      공백을 출력
					}
				}
				System.out.printf("%3d", k35_i);							// 일 출력
				k35_iWeekDay++;												// 요일에 해당하는 변수 증가
				if (k35_iWeekDay == 7) {									// 토요일(6)이 지나면 일요일(0)으로 돌아가야하므로 7이 되면
					System.out.printf("\n");								//   줄 바꾸고
					k35_iWeekDay = 0;										//   요일에 해당하는 변수를 일요일(0)으로 초기화(일요일)
				}
			}
		}
		
	}
	
}
