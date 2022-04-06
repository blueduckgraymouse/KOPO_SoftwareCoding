package ch09.assignment;

import java.util.ArrayList;
import java.util.Scanner;

public class A02 {

	public static void main(String[] args) {
		A02 a02 = new A02();
		ArrayList<String> list = new ArrayList<String>();
		
		list = a02.inputStrings(list);
		
		a02.compareStrings(list);
	}

	private ArrayList<String> inputStrings(ArrayList<String> list) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("input String1 : ");
		list.add(sc.next());
		
		System.out.print("input String2 : ");
		list.add(sc.next());
		
		return list;
	}

	private void compareStrings(ArrayList<String> list) {
		String str1 = list.get(0);
		System.out.println("1 : " + str1.length());
		
		String str2 = list.get(1);
		System.out.println("2 : " + str2.length());
		
		int count = 0;
		for (int i = 0 ; i < str1.length() ; i++) {
			if (str2.indexOf(str1.substring(i, i + 1)) != -1) {
				String target = str1.substring(i, i + 1);
				str2 = str2.replaceFirst(target, " ");
				count++;
			}
		}	
		System.out.println("3 : " + count);
	}

}
