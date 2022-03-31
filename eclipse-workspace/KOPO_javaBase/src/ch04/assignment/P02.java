package ch04.assignment;

import java.util.Scanner;

public class P02 {

	public static void main(String[] args) {
		int balance = 0;
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			System.out.println("#Menu");
			System.out.println("1. Deposit");
			System.out.println("2. Withdrawal");
			System.out.println("3. exit");
			System.out.println("Balance : " + balance);
			
			// input menu
			System.out.print("input menu : ");
			int menu = 0;
			while (true) {
				menu = sc.nextInt();
				if	(menu > 4 || menu < 1) 
					System.out.println("wrong input. input 1~3");
				else
					break;
			}

			// process
			int money = 0;
			switch (menu) {
				case 1:			// Deposit
					System.out.print("money : ");
					while (true) {
						money = sc.nextInt();
						if(money <= 0)
							System.out.println("wrong input. input positive amount.");
						else
							break;
					}
					balance += money;
					break;
				case 2:			// Withdrawal
					while (true) {
						System.out.print("money : ");
						while (true) {
							money = sc.nextInt();
							if(money <= 0)
								System.out.println("wrong input. input positive amount.");
							else
								break;
						}
						if (balance - money >= 0) {
							balance -= money;
							break;
						}
						else System.out.println("exceed your Balance. input again.");
					}
					break;
				case 3:			// exit
					return;
				default:		// input other menu number
					System.out.println("wrong menu number. input again.");
			}
			System.out.println();
		}
		
	}

}
