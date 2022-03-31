package ch03;

public class Ex05 {

	public static void main(String[] args) {
		System.out.println(Math.min(3,  13));
		System.out.println(Math.max(123, 54));
		System.out.println(Math.abs(-23.1));
		System.out.println(Math.sqrt(3));
		System.out.println(Math.random());		// 0.0 <= Math.random() < 1.0
		
		int random = (int)(Math.random() * 45); // 0~44
		System.out.println(random);	
	}

}
