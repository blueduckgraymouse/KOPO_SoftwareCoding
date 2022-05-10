package ch02;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ex_08_1_freewifi_select_p24 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");											// mysql jdbc 드라이버 로드
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.94:3306/kopoctc", "root", "abcd1234");	// 리눅스 서버내 mysql의 connection 객체 생성
		
		Statement stmt = conn.createStatement();				// DBMS에 쿼리문을 실행하는 statement 객체 생성
		
		double lat = 37.3860521;								// 현재 위치의 위도를 변수로 초기화
		double lng = 127.1214038;								// 현재 위치의 경도를 변수로 초기화
		
		String queryTxt;
		queryTxt = String.format("select * from freewifi where " +	// 현재 위치로 부터 가장 가까운 무료와이파이의 거리와 일치하는 레코드 조회 쿼리문 저장
								"SQRT( POWER(latitude - %f, 2) + POWER(longitude - %f, 2) ) = " +
								"(select MIN( SQRT( POWER(latitude - %f, 2) + POWER(longitude - %f, 2) ) ) from freewifi);",
								lat, lng, lat, lng);
				
		ResultSet rset = stmt.executeQuery(queryTxt);			// 조회 쿼리문 실행
		int iCnt = 0;											// 해당 레코드의 카운트 번호를 저장할 변수 초기화
		
		while (rset.next()) {
			System.out.printf("*(%d)********************************************\n", iCnt++);	// 해당 레코드의 카운트 번호 출력
			System.out.printf("설치장소명      : %s\n", rset.getString(1));						// 현재 위치로 부터 가장 가까운 무료와이파이의 모든 필드값 출력
			System.out.printf("설치장소상세    : %s\n", rset.getString(2));
			System.out.printf("설치시도명      : %s\n", rset.getString(3));
			System.out.printf("설치시군구명    : %s\n", rset.getString(4));
			System.out.printf("설치시설구분    : %s\n", rset.getString(5));
			System.out.printf("서비스제공사명  : %s\n", rset.getString(6));
			System.out.printf("와이파이SSID    : %s\n", rset.getString(7));
			System.out.printf("설치년월        : %s\n", rset.getString(8));
			System.out.printf("소재지도로명주소: %s\n", rset.getString(9));
			System.out.printf("소재지지번주소  : %s\n", rset.getString(10));
			System.out.printf("관리기관명      : %s\n", rset.getString(11));
			System.out.printf("관리기관전화번호: %s\n", rset.getString(12));
			System.out.printf("위도            : %f\n", rset.getFloat(13));
			System.out.printf("경도            : %f\n", rset.getFloat(14));
			System.out.printf("데이터기준일자  : %s\n", rset.getDate(15));
			System.out.printf("************************************************\n");
		}
		
		stmt.close();		// 자원 반납
		conn.close();		// 자원 반납
	}
	
}
