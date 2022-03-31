package ch02;

// p13

public class Ex09 {

	public static void main(String[] args) {
		int x = 10;
		int y = 20;
		
		System.out.println(x < 5 && y > 10); 	// 0
		System.out.println(x < 5 || y > 10);	// 1
		System.out.println(!(x < 5 && y > 10));	// 1
	}

}
