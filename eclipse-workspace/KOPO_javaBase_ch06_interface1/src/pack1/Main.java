package pack1;

public class Main extends Parents{

	public static void main(String[] args) {
		Main main = new Main();
		main.myName();
		main.myAge();
	}
	
	@Override
	public void myName() {
		super.myName();
	}
	public void myAge() {
		super.myName();
	}

}
