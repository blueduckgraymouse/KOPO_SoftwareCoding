package k35_ch08;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/** 소프트웨어코딩 심화 8강 - 파일
 * 
 * 거리 계산 - p8
 * 
 * @author KOPO
 *
 */
public class K35_ex04_p8_1 {

	public static void main(String[] args) throws IOException {
		File k35_f = new File("C:\\KOPO\\git_tracking\\소프트웨어코딩(v2022)-자바심화\\8강\\12_04_07_E_무료와이파이정보.csv");	// 내용을 읽을 파일의 경로를 파라미터로 전달한 file객체 생성 훟 변수에 저장
		BufferedReader k35_br = new BufferedReader(new FileReader(k35_f));											// file객체를 파라미터로 BufferedReader객체를 생성
		
		String k35_readtxt;																							// 파일 내용을 줄 단위로 읽을 때 마다 한 줄의 내용이 저장될 변수 선언
		
		if ((k35_readtxt = k35_br.readLine()) == null) {															// 파일에 읽을 내용이 없다면(비어있다면)
			System.out.printf("빈파일입니다\n");																		//   파일이 비어있음을 알리고
			return;																									//   메인문 종료
		}
		String[] k35_field_name = k35_readtxt.split(",");															// 첫 줄에 행의 제목(필드명)이 들어있으므로 읽어와 k35_field_name 배열에 저장
		
		
		double k35_lat = 37.3860521;																				// 융기원의 위도를 변수에 저장
		double k35_lng = 127.1214038;																				// 융기원의 경도를 변수에 저장
		
		int k35_lineCnt = 1;																						// 파일 내용을 읽은 줄의 수를 셀 변수 0으로 초기화
		
		while ((k35_readtxt = k35_br.readLine()) != null) {															// 파일의 모든 내용을 읽을 때까지 반복
			k35_readtxt = k35_readtxt.replaceAll("\"(.*?)\"", " ");													//   큰따움표 안의 모든 글자 제거.
																													//     서비스제공사 필드에 ,가 쓰이면 큰따움표로 닫혀있다.
																													//     행단위로 split할 때 ,기준이므로 예외를 없애줬다.
			String[] k35_field = k35_readtxt.split(",");															//   파일 내용을 한 줄 읽어 ,기준으로 쪼개 문자열 k35_field에 저장
			System.out.printf("**[%d번째 항목]********************\n", k35_lineCnt);									//   몇 번째 열의 내용인지 출력
			System.out.printf(" %s : %s\n", k35_field_name[9], k35_field[9]);										//   와이파이의 주소 출력
			System.out.printf(" %s : %s\n", k35_field_name[13], k35_field[13]);										//   와이파이의 위도 출력
			System.out.printf(" %s : %s\n", k35_field_name[14], k35_field[14]);										//   와이파이의 경도 출력
			double k35_dist = Math.sqrt(Math.pow(Double.parseDouble(k35_field[13]) - k35_lat, 2)
								+ Math.pow(Double.parseDouble(k35_field[14]) - k35_lng, 2));						//   융기원과 와이파이까지의 거리 계산
			System.out.printf(" 현재지점과 거리 : %f\n", k35_dist);														//   거리 출력
			System.out.printf("*******************************************\n");
			k35_lineCnt++;																							//   줄 수 증가
		}
		
		k35_br.close();																								// BufferedReader객체를 닫아준다.
	}
	
}
