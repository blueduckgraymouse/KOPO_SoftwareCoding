package k35_ch08;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/** 소프트웨어코딩 심화 8강 - 파일
 * 
 * 무식하게 읽고 쓰기 - p5
 * 
 * @author KOPO
 *
 */
public class K35_ex01_p5 {

	public static void main(String[] args) {
		
		try {
			File k35_f = new File("c:\\KOPO\\git_tracking\\소프트웨어코딩(v2022)-자바심화\\8강\\test.txt");	// text.txt라는 파일을 파일 객체로 생성하고 변수에 할당
			FileWriter k35_fw = new FileWriter(k35_f);													// 파일객체 k35_f 파일 객체를 생성자의 파라미터로 전달하여 FileWriter객체를 생성 후 변수에 할당
																										// FileWriter는 문자기반 입출력 스트림(데이터를 운반하는 연결통로)
			k35_fw.write("안녕 파일\n");																	// k35_fw 객체에 "안녕 파일\n"이라는 문자열을 쓴다.
			k35_fw.write("hello 헬로\n");																	// k35_fw 객체에 "hello 헬로\n"이라는 문자열을 쓴다.
			
			k35_fw.close();																				// 파일 내용을 모두 썼으면 FileWriter객체를 닫아준다.(파일을 저장한다)
			
			FileReader k35_fr = new FileReader(k35_f);													// 파일객체 k35_f 파일 객체를 생성자의 파라미터로 전달하여 FileReader객체를 생성 후 변수에 할당
			
			int k35_n = -1;																				// 파일내 읽을 내용이 더 이상 없는지를 나타내는 변수를 -1로 선언
			char[] k35_ch;																				// 파일의 내용일 저장될 char 배열 변수 선언 <-- FileWriter는 문자(char)단위 입출력이다.
			
			while (true) {																				// 파일의 모든 내용을 읽을 때 까지 반복
				k35_ch = new char[100];																	// 파일의 내용일 저장될 char 배열 변수의 크기 초기화
				k35_n = k35_fr.read(k35_ch);															// k35_fr파일 객체의 내용, 즉 파일의 내용을 읽어서 char 배열의 버퍼(메모리)에 저장
																										//  FileWriter객체의 read메서드는 char배열 혹은 charbuffer 객체르 파라미터로 받아
																										//  파일 내용을 저장한다. 즉, 문자단위 입출력이다.
																										//  반환값으로는 읽은 문자의 수를 반환하며, 읽을 문자가 없으면 -1을 반환한다.
				
				if (k35_n == -1)																		// 읽을 문자가 없으면
					break;																				//   반복문 종료
				for (int k35_i = 0 ; k35_i < k35_n ; k35_i++)											// 읽어온 문자의 개수만큼 반복
					System.out.printf("%c", k35_ch[k35_i]);												//   읽어온 모든 문자를 한 개씩 출력
			}
			k35_fr.close();																				// FileWriter객체를 닫아준다.
			System.out.println("\n FILE READ END");
		} catch (Exception e) {																			// 에러 이벤트 발생시
			System.out.printf("나 에러[%s]\n", e);														//   에러명 출력
		}
		
	}

}
