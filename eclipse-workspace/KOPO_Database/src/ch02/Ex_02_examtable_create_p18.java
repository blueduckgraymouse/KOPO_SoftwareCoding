package ch02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ex_02_examtable_create_p18 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");											// mysql jdbc 드라이버 로드
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.94:3306/kopoctc", "root", "abcd1234");	// 리눅스 서버내 mysql의 connection 객체 생성
		
		Statement stmt = conn.createStatement();				// DBMS에 쿼리문을 실행하는 statement 객체 생성
		
		stmt.execute("create table examtable(" + 				// statement 객체를 통해 examtable 테이블 생성 쿼리문 실행
					"name varchar(20)," +						// name이라는 필드명의 필드타입은 varcahr형이고 size는 20
					"studentid int not null primary key," + 	// studentid라는 필드명의 필드타입은 int형이며 null을 허용하지 않고 primary key로 설정
					"kor int," + 								// kor라는 필드명의 필드 타입은 int형
					"eng int," + 								// eng라는 필드명의 필드 타입은 int형								
					"mat int)" + 								// mat라는 필드명의 필드 타입은 int형
					"DEFAULT CHARSET=utf8;");					// examtable 테이블의 기본 CHARSET를 utf-8형식으로 설정
		
		stmt.close();		// 자원 반납
		conn.close();		// 자원 반납
	}

}
