<?xml version="1.0" encoding="UTF-8"?>								<!-- 작성한 document의 양식이 xml임을 명시하고 인코딩형식을 utf-8로 설정 -->
<%@ page contentType="text/xml; charset=utf-8"%>					<!-- 디렉티브 태그를 이용하여 생성할 문서의 컨텐츠 유형을 text/html로, 문자열세트를 utf-8로 지정-->
<%@ page import="java.sql.*, javax.sql.*, java.io.*, java.net.*"%>	<!-- 디렉티브 태그를 이용하여 필요한 자바 패키지를 import -->

<%
	Class.forName("com.mysql.jdbc.Driver");		// jdbc 로드
	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/kopoctc", "root", "abcd1234");	// 계정정보로 DB에 접속한 connecion 객체 저장
		
	Statement stmt = conn.createStatement();	// DB에 쿼리문를 날려주는 statement 객체 생성
	
	ResultSet rset = stmt.executeQuery("select * from examtable;");

	out.println("<datas>");
	while (rset.next()) {
		out.println("<data>");											// data태그로 감싸 하위 태그의 요소들이 하나의 data에 대한 정보임을 명시한다.
		out.println("<name>" + rset.getString(1) + "</name>");			// 학생 이름을 id태그로 감싼다.
		out.println("<studentid>" + rset.getInt(2) + "</studentid>");	// 학생 학번을 studentid태그 감싼다.
		out.println("<kor>" + rset.getInt(3) + "</kor>");				// 국어 성적을 kor태그 감싼다.
		out.println("<eng>" + rset.getInt(4) + "</eng>");				// 영어 성적을 eng태그 감싼다.
		out.println("<mat>" + rset.getInt(5) + "</mat>");				// 수학 성적을 mat태그 감싼다.
		
		out.println("</data>");
	}
	out.println("</datas>");
	
	stmt.close();		// Statment 자원 반납
	conn.close();		// Connection 자원 반납
%>