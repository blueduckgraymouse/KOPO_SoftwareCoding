package ch03;

import java.util.Calendar;

public class Ex04 {

	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.getTime());		// Tue Mar 29 10:54:45 KST 2022
		System.out.println(calendar.get(Calendar.YEAR));			// 연도
		System.out.println(calendar.get(Calendar.MONTH));			// 월-1
		System.out.println(calendar.get(Calendar.DATE));			// 일
		System.out.println(calendar.get(Calendar.DAY_OF_MONTH));	// 일
		System.out.println(calendar.get(Calendar.DAY_OF_WEEK));		// 요일에 해당하는 숫자, 1이 일요일
		System.out.println(calendar.get(Calendar.HOUR_OF_DAY));		// 시(24시 기준)
		System.out.println(calendar.get(Calendar.HOUR));			// 시(12시 기준)
		System.out.println(calendar.get(Calendar.MINUTE));			// 분
		System.out.println(calendar.get(Calendar.SECOND));			// 초
		
		System.out.println(calendar.get(Calendar.WEEK_OF_YEAR));	// 현재 올해의 몇번째 주인가? 1년은 55주
		System.out.println(calendar.get(Calendar.WEEK_OF_MONTH));	// 이번 달의 몇 번째 주인가?
	}

}
