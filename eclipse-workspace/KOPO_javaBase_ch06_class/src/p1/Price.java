package p1;

import java.util.Scanner;

public class Price {
	int[] price = {1500, 3000, 2000, 5000};
	// int[] price = new int[4];
	
	public Price() {
		System.out.println("#price");
		System.out.println("1.Apple");
		System.out.println("2.Strawberry");
		System.out.println("3.Grape");
		System.out.println("4.Watermelon");
	}
	
	public Price(int p1, int p2, int p3, int p4) {
		System.out.println("#price");
		System.out.println("1.Apple");
		System.out.println("2.Strawberry");
		System.out.println("3.Grape");
		System.out.println("4.Watermelon");
	}
	
	public String inputFNum() {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		return input;
	}
	
	public int calculate(int fNum) {
		return price[fNum];
	}
	public int calculate(int fNum1, int fNum2) {
		return price[fNum1] + price[fNum2];
	}
	public int calculate(int fNum1, int fNum2, int fNum3) {
		return price[fNum1] + price[fNum2] + price[fNum3];
	}
	public int calculate(int fNum1, int fNum2, int fNum3, int fNum4) {
		return price[fNum1] + price[fNum2] + price[fNum3] + price[fNum4];
	}
}
