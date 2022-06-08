package ch02.ex_12_otherIDE;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Ex_11_2_Training_stock_p27 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		Connection conn = getConnection();		// connecion객체 가져오는 메서드 호출
		Statement stmt = null;					// statement 변수 선언
		
		String sql = "drop table stock_total;";	// stock_total테이블 삭제

		try {
			stmt = conn.createStatement();		// DBMS에 쿼리문을 실행하는 statement 객체 생성
			stmt.execute(sql);					// 쿼리문 실행
			System.out.println("테이블 삭제 완료");	// 테이블 삭제 완료시 출력	
		} catch (Exception e) {					// 에러 발생시
			e.printStackTrace();				//   에러 내용 출력
		} finally {
			stmt.close();		// 자원 반납
			conn.close();		// 자원 반납
		}
	}
	
	// JDBC로드하여 conneciont 얻어오는 메서드
	private static Connection getConnection() {
		Connection conn = null;					// connection 변수 선언
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");					// mysql jdbc 드라이버 로드
			conn = DriverManager.getConnection("jdbc:mysql://192.168.23.94:3306/kopoctc", "root", "abcd1234");	// 리눅스 서버내 mysql의 connection 객체 생성
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	// PreparedStatement 자원을 반납하는 메서드
	private static void close(Statement stmt) {
		try {
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Connection 자원을 반납하는 메서드
	private static void close(Connection conn) {
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
