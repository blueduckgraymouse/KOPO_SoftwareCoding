package ch06.assignment.A4;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		FindingTheNearestNumber findingTheNearestNumber = new FindingTheNearestNumber();
		Main main = new Main();
		
		String sNumbers = main.inputNumbers();
		double dNumber	= main.inputNumber();
		findingTheNearestNumber.execute(dNumber, sNumbers);
		findingTheNearestNumber.print();
	}

	private String inputNumbers() {
		System.out.print("input numbers : ");
		while (true) {
			Scanner sc = new Scanner(System.in);
			try {
				String sNumbers = sc.nextLine();
				return sNumbers;
			} catch (Exception e) {
				System.out.println("wrong input. input only number.");
			}
		}
	}
	
	private double inputNumber() {
		System.out.print("input a number : ");
		while (true) {
			Scanner sc = new Scanner(System.in);
			try {
				double dNumber = sc.nextDouble();
				return dNumber;
			} catch (Exception e) {
				System.out.println("wrong input. input only number.");
			}
		}
	}
}
