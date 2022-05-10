package ch02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ex_06_freewifi_create_p22 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");											// mysql jdbc 드라이버 로드
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.94:3306/kopoctc", "root", "abcd1234");	// 리눅스 서버내 mysql의 connection 객체 생성
		
		Statement stmt = conn.createStatement();				// DBMS에 쿼리문을 실행하는 statement 객체 생성
		
		stmt.execute("create table freewifi(" +					// freewifi라는 이름의 테이블 생성
					"inst_place varchar(50)," +					
					"inst_place_detail varchar(50)," +
					"inst_city varchar(50)," +
					"inst_country varchar(50)," +
					"inst_place_flag varchar(50)," +
					"service_provider varchar(50)," +
					"wifi_ssid varchar(50)," +
					"inst_date varchar(50)," +
					"place_addr_road varchar(200)," +
					"place_addr_land varchar(200)," +
					"manage_office varchar(50)," +
					"manage_office_phone varchar(50)," +
					"latitude double," +
					"longitude double," +
					"write_date date," +							// write_date필드의 필드타입은 날짜 타입인 date형
					"primary key(inst_place, inst_place_detail, service_provider, wifi_ssid, latitude, longitude))" +	// primary 키를 설치장소(inst_place)와 설치장소 상세(inst_place_detail), 서비스 제공사(service_provider), 위도(latitude), 경도(longitude) 5개로 설정
					"DEFAULT CHARSET=utf8;");
		
		stmt.close();		// 자원 반납
		conn.close();		// 자원 반납
	}

}
