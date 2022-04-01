package pack1;

public class Main implements Parents_interface{

	public static void main(String[] args) {
		Main main = new Main();
		main.myName();
		main.myAge();
		//main.myAge(50);
		
	}

	@Override
	public void myName() {
		System.out.println("아빠는 폴, 엄마는 캐서린");
	}

	@Override
	public void myAge() {
		System.out.println("아빠는 45, 엄마는 40");
	}
	
//	@Override 		// error, 오버라이딩은 메서드명과 파리미터의 구성까지 동일해야 한다.
//	public void myAge(int age) {
//		System.out.println("아빠는 45, 엄마는 40" + age);
//	}

}
