package ch03;

// 캘린더 클래스의 add함수

import java.util.Calendar;
import java.util.Scanner;

public class P08 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// input
		System.out.println("input date : ");
		int inputDate = sc.nextInt();
		System.out.println("input day : ");
		int inputDay = sc.nextInt();
		
		// calculate
		Calendar date1 = Calendar.getInstance();
		date1.set(Calendar.YEAR, inputDate / 10000);
		date1.set(Calendar.MONTH, (inputDate % 10000) / 100 - 1);
		date1.set(Calendar.DATE, (inputDate % 10000 ) % 100);
		
		long addInMill =  inputDay * (24 * 60 * 60);
		
		long sumInMill = date1.getTimeInMillis() + addInMill * 1000;
		
		date1.setTimeInMillis(sumInMill);
		
		System.out.println(date1.getTime());
		System.out.println(date1.get(Calendar.YEAR));
		System.out.println(date1.get(Calendar.MONTH) + 1);
		System.out.println(date1.get(Calendar.DATE));
	}
}
