package ch06.assignment.A1;

import java.util.Scanner;

public class Registercourse {
	String[] courses;
	String DB = "";
	
	public Registercourse(String DB, String[] courses) {
		this.DB = DB;
		this.courses = courses;
	}
	
	public String excuteRegistration() {
		while (true) {
			printCourseList();
			int courseNumber = selectCourseNumber(courses);
			if (courseNumber == 0)
				break;
			DB = registerCorse(DB, courseNumber);
		}
		return DB;
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
	
	private String registerCorse(String DB, int courseNumber) {
		DB = DB + (courseNumber - 1) + "/";
		System.out.println("\"" + courses[courseNumber-1] +"\" is registered.");
		return DB;
	}

}
