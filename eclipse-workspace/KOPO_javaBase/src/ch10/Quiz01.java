package ch10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

// 유동적 - 효율 => 주로 사용

public class Quiz01 {
	static String columnName = "14시";
	
	public static void main(String[] args) throws IOException {
		FileReader fr = new FileReader("C:\\KOPO\\git_tracking\\기본프로그래밍_java\\trafficvolume.csv", Charset.forName("MS949"));
		BufferedReader trafficvolume = new BufferedReader(fr);
		String line = "";
		int colNumber = 0;
		int count = 0;
		int sum = 0;
		int min = 100000;	// 이부분 개선
		int max = 0;
		
		line = trafficvolume.readLine();
		String[] titles = line.split(",");
		for(int i = 0 ; i < titles.length ; i++) {
			if(titles[i].equals(columnName)) {
				colNumber = i;
			}
		}
		
		while((line = trafficvolume.readLine()) != null) {
			String[] datas = line.split(",");
			if(datas[1].equals("목")) {
				try {
					int volume = Integer.parseInt(datas[colNumber]);
					count++;
//					if(count == 1) {	// 첫번째로 14시에 데이터가 있는 값을 최대, 최소로 넣기 구현
//						min = volume;
//						max = volume;
//						System.out.println(min + max);
//					}
					min = min > volume ? volume : min;
					max = max < volume ? volume : max;
					if(volume == 0) {
						System.out.println(datas[0] + datas[1]+ datas[2]+ datas[3]+ datas[4]+ datas[5]);
					}
					sum += volume;
				} catch (Exception e) {
					//e.printStackTrace();
				}
			}
		}
		
		trafficvolume.close();
		
		System.out.println("count : " + count);
		System.out.println("sum : " + sum);
		System.out.printf("mean : %.3f\n", ((double)sum / count));
		System.out.println("min : " + min);
		System.out.println("max : " + max);
	}

}
