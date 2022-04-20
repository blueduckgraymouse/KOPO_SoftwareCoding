package k35_ch07;

/**
 * 소프트웨어코딩 심화 7강 - String,Byte,StringBuffer, Array, ArrayList
 * 
 * 한글 계산 - p9
 * 
 * @author KOPO
 */
public class K35_ex01_p10 {

	public static void main(String[] args) {
		System.out.printf("HanBkankForeword[%s]\n", k35_hanBlankForeword("한글abcd", 15));
		System.out.printf("HanBkankForeword[%s]\n", k35_hanBlankForeword("한글한글aa", 15));
		System.out.printf("HanBkankForeword[%s]\n", k35_hanBlankBackword("한글aa", 15));
		System.out.printf("HanBkankForeword[%s]\n", k35_hanBlankBackword("한글한글aa", 15));
		System.out.printf("한글은 [%d]개\n", k35_hanCount("한글한글aa"));
		
	}

	// 파라미터로 들어온 수의 크기에 맞게 파라미터로 들어온 문자열 앞에 공백 추가하는 메서드
	private static String k35_hanBlankForeword(String k35_str, int k35_num_blank) {
		String k35_result = "";
		int k35_size_byte = k35_str.getBytes().length;							// 문자열의 byte를 변수에 저장한다. 인코딩 형식은 기본값 UTF-8으로 설정되있으므로 한글은 3byte, 그 외는 1byte를 차지한다.
		int k35_length = k35_str.length();										// 문자열의 길이 변수에 저장
		int k35_num_han = (k35_size_byte - k35_length) / 2;						// 한글 한 글자 당 문자열길이와 byte크기가 2씩 차이나므로 그 차이를 세서 2로 나누면 한글 글자의 개수이다.
		int k35_size_in_colsole = k35_length + k35_num_han;						// 콘솔 내에서는 한글 한글자가 2byte, 그 외는 1byte를 차지하므로, 문자열의 콘솔 내 bit 길이 = 문자열길이 + 한글의 개수
		for (int k35_i = 0 ; k35_i < 15 - k35_size_in_colsole ; k35_i++) 
			k35_result += " ";
		k35_result += k35_str;													// 공백을 채워 준 후 마지막에 기존 문자열을 더한다.
		return k35_result;														// 실행결과 문자열 반환
	}

	// 파라미터로 들어온 수의 크기에 맞게 파라미터로 들어온 문자열 뒤에 공백 추가하는 메서드
	private static String k35_hanBlankBackword(String k35_str, int k35_num_blank) {
		String k35_result = k35_str;											// 뒤에 공백을 붙일 것이므로 기존 문자열 먼저 반환할 문자열에 저장한다.
		int k35_size_byte = k35_str.getBytes().length;							// 문자열의 byte를 변수에 저장한다. 인코딩 형식은 기본값 UTF-8으로 설정되있으므로 한글은 3byte, 그 외는 1byte를 차지한다.
		int k35_length = k35_str.length();
		int k35_num_han = (k35_size_byte - k35_length) / 2;						// 한글 한 글자 당 문자열길이와 byte크기가 2씩 차이나므로 그 차이를 세서 2로 나누면 한글 글자의 개수이다.
		int k35_size_in_colsole = k35_length + k35_num_han;						// 콘솔 내에서는 한글 한글자가 2byte, 그 외는 1byte를 차지하므로, 문자열의 콘솔 내 bit 길이 = 문자열길이 + 한글의 개수
		for (int k35_i = 0 ; k35_i < 15 - k35_size_in_colsole ; k35_i++) 
			k35_result += " ";
		return k35_result;														// 실행결과 문자열 반환
	}

	// 파라미터로 들어온 문자열에 포함된 한글의 개수 확인하는 메서드
	private static int k35_hanCount(String k35_str) {
		int k35_size_byte = k35_str.getBytes().length;							// 문자열의 byte를 변수에 저장한다. 인코딩 형식은 기본값 UTF-8으로 설정되있으므로 한글은 3byte, 그 외는 1byte를 차지한다.
		int k35_length = k35_str.length();										// 문자열의 길이 변수에 저장
		return (k35_size_byte - k35_length) / 2;								// 한글 한 글자 당 문자열길이와 byte크기가 2씩 차이나므로 그 차이를 세서 2로 나누면 한글 글자의 개수이다. 그 값을 반환한다.
	}
}
