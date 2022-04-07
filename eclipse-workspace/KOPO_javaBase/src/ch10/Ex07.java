package ch10;

public class Ex07 extends Thread {

	public static void main(String[] args) {
		Ex07 ex07 = new Ex07();
		Thread thread = new Thread(ex07);
		thread.start();
	}
	
	public void run() {
		System.out.println("extends Thread");
	}

}
