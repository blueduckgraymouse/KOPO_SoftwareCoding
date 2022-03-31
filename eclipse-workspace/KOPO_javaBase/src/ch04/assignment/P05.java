package ch04.assignment;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P05 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("#conditions");
		System.out.println("1. The string length should be between 8 and 20");
		System.out.println("2. The string should contains special characters");
		System.out.println("3. The string should contains number characters");
		System.out.println("4  The string should contains lowercase alphabets");
		System.out.println("5. The string should contains uppercase alphabets");
		System.out.println("6. The string should not contains blank");
		
		while (true) {
			System.out.print("input password : ");
			String pwd = sc.nextLine();
			
			// process
			int d = 1;
			if (!(7 < pwd.length() && pwd.length() < 21)) {
				d *= 0;
				System.out.println("error - 1");
			}
			Pattern pt2 = Pattern.compile("[!-/:-@\\[-`{-~]");
			Matcher mc2 = pt2.matcher(pwd);
			if (!mc2.find()) {
				d *= 0;
				System.out.println("error - 2");
			}
				
			Pattern pt3 = Pattern.compile("[0-9]");
			Matcher mc3 = pt3.matcher(pwd);
			if (!mc3.find()) {
				d *= 0;
				System.out.println("error - 3");
			}
			Pattern pt4 = Pattern.compile("[a-z]");
			Matcher mc4 = pt4.matcher(pwd);
			if (!mc4.find()) {
				d *= 0;
				System.out.println("error - 4");
			}
			Pattern pt5 = Pattern.compile("[A-Z]");
			Matcher mc5 = pt5.matcher(pwd);
			if (!mc5.find()) {
				d *= 0;
				System.out.println("error - 5");
			}
			if (pwd.contains(" ")) {
				d *= 0;
				System.out.println("error - 6");
			}
			
			if (d == 1) 
				System.out.println("PASS");
			else 		
				System.out.println("FAIL");
		}
	}

}
