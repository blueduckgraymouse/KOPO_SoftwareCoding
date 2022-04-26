package review1;

public class R1 {

	public static void main(String[] args) {
//		String s1 = "abcdefg";
//		String s2 = "fgdceba";
//		String s1 = "Abbbccd";
//		String s2 = "Bbbccda";
		String s1 = "Abc";
		String s2 = "cbA";
		
		System.out.println(s1 + " / " + s2);
		compare(s1, s2);
	}

	private static void compare(String s1, String s2) {
		String s1_sorted = sort(s1);
		String s2_sorted = sort(s2);
		System.out.println(" => " + s1_sorted + " / " + s2_sorted);
		
		if (s1_sorted.equals(s2_sorted)) {
			System.out.println("yes");
		} else {
			System.out.println("no");
		}
	}
	
	private static String sort(String s) {
		char[] cArr = s.toCharArray();
		
		// bubble sort
		for (int i = 0 ; i < s.length() ; i++) {
			for (int j = 0 ; j < s.length() - i - 1  ; j++) {
				if ((int) cArr[j] > (int) cArr[j + 1]) {
					char temp = cArr[j+1];
					cArr[j+1] = cArr[j];
					cArr[j] = temp;
				}
			}
		}
		
		// char array to String
		String s_sorted = "";
		for (int i = 0 ; i < s.length() ; i++) {
			s_sorted += cArr[i];
		}
		
		return s_sorted;
	}

}
