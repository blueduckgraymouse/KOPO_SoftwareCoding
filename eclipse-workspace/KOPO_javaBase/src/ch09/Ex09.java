package ch09;

import java.util.LinkedList;
import java.util.Queue;

public class Ex09 {

	public static void main(String[] args) {
		Queue<String> name = new LinkedList<String>();
		name.offer("Kim");
		System.out.println(name);
		name.offer("lee");
		System.out.println(name);
		name.offer("lim");
		System.out.println(name);
		name.offer("choi");
		System.out.println(name);
		System.out.println(name.poll());		// FIFO이므로 처음 들어간 KIm 삭제
		System.out.println(name);				
		System.out.println(name.poll());
		System.out.println(name);
	}

}
