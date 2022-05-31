<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
<%@ page contentType="text/html; charset=UTF-8" %>	<!-- 디렉티브 태그를 이용하여 생성할 문서의 컨텐츠 유형을 text/html로, 문자열세트를 utf-8로 지정-->
<%@ page import="java.sql.*" %>						<!-- 디렉티브 태그를 이용하여 자바 패키지 내 sql 하위 모든 클래스를 import -->
<html>

<head>
</head>

<body>
	<h1>조회</h1>
	
	<%
		Class.forName("com.mysql.jdbc.Driver");			// jdbc로드
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/kopoctc", "root", "abcd1234");	// 계정정보로 DB에 접속한 connecion 객체 저장
		Statement stmt = conn.createStatement();							// DB에 쿼리문를 날려주는 statement 객체 생성
		ResultSet rset = stmt.executeQuery("select * from examtable;");		// examtable테이블 조회 쿼리 실행한 결과를 ResultSet 객체에 저장
	%>
	
	<table cellspacing=1 width=600 border=1>			<!-- 테이블의 셀간 여백 1, 선 두께 1로 지정 -->
	<%
		while (rset.next()) {							// ResultSet에 저장된 모든 레코드에 대하여 반복
			out.println("<tr>");
			out.println("<td width=50>" + rset.getString(1) + "</td>");						// 이름 출력
			out.println("<td width=50><p align='center'>" + rset.getInt(2) + "</p></td>");	// 학번 출력
			out.println("<td width=50><p align='center'>" + rset.getInt(3) + "</p></td>");	// 국어 점수 출력
			out.println("<td width=50><p align='center'>" + rset.getInt(4) + "</p></td>");	// 영어 점수 출력
			out.println("<td width=50><p align='center'>" + rset.getInt(5) + "</p></td>");	// 수학 점수 출력
			out.println("</tr>");
		}
		rset.close();	// ResultSet 자원 반납
		stmt.close();	// Statment 자원 반납
		conn.close();	// Connection 자원 반납
	%>
	</table>
</body>
	
</html>