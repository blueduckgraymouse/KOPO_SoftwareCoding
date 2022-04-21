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
public class K35_ex09_p11_3 {

	public static void main(String[] args) throws IOException {
		File k35_f_read = new File("C:\\KOPO\\git_tracking\\소프트웨어코딩(v2022)-자바심화\\8강\\A005930.csv");
		BufferedReader k35_br = new BufferedReader(new FileReader(k35_f_read));
		
		File k35_f_write = new File("C:\\KOPO\\git_tracking\\소프트웨어코딩(v2022)-자바심화\\8강\\2015년삼성전자주가.csv");
		BufferedWriter k35_bw =new BufferedWriter(new FileWriter(k35_f_write));
		
		SimpleDateFormat k35_sdf = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
		
		String k35_readtxt;
		
		int k35_cnt = 0;
		int k35_wcnt = 0;
		
		int k35_price_total_max = 0;
		int k35_price_total_min = 10000000;
		String k35_date_total_max = "";
		String k35_date_total_min = "";
		
		Calendar k35_cal1 = Calendar.getInstance();
		System.out.println("파일 접근 시간 : " + k35_sdf.format(k35_cal1.getTime()));

		while ((k35_readtxt = k35_br.readLine()) != null) {
			String[] k35_datas = k35_readtxt.split(",");
			if(k35_datas[1].contains("2015")) {
				k35_bw.write(k35_readtxt);
				int k35_price_max = Integer.parseInt(k35_datas[3]);
				if(k35_price_max > k35_price_total_max) {
					k35_price_total_max = k35_price_max;
					k35_date_total_max = k35_datas[1];
				}
				int k35_price_min = Integer.parseInt(k35_datas[3]);
				if(k35_price_min < k35_price_total_min) {
					k35_price_total_min = k35_price_min;
					k35_date_total_min = k35_datas[1];
				}
				k35_bw.newLine();
				k35_wcnt++;
			}
			k35_cnt++;
		}
		
		k35_br.close();
		k35_bw.close();
		
		Calendar k35_cal2 = Calendar.getInstance();
		System.out.println("종료 시간 : " + k35_sdf.format(k35_cal2.getTime()));
		System.out.println("소요 시간(초 단위) : " + (k35_cal2.getTimeInMillis() - k35_cal1.getTimeInMillis()));
		
		System.out.printf("Program End[%d][%d]records\n", k35_cnt, k35_wcnt);
		
		System.out.println("2015년 최고 종가의 날 : " + k35_date_total_max + " / 주가 : " + k35_price_total_max);
		System.out.println("2015년 최저 종가의 날 : " + k35_date_total_min + " / 주가 : " + k35_price_total_min);
	}
}
