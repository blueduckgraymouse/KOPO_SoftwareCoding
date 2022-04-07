package ch10;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class P06 {

	public static void main(String[] args) {
		P06 p06 = new P06();
		
		System.out.println(p06.getCurrentTime());
		
		int seconds = p06.inputSeconds();
		
		System.out.println("start timer at " + p06.getCurrentTime());
		
		p06.waitSeconds(seconds);
		
		System.out.println("time is over(" + p06.getCurrentTime() + ")");
	}

	private int inputSeconds() {
		while (true) {
			Scanner sc = new Scanner(System.in);
			try {
				System.out.print("input a value of seconds : ");
				return sc.nextInt();
			} catch (Exception e) {
				System.out.println("wrong input.");
			}
		}
	}

	private void waitSeconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getCurrentTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		String dateAndTime = format.format(time);
		return dateAndTime;
	}
	
}
