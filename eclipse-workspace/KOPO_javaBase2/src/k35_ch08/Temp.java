package k35_ch08;

public class Temp {

	public static void main(String[] args) {
		String a = "a b c \n d e f";
		char[] b = a.toCharArray();
		for(char c : b) {
			System.out.println((int)c);
		}
	}

}
