package ch02.ex_10_otherIDE;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Ex_10_1_Training_stock_p26 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");											// mysql jdbc 드라이버 로드
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.94:3306/kopoctc", "root", "abcd1234");	// 리눅스 서버내 mysql의 connection 객체 생성
		Statement stmt = conn.createStatement();					// DBMS에 쿼리문을 실행하는 statement 객체 생성
		
		String sql = "create table stock("						// "전국주차장정보표준데이터"가 저장될 table 생성 쿼리문 작성
					+ "shrn_iscd varchar(20),"					// 단축코드
					+ "bsop_date int,"							// 일자
					+ "stck_oprc int,"							// 시가
					+ "stck_hgpr int,"							// 고가
					+ "stck_lwpr int,"							// 저가
					+ "stck_prpr int,"							// 종가
					+ "acml_vol long,"							// 거래량
					+ "acml_tr_pbmn long,"						// 거래대금
					+ "primary key(shrn_iscd, bsop_date));";	// primary key 단축코드랑 일자 설정
		
		try {
			stmt.execute(sql);					// 쿼리문 실행
			System.out.println("테이블 생성 완료");	// 테이블 생성 완료시 출력	
		} catch (Exception e) {					// 에러 발생시
			e.printStackTrace();				//   에러 내용 출력
		} finally {
			stmt.close();		// 자원 반납
			conn.close();		// 자원 반납
		}
	}
	
}
