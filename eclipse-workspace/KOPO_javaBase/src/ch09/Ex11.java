package ch09;

import java.util.Stack;

public class Ex11 {

	public static void main(String[] args) {
		Stack<String> stack = new Stack<String>();
		stack.add("kim");
		System.out.println(stack);
		stack.add("lee");
		System.out.println(stack);
		stack.add("ho");
		System.out.println(stack);
		stack.add("jang");
		System.out.println(stack);
		stack.add("choi");
		System.out.println(stack);
		
		System.out.println(stack.pop());
		System.out.println(stack);
		System.out.println(stack.pop());
		System.out.println(stack);
	}
	
	

}
