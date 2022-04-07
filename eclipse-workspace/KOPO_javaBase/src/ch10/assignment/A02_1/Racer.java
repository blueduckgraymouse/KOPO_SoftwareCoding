package ch10.assignment.A02_1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Racer extends Thread {
	
	List<String> record = new ArrayList<String>();
	double distance = 0;
	int endTime = 0;
	int rank = 1;
	
	public void run() {
		while(distance <= 50) {
			endTime++;
			distance += Math.random() * 10;
			if(distance <= 50) {
				record.add(Double.toString(distance).substring(0, 5));
			} else {
				record.add("Finished");
			}
		}
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
	
	public List<String> getRecord() {
		return record;
	}
	
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public int getRank() {
		return rank;
	}
	
	public int getEndTime() {
		return endTime;
	}
	
}
