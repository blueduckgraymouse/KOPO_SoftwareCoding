package ch10;

public class Ex04 {

	public static void main(String[] args) {
		Thread thread1 = new MultiThreadTest("[ Thread " + 1 + "]");
		thread1.start();
		Thread thread2 = new MultiThreadTest("[ Thread " + 2 + "]");
		thread1.setPriority(Thread.MAX_PRIORITY);	// 우선순위 부여 -> 따라서 2가 먼저 출력
		thread2.start();
	}

}

class MultiThreadTest extends Thread {
	public MultiThreadTest(String threadName) {
		this.setName(threadName);
	}
	
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(this.getName() + " Thread_Start ");
	}
	
}
