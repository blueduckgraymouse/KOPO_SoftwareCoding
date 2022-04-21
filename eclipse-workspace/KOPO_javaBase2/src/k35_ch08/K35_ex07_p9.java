package k35_ch08;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/** 소프트웨어코딩 심화 8강 - 파일
 * 
 * 파일 훝어보고 감잡기 - p9
 * 
 * @author KOPO
 *
 */
public class K35_ex07_p9 {

	public static void main(String[] args) throws IOException {
		File k35_f = new File("C:\\KOPO\\git_tracking\\소프트웨어코딩(v2022)-자바심화\\8강\\day_data\\THTSKS010H00.dat");	// 내용을 읽을 파일의 경로를 파라미터로 전달한 file객체 생성 훟 변수에 저장
		BufferedReader k35_br = new BufferedReader(new FileReader(k35_f));	// file객체를 파라미터로 BufferedReader객체를 생성
		
		String k35_readtxt;													// 파일 내용을 줄 단위로 읽을 때 마다 한 줄의 내용이 저장될 변수 선언
		int k35_lineCnt = 1;												// 파일 내용을 읽은 줄의 수를 셀 변수 0으로 초기화
		int k35_n = -1;														// 파일내 읽을 내용이 더 이상 없는지를 나타내는 변수를 -1로 선언
		StringBuffer k35_s = new StringBuffer();							// 잦은 string버퍼 생성을 막기 위하여 String 대신 읽은 파일 내용 출력에 사용할 StringBuffer 객체 생성
		
		while (true) {														// 파일의 모든 내용을 읽을 때 까지 반복
			char[] k35_ch = new char[1000];									// 파일의 내용일 저장될 char 배열 변수의 크기 초기화
			
			k35_n = k35_br.read(k35_ch);									// k35_fr파일 객체의 내용, 즉 파일의 내용을 읽어서 char 배열의 버퍼(메모리)에 저장
			
			if (k35_n == -1)												// 읽은 내용(문자)이 없으면
				break;														//   반복문 종료
			
			for (char k35_c : k35_ch) {										// 읽어온 문자의 개수만큼 반복
				if (k35_c == '\n') {										//   현재 읽은 문자가 줄바꿈이면
					System.out.printf("[%s]***\n", k35_s.toString());		//    StringBuffer 객체에 저장된 내용 출력
					k35_s.delete(0, k35_s.length());						//    StringBuffer 객체에 저장된 내용 비우기
				} else {
					k35_s.append(k35_c);									// 현재 읽고 있는 문자 StringBuffer 객체에 저장
				}
			}
		}
		System.out.printf("[%s]****\n", k35_s.toString());					// StringBuffer 객체에 저장된 내용 출력
		k35_br.close();
	}
	
}
