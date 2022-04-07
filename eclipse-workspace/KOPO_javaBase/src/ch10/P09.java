package ch10;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class P09 {

	public static void main(String[] args) throws IOException {
		P09 p09 = new P09();
		FileWriter fw = new FileWriter("C:\\KOPO\\git_tracking\\기본프로그래밍_java\\resultP09.csv", true);		// 추가
		
		System.out.println("# Rock-Paper-Scissors Game");
		
		while(true) {
			int selection = p09.inputSelection();
			if (selection == 3) {
				System.out.println("종료");
				break;
			}
			
			String result = p09.playGame(selection);
			
			fw.write(result + "," + p09.getCurrentTime() + "\n");
		}
		
		fw.close();
	}

	private int inputSelection() {
		while(true) {
			Scanner sc = new Scanner(System.in);
			try {
				System.out.print("input a selection(0 - rock, 1 - paper, 2 - scissor, 3 - exit) : ");
				int selection = sc.nextInt();
				if(4 > selection && selection >= 0) {
					return selection;
				} else {
					throw new Exception();
				}
			} catch(Exception e) {
				System.out.println("wrong input.");
			}
		}
	}
	
	private String playGame(int userSelection) {
		String[] selection = {"바위", "보", "가위"};
		int comSelection = (int)(Math.random() * 3);
		System.out.println("사용자 - " + selection[userSelection] + " / 컴퓨터 - " + selection[comSelection]);
		String result = "";
		// 사용자 기준 승패 계산
		if(userSelection == comSelection) {
			result = "무승부";
		} else if(Math.abs(userSelection - comSelection) == 1) {
			result = userSelection > comSelection ? "승리" : "패배";
		} else {
			result = userSelection < comSelection ? "승리" : "패배";
		}
		System.out.println(result);
		return result;
	}
	
	private String getCurrentTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		String dateAndTime = format.format(time);
		return dateAndTime;
	}
	
}

