package ch03;

import java.util.Calendar;
import java.util.Scanner;

public class P06 {

	public static void main(String[] args) {
		String DOW[] = {"Sunday\t", "Monday\t", "Thuesdays", "Wednesday", "Thursday", "Friday\t", "Saturday"};
		int inputYear = 0;
		int inputMonth = 0;
		int nFirstDOW = 0;		// first day of week in input month
		int nLastDay = 0;		// last day about input month
		
		Scanner sc = new Scanner(System.in);
		
		// input year, month
		System.out.println("input year : ");
		inputYear = sc.nextInt();
		System.out.println("input month : ");
		inputMonth = sc.nextInt();
		
		// check first day of week and last day about input month
		Calendar inputDate = Calendar.getInstance();
		inputDate.set(inputYear, inputMonth-1, 1);
		nFirstDOW = inputDate.get(inputDate.DAY_OF_WEEK);			
		nLastDay = inputDate.getActualMaximum(Calendar.DAY_OF_MONTH);

		// print day of week
		for(int i = 0 ; i < 7 ; i++) {
			System.out.print(DOW[i] + "\t");
		}
		System.out.println();
		
		// print date
		int nDay = 1;
		for(int i = 0 ; i < 6 ; i++) {				// week
			for(int j = 0 ; j < 7 ; j++) {			// day
				if(i == 0) {								// first week
					if(j >= nFirstDOW - 1) {												
						System.out.print(nDay + "\t\t");
					} else {
						System.out.print("\t\t");
						nDay--;
					}
				} else {									// not first week
					System.out.print(nDay + "\t\t");
				}
				if(nDay == nLastDay) return;	
				nDay++;
			}
			System.out.println();
		}
		
		
	}

}
