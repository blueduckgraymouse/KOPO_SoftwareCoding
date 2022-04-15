package k35_ch04;

/** 소프트웨어 코딩 심화 4강 - p16 실습
 * 
 *  숫자형, 문자형 비교
 * 
 * @author KOPO35
 *
 */
public class K35_ex03 {
	public static void main(String[] args) {
		
		int k35_iI;			// 숫자형 비교를 위한 int형 변수 선언
		double k35_iD;		// 숫자형 비교를 위한 double형 변수 선언
		
		k35_iI = 10 / 3;	// int형 변수에 10 / 3의 연산값인 3이 저장
		k35_iD = 10 / 3.0;	// double 변수에 10 / 3.0의 연산값인 3.333333이 저장
		
		// #1
		if (k35_iI == k35_iD )									// int형 변수와 double형 변수에 저장된 값이 같은지 비교 - 3 / 3.333333
			System.out.printf("#1. equal\n");
		else													// 조건문이 다르므로 else내용 실행, 출력하여 값 확인
			System.out.printf("#1. Not equal[%f][%f]\n", (double)k35_iI, k35_iD);
		
		// #2
		if (k35_iD == 3.333333)									// int형 변수에 저장된 값이 3.333333인지 비교 - 3 / 3.333333
			System.out.printf("#2. equal\n");
		else													// int형 변수에는 3이 저장되어 있어 다르믈 else내용 출력
			System.out.printf("#2. Not eqaul[3.333333][%f]\n", k35_iD);
		
		// #3
		k35_iD = (int)k35_iD;									// 기존에 3.333333이 저장된 double형 변수의 값을 int로 형변환하면 3, 3을 double형 변수에 저장
		if (k35_iI == k35_iD)									// 기존 int형 변수와 새로 저장된 double형 변수의 값이 같은지 비교 - 3 / 3
			System.out.printf("#3. equal\n");					// 같으므로 equal출력
		else
			System.out.printf("#3. Not eqaul[%f][%f]\n", k35_iI, k35_iD);
		
		
		System.out.printf("\nint로 인쇄[%d][%f]\n", k35_iI, k35_iD);
		System.out.printf("double로 인쇄[%f][%f]\n\n", (double)k35_iI, k35_iD);
		
		// #4
		char k35_a ='c';										// char형 변수에 'c'문자 저장
		if (k35_a == 'b')										// char형 변수에 저장된 값이 'b'인지 비교 - 'c' / 'b'
			System.out.printf("#4. a는 b이다\n");
		if (k35_a == 'c')										// char형 변수에 저장된 값이 'c'인지 비교 - 'c' / 'c' => 만족하므로 내용 출력
			System.out.printf("#4. a는 c이다\n");
		if (k35_a == 'd')										// char형 변수에 저장된 값이 'd'인지 비교 - 'c' / 'd'
			System.out.printf("#4. a는 d이다\n");
		
		// #5
		String k35_aa = "abcd";									// string형 변수에 "abcd"문자열 저장
		if (k35_aa.equals("abcd"))								// string형 변수에 저장된 값이 "abcd"인지 비교 - "abcd" / "abcd"
			System.out.printf("#5. aa는 abcd이다\n");
		else
			System.out.printf("#5. aa는 abcd이 아니다\n");
		
		// #6
		boolean k35_bb = true;									// boolean형의 변수에 true 저장
		if (!!k35_bb)											// boolean형의 변수에 저장된 값이 참인지 확인, !는 not을 의미, !!k35_bb는 k35_bb와 같다.
			System.out.printf("#6. bb가 아니고 아니면 참이다.\n");
		else
			System.out.printf("#6. bb가 아니고 아니면 거짓이다.\n");
	}
}
