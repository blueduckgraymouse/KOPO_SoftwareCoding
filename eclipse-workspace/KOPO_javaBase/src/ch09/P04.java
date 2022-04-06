package ch09;

import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class P04 {

	public static void main(String[] args) {
		HashMap<String, Integer> data = new HashMap<String, Integer>();
		P04 p04 = new P04();
		
		data = p04.inputData(data);
//		System.out.println(sortMapbyKey(data));
//		data.en
		
	}

	private HashMap<String, Integer> inputData(HashMap<String, Integer> data) {
		System.out.println("#input (exit code : \"exit\")");
		while(true) {
			Scanner sc = new Scanner(System.in);
			try {
				System.out.println("input a name and a score :");
				String input = sc.nextLine();
				if(input.equals("exit"))
					return data;
				String[] inputs = input.split(" ");
				if(inputs.length != 2)
					 new Exception();
				data.put(inputs[0], Integer.parseInt(inputs[1]));
			} catch(Exception e) {
				System.out.println("wrong input.");
			}
		}
	}

}
