package crawling_bakery;

import java.util.Comparator;

class UserSort implements Comparator {
	public int compare(Object o1, Object o2) {
		if(o1 instanceof Comparable && o2 instanceof Comparable) {
			String s1 = String.valueOf(o1);
			String s2 = String.valueOf(o2);
			String count1 = s1.split(" ")[1];
			String count2 = s2.split(" ")[1];
			int iCount1 = Integer.parseInt(count1);
	        int iCount2 = Integer.parseInt(count2);
	        Comparable c1 = (Comparable)iCount1;
	        Comparable c2 = (Comparable)iCount2;
	        return c1.compareTo(c2) * -1;
		}
	    return -1;
	}
}
