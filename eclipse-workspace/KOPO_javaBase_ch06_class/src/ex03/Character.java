package ex03;

public class Character {
	String name;
	int age;
	int power;
	
	public Character() {
		System.out.println("call default constructor");
	}
	
	public Character(String name, int age, int power) {
		this();
		this.name = name;
		this.age = age;
		this.power = power;
	}
}
