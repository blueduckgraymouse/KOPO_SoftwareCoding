package ch10.assignment;

import java.io.IOException;
import java.util.Scanner;

public class A03 {

	public static void main(String[] args) throws IOException {
		A03 a03 = new A03();
		
		System.out.println("# Rock-Paper-Scissors Game");
		
		while (true) {
			// input user selection
			int selection = a03.inputSelection();
			if (selection == 3) {
				System.out.println("종료");
				break;
			}
			
			// play game and print result
			a03.playGame(selection);
		}
		
	}

	private int inputSelection() {
		while (true) {
			Scanner sc = new Scanner(System.in);
			try {
				System.out.print("input a selection(0 - rock, 1 - paper, 2 - scissor, 3 - exit) : ");
				int selection = sc.nextInt();
				if (4 > selection && selection >= 0) {
					return selection;
				} else {
					System.out.println("wrong input.");
				}
			} catch (Exception e) {
				System.out.println("wrong input.");
			}
		}
	}
	
	private void playGame(int userSelection) {
		String[] selection = {"바위", "보", "가위"};
		int comSelection = (int)(Math.random() * 3);
		String result = "";
		
		System.out.println("사용자 - " + selection[userSelection] + " / 컴퓨터 - " + selection[comSelection]);
		
		// 사용자 기준 승패 계산
		if (userSelection == comSelection) {
			result = "무승부";
		} else if (Math.abs(userSelection - comSelection) == 1) {
			result = userSelection > comSelection ? "승리" : "패배";
		} else {
			result = userSelection < comSelection ? "승리" : "패배";
		}
		
		System.out.println(result);
	}

}
