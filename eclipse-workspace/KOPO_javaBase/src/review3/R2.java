package review3;

import java.util.Scanner;

public class R2 {

	public static void main(String[] args) {
		String input = input_nums();
		//String input = "88X93";
		
		
		int result = vedic_mathematic(input);
		
		System.out.println(result);
	}

	// multiple nums by vedic_mathematic
	private static int vedic_mathematic(String input) {
		String[] nums_o = input.trim().split("X");
		
		int num_s1 = 100 - Integer.parseInt(nums_o[0]);
		int num_s2 = 100 - Integer.parseInt(nums_o[1]);
		
		int num_v1 = num_s1 + num_s2;
		int num_v2 = num_s1 * num_s2;
		
		int result = (100 - num_v1) * 100 + num_v2;
		
		return result;
	}

	// input two numbers
	private static String input_nums() {
		Scanner sc = new Scanner(System.in);
		String input = "";
		
		System.out.println("input multiple expression(operator -> X) : ");
		try {
			input = sc.nextLine();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return input;
	}

}
