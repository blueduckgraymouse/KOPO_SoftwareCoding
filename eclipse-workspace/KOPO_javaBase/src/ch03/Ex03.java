package ch03;

import java.util.Date;

public class Ex03 {

	public static void main(String[] args) {
		Date date = new Date();
		System.out.println(date.toString());
		System.out.println(date.getTime());
		System.out.println(date.getMonth());	// 1월은 0으로 리턴, 즉 +1해야 우리가 생각하는 월
		System.out.println(date.getDay());		// 0 일요일, 1 월요일, ...
	}

}
