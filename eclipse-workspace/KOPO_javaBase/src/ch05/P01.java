package ch05;

import java.util.Scanner;

public class P01 {

	public static void main(String[] args) {
		P01 p01 = new P01();
		p01.menu();
		int fNum = p01.inputNum();
		p01.printName(fNum);
	}
	
	void menu() {
		System.out.println("#Printing a Name");
		System.out.println("1. Father");
		System.out.println("2. Mother");
		System.out.println("3. Son");
		System.out.println("4. Daughter");
	}
	
	int inputNum() {
		Scanner sc = new Scanner(System.in);
		System.out.print("-> ");
		int fNum = sc.nextInt();
		return fNum;
	}
	
	void printName(int fNum) {
		String[] name = {"Alex", "Merry", "John", "Ann"};
		System.out.println(name[fNum-1]);
	}

}
