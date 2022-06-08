package ch02.ex_10_otherIDE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Ex_10_4_Training_stock_p26 {

	public static void main(String[] args) {
		Connection conn = getConnection();					// connecion객체 가져오는 메서드 호출
		PreparedStatement pstmt = null;
		
		String sql = "select * from stock where shrn_iscd = 'A005930';";
		
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rset = pstmt.executeQuery();
			
			System.out.println("* 삼성전자 주식\n");
			System.out.printf("%6s%8s%8s%8s%8s%8s%16s%16s", "단축코드", "일자", "시가", "고가", "저가", "종가", "거래량", "거래대금");
			while (rset.next()) {
				System.out.printf("%10s%10d%10d%10d%10d%10d%20l%20l",
									rset.getString(1),
									rset.getInt(2),
									rset.getInt(3),
									rset.getInt(4),
									rset.getInt(5),
									rset.getInt(6),
									rset.getLong(7),
									rset.getLong(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
	}
	
	// JDBC로드하여 conneciont 얻어오는 메서드
	private static Connection getConnection() {
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");					// mysql jdbc 드라이버 로드
			conn = DriverManager.getConnection("jdbc:mysql://192.168.23.94:3306/kopoctc", "root", "abcd1234");	// 리눅스 서버내 mysql의 connection 객체 생성
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}
	
	// PreparedStatement 자원을 반납하는 메서드
	private static void close(PreparedStatement pstmt) {
		try {
			pstmt.close();
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
