package k35_ch07;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;

/**
 * 소프트웨어코딩 심화 7강 - String,Byte,StringBuffer, Array, ArrayList
 * 
 * 고정길이에서 필드 추출 처리 - p10
 * 
 * @author KOPO
 */
public class K35_ex02_p11 {

	public static void main(String[] args) throws UnsupportedEncodingException {
		DecimalFormat df = new DecimalFormat("###,###");
		
		String[] k35_oneRec =  {
			"01   해피홈 SAFE365     10,000  2     20,000",
			"02   (G)LH원형유리화     1,200  8      9,600",
			"03*  건포도             13,000  1     13,000",
			"04   오렌지주스          3,000  2      6,000",
			"05   초코에몽            1,000  3      3,000",
			"06   abc초콜렛             500  4      2,000",
			"07   서울우유            3,800  1      3,800",
			"08   크래미              3,300  1      3,300",
			"09   포카칩              2,000  2      4,000",
			"10   프링글스 오리지     1,700  1      1,700",
			"11*  고등어             10,000  1     10,000",
			"12*  양배추              1,500  2      4,500",
			"13*  고구마              9,900  1      9,900",
			"14*  감자                7,900  1      7,900",
			"15   비비고 만두        11,900  2     23,800",
			"16   티코                4,200  2      8,400",
			"17   하겐다즈            5,100  1      5,100",
			"18   텀블러             17,000  1     20,000",
			"19   모니터 clear하    213,200  1    213,200",
			"20   삼성 super 울트 2,000,000  1  2,000,000",
			"21   선풍기             79,300  1     79,300",
			"22   프린터            139,000  1    139,000",
			"23*  한우 등심          32,190  2     64,380",
			"24*  한우 안심          27,890  2     55,780",
			"25   키보드             65,900  1     65,900",
			"26   마우스             54,190  1     54,190",
			"27   카누                9,900  3     29,700",
			"28*  대파                2,500 10     25,000",
			"29*  1+ 등급란 10개     19,280  3     57,840",		// 2 + 3 + 15 + 10 + 3 + 11 = 44
			"30   동원양반볶음김      3,510 23     80,730"
		};
		
		for (String k35_rec : k35_oneRec) {
			byte[] k35_byte_arr = k35_rec.getBytes("euc-kr");
			String k35_price = new String(k35_byte_arr, 20, 10);
			String k35_amount = new String(k35_byte_arr, 30, 3);
			String k35_total = new String(k35_byte_arr, 33, 11);

			String k35_price_raw = k35_price.trim().replaceAll(",", "");
			String k35_total_raw = k35_total.trim().replaceAll(",", "");
			String k35_amount_raw = k35_amount.trim();
			if (Integer.parseInt(k35_price_raw) * Integer.parseInt(k35_amount_raw) != Integer.parseInt(k35_total_raw)) {
				System.out.println("--------------------------------------------------");
				System.out.println("오류[" + k35_rec + "]");
				System.out.printf("수정[%s]\n", k35_rec.replace( df.format(Integer.parseInt(k35_total_raw)), df.format(Integer.parseInt(k35_price_raw) * Integer.parseInt(k35_amount_raw))));
				System.out.println("--------------------------------------------------");
			}
		}
	}
}
