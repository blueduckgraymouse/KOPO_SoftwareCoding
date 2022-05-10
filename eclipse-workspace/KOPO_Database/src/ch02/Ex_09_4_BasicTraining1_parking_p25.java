package ch02;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ex_09_4_BasicTraining1_parking_p25 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// JDBC 커넥션 열기
		Class.forName("com.mysql.cj.jdbc.Driver");											// mysql jdbc 드라이버 로드
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.94:3306/kopoctc", "root", "abcd1234");	// 리눅스 서버내 mysql의 connection 객체 생성
		
		double lat = 37.3860521;								// 현재 위치의 위도를 변수로 초기화
		double lng = 127.1214038;								// 현재 위치의 경도를 변수로 초기화
		
		String readTxt = "";									// 한 줄에 대한 데이터를 저장할 String 변수 초기화
		
		
		// 최단거리 조회
		String sql1 = "select MIN( SQRT( POWER(latitude - ?, 2) + POWER(longitude - ?, 2) ) ) from parking;";	// 모든 주차장까지의 거리 중 최단거리 조회하는 쿼리문 저장
		
		PreparedStatement pstmt = conn.prepareStatement(sql1);					// DBMS에 쿼리문을 실행하는 statement 객체 생성하고 쿼리문 파라미터로 저장
		pstmt.setDouble(1, lat);												// 현재 위치의 위도 쿼리문의 ? 위치에 set
		pstmt.setDouble(2, lng);												// 현재 위치의 경도 쿼리문의 ? 위치에 set
		
		ResultSet rset1 = pstmt.executeQuery();									// 쿼리문 실행하여 조회한 데이터 ResultSet객체에 저장
		rset1.next();															// 커서 첫 줄로 이동
		Double distance = Double.parseDouble(rset1.getString(1)) * 111.35;		// 최단거리 변수 double로 형변환& km환산하여 변수에 저장
		
		
		// 최단거리에 해당하는 주차장 조회
		String sql2 = "select * from parking where " +							// 최단거리에 해당하는 주차장의 정보 조회
				"SQRT( POWER(latitude - ?, 2) + POWER(longitude - ?, 2) ) = ?;";
		
		pstmt = conn.prepareStatement(sql2);				// statement 객체에 쿼리문 파라미터로 저장
		pstmt.setDouble(1, lat);							// 현재 위치의 위도 쿼리문의 ? 위치에 set
		pstmt.setDouble(2, lng);							// 현재 위치의 경도 쿼리문의 ? 위치에 set
		pstmt.setString(3, rset1.getString(1));				// 최단거리 쿼리문의 ? 위치에 set
		
		ResultSet rset2 = pstmt.executeQuery();				// 쿼리문 실행하여 조회한 데이터 ResultSet객체에 저장
		
		
		// 조회 결과 출력
		System.out.printf("************************************************\n");	// 해당 레코드의 카운트 번호 출력
		
		System.out.println("\n # 최단 거리 : " + distance + " km\n");				// 최단거리 출력
		
		while(rset2.next()) {
			System.out.printf("주차장관리번호         : %s\n", rset2.getString(1));	// 현재 위치로 부터 가장 가까운 주차장의 모든 필드값 출력
			System.out.printf("주차장명               : %s\n", rset2.getString(2));
			System.out.printf("주차장구분             : %s\n", rset2.getString(3));
			System.out.printf("설치시군구명           : %s\n", rset2.getString(4));
			System.out.printf("소재지도로명주소       : %s\n", rset2.getString(5));
			System.out.printf("소재지지번주소         : %s\n", rset2.getString(6));
			System.out.printf("주차구획수             : %s\n", rset2.getString(7));
			System.out.printf("급지구분               : %s\n", rset2.getString(8));
			System.out.printf("부제시행구분           : %s\n", rset2.getString(9));
			System.out.printf("운영요일               : %s\n", rset2.getString(10));
			
			System.out.printf("평일운영시작시각       : %s\n", rset2.getString(11));
			System.out.printf("평일운영종료시각       : %s\n", rset2.getString(12));
			System.out.printf("토요일운영시작시각     : %s\n", rset2.getString(13));
			System.out.printf("토요일운영종료시각     : %s\n", rset2.getString(14));
			System.out.printf("공휴일운영시작시각     : %s\n", rset2.getString(15));
			System.out.printf("공휴일운영종료시각     : %s\n", rset2.getString(16));
			System.out.printf("요금정보               : %s\n", rset2.getString(17));
			System.out.printf("주차기본시간           : %s\n", rset2.getString(18));
			System.out.printf("주차기본요금           : %s\n", rset2.getString(19));
			System.out.printf("추가단위시간           : %s\n", rset2.getString(20));
			
			System.out.printf("추가단위요금           : %s\n", rset2.getString(21));
			System.out.printf("1일주차권요금적용시간  : %s\n", rset2.getString(22));
			System.out.printf("1일주차권요금          : %s\n", rset2.getString(23));
			System.out.printf("월정기권요금           : %s\n", rset2.getString(24));
			System.out.printf("결제방법               : %s\n", rset2.getString(25));
			System.out.printf("특기사항               : %s\n", rset2.getString(26));
			System.out.printf("관리기관명             : %s\n", rset2.getString(27));
			System.out.printf("전화번호               : %s\n", rset2.getString(28));
			System.out.printf("위도                   : %f\n", rset2.getDouble(29));
			System.out.printf("경도                   : %f\n", rset2.getDouble(30));
			
			System.out.printf("데이터기준일자         : %s\n", rset2.getString(31));
			System.out.printf("제공기관코드           : %s\n", rset2.getString(32));
			System.out.printf("제공기관명             : %s\n", rset2.getString(33));
			System.out.printf("************************************************\n");
		}

		// 자원 반납
		pstmt.close();
		conn.close();
	}
		
}
