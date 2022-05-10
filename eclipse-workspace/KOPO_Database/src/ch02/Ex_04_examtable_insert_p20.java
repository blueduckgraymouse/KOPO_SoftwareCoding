package ch02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ex_04_examtable_insert_p20 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");											// mysql jdbc 드라이버 로드
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.94:3306/kopoctc", "root", "abcd1234");	// 리눅스 서버내 mysql의 connection 객체 생성
		
		Statement stmt = conn.createStatement();				// DBMS에 쿼리문을 실행하는 statement 객체 생성
		
		stmt.execute("insert into examtable ( name, studentid, kor, eng, mat) values('나연',209901, 95, 100, 95);");		// statement 객체를 통해 examtable 테이블 트와이스 모든 맴버의 국어, 수학, 영어 점수 입력 쿼리문 실행
		stmt.execute("insert into examtable ( name, studentid, kor, eng, mat) values('정연',209902, 100, 100, 100);");
		stmt.execute("insert into examtable ( name, studentid, kor, eng, mat) values('모모',209903, 100, 90, 100);");
		stmt.execute("insert into examtable ( name, studentid, kor, eng, mat) values('사나',209904, 100, 95, 90);");
		stmt.execute("insert into examtable ( name, studentid, kor, eng, mat) values('지효',209905, 80, 100, 70);");
		stmt.execute("insert into examtable ( name, studentid, kor, eng, mat) values('미나',209906, 95, 90, 95);");
		stmt.execute("insert into examtable ( name, studentid, kor, eng, mat) values('다현',209907, 100, 90, 100);");
		stmt.execute("insert into examtable ( name, studentid, kor, eng, mat) values('채영',209908, 100, 75, 90);");
		stmt.execute("insert into examtable ( name, studentid, kor, eng, mat) values('쯔위',209909, 100, 100, 70);");
		
		stmt.close();		// 자원 반납
		conn.close();		// 자원 반납
	}

}
