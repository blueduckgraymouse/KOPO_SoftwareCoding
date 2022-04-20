package k35_ch06;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 소프트웨어코딩 심화 6강 - 객체지향 조금만 알기
 * 
 * 성적집계표 출력 (1페이지) - p39
 * 
 * @author KOPO
 */
public class K35_ex08_p40__refactoring {

	public static void main(String[] args) {
		int k35_iPerson = 200;													// 점수 통계 할 총 인원 설정
		
		K35_inputData_ex08__refactoring k35_inData = new K35_inputData_ex08__refactoring(k35_iPerson);	// 점수를 저장하고 통계낸 값을 저장할 K35_inputData_ex06라는 클래스의 객체 생성
		
		// 모든 학생의 모든 점수 저장
		for (int k35_i = 0 ; k35_i < k35_iPerson ; k35_i++) {					// 설정한 총 인원 만큼 반복
			String k35_name = String.format("홍길%02d", k35_i + 1);				//  홍길00이라는 형태의 문자열을 생성하여 name 변수에 저장
			int k35_kor = (int)(Math.random() * 100);							//  국어 과목의 랜덤 점수를 만들어 kor라는 변수에 저장
			int k35_eng = (int)(Math.random() * 100);							//  영어 과목의 랜덤 점수를 만들어 eng라는 변수에 저장
			int k35_mat = (int)(Math.random() * 100);							//  수학 과목의 랜덤 점수를 만들어 mat라는 변수에 저장
			k35_inData.k35_SetData(k35_i, k35_name, k35_kor, k35_eng, k35_mat);	//  현재 학생의 모든 랜덤 점수를 k35_inData의 각 점수 배열의 해당 인덱스 요소에 저장
		}
		
		// 누적 데이터로 과목별 평균 및 모든 성적 합게 및 평균 집계
		
		k35_print_all(k35_inData);
		
	}
	
	private static void k35_print_all(K35_inputData_ex08__refactoring k35_inData) {
		// 성적집계표 출력
		int k35_index = 0;
		int k35_count_page = 1;
		while (k35_index < k35_inData.k35_name.length) {
			int k35_index_start = k35_index;
			System.out.printf("%25s", "성적집계표\n");
			k35_print_pageNum_date(k35_count_page);
			System.out.println("==============================================================");
			k35_print_header();
			System.out.println("==============================================================");
			int k35_index_end = k35_print_score(k35_index, k35_inData);
			System.out.println("==============================================================");
			System.out.println("현재페이지");
			k35_print_total(k35_inData, k35_index_start, k35_index_end);
			System.out.println("==============================================================");
			System.out.println("누적페이지");
			k35_print_total(k35_inData, 0, k35_index_end);
			k35_index = k35_index_end;
			k35_count_page++;
		}
	}

	// 헤더('번호', '이름' 등등)를 출력하는 메서드
	private static void k35_print_header() {
		String[] k35_subjects = {"국어", "영어", "수학"};
		System.out.printf("%-6s", "번호");
		System.out.printf("%-7s", "이름");
		for (String k35_subject : k35_subjects) {		
			System.out.printf("%-4s", k35_subject);
		}
		System.out.printf("%-4s", "총점");
		System.out.printf("%5s\n", "평균");
	}
	
	// 출력일자는 출력하는 메서드
	private static void k35_print_pageNum_date(int k35_count_page) {
		Calendar k35_calendar = Calendar.getInstance();										// 현재 날짜 정보를 갖는 객체 생성 및 초기화
		SimpleDateFormat k35_sdf = new SimpleDateFormat("YYYY.MM.dd HH:mm:ss");				// 출력형태에 맞는 date 포맷 객체 생성
		
		System.out.printf("%-32s%s", "PAGE : " + k35_count_page, "출력일자 : " + k35_sdf.format(k35_calendar.getTime()) + "\n");		// 현재 일자를 출력일자로 출력
	}
	
	// 학생들의 데이터를 출력하는 메서드
	private static int k35_print_score(int k35_index, K35_inputData_ex08__refactoring k35_inData) {
		int k35_i;
		for (k35_i = k35_index; k35_i < k35_inData.k35_name.length && k35_i < k35_index + 30 ; k35_i++) {
			System.out.printf("%03d%5s", k35_i + 1, "");									// 번호 출력
			System.out.printf("%-7s", k35_inData.k35_name[k35_i]);							// 이름 출력
			System.out.printf("%4d%2s", k35_inData.k35_kor[k35_i], "");						// 국어 성적 출력
			System.out.printf("%4d%2s", k35_inData.k35_eng[k35_i], "");						// 영어 성적 출력
			System.out.printf("%4d%2s", k35_inData.k35_mat[k35_i], "");						// 수학 성적 출력
			System.out.printf("%4d%2s", k35_inData.k35_sum[k35_i], "");						// 3과목의 총점 출력
			System.out.printf("%7.2f\n", k35_inData.k35_ave[k35_i], "");					// 3과목의 평균 출력
		}
		return k35_i;
	}
	
	// 현재 페이지의 학생들의 데이터를 집계한 정보(합계, 평균)를 출력하는 메서드
	private static void k35_print_total(K35_inputData_ex08__refactoring k35_inData, int k35_index_start, int k35_index_end) {
		k35_inData.k35_total(k35_index_start, k35_index_end);
		
		System.out.printf("%-13s", "합계");		
		System.out.printf("%6d", k35_inData.k35_sum_kor);							// 모든 학생의 국어 성적 누적 합계 출력
		System.out.printf("%6d", k35_inData.k35_sum_eng);							// 모든 학생의 영어 성적 누적 합계 출력
		System.out.printf("%6d", k35_inData.k35_sum_mat);							// 모든 학생의 수학 성적 누적 합계 출력
		System.out.printf("%6d", k35_inData.k35_sum_sum);							// 모든 학생의 3과목의 총점 누적 합계 출력
		System.out.printf("%9d\n", (int)k35_inData.k35_sum_ave);						// 모든 학생의 3과목의 평균 누적 합계 출력
		System.out.printf("%-13s", "평균");
		System.out.printf("%6d", (int)k35_inData.k35_ave_kor);							// 모든 학생의 국어 성적 누적 합계의 평균 출력
		System.out.printf("%6d", (int)k35_inData.k35_ave_eng);							// 모든 학생의 영어 성적 누적 합계의 평균 출력
		System.out.printf("%6d", (int)k35_inData.k35_ave_mat);							// 모든 학생의 3과목의 총점 누적 합계의 평균 출력
		System.out.printf("%6d", (int)k35_inData.k35_ave_sum);							// 모든 학생의 3과목의 평균 누적 합계의 평균 출력
		System.out.printf("%9d\n", (int)k35_inData.k35_ave_ave);
	}
}

