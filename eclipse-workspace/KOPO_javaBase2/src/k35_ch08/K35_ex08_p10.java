package k35_ch08;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/** 소프트웨어코딩 심화 8강 - 파일
 * 
 * 감 잡은 내용으로 파일 정제 - p10
 * 
 * @author KOPO
 *
 */
public class K35_ex08_p10 {

	public static void main(String[] args) throws IOException {
		File k35_f = new File("C:\\KOPO\\git_tracking\\소프트웨어코딩(v2022)-자바심화\\8강\\day_data\\THTSKS010H00.dat");  // 내용을 읽을 파일의 경로를 파라미터로 전달한 file객체 생성 훟 변수에 저장
		BufferedReader k35_br = new BufferedReader(new FileReader(k35_f));		// file객체를 파라미터로 BufferedReader객체를 생성
		
		File k35_f1 = new File("C:\\KOPO\\git_tracking\\소프트웨어코딩(v2022)-자바심화\\8강\\StockDailyPrice.csv");		// 내용을 쓸 파일의 경로를 파라미터로 전달한 file객체 생성 훟 변수에 저장
		BufferedWriter k35_bw1 =new BufferedWriter(new FileWriter(k35_f1)); 	// file객체를 파라미터로 BufferedWriter객체를 생성
		
		SimpleDateFormat k35_sdf = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");	// 날짜출력 포팻을 설정하여 날짜 객체 생성 후 변수에 저장
		
		String k35_readtxt;														// 파일 내용을 줄 단위로 읽을 때 마다 한 줄의 내용이 저장될 변수 선언
		
		int k35_cnt = 0;														// 파일 내용을 읽은 줄의 수를 셀 변수 0으로 초기화
		int k35_wcnt = 0;														// 파일 내용을 읽은 줄의 수를 셀 변수 0으로 초기화
		
		Calendar k35_cal1 = Calendar.getInstance();								// 시작할 때 시간을 Calendar객체에 저장
		System.out.println("파일 접근 시간 : " + k35_sdf.format(k35_cal1.getTime()));	// 시작 시간 출력
		
		while ((k35_readtxt = k35_br.readLine()) != null) {						// 파일의 모든 내용을 읽을 때까지 반복
			StringBuffer k35_s = new StringBuffer();							// 잦은 string버퍼 생성을 막기 위하여 String 대신 읽은 파일 내용 출력에 사용할 StringBuffer 객체 생성
			String[] k35_field = k35_readtxt.split("%_%");						// %_%기준으로 현재 읽고 있는 한줄 쪼개서 문자열 배열에 저장
			
			if(k35_field.length > 2 && k35_field[2].replace("^", "").trim().substring(0,1).equals("A")) {	// 쪼갠 문자열 배열의 길이가 3이상이고 셋번째 문자열 배열 요소의 ^를 제거하고 양쪽 공백을 제거 후 첫 글자가 A라면
				k35_s.append(k35_field[0].replace("^", "").trim());											// 첫번째 문자열 배열 요소의 ^를 제거하고 양쪽 공백을 제거하여 StringBuffer객체에 저장
				for (int k35_j = 1 ; k35_j < k35_field.length ; k35_j++) {									// 문자열 배열의 길이만큼 i를 증가하며 반복
					k35_s.append("," + k35_field[k35_j].replace("^", "").trim());							// i번째 문자열 배열 요소의 ^를 제거하고 양쪽 공백을 제거하여 StringBuffer객체에 저장
				}
				k35_bw1.write(k35_s.toString());															// 여기까지.
				k35_bw1.newLine();
				k35_wcnt++;
			}
			
			k35_cnt++;
		}
		k35_br.close();
		k35_bw1.close();
		
		Calendar k35_cal2 = Calendar.getInstance();								// 종료 시간을 Calendar객체에 저장
		System.out.println("종료 시간 : " + k35_sdf.format(k35_cal2.getTime()));	// 종료 시간 출력
		System.out.println("소요 시간(초 단위) : " + (k35_cal2.getTimeInMillis() - k35_cal1.getTimeInMillis()));	// 걸린 시간 계산
		
		System.out.printf("Program End[%d][%d]records\n", k35_cnt, k35_wcnt);
	}
}
