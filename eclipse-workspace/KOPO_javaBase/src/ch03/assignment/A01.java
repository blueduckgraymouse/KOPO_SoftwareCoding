package ch03.assignment;

import java.util.Scanner;

public class A01 {

	public static void main(String[] args) {
		int max = 0;
		int min = 2147483647;	// int Maxium value
		int total = 0;
		int count = 0;
		
		while (true) {
			// input
			System.out.print("input : ");
			Scanner sc = new Scanner(System.in);
			int input = sc.nextInt();
			
			// check
			if(input > max) max = input;
			if(input < min) min = input;
			total += input;
			count++;
			
			// print
			System.out.println(count + " : " + "Mean " + ((float)total/(float)count) + ", Max " + max + ", Min " + min);
			
		}
	}

}
