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
 * 스스로 실습 - p11
 *  실습2 : 특정일자의 모든 종목을 추출하여 파일 만들기
 * 
 * @author KOPO
 *
 */
public class K35_ex09_p11_2 {

	public static void main(String[] args) throws IOException {
		File k35_f_read = new File("C:\\KOPO\\git_tracking\\소프트웨어코딩(v2022)-자바심화\\8강\\StockDailyPrice.csv"); // 내용을 읽을 파일의 경로를 파라미터로 전달한 file객체 생성 훟 변수에 저장
		BufferedReader k35_br = new BufferedReader(new FileReader(k35_f_read));		// file객체를 파라미터로 BufferedReader객체를 생성
		
		File k35_f_write = new File("C:\\KOPO\\git_tracking\\소프트웨어코딩(v2022)-자바심화\\8강\\20150112.csv");		// 내용을 쓸 파일의 경로를 파라미터로 전달한 file객체 생성 훟 변수에 저장
		BufferedWriter k35_bw =new BufferedWriter(new FileWriter(k35_f_write));		// file객체를 파라미터로 BufferedWriter객체를 생성
		
		SimpleDateFormat k35_sdf = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");		// 날짜출력 포팻을 설정하여 날짜 객체 생성 후 변수에 저장
		
		String k35_readtxt;															// 파일 내용을 줄 단위로 읽을 때 마다 한 줄의 내용이 저장될 변수 선언
		
		int k35_cnt = 0;															// 파일 내용을 읽은 줄의 수를 셀 변수 0으로 초기화
		int k35_wcnt = 0;															// 파일 내용을 쓴 줄의 수를 셀 변수 0으로 초기화
		
		Calendar k35_cal1 = Calendar.getInstance();									// 시작할 때 시간을 Calendar객체에 저장
		System.out.println("파일 접근 시간 : " + k35_sdf.format(k35_cal1.getTime()));	// 시작 시간 출력

		while ((k35_readtxt = k35_br.readLine()) != null) {							// 파일의 모든 내용을 읽을 때까지 반복
			String[] k35_datas = k35_readtxt.split(",");							// ,기준으로 현재 읽고 있는 한줄 쪼개서 문자열 배열에 저장
			if(k35_datas[1].contains("20150112")) {									// 주식영업일자에 해당하는 2번째 데이터가 2015년01월12일이면
				k35_bw.write(k35_readtxt);											// 그 줄의 데이터를 BufferedWriter에 쓴다.
				k35_bw.newLine();													// BufferedWriter객체에 줄바꿈을 쓴다.
				k35_wcnt++;															// 쓴 줄 수 증가
			}
			k35_cnt++;																// 읽은 줄 수 증가	
		}
		
		k35_br.close();																// BufferedReader객체를 닫아준다.
		k35_bw.close();																// BufferedWriter객체를 닫아준다.
		
		Calendar k35_cal2 = Calendar.getInstance();									// 종료 시간을 Calendar객체에 저장
		System.out.println("종료 시간 : " + k35_sdf.format(k35_cal2.getTime()));		// 종료 시간 출력
		System.out.println("소요 시간(초 단위) : " + (k35_cal2.getTimeInMillis() - k35_cal1.getTimeInMillis())); // 걸린 시간 계산
		
		System.out.printf("Program End[%d][%d]records\n", k35_cnt, k35_wcnt);		// 읽은 줄 수 와 쓴 줄수를 출력한다.
	}
}
