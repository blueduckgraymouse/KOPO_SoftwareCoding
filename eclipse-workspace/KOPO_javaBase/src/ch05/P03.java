package ch05;

import java.util.Scanner;

public class P03 {
	static final String[] fMember = {"Father", "Mother", "Son", "Daughter"};
	static final String[] fName = {"Alex", "Merry", "John", "Ann"};

	public static void main(String[] args) {
		P03 p03 = new P03();
		
		p03.menu(fMember);
		int fNum = p03.inputNum();
		p03.printName(fNum, fName);
	}
	
	
	
	void menu(String[] fMember) {
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
