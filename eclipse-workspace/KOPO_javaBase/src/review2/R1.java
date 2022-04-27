package review2;

import java.util.Scanner;

public class R1 {

	public static void main(String[] args) {
		
		//String[] nums = inputNums();
		//String[] nums = {"0001", "1101", "1101"};
		//String[] nums = {"1010", "1000", "0111"};
		String[] nums = {"100000", 
						 "100011", 
						 "010110",
						 "000101"};
		
		draw_line(nums);
		
	}
	
	// try to draw '0'-line vertically and horizontally and return the result
	private static void draw_line(String[] nums) {
		if (draw_vertical(nums) || draw_horizontal(nums)) {
			System.out.println("Pass");
		} else {
			System.out.println("Fail");
		}

	}
	
	// try to draw '0'-line vertically and return the result(boolean)
	private static boolean draw_horizontal(String[] nums) {
		for (String num : nums) {
			if (!(num.contains("1"))) {
				return true;
			}
		}
		
		return false;
	}

	// try to draw '0'-line vertically and return the result(boolean)
	private static boolean draw_vertical(String[] nums) {
		char[][] background = new char[nums.length][nums[0].length()];
		
		for (int i = 0 ; i < nums.length ; i++) {
			background[i] = nums[i].toCharArray();
		}

		for (int j = 0 ; j < background.length ; j++) {
			if (background[0][j] == '0') {
				boolean flag = true;
				for (int i = 1 ; i < background.length ; i++) {
					if (background[i][j] == '1') {
						flag = false;
					}
				}
				if (flag == true) {
					return true;
				}
			}
		}
		
		return false;
	}

	// input size 3x4 bit and return the input(String array)
	private static String[] inputNums() {
		Scanner sc = new Scanner(System.in);
		
		String[] nums = new String[3];
		
		for (int i = 0 ; i < 3 ; i++) {
			System.out.print("input a number of 4bit : ");
			try {
				nums[i] = sc.nextLine();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return nums;
	}

}
