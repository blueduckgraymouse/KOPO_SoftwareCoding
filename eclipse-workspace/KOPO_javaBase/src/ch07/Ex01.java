package ch07;

public class Ex01 {

	public static void main(String[] args) {
		int[] numbers = new int[10];
		String[] name = {"kim", "lee", "park", "choi"};
		
		System.out.println(numbers.length);
		System.out.println(name.length);
		
		for (int i = 0 ; i < numbers.length ; i++) {
			System.out.print(numbers[i] + "/");
		}
		
		for (int i = 0 ; i < name.length ; i++) {
			System.out.println(name[i]);
		}
	}

}
