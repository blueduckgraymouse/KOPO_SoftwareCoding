package ch09;

import java.util.ArrayList;
import java.util.Collections;

public class Ex02 {

	public static void main(String[] args) {
		ArrayList<String> name = new ArrayList<String>();
		name.add("kim");
		name.add("lee");
		name.add("john");
		System.out.println(name);
		System.out.println(name.size());
		System.out.println(name.get(2));	// 특정 순서의 값 꺼내기
		
		name.remove(1);
		System.out.println(name);
		
		name.set(1, "park");			// set은 특정 순서의 값을 설정. (순서, 값)
		System.out.println(name);
		System.out.println(name.size());
		
		name.add("lee");				// add는 마지막 순서에 추가
		Collections.sort(name);			// 정렬
		System.out.println(name);
		
		
		Collections.reverse(name);		// 역순 정렬
		System.out.println(name);
		
	}

}
