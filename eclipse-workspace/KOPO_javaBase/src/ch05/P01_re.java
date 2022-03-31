package ch05;

import java.util.Scanner;

public class P01_re {

	public static void main(String[] args) {
		String[] fMember = {"Father", "Mother", "Son", "Daughter"};
		String[] fName = {"Alex", "Merry", "John", "Ann"};
		
		P01_re p01 = new P01_re();
		
		p01.notice(fMember);
		int fNum = p01.inputNum();
		p01.printName(fNum, fName);
	}
	
	
	
	void notice(String[] fMember) {
		System.out.println("#Printing a Name");
		for (int i = 0 ; i < fMember.length ; i++) {
			System.out.println((i+1) + ". " + fMember[i]);
		}
	}
	
	int inputNum() {
		Scanner sc = new Scanner(System.in);
		System.out.print("-> ");
		int fNum = sc.nextInt();
		return fNum;
	}
	
	void printName(int fNum, String[] fName) {
		
		System.out.println(fName[fNum-1]);
	}

}
