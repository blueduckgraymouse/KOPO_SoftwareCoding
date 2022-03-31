package ch05;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P04_returnDouble {

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
				System.out.println("Wrong input - count of number are not 2");
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
			double result = 0;
			if (formula.contains("+"))
				result = addition(num1, num2);
			else if (formula.contains("-"))
				result = subtraction(num1, num2);
			else if (formula.contains("*"))
				result = multiplication(num1, num2);
			else if (formula.contains("/")) {
				if (Double.parseDouble(nums[1]) != 0)
					result = division(num1, num2);
				else {
					System.out.println("error - divide by 0");
					continue;
				}
			}
			System.out.println(result);
		}
	}
	
	public static double addition(double num1, double num2) {
		return num1 + num2;
	}
	
	public static double subtraction(double num1, double num2) {
		return num1 - num2;
	}
	
	public static double multiplication(double num1, double num2) {
		return num1 * num2;
	}
	
	public static double division(double num1, double num2) {
		return num1 / num2;
	}
	
}
