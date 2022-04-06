package ch09.assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class A01 {

	public static void main(String[] args) {
		A01 a01 = new A01();
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
			numbers = fibonacciSeqFrom2(numbers, inputNum, 2);
		}
		
		return numbers;
	}

	private List<Integer> fibonacciSeqFrom2(List<Integer> numbers, int fNum, int count) {
		if (fNum != 1) {
			System.out.println(numbers.get(count - 1));
			System.out.println(numbers.get(count - 2));
			int newNumber = numbers.get(count - 1) + numbers.get(count - 2);
			System.out.println(newNumber);
			numbers.add(newNumber);
			fibonacciSeqFrom2(numbers, --fNum , count++);
		}
		return numbers;
	}

}
