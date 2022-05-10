package ch02;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Ex_10_1_BasicTraining2_score_p25 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");											// mysql jdbc 드라이버 로드
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.94:3306/kopoctc", "root", "abcd1234");	// 리눅스 서버내 mysql의 connection 객체 생성
		Statement stmt = conn.createStatement();					// DBMS에 쿼리문을 실행하는 statement 객체 생성
		
		String sql = "create table score("							// 학생의 점수를 저장할 score 테이블 생성		
				+ "no 		int primary key,"				
				+ "name 	varchar(20),"
				+ "kor		int,"
				+ "eng		int,"
				+ "mat		int);";
		try {
			stmt.execute(sql);								// 쿼리문 실행
			System.out.println("테이블 생성 완료");			// 테이블 생성 완료시 출력
		} catch (Exception e) { 							// 에러 발생시	
			e.printStackTrace();							//   에러 출력
		} finally {
			stmt.close();		// 자원 반납
			conn.close();		// 자원 반납
		}
	}
	
}
