<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
	<!-- http-equiv 속성은 content 속성에 명시된 값에 대한 HTTP 헤더를 제공 -->
<%@ page errorPage="./error.jsp" %>					<!-- 디렉티브 태그를 이용하여 에러 발생시 호출할 페이지 지정-->
<%@ page contentType="text/html; charset=UTF-8" %>	<!-- 디렉티브 태그를 이용하여 생성할 문서의 컨텐츠 유형을 text/html로, 문자열세트를 utf-8로 지정-->
<%@ page import="java.sql.*" %>						<!-- 디렉티브 태그를 이용하여 자바 패키지 내 sql 하위 모든 클래스를 import -->
<html>
<head>
	<style>
		a {							/* a태그의 css속성 지정 */
			color : grey;			/* 글자 색 회색으로 지정 */
			text-decoration : none;	/* a태그 글자의 밑줄 삭제 */
		}
		a:hover {					/* a태그에 마우스가 올라왔을 때의 css속성 지정*/
			color : blue;			/* 글자 색 파란색으로 지정 */
		}
	</style>
	<%
		String id_str = request.getParameter("id_str");
		
		Class.forName("com.mysql.jdbc.Driver");				// jdbc 로드
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/kopoctc", "root", "abcd1234");	// 계정정보로 DB에 접속한 connecion 객체 저장
		
		Statement stmt = conn.createStatement();			// DB에 쿼리문를 날려주는 statement 객체 생성
		
		String query = "delete from examtable where studentid=" + id_str + ";";
		stmt.execute(query);
		
		stmt.close();		// Statment 자원 반납
		conn.close();		// Connection 자원 반납
	%>
	<script>
		window.location= "./allViewDB.jsp";		<!-- 데이터 삭제 후 목록 조회 allViewDB.jsp로 이동 -->
	</script>
</head>

<body>
	
</body>
</html>
