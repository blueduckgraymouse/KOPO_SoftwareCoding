package ch05;

public class Ex08 {

	public static void main(String[] args) {
		System.out.println(multiply(1));
		System.out.println(multiply(2));
		System.out.println(multiply(3));
	}

	private static int multiply(int n) {
		if(n == 1) {
			return 1;
		} else {
			return n * multiply(--n);
			
		}
	}

}
