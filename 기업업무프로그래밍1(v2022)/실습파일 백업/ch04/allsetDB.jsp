<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
	<!-- http-equiv 속성은 content 속성에 명시된 값에 대한 HTTP 헤더를 제공 -->
<%@ page contentType="text/html; charset=UTF-8" %>	<!-- 디렉티브 태그를 이용하여 생성할 문서의 컨텐츠 유형을 text/html로, 문자열세트를 utf-8로 지정-->
<%@ page import="java.sql.*" %>						<!-- 디렉티브 태그를 이용하여 자바 패키지 내 sql 하위 모든 클래스를 import -->
<%@ page errorPage="error.jsp" %>					<!-- 디렉티브 태그를 이용하여 에러 발생시 호출할 페이지 지정-->
<html>

<head>
	<%
		Class.forName("com.mysql.jdbc.Driver");				// jdbc 로드
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/kopoctc", "root", "abcd1234");	// 계정정보로 DB에 접속한 connecion 객체 저장
		
		Statement stmt = conn.createStatement();			// DB에 쿼리문를 날려주는 statement 객체 생성
		
		// 임의의 데이터 입력 쿼리 실행
		stmt.execute("insert into examtable (name, studentid, kor, eng, mat) values('나연',209901, 95, 100, 95);");
		stmt.execute("insert into examtable (name, studentid, kor, eng, mat) values('정연',209902, 95, 95, 95);");
		stmt.execute("insert into examtable (name, studentid, kor, eng, mat) values('모모',209903, 100, 100, 100);");
		stmt.execute("insert into examtable (name, studentid, kor, eng, mat) values('사나',209904, 100, 95, 90);");
		stmt.execute("insert into examtable (name, studentid, kor, eng, mat) values('지효',209905, 80, 100, 70);");
		stmt.execute("insert into examtable (name, studentid, kor, eng, mat) values('미나',209906, 100, 100, 70);");
		stmt.execute("insert into examtable (name, studentid, kor, eng, mat) values('다현',209907, 70, 70, 70);");
		stmt.execute("insert into examtable (name, studentid, kor, eng, mat) values('채영',209908, 80, 75, 72);");
		stmt.execute("insert into examtable (name, studentid, kor, eng, mat) values('쯔위',209909, 78, 79, 82);");
		stmt.execute("insert into examtable (name, studentid, kor, eng, mat) values('나연',209910, 100, 100, 100);");

		stmt.close();		// Statment 자원 반납
		conn.close();		// Connection 자원 반납
	%>
</head>

<body>
	<h1> 실습데이터 입력 <h1>	
</body>
</html>