package ch01;

import java.util.Scanner;

/* scanner를 이용한 입력 */

public class Ex03 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String line = sc.nextLine();
		System.out.println(line);
		
		sc.close();
	}
}
