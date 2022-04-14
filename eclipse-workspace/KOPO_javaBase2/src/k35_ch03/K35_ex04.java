package k35_ch03;
/**
 * 소프트웨어코딩_3강 - P9 실습 코드 실행해보기
 * 
 * #4 원하는 자릿수 반올림, 버림 처리
 * 
 * @author KOPO35
 */

public class K35_ex04 {

	public static void main(String[] args) {
		int k35_ii;
		
		// 1의 자리 버림
		k35_ii = 12345;
		int k35_j = (k35_ii / 10) * 10;				// ii의 1의 자리를 10으로 나눠 없앤 후 10을 곱해 버림 처리
		System.out.printf("#4-1 : %d\n", k35_j);
		
		// 1의자리 반올림의 버림 확인
		k35_ii = 12345;
		k35_j = ((k35_ii + 5) / 10) * 10;			// ii에 5를 더한 후 1의 자리를 10으로 나눠 없앤 후 10을 곱해 반올림 처리
		System.out.printf("#4-2 : %d\n", k35_j);
		
		// 1의 자리 반올림의 올림 확인
		k35_ii = 12344;
		k35_j = ((k35_ii + 5) / 10) * 10;			// ii에 5를 더한 후 1의 자리를 10으로 나눠 없앤 후 10을 곱해 반올림 처리
		System.out.printf("#4-2 : %d\n", k35_j);
		
	}

}
