package ch10.assignment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class A01 {

	public static void main(String[] args) {
		A01 a01 = new A01();
		Stopwatch stopwatch = new Stopwatch();
		
		// Thead - stopwatch start
		System.out.println(a01.getCurrentTime());
		stopwatch.start();
		
		while (true) {
			// user input
			int inputSeconds = a01.inputSeconds();
			
			// compare passed seconds after start
			if (stopwatch.getPassedSeconds() > inputSeconds * 10) {
				System.out.println("time is already over(It's " + a01.getCurrentTime() + ")");
				stopwatch.reset();	// reset stopwatch
			} else {
				// calculate last seconds to alert
				int secondsToWait = (inputSeconds * 10 - stopwatch.getPassedSeconds()) / 10;
				// wait the last seconds
				a01.waitSeconds(secondsToWait);
				// alert
				System.out.println("time is over(" + a01.getCurrentTime() + ")");
				break;
			}
		}
		stopwatch.end();
		
	}

	private void waitSeconds(double seconds) {
		try {
			Thread.sleep((long)seconds * 1000);
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
	
	private int inputSeconds() {
		while (true) {
			Scanner sc = new Scanner(System.in);
			try {
				System.out.print(" -> ");
				return sc.nextInt();
			} catch (Exception e) {
				System.out.println("wrong input.");
			}
		}
	}
	
}
