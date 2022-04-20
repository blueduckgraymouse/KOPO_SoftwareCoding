package k35_ch06;

public class K35_inputData_ex08 {
	// 학생의 점수를 저장되는 변수
	public String[] k35_name;				// 학생의 이름이 저장될 배열 변수를 선언
	public int[] k35_kor;					// 학생의 국어 점수이 저장될 배열 변수를 선언
	public int[] k35_eng;					// 학생의 영어 점수이 저장될 배열 변수를 선언
	public int[] k35_mat;					// 학생의 수학 점수이 저장될 배열 변수를 선언
	public int[] k35_sum;					// 학생의 점수 총점이 저장될 배열 변수를 선언
	public double[] k35_ave;				// 학생의 점수 평균이 저장될 배열 변수를 선언
	
	// 집계에 사용되는 변수
	public int k35_sum_kor = 0;				// 각 학생의 국어 점수를 누적하는 변수 선언
	public int k35_sum_eng = 0;				// 각 학생의 영어 점수를 누적하는 변수 선언
	public int k35_sum_mat = 0;				// 각 학생의 수학 점수를 누적하는 변수 선언
	public int k35_sum_sum = 0;				// 각 학생의 총점을 누적하는 변수 선언
	public double k35_sum_ave = 0;			// 각 학생의 평균을 누적하는 변수 선언
	
	public double k35_ave_kor = 0;			// k35_sum_kor 누적값의 평균이 저장될 변수 선언
	public double k35_ave_eng = 0;			// k35_sum_eng 누적값의 평균이 저장될 변수 선언
	public double k35_ave_mat = 0;			// k35_sum_mat 누적값의 평균이 저장될 변수 선언
	public double k35_ave_sum = 0;			// k35_sum_sum 누적값의 평균이 저장될 변수 선언
	public double k35_ave_ave = 0;			// k35_sum_ave 누적값의 평균이 저장될 변수 선언
	
	// 생성자
	public K35_inputData_ex08(int k35_iPerson) {	// 파라미터로 몇 명의 학생에 대한 정보를 담을지 받아와
		k35_name = new String[k35_iPerson];			//   그 크기의 이름 배열을 각각 생성
		k35_kor = new int[k35_iPerson];				//   그 크기의 국어 점수 배열을 각각 생성
		k35_eng = new int[k35_iPerson];				//   그 크기의 영어 점수 배열을 각각 생성
		k35_mat = new int[k35_iPerson];				//   그 크기의 수학 점수 배열을 각각 생성
		k35_sum = new int[k35_iPerson];				//   그 크기의 점수 총점 배열을 각각 생성
		k35_ave = new double[k35_iPerson];			//   그 크기의 점수 평균 배열을 각각 생성
	}

	// 한 학생에 대한 점수를 저장하고 합계와 평균을 저장하는 메서드
	public void k35_SetData(int k35_i, String k35_name, int k35_kor, int k35_eng, int k35_mat) {
		// 점수 저장 및 총점, 평균 계산 후 저장
		this.k35_name[k35_i] = k35_name;				// 파라미터로 메서드에 전달된 학생 이름을 객체의 필드인 이름 배열 속 특정 인덱스 요소에 저장
		this.k35_kor[k35_i] = k35_kor;					// 파라미터로 메서드에 전달된 국어 점수를 객체의 필드인 국어 점수 배열 속 특정 인덱스 요소에 저장
		this.k35_eng[k35_i] = k35_eng;					// 파라미터로 메서드에 전달된 영어 점수를 객체의 필드인 영어 점수 배열 속 특정 인덱스 요소에 저장
		this.k35_mat[k35_i] = k35_mat;					// 파라미터로 메서드에 전달된 수학 점수를 객체의 필드인 수학 점수 배열 속 특정 인덱스 요소에 저장
		k35_sum[k35_i] = k35_kor + k35_eng + k35_mat;	// 3과목의 점수를 모두 더한 총점을 객체의 필드인 점수 총점 배열 속 특정 인덱스 요소에 저장
		k35_ave[k35_i] = k35_sum[k35_i] / (double) 3;	// 총점을 3으로 나눈 평균을 객체의 필드인 점수 평균 배열 속 특정 인덱스 요소에 저장
	}
	
	/**
	 * 현재 페이지에 속한 첫번호부터 끝번호까지의 학생데이터 집계하는 메서드
	 * @param k35_index_start	: 집계하고자하는 학생의 첫번호
	 * @param k35_index_end		: 집계하고자하는 학생의 끝번호
	 */
	public void k35_total(int k35_index_start, int k35_index_end) {
		// 이전에 집계 후 남아있는 데이터 초기화
		k35_sum_kor = 0;
		k35_sum_eng = 0;
		k35_sum_mat = 0;
		k35_sum_sum = 0;
		k35_sum_ave = 0;
		k35_ave_kor = 0;	
		k35_ave_eng = 0;	
		k35_ave_mat = 0;
		k35_ave_sum = 0;
		k35_ave_ave = 0;
		
		int k35_size =  k35_index_end - k35_index_start;						// 총 학생의 수
		
		for (int k35_i = k35_index_start ; k35_i < k35_index_end ; k35_i++) {	// 현재 집계하고자하는 페이지에 속한 학생들의 데이터 집계
			k35_sum_kor += k35_kor[k35_i];										// 각 학생의 국어 점수를 누적
			k35_sum_eng += k35_eng[k35_i];										// 각 학생의 영어 점수를 누적
			k35_sum_mat += k35_mat[k35_i];										// 각 학생의 수학 점수를 누적
			k35_sum_sum += k35_sum[k35_i];										// 각 학생의 총점을 누적
			k35_sum_ave += k35_ave[k35_i];										// 각 학생의 평균을 누적
		}
		
		k35_ave_kor = k35_sum_kor / (double) k35_size;							// k35_sum_kor 누적값의 평균
		k35_ave_eng = k35_sum_eng / (double) k35_size;							// k35_sum_eng 누적값의 평균
		k35_ave_mat = k35_sum_mat / (double) k35_size;							// k35_sum_mat 누적값의 평균
		k35_ave_sum = k35_sum_sum / (double) k35_size;							// k35_sum_sum 누적값의 평균
		k35_ave_ave = k35_sum_ave / (double) k35_size;							// k35_sum_ave 누적값의 평균
	}

}
