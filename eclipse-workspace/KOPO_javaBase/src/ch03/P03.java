package ch03;

/*
 * 한글 유니코드 저장 규칙
 * 	(초성 * 21 + 중성) * 28 + 종성 + 0xAC00 ('ㄱ'의 유니코드 값)
 * 
 * 종성 =   (유니코드 값 - 0xAC00) % 28			+ 0x11a7 (종성 없음) (*첫 종/중/초성에 해당하는 유니코드 값)
 * 중성 =  ((유니코드 값 - 0xAC00) / 28 ) % 21	+ 0x1161 ('ㅏ')
 * 초성 = (((유니코드 값 - 0xAC00) / 28 ) / 21	+ 0x1100 ('ㄱ')
 * 
 * 
 * 초성/중성/종성 매핑 유니코드 값
 * 초성 19개 => "ㄱ","ㄲ","ㄴ","ㄷ","ㄸ","ㄹ","ㅁ","ㅂ","ㅃ", "ㅅ","ㅆ","ㅇ","ㅈ","ㅉ","ㅊ","ㅋ","ㅌ","ㅍ","ㅎ"
 * 			=> 0x1100, 0x1101, ...
 * 
 * 중성 21개 => "ㅏ","ㅐ","ㅑ","ㅒ","ㅓ","ㅔ","ㅕ","ㅖ","ㅗ","ㅘ", "ㅙ","ㅚ","ㅛ","ㅜ","ㅝ","ㅞ","ㅟ","ㅠ","ㅡ","ㅢ","ㅣ"
 * 			=> 0x1161, 0x1162, ...
 * 
 * 종성 28개 => "","ㄱ","ㄲ","ㄳ","ㄴ","ㄵ","ㄶ","ㄷ","ㄹ","ㄺ","ㄻ","ㄼ", "ㄽ","ㄾ","ㄿ","ㅀ","ㅁ","ㅂ","ㅄ","ㅅ","ㅆ","ㅇ","ㅈ","ㅊ","ㅋ","ㅌ","ㅍ","ㅎ"
 * 			=> 0x11a7, 0x11a8, ...
 * 
 */

// 11172? , 4519?


public class P03 {

	public static void main(String[] args) {
		String str = "안녕하세요";
		char[] cArr = str.toCharArray();
		
		System.out.println((char) 11168);
		System.out.println((char) 11169);
		System.out.println((char) 11170);
		System.out.println((char) 11171);
		System.out.println((char) 11172);
		
		for(int i = 0 ; i < cArr.length ; i++) {
			char uniVal = (char) (cArr[i] - 0xAC00);	// uniVal = (초성 * 21 + 중성) * 28 + 종성
			
			if (uniVal >= 0 && uniVal <= 11172) { 		// 한글일 경우 초,중,종성 분리, 11172 
					char cho  = (char) ((((uniVal - (uniVal % 28)) / 28) / 21) + 0x1100);	// 초성 + 0x1100('ㄱ')
					char jung = (char) ((((uniVal - (uniVal % 28)) / 28) % 21) + 0x1161); 	// 중성 + 0x1161('ㅏ')
					char jong = (char) ((uniVal % 28) + 0x11a7);							// 종성 + 0x11a7(종성이 없음)

					if(cho!=4519){				// 4519 => ㅗㅒ ?
						System.out.print(cho);
					}
					if(jung!=4519){
						System.out.print(jung);
					}
					if(jong!=4519){
						System.out.print(jong);
					}

			} else {
				// 한글이 아닐경우
				uniVal = (char) (uniVal + 0xAC00);
				System.out.print(uniVal);
			}
		}
	}

}
