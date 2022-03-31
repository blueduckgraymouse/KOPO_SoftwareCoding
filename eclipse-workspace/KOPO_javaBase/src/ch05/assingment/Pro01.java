package ch05.assingment;

import java.util.Scanner;

public class Pro01 {

	public static void main(String[] args) {
		Pro01 pro01 = new Pro01();
		while (true) {
			String input = pro01.input();
			if (input.equals("exit"))
				break;
			pro01.cal(input);
			System.out.println();
		}
	}

	public String input() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("input number : ");
		return sc.next();
	}
	
	private void cal(String input) {
		Pro01 pro01 = new Pro01();
		
		if (input.contains("!")) {
			pro01.printFactorial(Integer.parseInt(input.substring(0, input.length()-1)));
		} else if (input.contains("P")) {
			int pIndex = input.indexOf('P');
			int n = Integer.parseInt(input.substring(0, pIndex));
			int r = Integer.parseInt(input.substring(pIndex+1));
			pro01.printPermutation(n, r);
		} else if (input.contains("C")) {
			int pIndex = input.indexOf('C');
			int n = Integer.parseInt(input.substring(0, pIndex));
			int r = Integer.parseInt(input.substring(pIndex+1));
			pro01.printCombination(n, r);
		}
	}

	public int factorial(int n) {
		if (n == 1)
			return 1;
		else
			return n * factorial(--n);
	}
	
	private void printFactorial(int n) {
		Pro01 pro01 = new Pro01();
		
		for (int i = n ; i > 0 ; i--) {
			System.out.print(i);
			if (i != 1)
				System.out.print(" X ");
		}
		System.out.print(" = ");
		System.out.print(pro01.factorial(n));
	}
	
	private void printPermutation(int n, int r) {
		Pro01 pro01 = new Pro01();
		
		System.out.print("(");
		for (int i = n ; i > n - r ; i--) {
			System.out.print(i);
			if (i != n - r + 1)
				System.out.print(" X ");
		}
		System.out.print(") = ");
		System.out.print(pro01.factorial(n) / pro01.factorial(n - r));
	}
	
	public void printCombination(int n, int r) {
		Pro01 pro01 = new Pro01();
		
		System.out.print("(");
		
		for (int i = n ; i > n - r ; i--) {
			System.out.print(i);
			if (i != n - r + 1)
				System.out.print(" X ");
		}
		
		System.out.print(") / (");
		
		for (int i = r ; i > 0 ; i--) {
			System.out.print(i);
			if (i != 1)
				System.out.print(" X ");
		}
		System.out.print(") = ");
		System.out.print(pro01.factorial(n) / pro01.factorial(n - r) / pro01.factorial(r));
	}

}
