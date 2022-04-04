package ch07;

import java.util.Scanner;

public class P06 {

	public static void main(String[] args) {
		P06 p06 = new P06();
		
		String inputString = p06.getString();
		String inputWord = p06.getWord();

		int result = p06.countWord(inputString, inputWord);
		
		System.out.println(result);
	}

	private String getString() {
		System.out.print("input a String : ");
		Scanner sc = new Scanner(System.in);
		String inputString = sc.nextLine();
		return inputString;
	}
	
	private String getWord() {
		System.out.print("input a word : ");
		Scanner sc = new Scanner(System.in);
		String inputWord = sc.nextLine();
		return inputWord;
	}
	
	private int countWord(String inputString, String inputWord) {
		int count = 0;
		while(inputString.contains(inputWord)) {
			inputString = inputString.replaceFirst(inputWord, "");
			count++;
		}
		return count;
	}


	
	

}
