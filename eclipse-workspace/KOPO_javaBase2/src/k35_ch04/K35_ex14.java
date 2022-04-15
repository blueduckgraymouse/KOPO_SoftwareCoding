package k35_ch04;

/** 소프트웨어 코딩 심화 4강 - p27 실습
 * 
 *  피라미드 찍기
 * 
 * @author KOPO35
 *
 */
public class K35_ex14 {
	
	public static void main(String[] args) {
		int k35_m = 20;										// 처음 루프가 시작될 때 공백의 길이를 20으로 설정
		int k35_n = 1;										// 처음 루프가 시작될 때 *의 길이를 1로 설정
		
		while (true) {										// 무한 반복 - 한 줄에 대한 루프
			for (int k35_i = 0 ; k35_i < k35_m ; k35_i++)	//   m 만큼 반복 - 루프를 돌면서 공백을 줄여간다.
				System.out.printf(" ");						//     공백 출력
			for (int k35_i = 0 ; k35_i < k35_n ; k35_i++)	//   n 만큼 반복 - 루프를 돌면서 * 출력을 +2 늘려간다.
				System.out.printf("*");						//     * 출력
			System.out.printf("\n");						//   한 줄에 대한 출력이 끝났으면 줄바굼 출력
			
			k35_m = k35_m - 1;								//   다읆 루프를 위해서 출력할 공백 길이를 나타내는 m -1 감소
			k35_n = k35_n + 2;								//	 다읆 루프를 위해서 출력할 * 길이를 나타내는 n +2 증가
			
			if(k35_m < 0)									//   공백을 나타내는 m이 음수까지 감소하면
				break;										// 	   반복문 종료 
		}
	}
	
}
