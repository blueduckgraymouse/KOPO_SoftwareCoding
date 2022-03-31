package ch02;

public class Ex06 {

	// ++연산자의 실행 기준은 매개변수로 사용될 떄, 줄 단위가 아님.
	
	public static void main(String[] args) {
		int a = 3;
		System.out.println(a++);
		System.out.println(a);
		System.out.println(++a);
		
		System.out.println((a++) + "/"+ (a) + "/" + (++a));
	}
	
}
