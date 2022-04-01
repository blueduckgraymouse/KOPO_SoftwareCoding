package ch06.assignment.A5;

public class UnitConverter {
	int[] menus;
	int value;
	double convertedValue;

	public void excute(double[] unitValueBase_cm, int[] menuNumbers, double value) {
		convertedValue = value * unitValueBase_cm[menuNumbers[0] - 1] / unitValueBase_cm[menuNumbers[1] - 1];
	}

	public void print() {
		System.out.printf("result : %.6f", convertedValue);
	}

}
