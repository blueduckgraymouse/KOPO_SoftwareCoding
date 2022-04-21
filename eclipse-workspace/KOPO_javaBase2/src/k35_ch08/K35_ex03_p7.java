package k35_ch08;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/** 소프트웨어코딩 심화 8강 - 파일
 * 
 * 실제 파일에 응용 - p7
 * 
 * @author KOPO
 *
 */
public class K35_ex03_p7 {

	public static void main(String[] args) throws IOException {
		File k35_f = new File("C:\\KOPO\\git_tracking\\소프트웨어코딩(v2022)-자바심화\\8강\\12_04_07_E_무료와이파이정보.csv");	// 내용을 읽을 파일의 경로를 파라미터로 전달한 file객체 생성 훟 변수에 저장
		BufferedReader k35_br = new BufferedReader(new FileReader(k35_f));											// file객체를 파라미터로 BufferedReader객체를 생성
		
		String k35_readtxt;																							// 파일 내용을 줄 단위로 읽을 때 마다 한 줄의 내용이 저장될 변수 선언  
		
		if ((k35_readtxt = k35_br.readLine()) == null) {															// readLine메서드를 통해서 파일의 첫 줄 일기를 시도했으나 파일이 내용이 없어
																													// 반환값이 없어 string의 초기값 null로 그대로 남아있다면
			System.out.printf("빈파일입니다\n");																		//   파일이 비어있음을 알리고
			return;																									//   메인문 종료
		}
		String[] k35_field_name = k35_readtxt.split(",");															// 첫 줄에 행의 제목이 들어있으므로 읽어와 k35_field_name 배열에 저장
		
		int k35_lineCnt = 1;																						// 파일 내용을 읽은 줄의 수를 셀 변수 0으로 초기화
		
		while ((k35_readtxt = k35_br.readLine()) != null) {															// 파일의 모든 내용을 읽을 때까지 반복
			String[] k35_field = k35_readtxt.split(",");															//   파일 내용을 한 줄 읽어 ,기준으로 쪼개 문자열 k35_field에 저장
			System.out.printf("**[%d번째 항목]********************\n", k35_lineCnt);									//   몇 번째 열의 내용인지 출력
			for (int k35_j = 0 ; k35_j < k35_field_name.length ; k35_j++) {											//   행의 개수만큼 반복
				System.out.printf(" %s : %s\n", k35_field_name[k35_j], k35_field[k35_j]);							//     필드명(행의 제목)과 해당하는 값 출력 
			}
			System.out.printf("*******************************************\n");
			if (k35_lineCnt == 100)																					//   100개 출력하면
				break;																								//     반복문 종료
			k35_lineCnt++;																							//   줄 수 증가
		}
			
		k35_br.close();																								// BufferedReader객체를 닫아준다.
	}
	
}