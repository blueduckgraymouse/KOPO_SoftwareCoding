package k35_ch04;

/** 소프트웨어 코딩 심화 4강 - p26 실습
 * 
 *  sin함수 그래프
 * 
 * @author KOPO35
 *
 */
public class K35_ex13 {
	public static void main(String[] args) {
		double k35_fSin;												// sin(0) ~ sin(359)까지의 함수값이 저장되어 출력에 사용될 변수
		
		for (int k35_i = 0 ; k35_i < 360 ; k35_i++) {					// 0~359까지 i 반복
			k35_fSin = Math.sin(k35_i * 3.141592 / 180);				// Math.sin함수의 파라미터로 들어가는 값의 단위는 라디안이다.
																		// 우리는 도를 단위로 제어할 것이므로 변환과정이 필요하다.
																		// 360(도) : 2(pi) = 1(도) : 1(라디안) ==> 1(라디안) = 180(도) / 1(pi)
			System.out.printf("%d sin ==> %f\n", k35_i, k35_fSin);		// 0~359도에 해당하는 sin()값을 출력 : 0~360도 이므로 sin값의 추이는 0 ~ -1 ~ 0 ~ 1
		}
		
		for (int k35_i = 0 ; k35_i < 360 ; k35_i++) {					// 0~359까지 i 반복
			k35_fSin = Math.sin(k35_i * 3.141592 / 180);				// i도에서의 라디안을 계산 한 후 그 값을 Math.sin함수에 대입하여 해당 degree에서의 sin값을 변수 fSin에 저장
			int k35_iSpace = (int)((1.0 - k35_fSin) * 50);				// fSin의 값은 -1 ~ 1이다. 이걸 콘솔에 그래프와 같이 그려주기 위해서 (1 - sin값)으로 계산하여 0 ~ 2로 범위를 변경 후
																		//   50을 곱하여 sin()값 -1 ~ 1에 대칭되는 0~100의 값이 k35_iSpace에 저장
			for (int k35_k = 0 ; k35_k < k35_iSpace ; k35_k++)			// k35_iSpace의 값 만큼 공백을 출력하여 콘솔에 세로가 x축에 해당하는 것처럼 그래프를 그려준다.
				System.out.print(" ");
			
			System.out.printf("*[%f][%d]\n", k35_fSin, k35_iSpace);		// 해당 sin()값과 출력한 공백의 개수 출력
		}
	}
}
