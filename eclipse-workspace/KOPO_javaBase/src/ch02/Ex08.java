package ch02;

// p12

public class Ex08 {

	public static void main(String[] args) {
		int x = 3;
		int y = 3;
		int z = 5;
		
		System.out.println(x == y); // 1
		System.out.println(x != y); // 0
		System.out.println(x > y); 	// 0
		System.out.println(x < z);	// 1
		System.out.println(x >= z);	// 0
		System.out.println(y >= z);	// 0
	}

}
