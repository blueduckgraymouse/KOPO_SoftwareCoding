package ch08;

import java.util.Scanner;

public class P01 {
	
	public static void main(String[] args) {
		P01 p01 = new P01();
		
		double[][] nArrs = p01.inputNumbers();
		
		double[] duplicatedNums = p01.checkDuplication(nArrs);
		
		p01.print(duplicatedNums);
	}

	/* input numbers 1, numbers 2
	 * return : a double type array by 2 carrying numbers of each inputs
	 */
	private double[][] inputNumbers() {
		double[][] nArrs = new double[2][];
		
		// input numbers 1
		System.out.println("input numbers 1 : ");
		while (true) {
			Scanner sc = new Scanner(System.in);
			try {
				String sNumbers = sc.nextLine();
				String[] snArr = sNumbers.split(" ");
				double[] dnArr = new double[snArr.length];
				for (int i = 0 ; i < snArr.length ; i++) {
					dnArr[i] = Double.parseDouble(snArr[i]);
				}
				nArrs[0] = dnArr;
				break;
			} catch (Exception e) {
				System.out.println("worng input. input numbers.");
			}
		}
		
		// input numbers 2
		System.out.println("input numbers 2 : ");
		while (true) {
			Scanner sc = new Scanner(System.in);
			try {
				String sNumbers = sc.nextLine();
				String[] snArr = sNumbers.split(" ");
				double[] dnArr = new double[snArr.length];
				for (int i = 0 ; i < snArr.length ; i++) {
					dnArr[i] = Double.parseDouble(snArr[i]);
				}
				nArrs[1] = dnArr;
				break;
			} catch (Exception e) {
				System.out.println("worng input. input numbers.");
			}
		}
		return nArrs;
	}

	/* find duplicated numbers
	 * parameter : a double type array by 2 carrying numbers of each inputs
	 * return : a double type array by 1 carrying duplicated numbers
	 */
	private double[] checkDuplication(double[][] nArrs) {
		String dupliNums = "";
		
		for (int i = 0 ; i < nArrs[0].length ; i++) {
			for (int j = 0 ; j < nArrs[1].length ; j++) {
				if (nArrs[0][i] == nArrs[1][j]) {
					dupliNums = dupliNums + nArrs[0][i] + " ";
				}
			}
		}
		
		String[] dupliSArr = dupliNums.split(" ");
		double[] dupliDArr = new double[dupliSArr.length];
		for (int i = 0 ; i < dupliDArr.length ; i++) {
			dupliDArr[i] = Double.parseDouble(dupliSArr[i]);
		}
		
		return dupliDArr;
	}
	
	/* print factors of an array
	 * parameter : a double type array by 1 carrying duplicated numbers
	 */
	private void print(double[] duplicatedNums) {
		for (int i = 0 ; i < duplicatedNums.length ; i++) {
			System.out.print(duplicatedNums[i] + " ");
		}
	}
	
}
