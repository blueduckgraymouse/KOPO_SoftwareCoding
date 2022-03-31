package ch02;

// p11

public class Ex07 {

	public static void main(String[] args) {
		int x = 7;
		System.out.println(x);	// 7
		x += 3;
		System.out.println(x);	// 10
		x -= 3;
		System.out.println(x);	// 7
		x *= 3;
		System.out.println(x);	// 21
		x /= 3;
		System.out.println(x);	// 7
		x%= 3;
		System.out.println(x);	// 1
		
		System.out.println();
		
		x = 10;					// 1010(2)
		System.out.println(x);	// 10
		x &= 3;					// 0011(2), and연산
		System.out.println(x);	// 0010, 2  
		x |= 3;					// 0011(2), or연산
		System.out.println(x);	// 1011, 8+2+1=11 0011		??
		
		System.out.println();
		
		x = 10;					// 1010(2)
		System.out.println(x); 
		x ^= 3;					// 0011(2)
		System.out.println(x);
		x >>= 3;
		System.out.println(x);
		x<<= 3;
		System.out.println(x);
	}

}
