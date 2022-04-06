package ch09;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class P02_sub_iterator {

	public static void main(String[] args) {
		P02_sub_iterator p02 = new P02_sub_iterator();
		
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
		Iterator<Integer> iterator = numberQueue.iterator();
		int count = 1;
		while (iterator.hasNext()) {
			int number = iterator.next();
			if (number == targetNumber) {
				break;
			}
			count++;
		}
		
		if(count != numberQueue.size() + 1) {
			System.out.println(count);
		} else {
			System.out.println("nowhere");
		}
	}

}
