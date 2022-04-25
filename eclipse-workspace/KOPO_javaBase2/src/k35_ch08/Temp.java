package k35_ch08;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Temp {

	public static void main(String[] args) throws IOException {
//		File k35_f = new File("C:\\KOPO\\git_tracking\\소프트웨어코딩(v2022)-자바심화\\8강\\StockDailyPrice.csv");
//		BufferedReader k35_br = new BufferedReader(new FileReader(k35_f));
//		
//		File k35_f2 = new File("C:\\KOPO\\git_tracking\\소프트웨어코딩(v2022)-자바심화\\8강\\StockDailyPrice.csv");
//		BufferedWriter k35_wr = new BufferedWriter(new FileWriter(k35_f2));
		
		String k35_readtxt = "abcd eqrewr\nawerlawknfsa\nlskdf lasdfaswelkfr\n";
	
		int k35_cnt = 0;	
		
		//while ((k35_readtxt = k35_br.readLine()) != null) {						// 파일의 모든 내용을 읽을 때까지 반복
			StringBuffer k35_s = new StringBuffer();							// 잦은 string버퍼 생성을 막기 위하여 String 대신 읽은 파일 내용 출력에 사용할 StringBuffer 객체 생성
			
			k35_s.append(k35_readtxt);											// 첫번째 문자열 배열 요소의 ^를 제거하고 양쪽 공백을 제거하여 StringBuffer객체에 저장
			String a = k35_s.toString();		
			System.out.println("length"+k35_s.length()); 
			System.out.println(a);
			String b = k35_s.toString();	
			System.out.println(b);
			System.out.println("length"+k35_s.length()); 
			
			//k35_cnt++;
			//if(k35_cnt == 100) {
			//	break;
			//}
			
		//}
		
		//k35_br.close();
	}
}
