package ch02;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Ex_10_2_BasicTraining2_score_p25 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		Class.forName("com.mysql.cj.jdbc.Driver");					// mysql jdbc 드라이버 로드
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.94:3306/kopoctc", "root", "abcd1234");	// 리눅스 서버내 mysql의 connection 객체 생성
		
		String sql = "insert into score values(?, ?, ?, ?, ?)";		// insert 쿼리문 저장
		PreparedStatement pstmt = conn.prepareStatement(sql);		// DBMS에 쿼리문을 실행하는 statement 객체 생성하고 쿼리문 파라미터로 저장
		
		try {
			for (int i = 1 ; i < 1001 ; i++) {						// 1000번 반복
				pstmt.setInt(1, i);									//   학번 입력
				pstmt.setString(2, "아무개" + i);					//   이름 입력
				pstmt.setInt(3, (int)(Math.random() * 101));		//   임의의 국어점수 입력
				pstmt.setInt(4, (int)(Math.random() * 101));		//   임의의 영어점수 입력
				pstmt.setInt(5, (int)(Math.random() * 101));		//   임의의 수학점수 입력
				
				pstmt.execute();									// 쿼리문 실행
			}
			
			System.out.println("랜덤데이터 입력 완료");				// 임의의 1000개의 데이터 입력 완료시 출력
		} catch (Exception e) { 									// 에러 발생시
			e.printStackTrace();									//   에러 출력
		} finally {
			pstmt.close();		// 자원 반납
			conn.close();		// 자원 반납
		}
	}
	
}
