package ch09;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// 문제의도는 이름이랑 점수랑 각각 정렬.

public class P4_map {

	public static void main(String[] args) {
		Map<String, Integer> data = new HashMap<String, Integer>();
		P4_map p04 = new P4_map();
		
		data = p04.inputData(data);
		
		Object[] mapkey = data.keySet().toArray();
		Arrays.sort(mapkey);
		
		for (String nKey : data.keySet())
		{
			System.out.println(data.get(nKey));
		}

		
	}

	private Map<String, Integer> inputData(Map<String, Integer> data) {
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
