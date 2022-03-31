package ch02;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Number Converter Program
 * from Base 10 to Base 3
 * use List
 * @author KOPO
 *
 */

public class P04_re {

	public static void main(String[] args) {
		int n = 0;										// input
		List<Integer> con = new ArrayList<Integer>();	// calculated base 3 number
		int remainder = 0;								// remainder in calculating
		
		Scanner sc = new Scanner(System.in);
		
		// input
		System.out.print("Input Number : ");
		
		n = sc.nextInt();
		
		remainder = n;
		
		// convert
		while(true) {
			int temp = remainder % 3;
			con.add(temp);
			remainder = remainder / 3;
			//System.out.println(temp + "/" + remainder);
			if(remainder == 0) break;
		}
		
		// print
		for(int i=con.size()-1; i>=0 ;i--) {
			System.out.print(con.get(i));
		}
	}
	
}
