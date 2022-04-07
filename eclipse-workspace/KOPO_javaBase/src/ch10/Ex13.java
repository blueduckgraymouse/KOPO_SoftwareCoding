package ch10;

import java.io.File;
import java.io.IOException;

public class Ex13 {

	public static void main(String[] args) throws IOException {
		File file = new File("C:\\KOPO\\git_tracking\\기본프로그래밍_java\\ex12.csv");
		System.out.println(file.exists());
		if(file.exists()) {
			file.delete();
		}
		System.out.println(file.exists());
	}
	
}
