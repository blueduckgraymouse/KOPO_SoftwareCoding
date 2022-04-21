package k35_ch08;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/** 소프트웨어코딩 심화 8강 - 파일
 * 
 * 감 잡은 내용으로 파일 정제 - p10
 * 
 * @author KOPO
 *
 */
public class K35_ex08_p10_fieldname {

	public static void main(String[] args) throws IOException {
		File k35_f = new File("C:\\KOPO\\git_tracking\\소프트웨어코딩(v2022)-자바심화\\8강\\day_data\\THTSKS010H00.dat");
		BufferedReader k35_br = new BufferedReader(new FileReader(k35_f));
		
		File k35_f1 = new File("C:\\KOPO\\git_tracking\\소프트웨어코딩(v2022)-자바심화\\8강\\StockDailyPrice.csv");
		BufferedWriter k35_bw1 =new BufferedWriter(new FileWriter(k35_f1));
		
		File k35_f2 = new File("C:\\KOPO\\git_tracking\\소프트웨어코딩(v2022)-자바심화\\8강\\structure.txt");
		BufferedReader k35_br2 = new BufferedReader(new FileReader(k35_f2));
		
		String k35_readtxt;
		String k35_readtxt2;
		
		int k35_cnt = 0;
		int k35_wcnt = 0;
		
		//
		//String k35_fieldname = "";
		StringBuffer k35_fieldname = new StringBuffer();
		while ((k35_readtxt2 = k35_br2.readLine()) != null) {
			String[] k35_field_content = k35_readtxt2.split("/");
			//System.out.println(k35_field_content[1]);
			k35_field_content[1] = k35_field_content[1].replace("*", "");
			k35_field_content[1] = k35_field_content[1].trim();
			//k35_fieldname = k35_fieldname + ',' + k35_field_content[1];
			//k35_fieldname.append(k35_field_content[1]);
			k35_bw1.write(k35_field_content[1]);
		}
		k35_bw1.write(k35_fieldname.toString());
		k35_bw1.newLine();
		//
		
		
		
		while ((k35_readtxt = k35_br.readLine()) != null) {
			StringBuffer k35_s = new StringBuffer();
			String[] k35_field = k35_readtxt.split("%_%");
			
			if(k35_field.length > 2 && k35_field[2].replace("^", "").trim().substring(0,1).equals("A")) {
				k35_s.append(k35_field[0].replace("^", "").trim());
				for (int k35_j = 1 ; k35_j < k35_field.length ; k35_j++) {
					k35_s.append("," + k35_field[k35_j].replace("^", "").trim());
				}
				k35_bw1.write(k35_s.toString());
				k35_wcnt++;
			}
			k35_cnt++;
			
			if(k35_cnt == 50) 
				break;
		}
		k35_br.close();
		k35_bw1.close();
		
		System.out.printf("Program End[%d][%d]records\n", k35_cnt, k35_wcnt);
	}
}
