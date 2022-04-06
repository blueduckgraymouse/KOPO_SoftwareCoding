package ch09;

import java.util.Scanner;
import java.util.Stack;

public class P03_main_pop {

	public static void main(String[] args) {
		P03_main_pop p03 = new P03_main_pop();
		
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
		for(int i = 0 ; i < size ; i++) {		// 사이즈를 바로 넣으면 루프 돌면서 사이즈가 감소하므로 에러
			int number = numberStack.pop();		// pop()이 호출될 때마다 값이 꺼내지므로 변수로 저장 후 비교
			if(number == targetNumber) {
				System.out.println("-> " + (i+1));
				return;
			}
		}
		System.out.println("nowhere");
	}

}
