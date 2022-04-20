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
public class K35_ex07_p39 {

	public static void main(String[] args) {
		int k35_iPerson = 30;													// 점수 통계 할 총 인원 설정
		
		K35_inputData_ex07 k35_inData = new K35_inputData_ex07(k35_iPerson);	// 점수를 저장하고 통계낸 값을 저장할 K35_inputData_ex06라는 클래스의 객체 생성
		
		// 모든 학생의 모든 점수 저장
		for (int k35_i = 0 ; k35_i < k35_iPerson ; k35_i++) {					// 설정한 총 인원 만큼 반복
			String k35_name = String.format("홍길%02d", k35_i + 1);				//  홍길00이라는 형태의 문자열을 생성하여 name 변수에 저장
			int k35_kor = (int)(Math.random() * 100);							//  국어 과목의 랜덤 점수를 만들어 kor라는 변수에 저장
			int k35_eng = (int)(Math.random() * 100);							//  영어 과목의 랜덤 점수를 만들어 eng라는 변수에 저장
			int k35_mat = (int)(Math.random() * 100);							//  수학 과목의 랜덤 점수를 만들어 mat라는 변수에 저장
			k35_inData.k35_SetData(k35_i, k35_name, k35_kor, k35_eng, k35_mat);	//  현재 학생의 모든 랜덤 점수를 k35_inData의 각 점수 배열의 해당 인덱스 요소에 저장
		}
		
		// 누적 데이터로 과목별 평균 및 모든 성적 합게 및 평균 집계
		k35_inData.k35_total();													// 누적 데이터로 과목별 평균 및 모든 성적 합게 및 평균 집계하는 메서드 호출
		
		// 성적집계표 출력
		System.out.printf("%25s", "성적집계표\n\n");
		k35_print_date();
		System.out.println("===============================================================");
		k35_print_header();
		System.out.println("===============================================================");
		k35_print_score(k35_inData);
		System.out.println("===============================================================");
		k35_print_total(k35_inData);
	}
	
	// 헤더('번호', '이름' 등등)를 출력하는 메서드
	private static void k35_print_header() {
		String[] k35_subjects = {"국어", "영어", "수학"};
		System.out.printf("%-4s", "번호");
		System.out.printf("%-7s", "이름");
		for (String k35_subject : k35_subjects) {		
			System.out.printf("%-4s", k35_subject);
		}
		System.out.printf("%-4s", "총점");
		System.out.printf("%5s\n", "평균");
	}
	
	// 출력일자는 출력하는 메서드
	private static void k35_print_date() {
		Calendar k35_calendar = Calendar.getInstance();										// 현재 날짜 정보를 갖는 객체 생성 및 초기화
		SimpleDateFormat k35_sdf = new SimpleDateFormat("YYYY.MM.dd HH:mm:ss");				// 출력형태에 맞는 date 포맷 객체 생성
		System.out.printf("%-32s%s", "", "출력일자 : " + k35_sdf.format(k35_calendar.getTime()) + "\n");		// 현재 일자를 출력일자로 출력
	}
	
	// 학생들의 데이터를 출력하는 메서드
	private static void k35_print_score(K35_inputData_ex07 k35_inData) {
		for (int k35_i = 0; k35_i < k35_inData.k35_name.length ; k35_i++) {
			System.out.printf("%03d%3s", k35_i + 1, "");										// 번호 출력
			System.out.printf("%-7s", k35_inData.k35_name[k35_i]);							// 이름 출력
			System.out.printf("%4d%2s", k35_inData.k35_kor[k35_i], "");						// 국어 성적 출력
			System.out.printf("%4d%2s", k35_inData.k35_eng[k35_i], "");						// 영어 성적 출력
			System.out.printf("%4d%2s", k35_inData.k35_mat[k35_i], "");						// 수학 성적 출력
			System.out.printf("%4d%2s", k35_inData.k35_sum[k35_i], "");						// 3과목의 총점 출력
			System.out.printf("%7.2f\n", k35_inData.k35_ave[k35_i], "");					// 3과목의 평균 출력
		}
	}
	
	// 학새들의 데이터를 집계한 정보(합계, 평균)를 출력하는 메서드
	private static void k35_print_total(K35_inputData_ex07 k35_inData) {
		System.out.printf("%-12s", "합계");					
		System.out.printf("%4d%2s", k35_inData.k35_sum_kor, "");							// 모든 학생의 국어 성적 누적 합계 출력
		System.out.printf("%4d%2s", k35_inData.k35_sum_eng, "");							// 모든 학생의 영어 성적 누적 합계 출력
		System.out.printf("%4d%2s", k35_inData.k35_sum_mat, "");							// 모든 학생의 수학 성적 누적 합계 출력
		System.out.printf("%4d%2s", k35_inData.k35_sum_sum, "");							// 모든 학생의 3과목의 총점 누적 합계 출력
		System.out.printf("%8d\n", (int)k35_inData.k35_sum_ave, "");						// 모든 학생의 3과목의 평균 누적 합계 출력
		System.out.printf("%-12s", "평균");
		System.out.printf("%4.0f%2s", k35_inData.k35_ave_kor, "");							// 모든 학생의 국어 성적 누적 합계의 평균 출력
		System.out.printf("%4.0f%2s", k35_inData.k35_ave_eng, "");							// 모든 학생의 영어 성적 누적 합계의 평균 출력
		System.out.printf("%4.0f%2s", k35_inData.k35_ave_mat, "");							// 모든 학생의 3과목의 총점 누적 합계의 평균 출력
		System.out.printf("%4.0f%2s", k35_inData.k35_ave_sum, "");							// 모든 학생의 3과목의 평균 누적 합계의 평균 출력
		System.out.printf("%8d\n", (int)k35_inData.k35_ave_ave, "");
	}

}

