package ch06.assignment.A1;

import java.util.Scanner;

public class MyCourse {
	String[] courses;
	String DB;
	
	public MyCourse(String DB, String[] courses) {
		this.DB = DB;
		this.courses = courses;
	}

	public void myCourse() {
		printMyCourses(DB);
		waitExitCode();
	}

	private void printMyCourses(String DB) {
		if (DB.length() != 0) {
			String[] data = DB.split("/");
			for(int i = 0 ; i < data.length ; i++) {
				int myCourseNumber = Integer.parseInt(data[i]);
				System.out.println(i + ". " + courses[myCourseNumber]);
			}
		} else {
			System.out.println("Emply. Register Courses.");
		}
		System.out.println("= end = ");
	}
	
	private void waitExitCode() {
		while (true) {
			System.out.print("-> ");
			Scanner sc = new Scanner(System.in);
			try {
				int exitCode = sc.nextInt();
				if(exitCode == 0)
					break;
				else
					System.out.println("if you want to go back, input 0.");
			} catch (Exception e) {
				System.out.println("wrong input. exit code : 0");
			}
		}
	}
	

}
