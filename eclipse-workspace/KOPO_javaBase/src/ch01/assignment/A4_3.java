package ch01.assignment;

public class A4_3 {
	public static void main(String[] args) {
		int x = 7;
		System.out.println(x);
		x += 3;
		System.out.println(x);
		x -= 3;
		System.out.println(x);
		x *= 3;
		System.out.println(x);
		x /= 3;
		System.out.println(x);
		x %= 3;
		System.out.println(x);
		
		System.out.println();
		
		x = 10;
		System.out.println(x);
		x &= 3;
		System.out.println(x);
		x |= 3;
		System.out.println(x);
		
		System.out.println();
		
		x = 10;
		System.out.println(x);
		x ^= 3;
		System.out.println(x);
		x >>= 3;
		System.out.println(x);
		x <<= 3;
		System.out.println(x);
	}
}
