package ch09;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Ex10 {

	public static void main(String[] args) {
		Queue<Integer> multi3Number = new LinkedList<Integer>();
		
		while (true) {
			int randomNumber = (int)(Math.random() * 30) + 1;	// 1 ~ 30
			if (randomNumber % 2 != 0) {
				multi3Number.offer(randomNumber);
				System.out.print(randomNumber + " ");
			}
			if (multi3Number.size() == 10) {
				break;
			}
		}

		System.out.println("\n--------------------");
		
		Iterator<Integer> iterator = multi3Number.iterator();
		while (iterator.hasNext()) {
			int number = iterator.next();
			if ((number % 3) == 0) {
				System.out.print(number + " ");
			}
		}
	}

}
