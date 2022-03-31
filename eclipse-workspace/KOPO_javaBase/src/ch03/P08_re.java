package ch03;

import java.text.ParseException;
import java.text.SimpleDateFormat;

// 캘린더 클래스의 add함수

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class P08_re {

	public static void main(String[] args) throws ParseException {
		Scanner sc = new Scanner(System.in);
		
		// input
		System.out.println("input date : ");
		String inputDate = sc.next();
		System.out.println("input day : ");
		int inputDay = sc.nextInt();
		
		// calculate
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = sdf.parse(inputDate);
		
		cal.setTime(date);
		cal.add(Calendar.DATE, inputDay);
		
		System.out.println(sdf.format(cal.getTime()));
	}
}
