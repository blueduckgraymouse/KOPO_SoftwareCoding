package ch05;

public class Ex07 {

	public static void main(String[] args) {
		for (int i = 1 ; i <= 10 ; i++) {
			System.out.println(SumToOne(i));
		}
	}

	public static int SumToOne(int number) {
		if (number == 1) {
			return 1;
		} else {
			return number + SumToOne(--number);
		}
	}

}
