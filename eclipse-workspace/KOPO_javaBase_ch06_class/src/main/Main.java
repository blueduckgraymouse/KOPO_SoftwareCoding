package main;

import ex06.AddtionalB;


public class Main {
	
	public static void main(String[] args) {
//		// ex01
//		AddtionalA addtionalA = new AddtionalA();
//		AddtionalA addtionalA = new AddtionalA("addtionalA");
//		addtionalA.printMultiple();
//		addtionalA.printHelloworld();
		
		
//		// ex02
//		PrintClass printClass = new PrintClass();
//		printClass.printAdd(1, 2);
		
//		PrintClass printClass = new PrintClass(1, 2, 5);
//		printClass.printAdd();
//		printClass.printAdd(3, 4);
//		printClass.printAdd2(6, 7);
		
		
//		// P1
//		Price price = new Price();
//		
//		String input = price.inputFNum();
//		
//		String[] fNums = input.split(" ");
//		int fNum1, fNum2, fNum3, fNum4;
//		int result;
//		switch(fNums.length) {
//			case 1:
//				fNum1 = Integer.parseInt(fNums[0]) - 1; 
//				result = price.calculate(fNum1);
//				break;
//			case 2:
//				fNum1 = Integer.parseInt(fNums[0]) - 1; 
//				fNum2 = Integer.parseInt(fNums[1]) - 1; 
//				result = price.calculate(fNum1, fNum2);
//				break;
//			case 3:
//				fNum1 = Integer.parseInt(fNums[0]) - 1; 
//				fNum2 = Integer.parseInt(fNums[1]) - 1; 
//				fNum3 = Integer.parseInt(fNums[2]) - 1; 
//				result = price.calculate(fNum1, fNum2, fNum3);
//				break;
//			case 4:
//				fNum1 = Integer.parseInt(fNums[0]) - 1; 
//				fNum2 = Integer.parseInt(fNums[1]) - 1; 
//				fNum3 = Integer.parseInt(fNums[2]) - 1; 
//				fNum4 = Integer.parseInt(fNums[3]) - 1; 
//				result = price.calculate(fNum1, fNum2, fNum3, fNum4);
//				break;
//			default:
//				return;
//		}
//		System.out.println(result);
		
		
//		// P1 - 선생님이 원했던 의도
//		Price price = new Price(100, 200, 300, 400);
//		// 입력받아서 해당 가격 출력을 원하셨음.
		
		
//		// ex03
//		Character c1 = new Character();
//		Character c2 = new Character("name", 30, 11);
		
		
//		// P2
//		GameCharacters gameCharacter1_1 = new GameCharacters("A", 200, 30.5, 32.1);
//		GameCharacters gameCharacter1_2 = new GameCharacters("B", 123, 47.1, 18.9);
//		GameCharacters gameCharacter1_3 = new GameCharacters("C", 765, 21.6, 42.3);
//		
//		gameCharacter1_1.printGameCharacters();
//		gameCharacter1_2.printGameCharacters();
//		gameCharacter1_3.printGameCharacters();
		
		
//		// ex04
//		Character2 character = new Character2();
//		character.printCharacter();
		
		
//		// P3
//		GameCharacters2 gameCharacter2_1 = new GameCharacters2("A", 200, 30.5, 32.1);
//		GameCharacters2 gameCharacter2_2 = new GameCharacters2("B", 123, 47.1, 18.9);
//		GameCharacters2 gameCharacter2_3 = new GameCharacters2("C", 765, 21.6, 42.3);
//		GameCharacters2 gameCharacter2_4 = new GameCharacters2("D");
//		GameCharacters2 gameCharacter2_5 = new GameCharacters2("E", 234);
//		GameCharacters2 gameCharacter2_6 = new GameCharacters2("F", 543, 56.2);
//		GameCharacters2 gameCharacter2_7 = new GameCharacters2("G", 146, 25.1, 39.2);
//		GameCharacters2 gameCharacter2_8 = new GameCharacters2(146, 25.1, 39.2);
//		
//		gameCharacter2_1.printGameCharacters();
//		gameCharacter2_2.printGameCharacters();
//		gameCharacter2_3.printGameCharacters();
//		gameCharacter2_4.printGameCharacters();
//		gameCharacter2_5.printGameCharacters();
//		gameCharacter2_6.printGameCharacters();
//		gameCharacter2_7.printGameCharacters();
//		gameCharacter2_8.printGameCharacters();
		
		
//		// ex05
//		Ex05 ex05 = new Ex05(5, 10, 20);
//		ex05.printIntger();
//		ex05.printAdd();
		
		
//		// ex06
//		AddtionalB addtionalB = new AddtionalB();	// other package
//		addtionalB.printA();	// public
//		//addtionalB.printB();	// error, priavte
//		//addtionalB.printC();	// error, protected
//		//addtionalB.printD();	// error, default
//		AddtionalC addtionalC = new AddtionalC();	// same apckage, other class
//		addtionalC.printA();	// public
//		//addtionalC.printB();	// error, priavte	// same apckage, same class
//		addtionalC.printC();	// protected
//		addtionalC.printD();	// default
		
		
//		// P05
//		P05.printHelloworld();
//		System.out.println(P05.addOperation(10, 20));
//		System.out.println(P05.subOperation(5, 2));
		
		
//		// P06 - static을 사용하면 no 객체 생성, 메모리 공유, 생성자 안 쓰고 객체에 값 변경하려면 변수에 직접 접근
//		P06_gameCharacters.printGameCharacters();
//		P06_gameCharacters.name = "D";
//		P06_gameCharacters.age = 260;
//		P06_gameCharacters.offensePower = 35.5;
//		P06_gameCharacters.defensePower = 38.9;
//		P06_gameCharacters.printGameCharacters();
//		P06_gameCharacters.name = "E";
//		P06_gameCharacters.age = 1213;
//		P06_gameCharacters.offensePower = 46.1;
//		P06_gameCharacters.defensePower = 38.9;
//		P06_gameCharacters.printGameCharacters();
		
		
		// P07 - static을 사용하면 all record 빼고 간단. => 일단 패스
		
	}
	
}



class Ex05 {
	int a, b, n;
	
	public Ex05(int a, int b, int n) {
		this.a = a;
		this.b = b;
		this.n = n;
	}
	
	public void printIntger() {
		System.out.println(this.n);
	}
	
	public void printAdd() {
		int sum;
		sum = this.a + this.b;
		System.out.println(sum);
	}
}


class P05 {
	
	public static void printHelloworld() {
		System.out.println("Hello, World");
	}
	
	public static int addOperation(int a, int b) {
		return a + b;
	}
	
	public static int subOperation(int a, int b) {
		return a - b;
	}

}


class P06_gameCharacters {
	
	static String name;
	static int age;
	static double offensePower;
	static double defensePower;

	public P06_gameCharacters(String name) {
		this.name = name;
	}

	public P06_gameCharacters(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public P06_gameCharacters(String name, int age, double offensePower) {
		this.name = name;
		this.age = age;
		this.offensePower = offensePower;
	}
	
	public P06_gameCharacters(String name, int age, double offensePower, double defensePower) {
		this.name = name;
		this.age = age;
		this.offensePower = offensePower;
		this.defensePower = defensePower;
	}
	
	public P06_gameCharacters(int age, double offensePower, double defensePower) {
		this.age = age;
		this.offensePower = offensePower;
		this.defensePower = defensePower;
	}
	
	public static void printGameCharacters() {
		System.out.println(name + " / " + age + " / " + offensePower + " / " + defensePower);
	}
	
	
}

//class P07 { - 문제의도 파악.
//	static String 	nameOfLastInput;
//	static int		KoreanScore;
//	static int		EnglishScore;
//	static int		MathScore;
//	
//	public void collectData(String name, int KoreanScore, int EnglishScore, int MathScore) {
//		nameOfLastInput = name;
//		KoreanScore
//	}
//}

