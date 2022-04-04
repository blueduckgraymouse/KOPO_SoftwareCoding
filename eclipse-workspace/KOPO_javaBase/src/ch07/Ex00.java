package ch07;

public class Ex00 {

	public static void main(String[] args) {
		int[] kopoNum = new int[45];
//		kopoNum[0] = 232;
//		System.out.println(kopoNum);
		for (int i = 0 ; i < kopoNum.length ; i++) {
			kopoNum[i] = i * 10;
		}
		for (int i = 0 ; i < kopoNum.length ; i++) {
			System.out.println(kopoNum[i]);
		}
	}

}
