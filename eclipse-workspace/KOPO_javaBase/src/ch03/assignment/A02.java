package ch03.assignment;

import java.util.Scanner;

public class A02 {

	public static void main(String[] args) {
		int count[] = {0, 0, 0, 0};
		
		while(true) {
			System.out.print("input : ");
			Scanner sc = new Scanner(System.in);
			int input = sc.nextInt();
			
			if(input > 0) 	count[0]++;
			else 			count[1]++;
			if(input % 2 != 0) 	count[2]++;
			else				count[3]++;
			
			System.out.println("P - " + count[0] + ", N - " + count[1] + ", O - " + count[2] + ", E - "+ count[3]);
		}
	}

}
