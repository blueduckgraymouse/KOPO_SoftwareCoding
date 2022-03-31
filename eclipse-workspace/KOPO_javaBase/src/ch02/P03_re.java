package ch02;

import java.util.Scanner;

/**
 * Calculating Change Program
 * Calculate Gap And Change 
 * 
 * @author KOPO
 */

public class P03_re {

	public static void main(String[] args) {
		int[] 	n 			= {0,0};										// input
		int[] 	bill 		= {50000, 10000, 5000, 1000, 500, 100, 50, 10}; // bill type
		int[] 	changes 	= {0 ,0, 0, 0, 0, 0, 0, 0};						// bill counted
		int 	cur_change 	= 0;											// temp change in calculation
		
		Scanner sc = new Scanner(System.in);
		
		
		// input number
		System.out.print("Input number1 : ");
		n[0] = sc.nextInt();
		
		System.out.print("Input number2 : ");
		n[1] = sc.nextInt();
		
		
		// calculate change
		cur_change = n[0] - n[1];
		
		System.out.println("\nsub\t - " + cur_change + "\n");
		
		for(int i=0; i<bill.length ; i++) {
			changes[i] = cur_change / bill[i];
			cur_change = cur_change % bill[i];
		}
		
		// print change
		for(int i=0; i<changes.length ; i++) {
			System.out.println(bill[i] + "\t - " + changes[i]);
		}
	}

}
