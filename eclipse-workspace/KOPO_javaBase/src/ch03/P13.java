package ch03;

import java.util.Scanner;

public class P13 {
	public static void main(String args[]) {
		int sAlphabet[] = new int[26]; 		// alphabet count of sentence from a to z
		int wAlphabet[] = new int[26];		// alphabet count of word from a to z
		
		Scanner sc = new Scanner(System.in);
		
		// input
		System.out.println("input a sentence : ");
		//String sentence = sc.nextLine();
		String sentence = "I go to school by subway but it takes a very long  time to go to school";
		System.out.println("input a word : ");
		//String word = sc.nextLine();
		String word = "cool";
		
		// convert all alphabets to lower case
		sentence = sentence.toLowerCase();
		word = word.toLowerCase();

		// count alphabets of the sentence
		for(int i = 0 ; i < sentence.length() ; i++) {
			char ch = sentence.charAt(i);
			if('a' <= ch && ch <= 'z')
				sAlphabet[sentence.charAt(i) - 'a']++;
		}
		
		// count alphabets of the word
		for(int i = 0 ; i < word.length() ; i++) {
			char ch = word.charAt(i);
			if('a' <= ch && ch <= 'z')
				wAlphabet[word.charAt(i) - 'a']++;
		}
		
		// count times to subtract count of wAlphabet from count of sAlphabet
		int count = 0;
		while(true) {
			for(int i = 0 ; i < 26 ; i++) {
				sAlphabet[i] -= wAlphabet[i];
			}
			int d = 1;
			for(int i = 0 ; i < 26 ; i++) {
				if(sAlphabet[i] < 0) d = 0;
			}
			if(d == 1) count++;
			else	   break;
		}
		
		// output
		System.out.println(count);
	}
}
