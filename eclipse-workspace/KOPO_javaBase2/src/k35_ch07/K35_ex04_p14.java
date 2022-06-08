package k35_ch07;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 소프트웨어코딩 심화 7강 - String,Byte,StringBuffer, Array, ArrayList
 * 
 * 고정길이에서 필드 추출 처리 - p10
 * 
 * @author KOPO
 */
public class K35_ex04_p14 {

	public static void main(String[] args) throws IOException {
		File k35_f = new File("C:\\KOPO\\git_tracking\\소프트웨어코딩(v2022)-자바심화\\7강\\경상북도 청송군_지방세체납현황_20201231.csv");
		BufferedReader k35_br = new BufferedReader(new FileReader(k35_f));
		
		String k35_txt;
		
		k35_txt = k35_br.readLine();
		String[] k35_filename = k35_txt.split(",");
		//String[][] k35_datas = new String
		
		while ((k35_txt = k35_br.readLine()) != null) {
			String[] k35_datas_line = k35_txt.split(",");
			for (String k35_data : k35_datas_line) {
				System.out.println(k35_data);
			}
		}
		
		k35_br.close();
	}
}
