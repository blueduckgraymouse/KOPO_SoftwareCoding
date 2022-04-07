package ch10.assignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

public class A04 {
	static String columnName = "14시";
	
	public static void main(String[] args) throws IOException {
		FileReader fr = new FileReader("C:\\KOPO\\git_tracking\\기본프로그래밍_java\\trafficvolume.csv", Charset.forName("MS949"));
		BufferedReader trafficvolume = new BufferedReader(fr);
		
		String line = "";
		int colNumber = 0;
		int count = 0;
		int sum = 0;
		Integer min = null;
		Integer max = null;
		
		// 14시의 column번호 확인
		line = trafficvolume.readLine();
		String[] titles = line.split(",");
		for (int i = 0 ; i < titles.length ; i++) {
			if (titles[i].equals(columnName)) {
				colNumber = i;
			}
		}
		
		// 모든 데이터에 접근하며 원하는 조건에 해당하는 데이터 분석
		while ((line = trafficvolume.readLine()) != null) {
			String[] datas = line.split(",");
			if (datas[1].equals("목")) {
				try {
					int volume = Integer.parseInt(datas[colNumber]);
					count++;
					// min, max가 비어있는 경우
					if(min == null) {
						min = volume;
						max = volume;
					}
					// 최소 최대 확인
					min = min > volume ? volume : min;
					max = max < volume ? volume : max;
					
					// 누적
					sum += volume;
				} catch (Exception e) {	// 값이 없는 경우 예외 처리
					continue;
				}
			}
		}
		
		trafficvolume.close();
		
		System.out.printf("mean : %.3f\n", ((double)sum / count));
		System.out.println("min : " + min);
		System.out.println("max : " + max);
	}


}
