package ch08;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class P01_set {
	
	public static void main(String[] args) {
		P01_set p01 = new P01_set();
		
		Set<Double> setNum1 = p01.inputNumbers();
		Set<Double> setNum2 = p01.inputNumbers();
		
		Set<Double> dupliNums = p01.checkDuplication(setNum1, setNum2);
		
		p01.print(dupliNums);
	}

	/* input numbers
	 * return : a set carrying numbers of a input
	 */
	private Set<Double> inputNumbers() {
		Set<Double> nums = new HashSet();
		
		System.out.println("input numbers 1 : ");
		while (true) {
			Scanner sc = new Scanner(System.in);
			try {
				String sNumbers = sc.nextLine();
				String[] snArr = sNumbers.split(" ");
				for (int i = 0 ; i < snArr.length ; i++) {
					nums.add(Double.parseDouble(snArr[i]));
				}
				break;
			} catch (Exception e) {
				System.out.println("worng input. input numbers.");
			}
		}
		
		return nums;
	}

	/* find duplicated numbers
	 * parameter : two sets carrying numbers of a input
	 * return : a set carrying duplicated numbers
	 */
	private Set<Double> checkDuplication(Set<Double> setNum1, Set<Double> setNum2) {
		Set<Double> dupliNumSet = new HashSet<Double>();
		
		Iterator<Double> iter = setNum2.iterator();
		while (iter.hasNext()) {
			double dupliNum = iter.next();
			if (setNum1.contains(dupliNum)) {
				dupliNumSet.add(dupliNum);
			}
		}
		
		return dupliNumSet;
	}
	
	/* print factors of an array
	 * parameter : a set carrying duplicated numbers
	 */
	private void print(Set<Double> dupliNums) {
		for (Object num : dupliNums.toArray()) {
			System.out.print (num + " ");
		}
	}
	
}
