package ch10.assignment.A02_2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Racer extends Thread {
	
	List<String> record = new ArrayList<String>();
	double distance = 0;
	int endTime = 0;
	int rank = 1;
	boolean flag = false;
	
	public synchronized void run() {
		while(distance <= 50) {
			endTime++;
			distance += Math.random() * 10;
			if(distance <= 50) {
				synchronized(record) {
					record.add(Double.toString(distance).substring(0, 5));
				}
			} else {
				synchronized(record) {
					record.add("Finished");
				}
			}
			waitSeconds(1.5);
		}
		flag = true;
	}
	
	public List<String> getRecord() {
		return record;
	}
	
	public void printRecord() {
		Iterator<String> iter = record.iterator();
		while(iter.hasNext()) {
			System.out.print(iter.next());
			if(iter.hasNext()) {
				System.out.print("m\t");
			}
		}
		System.out.print("\t => " + rank);
		System.out.println();
	}
	
	public int getEndTime() {
		return endTime;
	}
	
	public int getRank() {
		return rank;
	}
	
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	private void waitSeconds(double seconds) {
		try {
			Thread.sleep((long)seconds * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
