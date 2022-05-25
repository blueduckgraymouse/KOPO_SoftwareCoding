package ch02;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ex_08_4_freewifi_select_p24 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");											// mysql jdbc 드라이버 로드
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.94:3306/kopoctc", "root", "abcd1234");	// 리눅스 서버내 mysql의 connection 객체 생성
		
		Statement stmt = conn.createStatement();				// DBMS에 쿼리문을 실행하는 statement 객체 생성
		
		double lat = 37.3860521;								// 현재 위치의 위도를 변수로 초기화
		double lng = 127.1214038;								// 현재 위치의 경도를 변수로 초기화
		
		String queryTxt;
		queryTxt = "select * from freewifi where inst_country = '수원시'"; // 설치된 도시가 '수원시'인 무료 와이파이 레코드 조회하는 쿼리문 저장
			
		ResultSet rset = stmt.executeQuery(queryTxt);			// 조회 쿼리문 실행
		int iCnt = 0;											// 해당 레코드의 카운트 번호를 저장할 변수 초기화

		while (rset.next()) {
			System.out.printf("*(%d)********************************************\n", iCnt++);	// 해당 레코드의 카운트 번호 출력
			System.out.printf("설치장소명      : %s\n", rset.getString(1));						// 조회된 필드값 출력
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
