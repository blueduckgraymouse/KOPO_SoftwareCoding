package ch06.assignment.A2;

public class ReverseNumber_number {
	int reversedNumber = 0;
	
	public void reverseNumber(int number) {
		int size = Integer.toString(number).length();
		int digit = 0;
		
		for(int i = 0 ; i < size ; i++) {
			reversedNumber = reversedNumber + (((number / (int)Math.pow(10, size - i - 1)) % 10) * (int)Math.pow(10, i));
			digit++;
		}
	}

	public void printReversedNumber() {
		System.out.println(reversedNumber);
	}
}
