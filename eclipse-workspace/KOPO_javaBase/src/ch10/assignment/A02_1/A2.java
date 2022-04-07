package ch10.assignment.A02_1;

import java.util.Scanner;

public class A2 {

	public static void main(String[] args) {
		A2 a02 = new A2();
		
		// input a number of racers
		int numRacer = a02.inputNumber();

		// play race each
		Racer[] racers = new Racer[numRacer];
		for(int i = 0 ; i < numRacer ; i++) {
			racers[i] = new Racer();
			racers[i].run();
		}
		
		// compare result and rank
		int finalEndTime = a02.checkFinalEndTime(racers);
		racers = a02.checkRank(racers);
		
		// print records
		a02.printResultFrame(finalEndTime);
		for(int i = 0 ; i < numRacer ; i++) {
			racers[i].printRecord();
		}
	}
	
	// compare end times and rank
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

	private int inputNumber() {
		while(true) {
			Scanner sc = new Scanner(System.in);
			try {
				System.out.println("input a number of racers");
				return sc.nextInt();
			} catch (Exception e) {
				System.out.println("wrong input.");
			}
		}
	}
	
}