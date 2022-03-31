package ch02;

import java.util.Scanner;

/**
 * Calculating Change Program
 * Calculate Gap And Change 
 * 
 * @author KOPO
 */

public class P03 {

	public static void main(String[] args) {
		int n1 = 0;
		int n2 = 0;
		
		int bill_50000 = 0;
		int bill_10000 = 0;
		int bill_5000 = 0;
		int bill_1000 = 0;
		int coin_500 = 0;
		int coin_100 = 0;
		int coin_50 = 0;
		int coin_10 = 0;
		int change = 0;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Input number1 : ");
		n1 = sc.nextInt();
		
		System.out.print("Input number2 : ");
		n2 = sc.nextInt();
		
		change = n1 - n2;
		
		bill_50000 = change / 50000;
		change =  change % 50000;
		
		bill_10000 = change / 10000;
		change =  change % 10000;

		bill_5000 = change / 5000;
		change =  change % 5000;
		
		bill_1000 = change / 1000;
		change =  change % 1000;
		
		coin_500 = change / 500;
		change =  change % 500;
		
		coin_100 = change / 100;
		change =  change % 100;
		
		coin_50 = change / 50;
		change =  change % 50;
		
		coin_10 = change / 10;
		change =  change % 10;

		System.out.println("50000\t - " + bill_50000);
		System.out.println("10000\t - " + bill_10000);
		System.out.println("5000\t - " + bill_5000);
		System.out.println("1000\t - " + bill_1000);
		System.out.println("500\t - " + coin_500);
		System.out.println("100\t - " + coin_100);
		System.out.println("50\t - " + coin_50);
		System.out.println("10\t - " + coin_10);
	}

}
