package k35_ch06;

/**
 * 소프트웨어코딩 심화 6강 - 객체지향 조금만 알기
 * 
 * 클래스를 만들기 - p38
 * 
 * @author KOPO
 */
public class K35_ex06_p38 {

	public static void main(String[] args) {
		int k35_iPerson = 10;													// 점수 통계 할 총 인원 설정
		
		K35_inputData_ex06 k35_inData = new K35_inputData_ex06(k35_iPerson);	// 점수를 저장하고 통계낸 값을 저장할 K35_inputData_ex06라는 클래스의 객체 생성
		
		for (int k35_i = 0 ; k35_i < k35_iPerson ; k35_i++) {					// 설정한 총 인원 만큼 반복
			String k35_name = String.format("홍길%02d", k35_i);					//  홍길00이라는 형태의 문자열을 생성하여 name 변수에 저장
			int k35_kor = (int)(Math.random() * 100);							//  국어 과목의 랜덤 점수를 만들어 kor라는 변수에 저장
			int k35_eng = (int)(Math.random() * 100);							//  영어 과목의 랜덤 점수를 만들어 eng라는 변수에 저장
			int k35_mat = (int)(Math.random() * 100);							//  수학 과목의 랜덤 점수를 만들어 mat라는 변수에 저장
			k35_inData.k35_SetData(k35_i, k35_name, k35_kor, k35_eng, k35_mat);	//  현재 학생의 모든 랜덤 점수를 k35_inData의 각 점수 배열의 해당 인덱스 요소에 저장
		}
		
		for (int k35_i = 0 ; k35_i < k35_iPerson ; k35_i++) {					//  설정한 총 인원 만큼 반복하며 한 명씩 배열 점수에 담긴 점수와 총점, 합계를 출력
			System.out.printf("번호:%d, 이름:%s, 국어:%d, 영어:%d, 수학:%d, 총점:%d, 평균:%f\n",
					k35_i, k35_inData.k35_name[k35_i], k35_inData.k35_kor[k35_i], k35_inData.k35_eng[k35_i], k35_inData.k35_mat[k35_i], k35_inData.k35_sum[k35_i], k35_inData.k35_ave[k35_i]);
		}
	}

}

