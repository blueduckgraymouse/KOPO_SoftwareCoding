package ch03;

public class P02 {

	public static void main(String[] args) {
		String str = "I am a boy";
		System.out.println(str);
		
		char chr[] = str.toCharArray();
		
		for(int i = 0 ; i < chr.length ; i++) {
			if('Z' >= chr[i] && chr[i] >= 'A')		System.out.print((char)(chr[i] + 32));
			else if('z' >= chr[i] && chr[i] >= 'a')	System.out.print((char)(chr[i] - 32));
			else 									System.out.print(chr[i]);
		}
		
		//str.charAt(0)
	}

}
