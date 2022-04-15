package k35_ch04;

import java.text.DecimalFormat;

/** 소프트웨어 코딩 심화 4강 - p28 실습
 * 
 *  피라미드 찍기
 * 
 * @author KOPO35
 *
 */
public class K35_ex15 {
	
	public static void main(String[] args) {
		String k35_item = "사과";											// 영수증에 찍을 물품 이름 을 item변수에 저장
		int k35_unit_price = 5000;										// 물품 가격을 unit_price변수에 저장
		int k35_num = 500;												// 물품 수량을 num변수에 저장
		int k35_total = k35_unit_price * k35_num;						// 총 가격 = 물품 가격 * 물품 수량의 값을 변수 total에 저장
		
		DecimalFormat k35_df = new DecimalFormat("###,###,###,###,###");// 세자리 마다 콤마를 찍는 양식의 DecimalFormat객체를 df로 생성
		
		System.out.printf("=======================================================\n");
		System.out.printf("%20.20s%8.8s%8.8s%8.8s\n", "품목", "단가", "수량", "합계");
		System.out.printf("=======================================================\n");
		
		System.out.printf("%20.20s%10.10s%10.10s%10.10s\n",				
				k35_item,												// 물품 이름 출력
				k35_df.format(k35_unit_price),							// 물품 가격을 df객체의 format메서드에 파라미터로 넣어 콤파찍힌 문자열을 반환 받아 출력
				k35_df.format(k35_num),									// 물품 수량을 df객체의 format메서드에 파라미터로 넣어 콤파찍힌 문자열을 반환 받아 출력
				k35_df.format(k35_total));								// 물품 총 가격을 df객체의 format메서드에 파라미터로 넣어 콤파찍힌 문자열을 반환 받아 출력
	}
	
}
