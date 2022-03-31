package ch03;

import java.util.Calendar;
import java.util.Scanner;

public class P07 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// input
		System.out.println("input date1 : ");
		int inputDate1 = sc.nextInt();
		System.out.println("input date2 : ");
		int inputDate2 = sc.nextInt();
		
		// calculate
		Calendar date1 = Calendar.getInstance();
		date1.set(Calendar.YEAR, inputDate1 / 10000);
		date1.set(Calendar.MONTH, (inputDate1 % 10000) / 100 - 1);
		date1.set(Calendar.DATE, (inputDate1 % 10000 ) % 100);
		//System.out.println(date1.getTime());
		
		Calendar date2 = Calendar.getInstance();
		date2.set(Calendar.YEAR, inputDate2 / 10000);
		date2.set(Calendar.MONTH, (inputDate2 % 10000) / 100 - 1);
		date2.set(Calendar.DATE, (inputDate2 % 10000 ) % 100);
		//System.out.println(date2.getTime());
		
		System.out.println(date2.getTimeInMillis());
		System.out.println(date1.getTimeInMillis());
		long diffInMill = Math.abs(date2.getTimeInMillis() - date1.getTimeInMillis()) / 1000;
		System.out.println(diffInMill);
		
		long diffInDate = diffInMill / (24 * 60 * 60);
		
		System.out.println(diffInDate);
		
		
	}

}
