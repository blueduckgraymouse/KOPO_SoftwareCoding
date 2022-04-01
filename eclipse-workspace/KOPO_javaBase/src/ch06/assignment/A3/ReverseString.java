package ch06.assignment.A3;

public class ReverseString {
	String reversedNumber = "";
	
	public void reverseString(String str) {
		for (int i = str.length() ; i > 0 ; i--) {
			reversedNumber = reversedNumber + str.charAt(i-1);
		}
	}

	public void printReversedString() {
		System.out.println(reversedNumber);
	}
}
