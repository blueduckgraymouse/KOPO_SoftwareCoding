package review3;

import java.util.Scanner;

public class R1 {

	public static void main(String[] args) {
		//int[] nums = input_nums();
		int[] nums = {88, 93};
		
		int result = vedic_mathematic(nums);
		
		System.out.println(result);
	}

	private static int vedic_mathematic(int[] num_o) {
		int num_s1 = 100 - num_o[0];
		int num_s2 = 100 - num_o[1];
		
		int num_v1 = num_s1 + num_s2;
		int num_v2 = num_s1 * num_s2;
		
		int result = (100 - num_v1) * 100 + num_v2;
		
		return result;
	}

	private static int[] input_nums() {
		Scanner sc = new Scanner(System.in);
		int nums[] = new int[2];
		
		System.out.println("input a number(10~99) : ");
		try {
			nums[0] = sc.nextInt();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("input a number(10~99) : ");
		try {
			nums[1] = sc.nextInt();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return nums;
	}

}
