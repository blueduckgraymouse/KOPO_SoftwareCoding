package ch02;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Ex_09_2_BasicTraining1_parking_p25 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");											// mysql jdbc 드라이버 로드
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.94:3306/kopoctc", "root", "abcd1234");	// 리눅스 서버내 mysql의 connection 객체 생성
		Statement stmt = conn.createStatement();											// DBMS에 쿼리문을 실행하는 statement 객체 생성
		
		String sql = "drop table parking;";													// "전국주차장정보표준데이터"가 저장된 table 삭제 쿼리문 작성
		
		stmt.execute(sql);	// 쿼리문 실행
		
		stmt.close();		// 자원 반납
		conn.close();		// 자원 반납
	}
	
}
