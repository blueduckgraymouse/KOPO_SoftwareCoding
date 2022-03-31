package ch02;

/*
 * eqaulsIgnoreCase()
 * 		compare regardless of upper case or lower case
 * 
 * toUpperCase()
 * 		convert all character to upper case
 * 
 * toLowerCase()
 * 		convert all character to lower case
 */


import java.util.Scanner;

public class P10 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String text = sc.nextLine();
		
		//if(text.toLowerCase().contains("CAR"))
		//if(text.toUpperCase().equals("car"))
		if(text.equalsIgnoreCase(text))
			System.out.println("car is included in the input strings.");
		else
			System.out.println("car is not included in the input stirng.");
		
		System.out.println(text);
	}

}
