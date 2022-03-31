package ch03;

import java.util.Scanner;

public class P15 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("input equation : ");
		
		String input = sc.nextLine();
		
		String arr[] = input.split("[*/+-]");
		String opr[] = input.split("[0-9]+");
		
		double result = 0;
		switch(opr[1]) {
			 case "+":
				 result = Double.parseDouble(arr[0]) + Double.parseDouble(arr[1]);
				 break;
			 case "-":
				 result = Double.parseDouble(arr[0]) - Double.parseDouble(arr[1]);
				 break;
			 case "*":
				 result = Double.parseDouble(arr[0]) * Double.parseDouble(arr[1]);
				 break;
			 case "/":
				 result = Double.parseDouble(arr[0]) / Double.parseDouble(arr[1]);
				 break;
			}
		
		
//		double result = 0;
//		for(int i = 0 ; i < opr.length -1 ; i++) {
//			switch(opr[i+1]) {
//			 case "+":
//				 result = Double.parseDouble(arr[i]) + Double.parseDouble(arr[i+1]);
//				 break;
//			 case "-":
//				 result = Double.parseDouble(arr[i]) - Double.parseDouble(arr[i+1]);
//				 break;
//			 case "*":
//				 result = Double.parseDouble(arr[i]) * Double.parseDouble(arr[i+1]);
//				 break;
//			 case "/":
//				 result = Double.parseDouble(arr[i]) / Double.parseDouble(arr[i+1]);
//				 break;
//			}
//		}
		System.out.println(result);
	}

}
