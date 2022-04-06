package ch09;

import java.util.Scanner;
import java.util.Stack;

public class P03_sub_elementAt {

	public static void main(String[] args) {
		P03_sub_elementAt p03 = new P03_sub_elementAt();
		
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
		int size = numberStack.size();
		for(int i = 0 ; i < size ; i++) {
			System.out.println(numberStack.elementAt(i));
			if(numberStack.elementAt(i) == targetNumber) {
				System.out.println(numberStack.size() - i);
				return;
			}
		}
		System.out.println("nowhere");
	}

}
