package review2;

import java.util.Calendar;
import java.util.Scanner;

public class R2 {

	public static void main(String[] args) {
		//int[] dates = inputDate();
		int[] dates = {20200902, 20180904};
		calculate_i_age(dates);
		calculate_k_age(dates);
	}

	// calculate international age
	private static void calculate_i_age(int[] dates) {
		Calendar cal_current = Calendar.getInstance();
		cal_current.set(dates[0] / 10000, dates[0] % 10000 / 100 - 1, dates[0] % 100);
	
		Calendar cal_birth = Calendar.getInstance();
		cal_birth.set(dates[1] / 10000, dates[1] % 10000 / 100 - 1, dates[1] % 100);
		
		long age = (cal_current.getTimeInMillis() - cal_birth.getTimeInMillis()) / 1000 / 3600 / 24 / 365;

		System.out.println(age + " years old in interanational age");
	}
	
	// calculate korean age
	private static void calculate_k_age(int[] dates) {
		int age = ((dates[0] / 10000 - dates[1] / 10000) + 1);
		
		
		System.out.println(age + " years old in Korean age");
	}

	// input current date and birthday date
	private static int[] inputDate() {
		Scanner sc = new Scanner(System.in);
		int[] dates = new int[2];
		
		System.out.print("Current Date : ");
		try {
			dates[0] = sc.nextInt();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.print("Brithday : ");
		try {
			dates[1] = sc.nextInt();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dates;
	}

}
