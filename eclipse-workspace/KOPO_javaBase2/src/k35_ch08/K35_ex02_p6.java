package k35_ch08;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/** 소프트웨어코딩 심화 8강 - 파일
 * 
 * Buffered 읽고 쓰기 - p6
 * 
 * @author KOPO
 *
 */
public class K35_ex02_p6 {

	public static void main(String[] args) throws IOException {
		k35_fileWrite();										// 파일 쓰는 메서드 호출
		k35_fileRead();											// 파일 읽는 메서드 호출
	}

	// 파일을 생성하여 파일 내용을 쓰는 메서드
	private static void k35_fileWrite() throws IOException {
		File k35_f = new File("c:\\KOPO\\git_tracking\\소프트웨어코딩(v2022)-자바심화\\8강\\test2.txt");	// 생성할 파일의 경로를 파라미터로 전달한 file객체 생성 훟 변수에 저장
		BufferedWriter k35_bw = new BufferedWriter(new FileWriter(k35_f));							// file객체를 파라미터로 BufferedWriter객체를 생성
																									// BufferedWriter는 문자기반 보조스트림 -> 라인 단위 입출력
		
		k35_bw.write("안녕 파일");																		// BufferedWriter객체에 파일 내용 쓰기
		k35_bw.newLine();																			// BufferedWriter객체에 줄바꿈 내용 쓰기
		k35_bw.write("hello 헬로");																	// BufferedWriter객체에 파일 내용 쓰기
		k35_bw.newLine();																			// BufferedWriter객체에 줄바꿈 내용 쓰기
																									
		k35_bw.close();																				// BufferedWriter객체를 닫아준다.
	}
	
	// 파일을 불러와 파일 내용을 읽는 메서드
	private static void k35_fileRead() throws IOException {	
		File k35_f = new File("c:\\KOPO\\git_tracking\\소프트웨어코딩(v2022)-자바심화\\8강\\test2.txt");	// 내용을 읽을 파일의 경로를 파라미터로 전달한 file객체 생성 훟 변수에 저장
		BufferedReader k35_br = new BufferedReader(new FileReader(k35_f));							// file객체를 파라미터로 BufferedReader객체를 생성
		
		String k35_readtxt;																			// 파일 내용을 줄 단위로 읽을 때 마다 한 줄의 내용이 저장될 변수 선언  
		
		while ((k35_readtxt = k35_br.readLine()) != null) {											// 파일의 모든 내용을 읽을 때까지 반복
			System.out.printf("%s\n", k35_readtxt);													// 읽은 한 줄의 내용 출력
		}
		
		k35_br.close();																				// BufferedReader객체를 닫아준다.
	}

}
