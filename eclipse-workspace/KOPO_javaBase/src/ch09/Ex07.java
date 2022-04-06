package ch09;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class Ex07 {

	public static void main(String[] args) {
		HashSet<String> stringSet = new HashSet<String>();
		
		Scanner sc = new Scanner(System.in);
		for (int i = 0 ; i < 5 ; i++) {
			System.out.println("input String " + (i + 1) + " : ");
			String input = sc.next();
			stringSet.add(input);
		}

		ArrayList<String> stringList = new ArrayList<String>(stringSet);
		Collections.sort(stringList);
		
		System.out.println(stringList);
	}

}
