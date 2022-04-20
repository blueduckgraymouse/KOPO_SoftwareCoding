package k35_ch06;

/**
 * 소프트웨어코딩 심화 6강 - 객체지향 조금만 알기
 * 
 * 메소드 오버로딩 - p11
 * 
 * @author KOPO
 */
public class K35_ex03_p11 {

	public static void main(String[] args) {
		// 출력할 과목명과 점수 변수로 초기화
		String[] k35_subject = {"국어", "영어", "수학", "과학", "사회"};					// 5과목에 대한 과목명을 배열로 초기화
		int[] k35_score = {100, 95, 87, 93, 82, 69};								// 5과목에 대한 점수를 배열로 초기화
		
		// 3월 성적표 출력
		System.out.println("3월 성적표");
		System.out.println("==================================================");
		k35_output_header(k35_subject, 3);											// 앞에서 3과목에 대해 과목명과 총점, 평균 헤더 출력
		System.out.println("==================================================");
		System.out.printf("%-6s","폴리융");											// 학생명 출력
		for (int i = 0 ; i < 3 ; i++)												// i를 3까지 반복
			System.out.printf("%4d%2s", k35_score[i], "");							//   성적 출력
		System.out.printf("%4d", k35_sum(k35_score[0], k35_score[1], k35_score[2]));		// 합계 출력
		System.out.printf("%7.2f\n\n", k35_avg(k35_score[0], k35_score[1], k35_score[2]));	// 평균
		
		// 4월 성적표 출력
		System.out.println("4월 성적표");
		System.out.println("==================================================");
		k35_output_header(k35_subject, 4);											// 앞에서 4과목에 대해 과목명과 총점, 평균 헤더 출력
		System.out.println("==================================================");
		System.out.printf("%-6s","폴리융");											// 학생명 출력										
		for (int i = 0 ; i < 4 ; i++) 												// i를 4까지 반복
			System.out.printf("%4d%2s", k35_score[i], "");							//   성적 출력
		System.out.printf("%4d", k35_sum(k35_score[0], k35_score[1], k35_score[2], k35_score[3]));		// 합계 출력		
		System.out.printf("%7.2f\n\n", k35_avg(k35_score[0], k35_score[1], k35_score[2], k35_score[3]));// 평균
		
		// 5월 성적표 출력
		System.out.println("4월 성적표");
		System.out.println("==================================================");
		k35_output_header(k35_subject, 5);											// 5과목에 대해 과목명과 총점, 평균 헤더 출력
		System.out.println("==================================================");
		System.out.printf("%-6s","폴리융");											// 학생명 출력		
		for (int i = 0 ; i < 5 ; i++) 												// i를 4까지 반복
			System.out.printf("%4d%2s", k35_score[i], "");							//   성적 출력
		System.out.printf("%4d", k35_sum(k35_score[0], k35_score[1], k35_score[2], k35_score[3],  k35_score[4]));	// 합계 출력	
		System.out.printf("%7.2f\n\n", k35_avg(k35_score[0], k35_score[1], k35_score[2], k35_score[3],  k35_score[4]));	// 평균
	}

	/**3과목의 평균을 구하는 메서드
	 * @param k35_score1, k35_score2, k35_score3 : 과목 점수
	 * @return : 3과목의 평균
	 */
	private static double k35_avg(int k35_score1, int k35_score2, int k35_score3) {
		return (k35_score1 + k35_score2 + k35_score3) / (double) 3;
	}

	/**4과목의 평균을 구하는 메서드
	 * @param k35_score1, k35_score2, k35_score3, k35_score4 : 과목 점수
	 * @return : 4과목의 평균
	 */
	private static double k35_avg(int k35_score1, int k35_score2, int k35_score3, int k35_score4) {
		return(k35_score1 + k35_score2 + k35_score3 + k35_score4) / (double) 4;
	}

	/**5과목의 평균을 구하는 메서드
	 * @param k35_score1, k35_score2, k35_score3, k35_score4, k35_score5 : 과목 점수
	 * @return : 5과목의 평균
	 */
	private static double k35_avg(int k35_score1, int k35_score2, int k35_score3, int k35_score4, int k35_score5) {
		return(k35_score1 + k35_score2 + k35_score3 + k35_score4 + k35_score5) / (double) 5;
	}

	/**5과목의 합계를 구하는 메서드
	 * @param k35_score1, k35_score2, k35_score3, k35_score4, k35_score5 : 과목 점수
	 * @return : 5과목의 합계
	 */
	private static int k35_sum(int k35_score1, int k35_score2, int k35_score3, int k35_score4, int k35_score5) {
		
		return k35_score1 + k35_score2 + k35_score3 + k35_score4 + k35_score5;
	}

	/**5과목의 합계를 구하는 메서드
	 * @param k35_score1, k35_score2, k35_score3, k35_score4, k35_score5 : 과목 점수
	 * @return : 5과목의 합계
	 */
	private static int k35_sum(int k35_score1, int k35_score2, int k35_score3, int k35_score4) {
		return k35_score1 + k35_score2 + k35_score3 + k35_score4;
	}

	/**5과목의 합계를 구하는 메서드
	 * @param k35_score1, k35_score2, k35_score3, k35_score4, k35_score5 : 과목 점수
	 * @return : 5과목의 합게
	 */
	private static int k35_sum(int k35_score1, int k35_score2, int k35_score3) {
		return k35_score1 + k35_score2 + k35_score3;
	}
	
	/**이름, 과목명, 총점, 평균 헤더를 출력하는 메서드
	 * @param k35_subject : 과목명 배열
	 * @param k35_subject_nums : 앞에서 부터 출력할 과목의 개수
	 */
	private static void k35_output_header(String[] k35_subject, int k35_subject_nums) {
		System.out.printf("%-7s", "이름");
		for (int i = 0 ; i < k35_subject_nums ; i++) {		// k35_subject 배열 앞에서 부터 k35_subject_nums 수 만큼 과목명 출력
			System.out.printf("%-4s", k35_subject[i] );
		}
		System.out.printf("%-4s", "총점");
		System.out.printf("%3s\n", "평균");
	}

}
