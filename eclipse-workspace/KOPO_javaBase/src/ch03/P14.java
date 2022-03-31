package ch03;

import java.util.Scanner;

public class P14 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// input
		System.out.println("input a sentence : ");
		String str = sc.nextLine();
		
		// process, 
		String sArr[] = str.split(" ");
		for(int i = 0 ; i < sArr.length ; i++) {
			// a -> an인 경우
			if(sArr[i].equals("a") && i != sArr.length-1) {
				if(sArr[i+1].charAt(0) == 'a' || sArr[i+1].charAt(0) == 'e' || 
						sArr[i+1].charAt(0) == 'i' || sArr[i+1].charAt(0) == 'o'
						|| sArr[i].charAt(0) == 'u') {
					sArr[i] = "an";
				}
			}
			// an -> a인 경우
			if(sArr[i].equals("an") && i != sArr.length-1) {
				if(sArr[i+1].charAt(0) != 'a' && sArr[i+1].charAt(0) != 'e' && 
						sArr[i+1].charAt(0) != 'i' && sArr[i+1].charAt(0) != 'o'
						&& sArr[i].charAt(0) != 'u') {
					sArr[i] = "a";
				}
			}
		}
		
		// output
		for(String s : sArr) {
			System.out.print(s + " ");
		}
	}

}
