package ch09;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class P03_sub_iterator {

	public static void main(String[] args) {
		P03_sub_iterator p03 = new P03_sub_iterator();
		
		Stack<Integer> numberStack = p03.inputNumbers();
		int targetNumber = p03.inputNumber();
		
		p03.findIndex(numberStack, targetNumber);
	}

	private Stack<Integer> inputNumbers() {
		while (true) {
			Stack<Integer> numberStack = new Stack<Integer>();
			Scanner sc = new Scanner(System.in);
			try {
				System.out.print("input a series of numbers : ");
				String input = sc.nextLine();
				String[] iArr = input.split(" ");
				for (String factor : iArr) {
					numberStack.add(Integer.parseInt(factor));
				}
				return numberStack;
			} catch (Exception e) {
				System.out.println("wrong input.");
			}
		}
	}

	private int inputNumber() {
		while (true) {
			Scanner sc = new Scanner(System.in);
			try {
				System.out.print("input a number : ");
				int input = sc.nextInt();
				return input;
			} catch (Exception e) {
				System.out.println("wrong input.");
			}
		}
	}
	
	private void findIndex(Stack<Integer> numberStack, int targetNumber) {
		Iterator<Integer> iterator = numberStack.iterator();
		int count = numberStack.size();
		while (iterator.hasNext()) {
			int number = iterator.next();
			if (number == targetNumber) {
				break;
			}
			count--;
		}
		
		if(count != 0) {
			System.out.println(count);
		} else {
			System.out.println("nowhere");
		}
	}

}
