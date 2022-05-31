<?xml version="1.0" encoding="UTF-8"?>
<%@ page contentType="text/xml; charset=UTF-8" %>	<!-- 디렉티브 태그를 이용하여 생성할 문서의 컨텐츠 유형을 text/html로, 문자열세트를 utf-8로 지정-->
<%@ page import="java.sql.*" %>						<!-- 디렉티브 태그를 이용하여 자바 패키지 내 sql 하위 모든 클래스를 import -->
<%
	request.setCharacterEncoding("UTF-8");		// request객체의 인코딩을 utf-8로 명시

	String id_hubo = request.getParameter("id_hubo");	// 특정 후보의 연령대별 득표율을 확인하기 위한 후보 기호를 파라미터로 받아 변수에 저장

	Class.forName("com.mysql.jdbc.Driver");		// jdbc 로드
	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/kopoctc", "root", "abcd1234");	// 계정정보로 DB에 접속한 connecion 객체 저장
	
	Statement stmt = conn.createStatement();	// DB에 쿼리문를 날려주는 statement 객체 생성
	
	// 이름 조회
	String query1 ="select name from hubo_table where id=" + id_hubo +";";	// 해당 기호의 후보 이름 조회 쿼리문 변수에 저장
	ResultSet rset1 = stmt.executeQuery(query1);	// 해당 기호의 후보 이름 조회 쿼리 실행
	rset1.next();
	String name_hubo = rset1.getString(1);			// 조회한 이름을 변수에 저장
	
	// 연령대별 득표 정보 조회
	String query2 = "select a.ageRange, " + 		
				"ifnull((select count(*) from tupyo_table as t where t.ageRange=a.ageRange and t.id = " + id_hubo + " group by t.ageRange), 0) as cnt, " + 
				"ifnull((select count(*) from tupyo_table as t where t.ageRange=a.ageRange and t.id = " + id_hubo + " group by t.ageRange), 0)/(select count(*) from tupyo_table where id=" + id_hubo + ") as rate " +
				"from ageRange as a group by ageRange;";	// 특정 후보에 대하여 연령대에 따른 득표수와 득표율을 조회하는 쿼리를 변수에 저장
	ResultSet rset2 = stmt.executeQuery(query2);			// 특정 후보에 대하여 연령대에 따른 득표수와 득표율을 조회하는 쿼리 실행


	// xml 양식
	out.println("<datas>");									// 전체 데이터를 datas태그로 감싸 데이터 임을 명시
	out.println("<id>" + id_hubo + "</id>");				// 후보기호를 id태그로 감싼다.
	out.println("<name>" + name_hubo + "</name>");			// 후보이름를 name_hubo태그로 감싼다.
	
	out.println("<ranges>");								// 연령대 별 연령대, 득표수, 득표율을 ranges태그로 묶어 연령대 조히에 대한 데이터임을 명시
	while (rset2.next()) {
		out.println("<range>");								
		out.println("<ageRange>" + rset2.getString(1) + "</ageRange>");	// 연령대를 ageRange태그로 감싼다.
		out.println("<count>" + rset2.getInt(2) + "</count>");			// 득표수를 count태그로 감싼다.
		out.println("<rate>" + rset2.getDouble(3) + "</rate>");			// 득표율을 rate태그로 감싼다.
		out.println("</range>");
	}
	out.println("</ranges>");		
	out.println("</datas>");
	rset1.close();		// ResultSet 자원 반납
	rset2.close();		// ResultSet 자원 반납
	stmt.close();		// Statment 자원 반납
	conn.close();		// Connection 자원 반납
	%>