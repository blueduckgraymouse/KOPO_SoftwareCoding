package crawling_distance;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Convert {

	public static void main(String[] args) throws IOException {
		File rf = new File("C:\\KOPO\\git_tracking\\기본프로그래밍_java\\Pro\\schooldistance\\datas\\강서, 강동, 강북, 강서 완료_utf8.csv");
		BufferedReader br = new BufferedReader(new FileReader(rf));
		File wf = new File("C:\\KOPO\\git_tracking\\기본프로그래밍_java\\Pro\\schooldistance\\datas\\강서, 강동, 강북, 강서 완료_re.csv");
		BufferedWriter bw = new BufferedWriter(new FileWriter(wf));
		
		String txt_read = "";
		String txt_write = "";
		
		try {
			while((txt_read = br.readLine()) != null) {
				System.out.println(txt_read);
				String[] datas = txt_read.split(",");
				txt_write = datas[0] + "," + datas[1] + "," + datas[2];
				
				String[] prices = datas[3].replace("-", "").split("~");
				if(prices.length == 2) {
					String price = "";
					if(prices[0].contains("억 ")) {
						price = prices[0].replace("억 ", "");
					} else {
						price = prices[0].replace("억", "0000");
					}
					txt_write = txt_write + "," + price;
					if(prices[1].contains("억 ")) {
						price = prices[1].replace("억 ", "");
					} else {
						price = prices[1].replace("억", "0000");
					}
					txt_write = txt_write + "," + price;
				} else {
					String price = "";
					if(prices[0].contains("억 ")) {
						price = prices[0].replace("억 ", "");
					} else {
						price = prices[0].replace("억", "0000");
					}
					txt_write = txt_write + "," + price + "," + price;
				}
				
				txt_write = txt_write + "," + datas[4].replace("세대", "");
				
				txt_write = txt_write + "," + datas[5].replace("총", "").replace("동", "");
				
				String[] sizes = datas[6].split("~");
				if(sizes.length==2) {
					txt_write = txt_write + "," + sizes[0].trim().replace("㎡", "");
					txt_write = txt_write + "," + sizes[1].trim().replace("㎡", "");
				} else {
					txt_write = txt_write + "," + sizes[0].trim().replace("㎡", "");
					txt_write = txt_write + "," + sizes[0].trim().replace("㎡", "");
				}
				
				txt_write = txt_write + "," + datas[7].replace("정보 없음", "");
				
				System.out.println(txt_write+ "\n");
				bw.write(txt_write);
				bw.newLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		bw.close();
	}

}
