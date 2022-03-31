package ch05;

public class P07_base {

	public static void main(String[] args) {
		for (int i = 1; i <= 5; i++) {
			System.out.println(Factorial(i));
		}
	}

	private static int Factorial(int number) {
		if (number == 1) {
			return 1;
		} else {
			return number * Factorial(--number);
		}
	}
}
