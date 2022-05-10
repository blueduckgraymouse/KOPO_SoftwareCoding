package ch02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ex_03_examtable_delete_p19 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");											// mysql jdbc 드라이버 로드
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.94:3306/kopoctc", "root", "abcd1234");	// 리눅스 서버내 mysql의 connection 객체 생성
		
		Statement stmt = conn.createStatement();				// DBMS에 쿼리문을 실행하는 statement 객체 생성
		
		stmt.execute("delete from examtable;");					// statement 객체를 통해 examtable 테이블 삭제 쿼리문 실행
																//    -> 그러나 테이블 삭제 의도와 다르게 delete from examtable 쿼리문은 테이블 내 모든 레코드를 삭제하는 쿼리문
		stmt.close();		// 자원 반납
		conn.close();		// 자원 반납
	}

}
