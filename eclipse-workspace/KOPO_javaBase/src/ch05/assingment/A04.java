package ch05.assingment;

import java.util.Scanner;

public class A04 {

	public static void main(String[] args) {
		A04 a04 = new A04();
		
		int inputNum = a04.input();
		a04.printFibonacciSeq(inputNum);
	}

	/* input integer number of Fibonacci Sequence
	 * 
	 * return : a number of Fibonacci Sequence
	 */
	public int input() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("input number : ");
		return sc.nextInt();
	}
	
	/* print result as a number of input
	 * 
	 * parameter : a number of input
	 */
	private void printFibonacciSeq(int inputNum) {
		if (inputNum == 0)
			System.out.print("0");
		else if (inputNum > 0)
			System.out.print("0 1");
		
		if (inputNum > 1) {
			fibonacciSeqFrom2(1, 0, inputNum);
		}
	}
	
	/* a recursive function to print Fibonacci Sequence over 2
	 * 
	 * parameter : (n-1)th Fibonacci Sequence value, (n-2)th Fibonacci value, remaining number of times
	 * return : n-th Fibonacci Sequence value
	 */
	private int fibonacciSeqFrom2(int fBefore1Step, int fBefore2step, int fNum) {
		if (fNum != 1) {
			System.out.print(" " + (fBefore1Step + fBefore2step));
			int temp = fibonacciSeqFrom2(fBefore1Step + fBefore2step, fBefore1Step ,--fNum);
		}
		return fBefore1Step + fBefore2step;
	}
}
