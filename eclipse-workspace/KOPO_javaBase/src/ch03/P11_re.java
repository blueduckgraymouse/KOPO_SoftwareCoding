package ch03;

import java.util.Scanner;

public class P11_re {

	public static void main(String[] args) {
		// exchange Rate , base 1000won, {USD, JPY}
		double exchangeRate[] = {0.82, 101.28, 89.43, 5.23};
		String baseCountry = "KRW";
		String targetCountry[] = {" USD", "JPY", "EUR", "CNY"}; 
		
		Scanner sc = new Scanner(System.in);
		
		// print options
		System.out.println("#Current Converter");	
		for(int i = 0 ; i < exchangeRate.length * 2 ; i++) {
			if(i % 2 == 0) 	System.out.println((i + 1) + ". " + baseCountry 			+ " => " + targetCountry[i / 2]);
			else			System.out.println((i + 1) + ". " + targetCountry[i / 2] 	+ " => " + baseCountry);
		}
		
		// input option and money
		System.out.println("input option : ");		
		int option = sc.nextInt();
		System.out.println("input money : ");
		double money = sc.nextDouble();
		
		// exchange process
		double change = 0;
		if((option-1) % 2 == 0) change = money / 1000 * exchangeRate[(option-1) / 2];
		else					change = money / exchangeRate[(option-1) / 2] * 1000;
		
		// output
		System.out.printf("%.2f", change);
	}

}
