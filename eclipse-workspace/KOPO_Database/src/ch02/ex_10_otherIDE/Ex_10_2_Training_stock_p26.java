package ch02.ex_10_otherIDE;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Ex_10_2_Training_stock_p26 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");											// mysql jdbc 드라이버 로드
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.94:3306/kopoctc", "root", "abcd1234");	// 리눅스 서버내 mysql의 connection 객체 생성
		Statement stmt = conn.createStatement();					// DBMS에 쿼리문을 실행하는 statement 객체 생성
		
		String sql = "drop table stock;";	// stock테이블 삭제
		
		try {
			stmt.execute(sql);					// 쿼리문 실행
			System.out.println("테이블 삭제 완료");	// 테이블 삭제 완료시 출력	
		} catch (Exception e) {					// 에러 발생시
			e.printStackTrace();				//   에러 내용 출력
		} finally {
			stmt.close();		// 자원 반납
			conn.close();		// 자원 반납
		}
	}
	
}
