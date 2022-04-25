package k35_ch08;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/** 소프트웨어코딩 심화 8강 - 파일
 * 
 * 거리 계산 : 주차장 , 가장 가까운, 먼 곳 찾기 - p8
 * 
 * @author KOPO
 *
 */
public class K35_ex06_p8_2 {

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
		
		double k35_dist_max = 0;																					// 융기원으로부터 와이파이까지 거리가 최대인 지점의 거리값이 저장될 변수
		String k35_loc_max = "";																					// 융기원으로부터 와이파이까지 거리가 최대인 지점의 주소가 저장될 변수
		double k35_dist_min = 100;																					// 융기원으로부터 와이파이까지 거리가 최소인 지점의 거리값이 저장될 변수
		String k35_loc_min = "";																					// 융기원으로부터 와이파이까지 거리가 최소인 지점의 주소가 저장될 변수
		
		while ((k35_readtxt = k35_br.readLine()) != null) {															// 파일의 모든 내용을 읽을 때까지 반복
			k35_readtxt = k35_readtxt.replaceAll("\"(.*?)\"", " ");													//   큰따움표 안의 모든 글자 제거
			String[] k35_field = k35_readtxt.split(",");															//   파일 내용을 한 줄 읽어 ,기준으로 쪼개 문자열 k35_field에 저장
			System.out.printf("**[%d번째 항목]********************\n", k35_lineCnt);									//   몇 번째 열의 내용인지 출력
			System.out.printf(" %s : %s\n", k35_field_name[9], k35_field[9]);										//   와이파이의 주소 출력
			System.out.printf(" %s : %s\n", k35_field_name[13], k35_field[13]);										//   와이파이의 위도 출력
			System.out.printf(" %s : %s\n", k35_field_name[14], k35_field[14]);										//   와이파이의 경도 출력
			double k35_dist = Math.sqrt(Math.pow(Double.parseDouble(k35_field[13]) - k35_lat, 2)
								+ Math.pow(Double.parseDouble(k35_field[14]) - k35_lng, 2)) * 111.35;				//   융기원과 와이파이까지의 거리 계산
			System.out.printf(" 현재지점과 거리 : %fKm\n", k35_dist);													//   거리 출력
			System.out.printf("*******************************************\n");
			
			if (k35_dist_max < k35_dist) {																			//    현재 저장된 최대값보다 현재 확인하고 있는 와이파이의 거리가 더 길면
				k35_dist_max = k35_dist;																			//      현재 확인하고 있는 와이파이의 거리를 최대값으로 저장
				k35_loc_max = k35_field[9];																			//      최대 거리의 주소 새로 저장
			}
			if (k35_dist_min > k35_dist) {																			//    현재 저장된 최대값보다 현재 확인하고 있는 와이파이의 거리가 더 길면
				k35_dist_min = k35_dist;																			//      현재 확인하고 있는 와이파이의 거리를 최대값으로 저장
				k35_loc_min = k35_field[9];																			//      최소 거리의 주소 새로 저장
			}	
			k35_lineCnt++;																							//   줄 수 증가
		}
		
		System.out.println("융기원으로 부터 가장 먼 와이파이주소 : " + k35_loc_max + " / 거리 : " + k35_dist_max +"km");				// 확인한 최대거리의 와이파이 주소와 거리 출력
		System.out.println("융기원으로 부터 가장 가까운 와이파이주소 : " + k35_loc_min + " / 거리 : " + k35_dist_min +"km");			// 확인한 최소거리의 와이파이 주소와 거리 출력
		
		k35_br.close();																								// BufferedReader객체를 닫아준다.
	}
	
}
