package ch06.assignment.A4;

public class FindingTheNearestNumber {
	double theNearestNumber;
	
	public void execute(double iNumber, String sNumbers) {
		double[] iNumbers = splitNumbers(sNumbers);
		findingTheNearestNumber(iNumber, iNumbers);
	}

	private void findingTheNearestNumber(double iNumber, double[] iNumbers) {
		Double[] nearestNumber = new Double[2]; // 숫자, 거리
		for (int i = 0 ; i < iNumbers.length ; i++) {
			if (nearestNumber[0] == null) {
				nearestNumber[0] = (double)iNumbers[i];
				nearestNumber[1] = Math.abs((double)iNumber - iNumbers[i]);
			}
			else {
				if (nearestNumber[1] > Math.abs((double)iNumber - iNumbers[i])) {
					nearestNumber[0] = (double)iNumbers[i];
					nearestNumber[1] = Math.abs((double)iNumber - iNumbers[i]);
				}
			}
		}
		theNearestNumber = nearestNumber[0];
	}

	private double[] splitNumbers(String sNumbers) {
		String[] sArrNumbers = sNumbers.split(" ");
		double[] iArrNumbers = new double[sArrNumbers.length];
		for (int i = 0 ; i < sArrNumbers.length ; i++) {
			iArrNumbers[i] = Double.parseDouble(sArrNumbers[i]);
		}
		return iArrNumbers;
	}

	public void print() {
		System.out.println(theNearestNumber);
	}
	
}
