package ch10;

public class Ex08 {
	
	public static void main(String[] args) {
		int[] numbers = {5, 10, 12};
		
		try {
			System.out.println(numbers[3]);
			
//			Integer a = null;
//			System.out.println(5 + a);
//		} catch (Exception e) {		-> 하위 exception에 unreachable이므로 에러 발생 -> 수정 요구
//			e.printStackTrace();
//			System.out.println("General Exception");
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("NullPointer Exception");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(numbers[2]);
			e.printStackTrace();
			System.out.println("ArrayIndexOutOfBounds Exception");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("General Exception");
		} finally {
			System.out.println("General Exception");
		}
	}
}
