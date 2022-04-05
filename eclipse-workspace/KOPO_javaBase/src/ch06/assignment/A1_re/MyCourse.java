package ch06.assignment.A1_re;

import java.util.Scanner;

public class MyCourse {
	String[] courses;
	
	public MyCourse(String[] courses) {
		this.courses = courses;
	}

	public void myCourse(boolean[] regCheck) {
		printMyCourses(regCheck);
		waitExitCode();
	}

	private void printMyCourses(boolean[] regCheck) {
		String output = "";
		for(int i = 0 ; i < regCheck.length ; i++) {
			if(regCheck[i] == true) {
				output = output + (i + 1) + ". " + courses[i] + "\n";
			}
		}
		
		if (output.equals("")) {
			System.out.println("Emply. Register Courses.");
		} else {
			System.out.println(output);
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
