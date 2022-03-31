package ch02;

import java.util.Scanner;

public class P09 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String text = sc.nextLine();
		
		if(text.contains("car"))
			System.out.println("car is included in the input strings.");
		else
			System.out.println("car is not included in the input stirng.");
		
		System.out.println(text);
	}

}
