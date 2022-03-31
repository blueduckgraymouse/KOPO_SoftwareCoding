package ch05;

public class Ex04 {

	public static void main(String[] args) {
		Ex04 ex04 = new Ex04();
		
		ex04.addOperation(12, 5);
		ex04.subOperation(12, 5);
		ex04.mulOperation(12, 5);
		ex04.divOperation(12, 5);
		ex04.modOperation(12, 5);
	}
	
	
	
	void addOperation(int n1, int n2) {
		System.out.println(n1 + n2);
	}
	
	void subOperation(int n1, int n2) {
		System.out.println(n1 - n2);
	}
	
	void mulOperation(int n1, int n2) {
		System.out.println(n1 * n2);
	}
	
	void divOperation(int n1, int n2) {
		if (n2 != 0)
			System.out.println(n1 / n2);
		else
			System.out.println("error");
	}
	
	void modOperation(int n1, int n2) {
		if (n2 != 0)
			System.out.println(n1 % n2);
		else
			System.out.println("error");
	}

}
