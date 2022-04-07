package ch10;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Ex03 extends Thread {

	public static void main(String[] args) {
		Ex03 thread = new Ex03();
		thread.start();
		System.out.println("Check 1 : " + getCurrentTime() + "-" + thread.isAlive());
		try {
			Thread.sleep(3000);					// 쓰레드은 자동소멸이지만 약간의 시간이 걸린다.
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Check 2 : " + getCurrentTime() + "-" + thread.isAlive());
	}

	private static String getCurrentTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		String dateAndTime = format.format(time);
		return dateAndTime;
	}
	
	public void run() {
		int cnt = 0;
		while (true) {
			if (cnt == 5) {
				break;
			}
			try {
				System.out.println( getCurrentTime() + "-" + cnt);
				Thread.sleep(100);
				cnt++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
