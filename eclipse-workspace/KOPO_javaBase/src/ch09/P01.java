package ch09;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class P01 {

	public static void main(String[] args) {
		P01 p01 = new P01();
		
		String input = p01.inputString();
		
		ArrayList<String> splitedString = p01.splitString(input);
		
		p01.print(splitedString);
		
	}

	private String inputString() {
		System.out.print("input a string : ");
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
	}

	private ArrayList<String> splitString(String input) {
		ArrayList<String> stringList = new ArrayList<String>();
		String[] splitString = input.split(" ");
		for(String word : splitString) {
			stringList.add(word);
		}
		return stringList;
	}

	private void print(ArrayList<String> splitedString) {
		Collections.sort(splitedString);
		System.out.println(splitedString);
//		Collections.reverse(splitedString);
//		System.out.println(splitedString);
	}
	
}
