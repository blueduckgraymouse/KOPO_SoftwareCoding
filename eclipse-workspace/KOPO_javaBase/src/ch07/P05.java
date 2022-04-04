package ch07;

public class P05 {
	
	public static void main(String[] args) {
		String[] subNames = {"Korean", "English", "Math"};
		String[] stuNames = {"Jeong", "Pyo", "Choi", "Mike"};
		int[][] scores = {{70, 80, 100},
							{60, 70 ,86},
							{54, 100, 82},
							{87, 95, 79}};
		
		P05 p05 = new P05();
		
		p05.printStatisticsAboutSubject(scores, subNames);
		System.out.println();
		p05.printStatisticsAboutStudent(scores, stuNames);
	}

	private void printStatisticsAboutSubject(int[][] scores, String[] subNames) {
		int[] sum = new int[scores[0].length];
		double[] average = new double[scores[0].length];
		int[] minimum = new int[scores[0].length];
		int[] maximum = new int[scores[0].length];
		
		System.out.println("Statistics About Subject");
		
		for (int i = 0 ; i < scores[0].length ; i++) {	// column
			for (int j = 0 ; j < scores.length ; j++) {	// row
				if (j == 0) {	// initialize min, max
					minimum[i] = scores[j][i];
					maximum[i] = scores[j][i];
				} else {
					if(minimum[i] > scores[j][i])
						minimum[i] = scores[j][i];
					if(maximum[i] < scores[j][i])
						maximum[i] = scores[j][i];
				}
				sum[i] = sum[i] + scores[j][i];
			}
			
			average[i] = (double) sum[i] / scores.length;
			System.out.println(subNames[i] + "\t- sum : " + sum[i] + " / average : " + average[i] + " / minimum : " + minimum[i] + " / maximum : " + maximum[i]);
		}
	}
	
	private void printStatisticsAboutStudent(int[][] scores, String[] stuNames) {
		int[] sum = new int[scores.length];
		double[] average = new double[scores.length];
		int[] minimum = new int[scores.length];
		int[] maximum = new int[scores.length];
		
		System.out.println("Statistics About Student");
		
		for (int i = 0 ; i < scores.length ; i++) {			// column
			for (int j = 0 ; j < scores[0].length ; j++) {	// row
				if (j == 0) {
					minimum[i] = scores[i][j];
					maximum[i] = scores[i][j];
				} else {
					if (minimum[i] > scores[i][j])
						minimum[i] = scores[i][j];
					if (maximum[i] < scores[i][j])
						maximum[i] = scores[i][j];
				}
				sum[i] = sum[i] + scores[i][j];
			}
			
			average[i] = (double) sum[i] / scores.length;
			System.out.println(stuNames[i] + "\t- sum : " + sum[i] + " / average : " + average[i] + " / minimum : " + minimum[i] + " / maximum : " + maximum[i]);
		}
	}
	
}