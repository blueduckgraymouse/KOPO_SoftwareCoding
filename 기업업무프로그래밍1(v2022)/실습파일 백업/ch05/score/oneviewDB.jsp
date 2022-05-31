<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
	<!-- http-equiv 속성은 content 속성에 명시된 값에 대한 HTTP 헤더를 제공 -->
<%@ page errorPage="./error.jsp" %>					<!-- 디렉티브 태그를 이용하여 에러 발생시 호출할 페이지 지정-->
<%@ page contentType="text/html; charset=UTF-8" %>	<!-- 디렉티브 태그를 이용하여 생성할 문서의 컨텐츠 유형을 text/html로, 문자열세트를 utf-8로 지정-->
<%@ page import="java.sql.*" %>						<!-- 디렉티브 태그를 이용하여 자바 패키지 내 sql 하위 모든 클래스를 import -->
<html>
<head>
	<%
		String name = request.getParameter("name"); // 현재 페이지로 들어온 request객체에 name이라는 파라미터의 값을 변수 name에 저장
	
		Class.forName("com.mysql.jdbc.Driver");		// jdbc 로드
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/kopoctc", "root", "abcd1234");	// 계정정보로 DB에 접속한 connecion 객체 저장
		
		Statement stmt = conn.createStatement();	// DB에 쿼리문를 날려주는 statement 객체 생성
		String query = "select *, (t.kor+t.eng+t.mat) as sum, (t.kor+t.eng+t.mat)/3 as ave, (select count(*)+1 from examtable as r where r.kor+r.eng+r.mat > sum) as rank_sum from examtable as t where name ='" + name +"';";	// examtable 테이블에서 name변수의 값을 조건으로 조회 쿼리문을 query변수에 저장
		ResultSet rset = stmt.executeQuery(query); 	// 쿼리문 실행한 결과를 ResultSet객체에 저장
	%>
</head>

<body>
	<div align="center">
		<table cellspacing=1 border=1>		<!-- 테이블의 셀간 여백 1, 선 두께 1로 지정 -->
		<tr height=40px  bgcolor=gray>		<!-- 현재 행의 높이를 40px, 배경색을 회색으로 지정 -->
			<th width=100px>				<!-- 현재 열의 너비를 100px로 지정 -->
				이름
			</th>
			<th width=100px>				<!-- 현재 열의 너비를 100px로 지정 -->
				학번
			</th>
			<th width=100px>				<!-- 현재 열의 너비를 100px로 지정 -->
				국어 점수
			</th>
			<th width=100px>				<!-- 현재 열의 너비를 100px로 지정 -->
				영어 점수
			</th>
			<th width=100px>				<!-- 현재 열의 너비를 100px로 지정 -->
				수학 점수
			</th>	
			<th width=100px>				<!-- 현재 열의 너비를 100px로 지정 -->
				총점
			</th>
			<th width=100px>				<!-- 현재 열의 너비를 100px로 지정 -->
				평균
			</th>	
			<th width=100px>				<!-- 현재 열의 너비를 100px로 지정 -->
				등수
			</th>	
		</tr>
		<%
			while (rset.next()) {			// ResultSet에 저장된 모든 레코드에 대하여 반복
				out.println("<tr height=50px>");
				out.println("<td width=50><p align='center'>" + rset.getString(1) + "</p></td>");	// 이름 출력
				out.println("<td width=50><p align='center'>" + rset.getInt(2) + "</p></td>");		// 학번 출력
				out.println("<td width=50><p align='center'>" + rset.getInt(3) + "</p></td>");		// 국어 점수 출력
				out.println("<td width=50><p align='center'>" + rset.getInt(4) + "</p></td>");		// 영어 점수 출력
				out.println("<td width=50><p align='center'>" + rset.getInt(5) + "</p></td>");		// 수학 점수 출력
				out.println("<td width=50><p align='center'>" + rset.getObject(6) + "</p></td>"); 	// 총점 출력
				out.println("<td width=50><p align='center'>" + rset.getObject(7) + "</p></td>"); 	// 평균 출력
				out.println("<td width=50><p align='center'>" + rset.getObject(8) + "</p></td>"); 	// 등수 출력			
				out.println("</tr>");
			}
		%>
		</table>
		
		<%	
			rset.close();		// ResultSet 자원 반납
			stmt.close();		// Statment 자원 반납
			conn.close();		// Connection 자원 반납
		%>
	</div>
</body>
</html>