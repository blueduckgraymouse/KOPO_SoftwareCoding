package ch03;

import java.util.Scanner;

public class P12 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// print options
		System.out.println("#Temperature Converter");
		System.out.println("1. Celsius -> Fahrenheit");
		System.out.println("2. Fahrenheit -> Celsius");

		// input option and temperature
		System.out.println("input option : ");		
		int option = sc.nextInt();
		System.out.println("input temperature : ");
		double temperature = sc.nextDouble();
		
		// process
		double result = 0;
		
		if(option == 1) result = temperature * 1.8 + 32;
		else result = (temperature - 32) / 1.8;
		
		System.out.printf("%.2f", result);
	}

}
