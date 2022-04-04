package ch06.assignment.A5;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		String[] unitName = {"cm", "m", "mm", "km", "mile"};
		double[] unitValueBase_cm = {1, 100, 0.1, 100000, 160934.4};
		
		Main main = new Main();
		UnitConverter unitConverter = new UnitConverter();
		
		main.printMenus(unitName);
		
		int[] menuNumbers = main.selectMenus();
		double value = main.inputValue();
		
		unitConverter.excute(unitValueBase_cm, menuNumbers, value);
		unitConverter.print();
		
	}

	private void printMenus(String[] unitName) {
		System.out.println("#Unit");
		for (int i = 0 ; i < unitName.length ; i++) {
			System.out.println((i + 1) + ". " + unitName[i]);
		}
	}

	private int[] selectMenus() {
		System.out.print("input two numbers of menu : ");
		String sNumbers;
		while (true) {
			Scanner sc = new Scanner(System.in);
			try {
				sNumbers = sc.nextLine();
				if(sNumbers.split(" ").length == 2)
					break;
			} catch (Exception e) {
				System.out.println("wrong input. input two menu numbers.");
			}
		}
		String[] sArrNumbers = sNumbers.split(" ");
		int[] iArrNumbers = new int[2];
		for (int i = 0 ; i < 2 ; i++) {
			iArrNumbers[i] = Integer.parseInt(sArrNumbers[i]);
		}
		return iArrNumbers;
	}
	
	private double inputValue() {
		System.out.print("input value to convert : ");
		while (true) {
			Scanner sc = new Scanner(System.in);
			try {
				double value = sc.nextDouble();
				return value;
			} catch (Exception e) {
				System.out.println("wrong input. input two menu numbers.");
			}
		}
	}
}
