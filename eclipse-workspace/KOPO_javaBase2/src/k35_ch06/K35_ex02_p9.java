package k35_ch06;

/**
 * 소프트웨어코딩 심화 6강 - 객체지향 조금만 알기
 * 
 * 클래스, 객체, 인스턴스 - p9
 * 
 * @author KOPO
 */
public class K35_ex02_p9 {

	public static void main(String[] args) {
		K35_TvRemocon_ex02 k35_remocon = new K35_TvRemocon_ex02();		// TV리모콘 객체 생성
	
		for (int i = 0 ; i < 10 ; i++)							// 10번 반복
			k35_remocon.k35_channelUp();						//	 채널 up 메서드 호출

		System.out.println();
		
		for (int i = 0 ; i < 10 ; i++)							// 10번 반복
			k35_remocon.k35_channelDn();						//	 채널 down 메서드 호출

		System.out.println();
		
		for (int i = 0 ; i < 5 ; i++)							// 5번 반복
			k35_remocon.k35_volUp();							//	 음량 up 메서드 호출
		
		System.out.println();
		
		for (int i = 0 ; i < 5 ; i++)							// 5번 반복
			k35_remocon.k35_volDn();							//	 음량 up 메서드 호출
	}

}
