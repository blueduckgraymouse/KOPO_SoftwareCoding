package ch03;

import java.math.BigDecimal;

public class Ex09 {

	public static void main(String[] args) {
		String text = "-31.5123121231231231231231513453145";
		System.out.println(text);
		System.out.println(Float.parseFloat(text));
		System.out.println(Double.parseDouble(text));
		
		System.out.println();
		
		Double d = Double.parseDouble(text);
		System.out.println(d);
		System.out.println(d.intValue());
		
		System.out.println();
		
		BigDecimal bd = new BigDecimal(text);	// BigDecimal BigDecimal(string)
		System.out.println(bd);
		System.out.println(Math.round(d)); 	// 반환형 int
		System.out.println(Math.floor(d));	// 반환형 double
		System.out.println(Math.ceil(d));	// 반환형 double
		
		System.out.println();
		
		float f = 3.26f;
		System.out.println(f);
		System.out.println((int)f);
	}

}
