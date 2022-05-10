package ch02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ex_01_JDBC_connection_p17 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");											// mysql jdbc 드라이버 로드
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.94:3306/kopoctc", "root", "abcd1234");	// 리눅스 서버내 mysql의 connection 객체 생성
		
		Statement stmt = conn.createStatement();				// DBMS에 쿼리문을 실행하는 statement 객체 생성
		ResultSet rset = stmt.executeQuery("show databases;");	// statement 객체를 통해 "show databases;" 쿼리 실행 후 결과를 ResultSet객체에 저장 
		
		while(rset.next()) {									// ResultSet 객체에 저장된 data를 한 레코드씩 접근
			System.out.println("값 : " + rset.getString(1));	// 해당 레코드의 첫번째 필드값 출력
		}
		
		rset.close();		// 자원 반납
		stmt.close();		// 자원 반납
		conn.close();		// 자원 반납
	}

}
