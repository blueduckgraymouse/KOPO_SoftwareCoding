package ch10.assignment.A02_f;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class A2 {

	public static void main(String[] args) {
		A2 a02 = new A2();
		
		// 경주인원 입력
		int numRacer = a02.inputNumber();
		
		// 경주 인원 수 만큼 racer라는 쓰레드 객체의 배열인 racers 생성
		Racer[] racers = new Racer[numRacer];
		
		// racers 배열의 모든 racer객체 생성하고 쓰레드 시작
		for (int i = 0 ; i < numRacer ; i++) {
			racers[i] = new Racer();
			racers[i].start();
		}
		
		a02.waitMS(1000);
		
		// 실시간 경주 중계
		racers = a02.liveBroadcast(racers, numRacer, a02);
		
		// 최종 종료 시간과 순위 확인
		int finalEndTime = a02.checkFinalEndTime(racers);
		racers = a02.checkRank(racers);
		
		// 최종 기록과 순위 출력
		outputRaceResult(finalEndTime, numRacer, racers, a02);
		
	}

	// 경주 인원 입력
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
	
	// 실시간 전체 racer 중계
	private Racer[] liveBroadcast(Racer[] racers, int numRacer, A2 a02) {
		boolean raceFlag = false;
		for (int i = 0 ; raceFlag == false ; i++) {	// 모든 racer가 완주할 때 까지 중계
			System.out.println("\n#live");
			boolean totalRacersFlag = true;
			a02.printResultFrame(i);			// 경과 시간 출력
			for (int j = 0 ; j < numRacer ; j++) {
				synchronized(racers[j].record) {		// => java.util.ConcurrentModificationException 방지
					a02.printList(racers[j].record);	// 해당 racer 중계
					totalRacersFlag = totalRacersFlag && racers[j].flag;	// 모든 racer의 완주 여부 수집
				}
			}
			raceFlag = totalRacersFlag;
			a02.waitMS(1000);
			System.out.println();
		}
		return racers;
	}
	
	// 한 명의 racer에 대해 실시간 중계
	private void printList(List<String> record) {
		Iterator<String> iter = record.iterator();
		while (iter.hasNext()) {
			String nowRecord = iter.next();
			System.out.print(nowRecord);
			if (!nowRecord.equals("Finished")) {
				System.out.print("m\t");
			}
		}
		System.out.println();
	}

	// 실시간 중계의 경과 시간 표시
	private void printResultFrame(int endTime) {
		for(int i = 0 ; i <= endTime ; i++) {
			System.out.print("(" + i + "s)\t");
		}
		System.out.println();
	}
	
	// 가장 늦게 들어온 racer의 시간 확인
	private int checkFinalEndTime(Racer[] racers) {
		int finalEndTime = 0;
		for (Racer racer : racers) {
			finalEndTime = finalEndTime < racer.endTime ? racer.endTime : finalEndTime;
		}
		return finalEndTime;
	}
	
	// racer의 순위 확인
	private Racer[] checkRank(Racer[] racers) {
		for (Racer racer : racers) {
			for (Racer cpRacer : racers) {
				racer.rank = racer.endTime > cpRacer.endTime ? racer.rank + 1 : racer.rank;
			}
		}
		return racers;
	}
	
	// 최종 경기 기록과 순위 출력
	private static void outputRaceResult(int finalEndTime, int numRacer, Racer[] racers, A2 a02) {
		System.out.println("\n#Result");
		a02.printResultFrame(finalEndTime);
		for (int i = 0 ; i < numRacer ; i++) {
			racers[i].printFinalRecord();
		}
	}
	
	private void waitMS(long ms) {
		try {
			Thread.sleep(ms);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}