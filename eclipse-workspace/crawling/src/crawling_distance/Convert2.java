package crawling_distance;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Convert2 {

	public static void main(String[] args) throws IOException {
		File rf = new File("C:\\KOPO\\git_tracking\\기본프로그래밍_java\\Pro\\schooldistance\\datas\\final_data_distance_avg.csv");
		BufferedReader br = new BufferedReader(new FileReader(rf));
		File wf = new File("C:\\KOPO\\git_tracking\\기본프로그래밍_java\\Pro\\schooldistance\\datas\\final_data_distance_exp_no_data.csv");
		BufferedWriter bw = new BufferedWriter(new FileWriter(wf));
		
		String txt_read = "";
		String txt_write = "";
		
		try {
			while((txt_read = br.readLine()) != null) {
				String[] data = txt_read.split(",");
				System.out.println(txt_read);
				if(!(data[5].equals("") || data.length == 11)) {
					bw.write(txt_read);
					bw.newLine();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		bw.close();
	}

}
