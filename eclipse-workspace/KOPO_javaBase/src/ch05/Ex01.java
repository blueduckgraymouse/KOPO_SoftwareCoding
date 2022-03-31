package ch05;

public class Ex01 {

	public static void main(String[] args) {
		showMyMoney1();
		
		Ex01 ex01 = new Ex01();
		ex01.showMyMoney2();
		ex01.showMyMoney3();
		ex01.showMyMoney4();
		ex01.showMyMoney5();
	}
	
	public static void showMyMoney1() {		// static, 호출시 메모리에 올리가는 것이 아닌 프로그램이 실행되면
		System.out.println("1000won");		// 			메모리에 상주. - 따라서 객체 생성 안해도 됨.
		// showMyMoney2();					// error,  static필요.
	}
	
	public void showMyMoney2() {			// public, 	 
		System.out.println("1000won");
		showMyMoney1();
	}
	
	private void showMyMoney3() {
		System.out.println("1000won");
		Ex01 ex01 = new Ex01();
		ex01.showMyMoney2();
	}
	
	protected void showMyMoney4() {
		System.out.println("1000won");
	}
	
	void showMyMoney5() {
		System.out.println("1000won");
	}
	
}
