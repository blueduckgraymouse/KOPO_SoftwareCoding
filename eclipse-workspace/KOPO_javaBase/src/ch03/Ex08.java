package ch03;

public class Ex08 {

	public static void main(String[] args) {
		String text = "Hello, hi,";
		System.out.println(text);
		System.out.println(text.length());
		System.out.println(text.charAt(2));
		
		char[] c = text.toCharArray();
		System.out.println(c[0]);
		
		System.out.println(text.replace("H", "h"));
		
		String[] t = text.split(",");
		System.out.println(t[0]);
		System.out.println(t[1]);
		
		//System.out.println(t[2]);
	
		// error message
		/*
		 * Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 2
		 * out of bounds for length 2 at ch03.Ex08.main(Ex08.java:19)
		 */

	}

}
