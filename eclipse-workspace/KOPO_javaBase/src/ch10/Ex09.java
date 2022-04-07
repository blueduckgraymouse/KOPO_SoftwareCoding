package ch10;

import java.io.FileOutputStream;
import java.io.IOException;

public class Ex09 {

	public static void main(String[] args) throws IOException {
		FileOutputStream output = new FileOutputStream("C:\\KOPO\\git_tracking\\기본프로그래밍_java\\ex09.csv");
		for (int i = 1 ; i < 11 ; i++) {
			String data = i + "," + "학생" + i + "\n";
			output.write(data.getBytes());
		}
		output.close();
	}

}
