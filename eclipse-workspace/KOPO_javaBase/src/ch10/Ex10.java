package ch10;

import java.io.FileInputStream;
import java.io.IOException;

// 고정적 - 비효율 , 잘 사용 x

public class Ex10 {

	public static void main(String[] args) throws IOException {
		byte[] content = new byte[1000];
		FileInputStream input = new FileInputStream("C:\\KOPO\\git_tracking\\기본프로그래밍_java\\ex10.txt");
		
		input.read(content);
		String allLine = new String(content);
		System.out.println(allLine);
		
		input.close();
	}

}
