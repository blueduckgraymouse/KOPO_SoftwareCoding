package ch09;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class P02_main_poll {

	public static void main(String[] args) {
		P02_main_poll p02 = new P02_main_poll();
		
		Queue<Integer> numberQueue = p02.inputNumbers();
		int targetNumber = p02.inputNumber();
		
		p02.findIndex(numberQueue, targetNumber);
	}

	private Queue<Integer> inputNumbers() {
		while (true) {
			Queue<Integer> numberQueue = new LinkedList<Integer>();
			Scanner sc = new Scanner(System.in);
			try {
				System.out.print("input a series of numbers : ");
				String input = sc.nextLine();
				String[] iArr = input.split(" ");
				for (String factor : iArr) {
					numberQueue.add(Integer.parseInt(factor));
				}
				return numberQueue;
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
	
	private void findIndex(Queue<Integer> numberQueue, int targetNumber) {
		int size = numberQueue.size();
		for(int i = 0 ; i < size ; i++) {				// 사이즈를 바로 넣으면 루프 돌면서 사이즈가 감소하므로 에러
			int number = numberQueue.poll();			// pull()이 호출될 때마다 값이 꺼내지므로 변수로 저장 후 비교
			if(number == targetNumber) {
				System.out.println("-> " + (i+1));
				return;
			}
		}
		System.out.println("nowhere");
	}

}
