package ch02;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Temp {

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf1 = new SimpleDateFormat("YYYY-MM", new Locale("en", "US"));
		String a = "2018-01";
		Date d = sdf1.parse(a);
		SimpleDateFormat sdf2 = new SimpleDateFormat("YYYY-MMM", new Locale("en", "US"));
		System.out.println(sdf2.format(d));
		
//	    Date date = new Date();
//
//	    SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MMM", new Locale("en", "US"));
//
//	    System.out.println(date.getTime());
//	    System.out.println("Current Month in MMM format : " + sdf.format(date));
//	    
//	    String d = sdf.format(date);
//	    SimpleDateFormat sdf2 = new SimpleDateFormat("YYYY-MM", new Locale("en", "US"));
//	    Date ddate = sdf.parse(d);
//	    
//	    System.out.println(ddate.getTime());
//	    System.out.println("Current Month in MM format : " + sdf2.format(ddate));
//	    
	}
	
}
