package ch07;

import java.util.Arrays;
import java.util.Collections;

public class P04_alg {

	public static void main(String[] args) {
		P04_alg p04_alg = new P04_alg();
		
		int[] arr = {2, 33, 7, 5, 12, 34, 99, 25, 28, 53, 20};
		
		int[] resultAsc = p04_alg.sortArrAsc(arr);
		p04_alg.printArr(resultAsc);
		
		int[] resultDesc = p04_alg.sortArrDesc(arr);
		p04_alg.printArr(resultDesc);
	}

	private int[] sortArrAsc(int[] arr) {
		for (int i = 0 ; i < arr.length ; i++) {
			for (int j = i ; j < arr.length ; j++) {
				int temp = 0;
				if (arr[j] > arr[i]) {
					temp = arr[j];
					arr[j] = arr[i];
					arr[i] = temp;
				}
			}
		}
		return arr;
	}
	
	private int[] sortArrDesc(int[] arr) {
		for (int i = 0 ; i < arr.length ; i++) {
			for (int j = i ; j < arr.length ; j++) {
				int temp = 0;
				if (arr[j] < arr[i]) {
					temp = arr[j];
					arr[j] = arr[i];
					arr[i] = temp;
				}
			}
		}
		return arr;
	}

	private void printArr(int[] arr) {
		System.out.print("{");
		for (int i = 0 ; i < arr.length ; i++) {
			System.out.print(arr[i]);
			if (i != arr.length - 1)
				System.out.print(", ");
		}
		System.out.println("}");
	}
}
