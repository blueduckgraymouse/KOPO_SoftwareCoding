package ch01.assignment;

public class A4_1 {
	public static void main(String[] args) {
		byte 	bTemp 	= 121;
		short 	sTemp 	= 12312;
		int		iTemp	= 727;
		long	lTemp	= 123123223;
		float	fTemp	= 1.123456789f;
		double	dTemp	= 1.123456789;
		boolean	boolTemp= true;
		char	cTemp	= 'A';
		
		System.out.println(bTemp);
		System.out.println(sTemp);
		System.out.println(iTemp);
		System.out.println(lTemp);
		System.out.println(fTemp);
		System.out.println(dTemp);
		System.out.println(boolTemp);
		System.out.println(cTemp);
		System.out.println();
		
		// P1
		cTemp = 'B';
		System.out.println(cTemp);
		System.out.println();
		
		cTemp = 'C';
		System.out.println(cTemp);
		System.out.println();
		
		// P2
		iTemp = (int)'2';
		System.out.println(iTemp);
		System.out.println();
	}
}
