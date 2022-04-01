package ex02;

public class PrintClass {
	int a, b, n;
	
	public PrintClass(int a, int b, int n) {
		this.a = a;
		this.b = b;
		this.n = n;
	}
	
	public void printInteger(int n) {
		System.out.println(n);
	}
	
	public void printAdd(int a, int b) {
		int sum;
		sum = a + b;
		System.out.println(sum);
	}
	
	public void printAdd2(int a, int b) {
		int sum;
		sum = this.a + this.b;
		System.out.println(sum);
	}
	
	public void printAdd() {
		int sum;
		sum = this.a + this.b;
		System.out.println(sum);
	}
}
