package ch02;

import java.util.Scanner;

/*
 * object.equals("string")
 *  => compare a content of object and parameter string.
 *     perfect same, return true
 *     else,		 return false
 *     
 * object.contains("string")
 * => check the contents of object
 * 	  whether or not it contains the parameter string.
 *    contain, return true
 *    else	   return false	
 *      
 */

public class Ex12 {

	public static void main(String[] args) {
//		String str1 = "Study";
//		String part1 = "tud";
//		String lec1 = "javaClass";
//		String part2 = "Cla";
//		
//		System.out.println(str1.equals(lec1));
//		System.out.println(str1.equals(part1));
//		System.out.println(str1.contains(part1));
//		
		
		Scanner sc = new Scanner(System.in);
		
		String input1 = sc.next();
		
		if(input1.equals("hello")) {
			System.out.println("same");
		} else {
			System.out.println("not same");
		}
		
		String input2 = sc.next();
		
		if(input2.contains("hello")) {
			System.out.println("contains");
		} else {
			System.out.println("not contains");
		}
	}

}
