package ch06.assignment.A1;

import java.util.Scanner;

public class Menu {
	String[] menu = {"Course List", "My Course"};
	String[] courses = {"Korean", "Math", "English", "Social Studies", "Science"};
	String DB = "";

	public void excuteMenu() {
		while (true) {
			printMenu();
			int menuNumber = inputMenuNumber(menu);
			if(menuNumber == 0)
				break;
			DB = excuteMenu(DB, menuNumber);
		}
	}

	
	private void printMenu() {
		System.out.println("#menu");
		for (int i = 0 ; i < menu.length ; i++) {
			System.out.println((i + 1) + ". " + menu[i]);
		}		
	}

	private int inputMenuNumber(String[] menu) {
		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.print(" -> ");
			try {
				int menuNumber = sc.nextInt();
				if (menuNumber >= 1 && menuNumber <= menu.length)
					return menuNumber;
				else
					System.out.println("wrong input. input a menu number.");
			} catch (Exception e) {
				System.out.println("wrong input. input a menu number.");
			}
		}
	}
	
	private String excuteMenu(String DB, int menuNumber) {
		Registercourse registercourse = new Registercourse(DB, courses);
		MyCourse myCourse = new MyCourse(DB, courses);
		
		switch(menuNumber) {
		case 1:
			DB = registercourse.excuteRegistration();
			break;
		case 2:
			myCourse.myCourse();
			break;
		}
		return DB;
	}

}
