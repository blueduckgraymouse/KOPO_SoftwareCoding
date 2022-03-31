package ch04.assignment;

import java.util.Scanner;

/*
 * 암호화 알고리즘
 * 	String 입력 암호화할 내용
 *  int 입력 nkey - 암호화 복호화에 사용하는 데이터 0 ~ 5

 * => 입력 string의 각 알파벳의 아스키 코드는 33 ~ 126 , range : 93
 * 		-33 + nkey			=> (min) 0 ~ 93 / (max) 5 ~ 98 
 * 	
 * => 9로 나눈 몫과 나머지로 9진수의 2bit로 변환.
 */

public class P04 {

	public static void main(String[] args) {
		while (true) {
			Scanner sc = new Scanner(System.in);
			
			System.out.println("#Menu");
			System.out.println("1. Encrpytion");
			System.out.println("2. Decrpytion");
			System.out.println("3. Exit");
			
			// input
			System.out.print("input menu : ");
			int menu = sc.nextInt();
			if (menu == 3)
				return;
			
			sc.nextLine();	
			
			System.out.print("input String : ");
			String str = sc.nextLine();
			
			int nKey = 9;
			while (true) {
				System.out.print("input key number (0~5) : ");
				nKey = sc.nextInt();
				if (nKey >= 0 && nKey <= 5) 
					break;
				else {
					System.out.println("wrong input. input only number bewteen 0 and 5.");
				}
			}
			
			// process
			if (menu == 1) {	// encryption
				char[] cArr = str.toCharArray();
				String result = "";
				for (int i = 0 ; i < cArr.length ; i++) {
					// step1 : -33 + nKey(0~5)				=> (min) 0 ~ 93 / (max) 5 ~ 98 
					int nStep1 = cArr[i] - 33 + nKey;
					// step2 - convert to 9 number
					int n1Step2 = nStep1 / 9; 		// share
					int n2Step2 = nStep1 % 9;		// remainder
					result = result + n1Step2 + n2Step2;
				}
				System.out.println(" => " + result);
			} else {		// decryption
				System.out.print(" => ");
				for (int i = 0 ; i < str.length() / 2 ; i++) {
					// step2 : +33 - nKey(0~5)
					String sStep2 = str.substring(i * 2, i * 2 + 2);
					int nStep2 = Integer.parseInt(sStep2);
					// step1 - convert to decimal number
					int nStep1 = (nStep2 / 10) * 9 + (nStep2 % 10) + 33 - nKey;
					// convert ASCII and print
					System.out.print((char)nStep1);
				}
				System.out.println();
			}
			
			System.out.println();
		}
	}

}
