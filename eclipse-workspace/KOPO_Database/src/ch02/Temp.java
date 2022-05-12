package ch02;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Temp {

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf1 = new SimpleDateFormat("YYYY-MM", new Locale("en", "US"));
//		String a = "2018-01";
//		Date d = sdf1.parse(a);
//		SimpleDateFormat sdf2 = new SimpleDateFormat("YYYY-MMM", new Locale("en", "US"));
//		System.out.println(sdf2.format(d));
		
//	    Date date = new Date();
//
//	    SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MMM", new Locale("en", "US"));
//
//	    System.out.println(date.getTime());
//	    System.out.println("Current Month in MMM format : " + sdf.format(date));
//	    
//	    String d = sdf.format(date);
//	    SimpleDateFormat sdf2 = new SimpleDateFormat("YYYY-MM", new Locale("en", "US"));
//	    Date ddate = sdf.parse(d);
//	    
//	    System.out.println(ddate.getTime());
//	    System.out.println("Current Month in MM format : " + sdf2.format(ddate));
//	    
		
//		SimpleDateFormat formatter = new SimpleDateFormat("yy-MMM", Locale.ENGLISH);
//
//		String dateInString = "10-AUG";
//		Date date = formatter.parse(dateInString);
//		System.out.println(sdf1.format(date));
//		
//	    System.out.println();
//		
//		java.sql.Date sqlDate = reformat("10-AUG");
//		System.out.println(sqlDate.getMonth());
	
		java.sql.Date sqlDate = reformat("2014");
		//System.out.println(sqlDate.getMonth());

	}
	
//	// 설치일자의 날짜 양식을 통일하여 반환하는 메서드
//		static java.sql.Date reformat(String inst_date) {
//			SimpleDateFormat[] sdfs  = {new SimpleDateFormat("yyyy-MM"), new SimpleDateFormat("yyyy-MM-dd"), new SimpleDateFormat("yyyy년 MM월"), new SimpleDateFormat("yyyy-MM(예정)"), 
//										new SimpleDateFormat("MMM-yy", Locale.ENGLISH),	new SimpleDateFormat("yy-MMM", Locale.ENGLISH),
//										new SimpleDateFormat("YYYY")};																				// 파일 내 존재하는 설치일자의 날짜 형식 모두 SimpleDateFormat객체 배열로 초기화
////			SimpleDateFormat[] sdfs_f = {new SimpleDateFormat("YYYY-MM"), new SimpleDateFormat("20YY-MM"), new SimpleDateFormat("YYYY-01")};		// 변환할 날짜 양식  모두 SimpleDateFormat객체 배열로 초기화
//			
//			Date date = null;					// 설치일자가 저장될 date객체 선언
//			
//			for (int i = 0 ; i < sdfs.length ; i++) {
//				try {
//					if (inst_date.equals("")) {
//						date = new Date();
//					} else {
////						if (i == 4) {
////							inst_date = inst_date.replace("-", "-20");
////						} else if(i == 5) {
////							inst_date = "20" + inst_date;
////						}
//						date = sdfs[i].parse(inst_date);	// 읽어온 설치일자를 parse하여 date객체에 저장
//						System.out.println(i);
//					    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//						System.out.println(sdf.format(date));
//						break;
////						if (i == 4 || i == 5)
////							date.				 yy가 00yyㄹ 인식되는거 처리.
//					}
//					
//
////					if (i < 4)							// parse한 형태애 맞춰 변환한 날짜양식 반환
////						return sdfs_f[0].format(date);	
////					else if (i == 4 || i == 5)
////						return sdfs_f[1].format(date);	
////					else
////						return sdfs_f[2].format(date);
//				} catch (Exception e) {					// 에러 발생시
//					//e.printStackTrace();				//   에러 내용 출력
//				}
//			}
//			
////			return "";									// 읽어온 설치일자가 공백일 경우 그대로 공백 반환
//			long timeInMillSeconds = date.getTime();
//			java.sql.Date sqlDate = new java.sql.Date(timeInMillSeconds);
//			return sqlDate;
//		}
	// 설치일자의 날짜 읽어 sql.Date형으로 형변환하여 반환하는 메서드
		static java.sql.Date reformat(String inst_date) {
			SimpleDateFormat[] sdfs  = {new SimpleDateFormat("yyyy-MM"), new SimpleDateFormat("yyyy-MM-dd"), new SimpleDateFormat("yyyy년 MM월"), new SimpleDateFormat("yyyy-MM(예정)"), 
										new SimpleDateFormat("MMM-yy", Locale.ENGLISH),	new SimpleDateFormat("yy-MMM", Locale.ENGLISH),
										new SimpleDateFormat("YYYY")};		// 파일 내 존재하는 설치일자의 날짜 형식 모두 SimpleDateFormat객체 배열로 초기화
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			Date date = null;			// 설치일자가 저장될 util.date객체 선언
			System.out.println(inst_date);
			for (int i = 0 ; i < sdfs.length ; i++) {		// SimpleDateFormat 객체 배열의 크기만 큼반복
				try {
					if (inst_date.equals("")) {				// 설치일자가 공백일 경우
						date = new Date();					//   오늘 날짜로 입력
						break;
					} else {
						if (i != 6) {
							date = sdfs[i].parse(inst_date);	// 읽어온 설치일자를 parse하여 date객체에 저장
							break;								// 파싱이 성공하면 반복문 종료
						} else {
							inst_date = inst_date + "-01";
							date = sdfs[0].parse(inst_date);
							break;
						}
					}
				} catch (Exception e) {	// SimpleDateFormat 객체 배열의 모든 형태로 파싱을 시도하므로 파싱 안되는 경우를
										//   try catch로 pass 시킨다.
				}
			}

			long timeInMillSeconds = date.getTime();		// sql.Date타입으로 바꾸기 위하여 ms기준으로 숫자값 저장
			java.sql.Date sqlDate = new java.sql.Date(timeInMillSeconds);	// sql.Date타입으로 변환
			
			return sqlDate;				// 변환된 java.sql.Date 객체 반환
		}
}

