package ch10;

public class P01 {

	public static void main(String[] args) {
		stopwatch();
	}

	private static void stopwatch() {
		for(int i = 1 ; i <= 100 ; i++) {
			try { 
				System.out.println(i);
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
