package ch10.assignment2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

public class A1 {

	public static void main(String[] args) {
		ArrayList<String> arr = new ArrayList<String>();
		
		int i = 1;
		while (true) {
			try {
				System.out.println("input " + i + "th name and score :");
	            Scanner sc = new Scanner(System.in);
	            String input = sc.nextLine();
	            if (input.equals("end")) {
	            	break;
	            }
	            String[] inputs = input.split(" ");
	            if (Integer.parseInt(inputs[1]) > 0 && Integer.parseInt(inputs[1]) <= 100) { 
	               arr.add(input);
	            }
	            i++;
	         } catch (Exception e) {
	            System.out.println("wrong input.");
	         }
		}
      
      Collections.sort(arr, new usersort());

      System.out.println("#Rank");
      Iterator iterator = arr.iterator();
      while(iterator.hasNext()) {
         String data = (String)iterator.next();
         System.out.print(data.split(" ")[0]);
         if(iterator.hasNext()) {
            System.out.print(", ");
         }
      }
   }
}


class usersort implements Comparator<String> {
   public int compare(String s1, String s2) {
         String score1 = s1.split(" ")[1];
         String score2 = s2.split(" ")[1];
         int iScore1 = Integer.parseInt(score1);
         int iScore2 = Integer.parseInt(score2);
         return (iScore1 - iScore2) * -1;
   }
}
