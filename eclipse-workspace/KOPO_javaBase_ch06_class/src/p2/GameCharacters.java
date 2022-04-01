package p2;

public class GameCharacters {
	String name;
	int age;
	double offensePower;
	double defensePower;
	
	public GameCharacters(String name, int age, double offensePower, double defensePower) {
		this.name = name;
		this.age = age;
		this.offensePower = offensePower;
		this.defensePower = defensePower;
	}
	
	public void printGameCharacters() {
		System.out.println(name + " / " + age + " / " + offensePower + " / " + defensePower);
	}
}
