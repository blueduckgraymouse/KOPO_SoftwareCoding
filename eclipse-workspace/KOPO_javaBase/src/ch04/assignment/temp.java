package ch04.assignment;

import java.util.ArrayList;
import java.util.List;

public class temp {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(0);
		
		List<Integer> output = fcn(list);
		
		for(int i = 0 ; i <list.size() ; i++) {
			System.out.println(list.get(i));
		}
	}
	
	public static List<Integer> fcn(List<Integer> list) {
		list.add(1);
		return list;
	}

}
