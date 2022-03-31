package ch02;

import java.util.Scanner;

/**
 * Number Converter Program
 * from Base 10 to Base 
 * use array
 * @author KOPO
 */

public class P04 {

	public static void main(String[] args) {
		int n = 0;
		int con[] = {0, 0, 0, 0, 0, 0, 0};
		int remainder = 0;
		
		Scanner sc = new Scanner(System.in);
		
		// input
		System.out.print("Input Number : ");
		
		n = sc.nextInt();
		
		remainder = n;
		
		// convert
		for(int i=0 ; i<con.length ; i++) {
			con[i] = remainder % 3;
			remainder = remainder / 3;
			//System.out.println(con[i] + "/" + remainder);
		}
		
		// print
		for(int i=con.length-1; i>=0 ;i--) {
			System.out.print(con[i]);
		}
		
	}
	
}
