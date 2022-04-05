package ch06.assignment.A2;

public class ReverseNumber {
	String reversedNumber = "";
	
	public void reverseNumber(int number) {
		String sNumber = Integer.toString(number);
		for (int i = sNumber.length() ; i > 0 ; i--) {
			reversedNumber = reversedNumber + sNumber.charAt(i-1);
		}
	}

	public void printReversedNumber() {
		System.out.println(reversedNumber);
	}
}