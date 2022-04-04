package ch07;

public class Ex03 {

	public static void main(String[] args) {
		int[][] numbers = {{1, 2, 3}, {4, 5}};
//		System.out.println(numbers.length);
//		System.out.println(numbers[0].length);
//		System.out.println(numbers[0].length);
		
		for (int i = 0 ; i < numbers.length ; i++) {
			for(int j = 0 ; j < numbers[i].length ; j++) {
			System.out.print(numbers[i][j] + " ");
			}
			System.out.println();
		}
	}

}
