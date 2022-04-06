package ch09;

import java.util.ArrayList;
import java.util.Collections;

public class Ex03 {

	public static void main(String[] args) {
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		
		for(int i = 0 ; i < 10 ; i++) {
			numbers.add((int)(Math.random() * 10));
		}
		System.out.println(numbers);
		
		Collections.sort(numbers);
		System.out.println(numbers);
		
		Collections.reverse(numbers);
		System.out.println(numbers);
		
	}

}
