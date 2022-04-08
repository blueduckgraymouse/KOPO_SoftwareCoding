package ch10.assignment.A02_f;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Racer extends Thread {
	
	List<String> record = new ArrayList<String>();
	public double distance = 0;
	public int endTime = 0;
	public int rank = 1;
	public boolean flag = false;			// 목적지 도착여부
	
	public synchronized void run() {
		record.add("0");
		
		waitMS(1000);
		
		while (distance <= 50) {
			distance += Math.random() * 10;
			if (distance <= 50) {
				synchronized(record) {
					record.add(Double.toString(distance).substring(0, 5));
				}
			} else {
				synchronized(record) {
					record.add("Finished");
					flag = true;
				}
			}
			endTime++;
			waitMS(1050);		// synchronized로 인해 중계보다 reocord에 저장이 조금 빨라 싱크를 맞추기 위해서 조율
		}
	}
	
	// 최종 기록, 결과 출력
	public void printFinalRecord() {
		Iterator<String> iter = record.iterator();
		while (iter.hasNext()) {
			System.out.print(iter.next());
			if (iter.hasNext()) {
				System.out.print("m\t");
			}
		}
		System.out.print("\t => " + rank);
		System.out.println();
	}
	
	private void waitMS(long ms) {
		try {
			Thread.sleep(ms);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
