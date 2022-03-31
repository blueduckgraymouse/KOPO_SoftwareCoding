package ch05;

import java.util.Scanner;

public class P04_no_condition {

	public static void main(String[] args) {
		P04_no_condition p04 = new P04_no_condition();
		String exp = p04.getFormula();
		p04.calculate(exp);
	}
	
	
	
	String getFormula() {
		Scanner sc = new Scanner(System.in);
		System.out.print("input : ");
		String exp = sc.nextLine();
		return exp;
	}
	
	void calculate(String exp) {
		String[] nums = exp.split("[/+/-/*//]");
		double result = 0;
		if(exp.contains("+"))
			result = Double.parseDouble(nums[0]) + Double.parseDouble(nums[1]);
		else if(exp.contains("-"))
			result = Double.parseDouble(nums[0]) - Double.parseDouble(nums[1]);
		else if(exp.contains("*"))
			result = Double.parseDouble(nums[0]) * Double.parseDouble(nums[1]);
		else if(exp.contains("/")) {
			if(Double.parseDouble(nums[1]) != 0)
				result = Double.parseDouble(nums[0]) / Double.parseDouble(nums[1]);
			else
				System.out.println("error - divide from 0");
		}
		System.out.println(result);
	}

}
