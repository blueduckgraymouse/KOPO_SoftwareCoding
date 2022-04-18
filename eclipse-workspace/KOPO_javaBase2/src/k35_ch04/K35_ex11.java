package k35_ch04;

/** 소프트웨어 코딩 심화 4강 - p23 실습
 * 
 *  숫자 한글로 읽기 고급
 * 
 * @author KOPO35
 *
 */
public class K35_ex11 {
	public static void main(String[] args) {
		// 변수 선언
		int k35_iNumVal = 1001034567;												// 한글로 읽을 숫자를 int형 변수 iNumVal에 초기화
		String k35_sNumVal = String.valueOf(k35_iNumVal);							// iNumVal에 저장된 한글로 읽을 숫자를 String형 변수 sNumVal에 초기화
		String k35_sNumVoice = "";													// 한글로 읽은 문자열이 저장될 String형 변수 sNumVoice 선언
		String[] k35_units = {"영", "일", "이", "삼", "사", "오", "육", "칠", "팔", "구"};	// 0~9까지 숫자 한글을 배열 units으로 초기화
		String[] k35_unitX = {"", "십", "백", "천", "만", "십", "백", "천", "억", "십"};	// 자리 수에 따른 한글 표현을 배열 unitX에 초기화
		int k35_i = 0;																// 한글로 왼쪽 숫자부터 읽을 때 현재 읽고 있는 자리수를 나타내는 변수 i를 0으로 초기화 
		int k35_j = k35_sNumVal.length() - 1;										// 위의 i자리에서 현재 자리의 숫자가 나타내는 단위를 확인하기 위해서 숫자의 총 길이를 변수 j에 초기화
		
		// 한글로 읽어야 하는 숫자와 총 자리수 출력
		System.out.printf("==> %s [%d자리]\n", k35_sNumVal, k35_sNumVal.length());
		
		// 왼쪽 자리수부터 오른쪽방향으로 한글로 읽어 sNumVoice에 저장하는 반복문
		while(true) {																// 무한 반복
			if (k35_i >= k35_sNumVal.length())										// 현재까지 읽은 자리 수의 개수가 읽어야 하는 숫자의 총 자리수 개수와 같거나 커지면 
				break;																//   다 읽은 것이므로 반복문 종료
			
			String k35_digit = k35_sNumVal.substring(k35_i, k35_i + 1);				// 문자열로 저장된 읽어야 하는 숫자로 부터 지금 loop에서 읽어야 하는 자리의 숫자 한 개 추출하여 변수 digit에 저장
			
			System.out.printf("%s[%s]",												// 지금 loop에서 읽어야 하는 자리의 숫자 한 개(digit)의 숫자 값과 한글 값 출력
					k35_digit,
					k35_units[Integer.parseInt(k35_digit)]);
			
			if (k35_digit.equals("0")) {											// 지금 loop에서 읽어야 하는 자리의 숫자 한 개(digit)가 0이면
				if (k35_unitX[k35_j].equals("만") || k35_unitX[k35_j].equals("억")) {	// 	 한글에서 굳이 소리 내지 않아도 되지만, '만'의 자리나 '억'의 자리 일 경우 0이어도 이전에 출력된 일십(만)~구백(만) 혹은 일십(억)~구백(억)을 위해서 읽어줘야 한다.
																					// 	 !!! 여기에는 오류가 존재한다. 위 방법을 적용해서 1,000,000,000를 읽으면 십억을 읽으면 십업만원이 된다. 1만~999만에 해당하는 값이 없어도 무조건 만을 붙여주기 때문이다.
					k35_sNumVoice = k35_sNumVoice + "" + k35_unitX[k35_j];			//   이전 자리수까지 한글로 읽어서 저장된 문자열에 이어서 현재 자리의 숫자한글 저장
				}
			} else {																// 지금 loop에서 읽어야 하는 자리의 숫자 한 개(digit)가 0이 아니면
				k35_sNumVoice = k35_sNumVoice + k35_units[Integer.parseInt(k35_digit)] + k35_unitX[k35_j];	// 이전 자리수까지 한글로 읽어서 저장된 문자열에  이어서 현재 자리의 숫자한글 저장
			}
			k35_i++;																// i를 증가시켜 다음 루프에서 현재 자리수의 하나 오른쪽 자리를 읽도록 설정 
			k35_j--;																// j를 감소시켜 다음 루프에서 읽는 자리수의 단위를 감소시킴
		}
		
		// 한글로 읽은 숫자 문자열 출력
		System.out.printf("\n %s[%s]\n", k35_sNumVal, k35_sNumVoice);
	}
}
