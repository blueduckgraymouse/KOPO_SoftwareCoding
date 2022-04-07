package ch10;

import java.io.FileWriter;
import java.io.IOException;

public class Ex12 {

	public static void main(String[] args) throws IOException {
		//FileWriter fw = new FileWriter("C:\\KOPO\\git_tracking\\기본프로그래밍_java\\ex12.csv", false);	// 생성
		FileWriter fw = new FileWriter("C:\\KOPO\\git_tracking\\기본프로그래밍_java\\ex12.csv", true);		// 추가
		for(int i = 11 ; i < 16 ; i++) {
			String data ="Line #" + i + "\n";
			fw.write(data);
		}
		fw.close();
	}

}
