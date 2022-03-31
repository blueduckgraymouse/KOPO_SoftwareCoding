package ch03;

import java.util.Scanner;

public class P11 {

	public static void main(String[] args) {
		// exchange Rate , base 1000won, {USD, JPY}
		double exchangeRate[] = {0.82, 101.28};
		
		Scanner sc = new Scanner(System.in);
		
		// print options
		System.out.println("#Current Converter");
		System.out.println("1. South Korean, KRW => United States, USD");
		System.out.println("2. United States, USD => South Korean, KRW");
		System.out.println("3. South Korean, KRW => Japan, JPY");
		System.out.println("4. Japan, JPY => South Korean, KRW");
		
		// input option and money
		System.out.println("input option : ");		
		int option = sc.nextInt();
		System.out.println("input money : ");
		double money = sc.nextDouble();
		
		// exchange process
		double change = 0;
		switch(option) {
			case 1:
				change = money / 1000 * exchangeRate[0];
				break;
			case 2:
				change = money / exchangeRate[0] * 1000;
				break;
			case 3:
				change = money / 1000 * exchangeRate[1];
				break;
			case 4:
				change = money / exchangeRate[1] * 1000;
				break;
		}
		
		// output
		System.out.printf("%.2f", change);
	}

}
