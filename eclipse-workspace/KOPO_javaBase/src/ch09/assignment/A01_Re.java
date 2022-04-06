package ch09.assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class A01_Re {

	public static void main(String[] args) {
		A01_Re a01 = new A01_Re();
		List<Integer> numbers = new ArrayList<Integer>();
		
		int inputNum = a01.input();
		numbers = a01.fibonacciSeq(numbers, inputNum);
		
		System.out.println(numbers);
	}

	public int input() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("input number : ");
		return sc.nextInt();
	}

	private List<Integer> fibonacciSeq(List<Integer> numbers, int inputNum) {
		if (inputNum == 0) {
			numbers.add(0);
		} else if (inputNum > 0) {
			numbers.add(0);
			numbers.add(1);
		}
		
		if (inputNum > 1) {
			numbers = fibonacciSeqFrom2(numbers, 1, 0, inputNum);
		}
		
		return numbers;
	}

	private List<Integer> fibonacciSeqFrom2(List<Integer> numbers, int fBefore1Step, int fBefore2step, int fNum) {
		if (fNum != 1) {
			numbers.add(fBefore1Step + fBefore2step);
			fibonacciSeqFrom2(numbers, fBefore1Step + fBefore2step, fBefore1Step ,--fNum);
		}
		return numbers;
	}

}
