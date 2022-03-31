package ch02;

import java.util.Scanner;

/**
 *  Check number program
 *  if input is 1   print "one!"
 *  else			print "Not One!"
 *  
 * @author KOPO
 *
 */

public class P05_re {

	public static void main(String[] args) {
		while(true) {
			try {
				Scanner sc = new Scanner(System.in);
				int input = sc.nextInt();
				
				if(input == 1) 	System.out.println("One!");
				else			System.out.println("Not One!");
				break;
			} catch(Exception e) {
				System.out.println("wrong input. try again.");
			}
		}
	}

}
