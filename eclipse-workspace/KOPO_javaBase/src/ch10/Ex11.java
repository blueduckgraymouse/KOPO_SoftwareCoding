package ch10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// 유동적 - 효율 => 주로 사용

public class Ex11 {

	public static void main(String[] args) throws IOException {
		BufferedReader trafficvolume = new BufferedReader(new FileReader("C:\\KOPO\\git_tracking\\기본프로그래밍_java\\ex11.csv"));
		String line;
		while((line = trafficvolume.readLine()) != null) {
			System.out.println(line);
		}
		trafficvolume.close();
	}

}
