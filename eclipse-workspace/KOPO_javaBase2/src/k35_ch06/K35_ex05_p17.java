package k35_ch06;

/**
 * 소프트웨어코딩 심화 6강 - 객체지향 조금만 알기
 * 
 * 생성자(constructor) - p17
 * 
 * @author KOPO
 */
public class K35_ex05_p17 {

	public static void main(String[] args) {
		K35_TvRemoconX_ex05 k35_remoconX = new K35_TvRemoconX_ex05();		// TV리모콘 객체 생성
		
		k35_remoconX.k35_check_battery();	// 자식클래스에서 새로 만든 배터리 잔량을 확인하는 메서드를 호출한다.
	}

}

