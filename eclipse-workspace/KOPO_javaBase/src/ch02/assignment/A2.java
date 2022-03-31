package ch02.assignment;

public class A2 {
	public static void main(String[] args) {
		int d = 2;
		while(true) {
			int i = 2;
			switch(d) {
				case 2:
					while(i<10) {
						System.out.println("2 X " + i + " = " + (2 * i));
						i++;
					}
					break;
				case 3:
					while(i<10) {
						System.out.println("3 X " + i + " = " + (3 * i));
						i++;
					}
					break;
				case 4:
					while(i<10) {
						System.out.println("4 X " + i + " = " + (4 * i));
						i++;
					}
					break;
				case 5:
					while(i<10) {
						System.out.println("5 X " + i + " = " + (5 * i));
						i++;
					}
					break;
				case 6:
					while(i<10) {
						System.out.println("6 X " + i + " = " + (6 * i));
						i++;
					}
					break;
				case 7:
					while(i<10) {
						System.out.println("7 X " + i + " = " + (7 * i));
						i++;
					}
					break;
				case 8:
					while(i<10) {
						System.out.println("8 X " + i + " = " + (8 * i));
						i++;
					}
					break;
				case 9:
					while(i<10) {
						System.out.println("9 X " + i + " = " + (9 * i));
						i++;
					}
					break;
				default:
					return;
			}
			System.out.println();
			d++;
		}
	}
}

