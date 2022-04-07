package ch10;

public class Ex06 implements Runnable {

	public static void main(String[] args) {
		Ex06 ex06 = new Ex06();
		Thread thread = new Thread(ex06);
		thread.start();
	}
	
	@Override
	public void run() {
		System.out.println("implements Runnable");
	}
	
}
