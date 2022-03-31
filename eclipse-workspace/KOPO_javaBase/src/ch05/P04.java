package ch05;

/*
 * 입력 예외
 * 숫자랑 연산자가 아닌 값이 포함.
 * 숫자 2개 연산자 1개 초과 일 경우
 */

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P04 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			// input
			System.out.print("input : ");
			String formula = sc.nextLine();
			if (formula.equals("exit"))
				break;
			
			// validation
			String[] nums = formula.split("[/+/*//-]");
			if (nums.length != 2 || nums[0].matches("^[0-9]*$")) {	// count of number are not 2 & Exception Handling like'+ 123  123'
				System.out.println("Wrong input - count of number are not 2  OR first value is a operation");
				continue;
			}
			Pattern pt = Pattern.compile("[/+/*//-]");
			Matcher mc = pt.matcher(formula);
			if (mc.find() == true && mc.find() != false) {	// count of operator exceed 1
				System.out.println("Wrong input -  count of operator exceed 1");
				continue;
			}

			// preprocess
			double num1 = Double.parseDouble(nums[0].replaceAll(" ", ""));
			double num2 = Double.parseDouble(nums[1].replaceAll(" ", ""));
			
			// calculate and print
			if (formula.contains("+"))
				addition(num1, num2);
			else if (formula.contains("-"))
				subtraction(num1, num2);
			else if (formula.contains("*"))
				multiplication(num1, num2);
			else if (formula.contains("/")) {
				if (Double.parseDouble(nums[1]) != 0)
					division(num1, num2);
				else
					System.out.println("error - divide by 0");
			}
		}
	}
	
	public static void addition(double num1, double num2) {
		System.out.println(num1 + num2);
	}
	
	public static void subtraction(double num1, double num2) {
		System.out.println(num1 - num2);
	}
	
	public static void multiplication(double num1, double num2) {
		System.out.println(num1 * num2);
	}
	
	public static void division(double num1, double num2) {
		System.out.println(num1 / num2);
	}
	
}
