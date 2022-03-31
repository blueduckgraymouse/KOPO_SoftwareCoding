package ch05;

public class Temp {

	public static void main(String[] args) {
		String a = "abde*ae";
		String b[] = a.split("\\*");
		for(String aa : b) {
			System.out.println(aa);
		}
	}

}
