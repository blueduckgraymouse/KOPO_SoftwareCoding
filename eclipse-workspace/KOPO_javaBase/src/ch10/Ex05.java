package ch10;

import java.util.ArrayList;
import java.util.List;

public class Ex05 {

	public static void main(String[] args) {
		List<String> list1 = new ArrayList<String>();
		list1.add("철수");
		list1.add("영희");
		list1.add("민희");
		list1.add("종주");
		Thread thread1 = new Run01(list1);
		thread1.run();
		List<String> list2 = new ArrayList<String>();
		list2.add("철수2");
		list2.add("영희2");
		list2.add("민희2");
		list2.add("종주2");
		Thread thread2 = new Run01(list1);
		thread2.run();
		
	}

}

class Run01 extends Thread {
	public List<String> list;
	
	public Run01(List<String> list) {
		this.list = list;
	}
	
	public void run(List<String> list) {
		for(int i = 0 ; i < 5 ; i++) {
			System.out.println("!" + list);
			wait1000ms();
		}
	}
	
	private static void wait1000ms() {
		try { 
			Thread.sleep(500);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}