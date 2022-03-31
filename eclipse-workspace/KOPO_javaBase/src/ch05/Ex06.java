package ch05;

public class Ex06 {

	public static void main(String[] args) {
		int sum1;
		sum1 = addOperation1(5, 12);
		System.out.println(sum1);
		double sum2;
		sum2 = addOperation2(5, 12);
		System.out.println(sum2);
	}

	public static int addOperation1(int a, int b) {
		int sum = a + b;
		return sum;
	}
	public static double addOperation2(int a, int b) {
		double sum = a + b;
		return sum;
	}
}
