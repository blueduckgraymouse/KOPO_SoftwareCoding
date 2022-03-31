package ch05;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P06 {

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
			if (nums.length != 2 || !nums[0].matches("^[0-9]*$")) {	// count of number are not 2 & Exception Handling like'+ 123  123'
				System.out.println("Wrong input.");
				continue;
			}
			Pattern pt = Pattern.compile("[/+/*//-]");
			Matcher mc = pt.matcher(formula);
			if (mc.find() == true && mc.find() != false) {	// count of operator exceed 1
				System.out.println("Wrong input.");
				continue;
			}
			
			// preprocess
			double num1 = Double.parseDouble(nums[0].replaceAll(" ", ""));
			double num2 = Double.parseDouble(nums[1].replaceAll(" ", ""));
			char operator = getOperator(formula);
			
			// calculate
			double result = 0;
			switch (operator) {
			case '+':
				result = addition(num1, num2);
				break;
			case '-':
				result = subtraction(num1, num2);
				break;
			case '*':
				result = multiplication(num1, num2);
				break;
			case '/':
				if (Double.parseDouble(nums[1]) != 0)
					result = division(num1, num2);
				else {
					System.out.println("error - divide by 0");
					continue;
				}
			}
			
			// print
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
	
	public static char getOperator(String formula) {
		String[] operators = {"+", "-", "*", "/"};
		char operator = ' ';
		for(int i = 0 ; i < operators.length ; i++) {
			if(formula.contains(operators[i]))
				operator = operators[i].charAt(0);
		}
		return operator;
	}
}
