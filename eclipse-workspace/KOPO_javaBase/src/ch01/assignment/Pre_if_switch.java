package ch01.assignment;

import java.util.Scanner;

public class Pre_if_switch {
	public static void main(String[] args) {
//		System.out.println("퀴즈) 3+5=?");
//		
//		System.out.println("1번. 5");
//		System.out.println("2번. 6");
//		System.out.println("3번. 7");
//		System.out.println("4번. 8");
//		
//		Scanner sc = new Scanner(System.in);
//		
//		boolean d = false;
//		
//		while(!d) {
//			int answer = sc.nextInt();
//			
//			switch(answer) {
//				case 1: case 2: case 3:
//					System.out.println("다시 도전해보세요~");
//					break;
//				case 4:
//					d = true;
//					System.out.println("정답입니다~");
//					break;
//				default:
//					System.out.println("잘못된 입력입니다. 다시 입력하세요");
//					break;
//			}
//		}
		
		System.out.println("퀴즈) 3+5=?");
		
		System.out.println("1번. 5");
		System.out.println("2번. 6");
		System.out.println("3번. 7");
		System.out.println("4번. 8");
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			int answer = sc.nextInt();
			
			if(answer == 4) {
				System.out.println("정답");
				break;
			} else if(answer==1 || answer==2 || answer==3) {
				System.out.println("오답, 다시 시도해보세요.");
			} else {
				System.out.println("잘못된 입력입니다. 다시 입력하세요.");
			}
		}
	}
}
