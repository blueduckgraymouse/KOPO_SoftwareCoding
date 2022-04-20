package k35_ch06;

public class K35_TvRemocon_ex04 {
	// 필드
	private int k35_now_channel = 10;;												// 현재 음량 정보를 나타내는 변수 7 으로 초기화
	private int k35_now_volume  = 7;												// 현재 음량 정보를 나타내는 변수 10 으로 초기화
	
	// 기본 생성자
	public K35_TvRemocon_ex04() {													
		System.out.println("TV가 켜졌습니다. 현재 채널은 " + k35_now_channel + ","		// 현재 채널과 음량 출력
							+ " 현재 음량은 " + k35_now_volume + " 입니다.");
		
		for (int i = 0 ; i < 10 ; i++)											// 10번 반복
			this.k35_channelUp();												//	 채널 up 메서드 호출

		System.out.println();
		
		for (int i = 0 ; i < 10 ; i++)											// 10번 반복
			this.k35_channelDn();												//	 채널 down 메서드 호출

		System.out.println();
		
		for (int i = 0 ; i < 5 ; i++)											// 5번 반복
			this.k35_volUp();													//	 음량 up 메서드 호출
		
		System.out.println();
		
		for (int i = 0 ; i < 5 ; i++)											// 5번 반복
			this.k35_volDn();													//	 음량 up 메서드 호출
	}
	
	// 채널 증가 메서드
	public void k35_channelUp() {
		k35_now_channel++;														// 채널 증가
		k35_check_channel();													// 채널이 1~999 범위를 벗어나려 하면 순환시키는 메서드 호출
		System.out.println("채널을 변경하였습니다. 현재 채널 : " + k35_now_channel);	// 현재 채널 출력
	}
	
	// 채널 감소 메서드
	public void k35_channelDn() {
		k35_now_channel--;														// 채널 감소
		k35_check_channel();													// 채널이 1~999 범위를 벗어나려 하면 순환시키는 메서드 호출
		System.out.println("채널을 변경하였습니다. 현재 채널 : " + k35_now_channel);	// 현재 채널 출력
	}

	// 음량 증가 메서드
	public void k35_volUp() {
		k35_now_volume++;														// 음량 증가
		k35_check_volume();														// 음량이 최소인 0일 때 감소하거나, 최대인 100 일 때 증가하려하면 유지시키는 메서드 호출
		System.out.println("음량을 변경하였습니다. 현재 음량 : " + k35_now_volume);		// 현재 음량 출력
	}
	
	// 음량 감소 메서드
	public void k35_volDn() {
		k35_now_volume--;														// 음량 감소
		k35_check_volume();														// 음량이 최소인 0일 때 감소하거나, 최대인 100 일 때 증가하려하면 유지시키는 메서드 호출
		System.out.println("음량을 변경하였습니다. 현재 음량 : " + k35_now_volume);		// 현재 음량 출력
	}
	
	// 채널 순환 메서드
	private void k35_check_channel() {
		if (k35_now_channel > 1000)												// 채널이 999번을 넘어가면
			k35_now_channel = 1;												//   1 번으로 변경
		if (k35_now_channel < 1) {												// 채널이 1번 밑으로 떨어지면
			k35_now_channel = 999;												//   999 번으로 변경
		}
	}
	
	// 을량 최대, 최소 제한 메서드
	private void k35_check_volume() {
		if (k35_now_volume > 100)												// 음량이 최대 음량값이 100을 넘어가면
			k35_now_volume = 100;												//   100 으로 유지
		if (k35_now_volume < 0) {												// 음량이 최소 음량값이 0을 밑으로 떨어지려 하면	
			k35_now_volume = 1;													//   0 으로 유지
		}
	}
}
