package ch10;

public class Ex01 {

	public static void main(String[] args) {
		String line = "abc";
		
		try {
			System.out.println(line.charAt(2));
		} catch (Exception e) {

		} finally {
			System.out.println("finally");
		}
		
		System.out.println("hello world");
	}
}
