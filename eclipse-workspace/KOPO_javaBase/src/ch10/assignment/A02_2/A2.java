package ch10.assignment.A02_2;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class A2 {

	public static void main(String[] args) {
		A2 a02 = new A2();
		
		// input a number of racers
		int numRacer = a02.inputNumber();
		
		// 객체 선언
		Racer[] racers = new Racer[numRacer];
		
		// 객체 생성하고 실행
		for(int i = 0 ; i < numRacer ; i++) {
			racers[i] = new Racer();
			racers[i].start();
		}
		
		a02.waitSeconds(0.5);
		
		// 출력
		boolean Flag = false;
		for(int i = 0 ; Flag == false ; i++) {
			boolean fFlag = true;
			a02.printResultFrame(i + 1);
			for(int j = 0 ; j < numRacer ; j++) {
				synchronized(racers[j].getRecord()) {
					a02.printList(racers[j].getRecord());
					//System.out.println(racers[j].getRecord());
					fFlag = fFlag && racers[j].flag;
				}
			}
			Flag = fFlag;
			a02.waitSeconds(1.5);
			System.out.println();
		}
		
		int finalEndTime = a02.checkFinalEndTime(racers);
		racers = a02.checkRank(racers);
		
		a02.printResultFrame(finalEndTime);
		for(int i = 0 ; i < numRacer ; i++) {
			racers[i].printRecord();
		}
	}
	
	private void printList(List<String> record) {
		Iterator<String> iter = record.iterator();
		while(iter.hasNext()) {
			String nowRecord = iter.next();
			System.out.print(nowRecord);
			if(!nowRecord.equals("Finished")) {
				System.out.print("m\t");
			}
		}
		System.out.println();
	}

	private void printResultFrame(int endTime) {
		for(int i = 1 ; i <= endTime ; i++) {
			System.out.print("(" + i + "s)\t");
		}
		System.out.println();
	}
	
	private int checkFinalEndTime(Racer[] racers) {
		int finalEndTime = 0;
		for (Racer racer : racers) {
			if (finalEndTime < racer.getEndTime()) {
				finalEndTime = racer.getEndTime();
			}
		}
		return finalEndTime;
	}
	
	private Racer[] checkRank(Racer[] racers) {
		int size = racers.length;
		for(Racer racer : racers) {
			for(Racer cpRacer : racers) {
				if(racer.getEndTime() > cpRacer.getEndTime()) {
					racer.setRank(racer.getRank() + 1);
				}
			}
		}
		return racers;
	}
	
	private int inputNumber() {
		while (true) {
			Scanner sc = new Scanner(System.in);
			try {
				System.out.println("input a number of racers");
				return sc.nextInt();
			} catch (Exception e) {
				System.out.println("wrong input.");
			}
		}
	}
	
	private void waitSeconds(double seconds) {
		try {
			Thread.sleep((long)seconds * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}