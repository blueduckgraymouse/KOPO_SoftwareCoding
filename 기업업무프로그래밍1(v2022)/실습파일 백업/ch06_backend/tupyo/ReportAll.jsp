<?xml version="1.0" encoding="UTF-8"?>				<!-- 작성한 document의 양식이 xml임을 명시하고 인코딩형식을 utf-8로 설정 -->
<%@ page contentType="text/xml; charset=UTF-8" %>	<!-- 디렉티브 태그를 이용하여 생성할 문서의 컨텐츠 유형을 text/xml, 문자열세트를 utf-8로 지정-->
<%@ page import="java.sql.*" %>						<!-- 디렉티브 태그를 이용하여 자바 패키지 내 sql 하위 모든 클래스를 import -->
<%
	Class.forName("com.mysql.jdbc.Driver");		// jdbc 로드
	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/kopoctc", "root", "abcd1234");	// 계정정보로 DB에 접속한 connecion 객체 저장
	
	Statement stmt = conn.createStatement();	// DB에 쿼리문를 날려주는 statement 객체 생성
	
	String query = "select h.id, h.name, " + 
				"ifnull((select count(*) from tupyo_table as t where t.id=h.id group by t.id), 0) as cnt, " + 
				"ifnull((select count(*) from tupyo_table as t where t.id=h.id group by t.id), 0)/(select count(*) from tupyo_table) as rate " + 
				"from hubo_table as h group by id;";	// 모든 후보에 대하여 모든 득표수와 득표율을 조회하는 쿼리를 변수에 저장
	
	ResultSet rset = stmt.executeQuery(query);	// 모든 후보에 대하여 모든 득표수와 득표율을 조회하는 쿼리 실행
	
	out.println("<datas>");						
	out.println("<hubos>");
	while (rset.next()) {										// 모든 조회 레코드에 대하여 반복
		out.println("<hubo>");									// 필드값의 태그들을 hubo태그로 감싸 한 명의 후보에 대한 데이터를 명시한다.
		out.println("<id>" + rset.getInt(1) + "</id>");			// 후보 기호를 id태그로 감싼다.
		out.println("<name>" + rset.getString(2) + "</name>");	// 후보 이름을 name태그로 감싼다.
		out.println("<count>" + rset.getInt(3) + "</count>");	// 특표수를 count태그로 감싼다.
		out.println("<rate>" + rset.getDouble(4) + "</rate>");	// 득표율을 rate태그로 감싼다.
		out.println("</hubo>");
	}
	out.println("</hubos>");
	out.println("</datas>");
	
	rset.close();		// ResultSet 자원 반납
	stmt.close();		// Statment 자원 반납
	conn.close();		// Connection 자원 반납
%>