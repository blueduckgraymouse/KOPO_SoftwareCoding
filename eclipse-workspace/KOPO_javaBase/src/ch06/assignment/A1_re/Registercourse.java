package ch06.assignment.A1_re;

import java.util.Scanner;

public class Registercourse {
	String[] courses;
	
	public Registercourse(String[] courses) {
		this.courses = courses;
	}
	
	public boolean[] excuteRegistration(boolean[] regCheck) {
		while (true) {
			printCourseList();
			int courseNumber = selectCourseNumber(courses);
			if (courseNumber == 0)
				break;
			regCheck = registerCorse(regCheck, courseNumber);
		}
		return regCheck;
	}

	private void printCourseList() {
		System.out.println("#Courses");
		for (int i = 0 ; i < courses.length ; i++) {
			System.out.println((i + 1) + ". " + courses[i]);
		}
		System.out.println("0. Back to Main");
	}
	
	private int selectCourseNumber(String[] courses) {
		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.println(" -> ");
			try {
				int CouresNumber = sc.nextInt();
				if (CouresNumber >= 0 && CouresNumber <= courses.length)
					return CouresNumber;
				else
					System.out.println("wrong input. input a course number.");
			} catch (Exception e) {
				System.out.println("wrong input. input a course number.");
			}
		}
	}
	
	private boolean[] registerCorse(boolean[] regCheck, int courseNumber) {
		if (regCheck[courseNumber-1] == false) {
			regCheck[courseNumber-1] = true;
			System.out.println("Success. \"" + courses[courseNumber-1] +"\" is registered.");
		} else {
			System.out.println("Fail. \"" + courses[courseNumber-1] +"\" is already registered.");
		}
		return regCheck;
	}

}
