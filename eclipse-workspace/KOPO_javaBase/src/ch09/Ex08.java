package ch09;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Ex08 {

	public static void main(String[] args) {
		ArrayList<String> stringList = new ArrayList<String>();
		
		Scanner sc = new Scanner(System.in);
		for (int i = 0 ; i < 5 ; i++) {
			System.out.println("input String " + (i + 1) + " : ");
			String input = sc.next();
			stringList.add(input);
		}
		
		Collections.sort(stringList);
		
		System.out.println(stringList);
	}

}
