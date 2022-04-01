package ex04;

public class Character2 {
	String name;
	int age;
	int power;
	
	public Character2() {
		this("hello", 10, 20);
	}
	
	public Character2(String name, int age, int power) {
		this.name = name;
		this.age = age;
		this.power = power;
	}
	
	public void printCharacter() {
		System.out.println(name + " / " + age + " / " + power);
	}
}
