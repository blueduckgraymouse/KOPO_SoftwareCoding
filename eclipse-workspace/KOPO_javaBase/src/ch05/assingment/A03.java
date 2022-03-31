package ch05.assingment;

import java.util.Scanner;

public class A03 {

	public static void main(String[] args) {
		A03 a03 = new A03();
		
		int inputNum = a03.input();
		a03.printMultiTable(inputNum, 2);
	}

	/* input integer number of multiplication table
	 * 
	 * return : a number of multiplication table
	 */
	public int input() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("input number : ");
		return sc.nextInt();
	}

	/* recursive function to print multiplication
	 * 
	 * parameter : a number of input, a number of start line
	 */
	private void printMultiTable(int inputNum, int count) {
		System.out.println(inputNum + " X " + count + " = " + (inputNum * count));
		if (count != 9)
			printMultiTable(inputNum, ++count);
	
	}
}
