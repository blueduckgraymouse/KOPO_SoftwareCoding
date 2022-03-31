package ch05;

public class Ex03 {
	public static final int johnsAge = 37;
	public static final int katesAge = 55;
	public static final int mikesAge = 31;
	static final int age1 = 31;
	final int age2 = 31;
	//static final int age3;
	//final int age4;
	static int age5;
	
	/*
	 * 변수에서 static vs final vs static final 차이
	 * static은 메모리 관점. 메서드와 마찬가지로 해당 클래스의 객체 생성없이 사용 가능
	 * 		보통 값이 변하지 않을 때 static으로 선언할 때 이점이 있으므로 final과 비슷한 의미로 사용하거나 같이 사용되어 혼동 가능
	 * final은 상수변수로 선언을 의미
	 * static final은 둘 다.
	 */

	public static void main(String[] args) {
		printAge(mikesAge);
		printAge(mikesAge, 10);
		//age1 = 10; - 
		//age2 = 10;
		//age3 = 10;
		//age4 = 10;
		age5 = 10;
		System.out.println(age1);
	}
	
	public static void printAge(int personAge) {
		System.out.println("Age : " + personAge);
	}
	
	public static void printAge(int personAge, int nAge) {
		System.out.println("Age : " + (personAge + nAge));
		System.out.println("Age : " + personAge + nAge);
	}
}
