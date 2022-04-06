package ch09;

import java.util.ArrayList;
import java.util.Collections;

public class Ex05 {

	public static void main(String[] args) {
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		
		for (int i = 0 ; i < 10 ; i++) {
			numbers.add((int)(Math.random() * 10));
		}
		
		Collections.sort(numbers);
		
		System.out.println(numbers);

		System.out.print("[");
		for (int i = 0 ; i < numbers.size() ; i++) {
			if (i != numbers.size() / 2 - 1 && i != numbers.size() / 2) {
				System.out.print(numbers.get(i));
			} else {
				System.out.print(" ");
			}
			
			if (i != numbers.size() - 1)
				System.out.print(", ");
		}
		System.out.print("]");
	}

}
