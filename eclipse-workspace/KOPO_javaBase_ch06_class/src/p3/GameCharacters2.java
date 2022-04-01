package p3;

public class GameCharacters2 {
	String name;
	int age;
	double offensePower;
	double defensePower;
	
	// 접근제어자 클래스명(매개변수 타입 매개변수명 , .. )
	public GameCharacters2(String name) {
		this.name = name;
	}

	public GameCharacters2(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public GameCharacters2(String name, int age, double offensePower) {
		this.name = name;
		this.age = age;
		this.offensePower = offensePower;
	}
	
	public GameCharacters2(String name, int age, double offensePower, double defensePower) {
		this.name = name;
		this.age = age;
		this.offensePower = offensePower;
		this.defensePower = defensePower;
	}
	
	public GameCharacters2(int age, double offensePower, double defensePower) {
		this.age = age;
		this.offensePower = offensePower;
		this.defensePower = defensePower;
	}
	
	public void printGameCharacters() {
		System.out.println(name + " / " + age + " / " + offensePower + " / " + defensePower);
	}
}
