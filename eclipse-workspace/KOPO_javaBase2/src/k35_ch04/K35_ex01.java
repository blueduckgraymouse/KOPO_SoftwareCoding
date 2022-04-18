package k35_ch04;

/** 소프트웨어 코딩 심화 4강 - p7 실습
 * 
 *  for문
 * 
 * @author KOPO35
 *
 */
public class K35_ex01 {
	public static void main(String[] args) {
		
		int k35_sum = 0;						// 누적값이 저장될 변수 sum을 0으로 초기화
		
		int[] a = new int[3];
		a[0] = 1;
		a[1] = 2;
		a[2] = 3;
		
		System.out.println(a[0]);
		
		for (int i = 0 ; i < 10 ; i++) {		// i를 0~9까지 반복
			k35_sum = k35_sum + i;				// 변수 sum에 i를 0~9까지 누적
		}
		
		System.out.printf("sum %d\n", k35_sum);
		
		for (int k35_i = 1 ; k35_i < 10 ; k35_i++) {		// i를 1~9까지 반복 -> 1~9단 반복
			System.out.printf("******************\n");	
			System.out.printf("    구구단 %d단 \n", k35_i);	// 몇 단의 구구단을 출력한건지 명시
			System.out.printf("******************\n");
			
			for (int k35_j = 1 ; k35_j < 10 ; k35_j++) {								// j를 1~9까지 반복 -> i단에 해당하는 구구단의 *1 ~ *9까지
				System.out.printf("%d * %d = %d \n", k35_i , k35_j , k35_i * k35_j);	// i 와 j를 출력하고 i * j를 계산하여 출력
			}
		}
	}
}
