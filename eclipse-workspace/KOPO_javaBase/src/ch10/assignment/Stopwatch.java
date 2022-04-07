package ch10.assignment;

public class Stopwatch extends Thread {
	private int passedSeconds = 0;
	private boolean flag = true;
	
	public void run() {
		while(flag) {
			try {
				Thread.sleep(100);
				passedSeconds++;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public int getPassedSeconds() {
		return passedSeconds;
	}
	
	public void reset() {
		passedSeconds = 0;
	}
	
	public void end() {
		flag = false;
	}
	
}
