package ch10;

import java.io.File;

public class Ex14 {

	public static void main(String[] args) {
		File dir = new File("C:\\KOPO\\git_tracking\\기본프로그래밍_java");
		File files[] = dir.listFiles();
		
		for(int i = 0 ; i < files.length ; i++) {
			System.out.println(files[i]);
		}
		
	}

}
