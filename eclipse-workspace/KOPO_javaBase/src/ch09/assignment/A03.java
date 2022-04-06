package ch09.assignment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class A03 {

	public static void main(String[] args) {
		A03 a03 = new A03();
		List<Integer> list = new ArrayList<Integer>();
		while (true) {
			list = a03.inputNumber(list);
			
			printStatistics(list);
		}
	}

	private List<Integer> inputNumber(List<Integer> list) {
		while (true) {
			Scanner sc = new Scanner(System.in);
			try {
				System.out.print("input a number : ");
				int number = sc.nextInt();
				list.add(number);
				return list;
			} catch (Exception e) {
				System.out.println("wrong input.");
			}
		}
	}

	private static void printStatistics(List<Integer> list) {
		Iterator<Integer> iter = list.iterator();
		int sum = 0;
		int max = 0;
		int min = 0;
		int count = 0;
		
		if (iter.hasNext()) {
			int firstNumber = (int)iter.next();
			sum = firstNumber; 
			max = firstNumber;
			min = firstNumber;
			count = 1;
		}
		while (iter.hasNext()) {
			int currentNumber = (int)iter.next();
			sum = sum + currentNumber;
			if (max < currentNumber) {
				max = currentNumber;
			} else if (min > currentNumber) {
				min = currentNumber;
			}
			count++;
		}
		
		System.out.println("Result : " + count + " - Mean " + ((double)sum / count) + ", Max " + max + ", Min " + min);
	}

}
