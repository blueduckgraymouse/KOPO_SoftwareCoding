package ch03;

import java.util.Scanner;

public class P15_re {

	// 다른 방법 : +, -, *, / 정확히 인덱스 번호 찾아서 substring
	// 제대로 게산기로 기능 확장할꺼 아니면 그냥 지금 방법이 더 간단해보이니까 안함.
	
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
	
		System.out.println(result);
	}

}
