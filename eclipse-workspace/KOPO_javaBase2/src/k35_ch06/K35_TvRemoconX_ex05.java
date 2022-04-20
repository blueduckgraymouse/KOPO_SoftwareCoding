package k35_ch06;

public class K35_TvRemoconX_ex05 extends K35_TvRemocon_ex04 {
	int k35_battery_capacity = 75;	// 현재 배터리 잔량을 75%라고 가정.
	
	// 기본생성자
	K35_TvRemoconX_ex05() {
		super();				// 부모클래스인 K35_TvRemocon_ex04.class의 기본생성자를 호출하여 내용을 실행한다.
	}
	
	// 배터리 잔량 확인 메서드
	public void k35_check_battery() {
		System.out.println("\n현재 배터리 잔량은 " + k35_battery_capacity + "% 입니다.");		// 배터리 잔량 출력
	}
}
