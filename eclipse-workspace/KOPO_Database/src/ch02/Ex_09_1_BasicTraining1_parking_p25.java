package ch02;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Ex_09_1_BasicTraining1_parking_p25 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");											// mysql jdbc 드라이버 로드
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.94:3306/kopoctc", "root", "abcd1234");	// 리눅스 서버내 mysql의 connection 객체 생성
		Statement stmt = conn.createStatement();					// DBMS에 쿼리문을 실행하는 statement 객체 생성
		
		String sql = "create table parking("						// "전국주차장정보표준데이터"가 저장될 table 생성 쿼리문 작성
				+ "no 					varchar(20),"				// 주차장관리번호
				+ "name 				varchar(50),"				// 주차장명	
				+ "kind_parking 		varchar(20),"				// 주차장구분	
				+ "kind_side		 	varchar(20),"				// 주차장유형	
				+ "addr_road	 		varchar(50),"				// 소재지도로명주소	
				+ "addr_land	 		varchar(50),"				// 소재지지번주소	
				+ "size		 			varchar(20),"				// 주차구획수	
				+ "kind_infra		 	varchar(20),"				// 급지구분
				+ "kind_daySystem	 	varchar(20),"				// 부제시행구분	
				+ "days_open	 		varchar(20),"				// 운영요일	
				
				+ "startTime_weekday	varchar(20),"				// 평일운영시작시각	
				+ "endTime_weekday	 	varchar(20),"				// 평일운영종료시각	
				+ "startTime_saturday	varchar(20),"				// 토요일운영시작시각	
				+ "endTime_saturday	 	varchar(20),"				// 토요일운영종료시각	
				+ "startTime_holiday	varchar(20),"				// 공휴일운영시작시각	
				+ "endTime_holiday	 	varchar(20),"				// 공휴일운영종료시각	
				+ "kind_charge	 		varchar(20),"				// 요금정보	
				+ "time_basic_parking	varchar(20),"				// 주차기본시간	
				+ "charge_basic_parking	varchar(20),"				// 주차기본요금	
				+ "time_add_parking		varchar(20),"				// 추가단위시간
				
				+ "charge_add_parking	varchar(20),"				// 추가단위요금	
				+ "time_day_parking	 	varchar(20),"				// 1일주차권요금적용시간
				+ "charge_day_parking	varchar(20),"				// 1일주차권요금
				+ "charge_month_parking	varchar(20),"				// 월정기권요금
				+ "kind_pay				varchar(20),"				// 결제방법	
				+ "imformation			varchar(500),"				// 특기사항	
				+ "name_office			varchar(20),"				// 관리기관명	
				+ "tel				 	varchar(20),"				// 전화번호	
				+ "latitude				double,"					// 위도				
				+ "longitude			double,"					// 경도	
				
				+ "date_write			varchar(20),"				// 데이터기준일자	
				+ "officeCode_write		varchar(20),"				// 제공기관코드	
				+ "officeName_write		varchar(20),"				// 제공기관명
				+ "primary key(no, name, latitude, longitude));";	// 주차장관리번호, 주차장명, 위도, 경도를 primary key로 
																	// 위경도까지 같은 중복데이터는 저장되지 않도록 설정
		stmt.execute(sql);	// 쿼리문 실행
		
		stmt.close();		// 자원 반납
		conn.close();		// 자원 반납
	}
	
}
