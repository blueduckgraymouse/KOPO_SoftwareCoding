<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
	<!-- http-equiv 속성은 content 속성에 명시된 값에 대한 HTTP 헤더를 제공 -->
<%@ page contentType="text/html; charset=UTF-8" %>	<!-- 디렉티브 태그를 이용하여 생성할 문서의 컨텐츠 유형을 text/html로, 문자열세트를 utf-8로 지정-->
<%@ page import="java.sql.*" %>						<!-- 디렉티브 태그를 이용하여 자바 패키지 내 sql 하위 모든 클래스를 import -->
<%@ page errorPage="./error.jsp" %>					<!-- 디렉티브 태그를 이용하여 에러 발생시 호출할 페이지 지정-->
<html>
<head>
	<style>
		.banner {
			border : 1px blue solid;/* 배너 클래스에 꽉 찬 바란색 경계선 두께 1px로 지정 & 폰트 사이즈 20px */
			font-size : 20px;
		}
		a {							/* a태그의 css속성 지정 */
			color : grey;			/* 글자 색 회색으로 지정 */
			text-decoration : none;	/* a태그 글자의 밑줄 삭제 */
		}
		a:hover {					/* a태그에 마우스가 올라왔을 때의 css속성 지정*/
			color : blue;			/* 글자 색 파란색으로 지정 */
		}		
	</style>
	<%
		request.setCharacterEncoding("UTF-8");				// request객체의 인코딩을 utf-8로 명시
	
		String id_hubo = request.getParameter("id_hubo");		// 입력할 후보 번호를 파라미터로 받아 변수에 저장
		String name_hubo = request.getParameter("name_hubo");	// 입력할 후보 이름을 파라미터로 받아 변수에 저장
	
		Class.forName("com.mysql.jdbc.Driver");		// jdbc 로드
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/kopoctc", "root", "abcd1234");	// 계정정보로 DB에 접속한 connecion 객체 저장
		
		Statement stmt = conn.createStatement();	// DB에 쿼리문를 날려주는 statement 객체 생성
		
		String query = "insert into hubo_table values(" + id_hubo + ", '" + name_hubo + "');";	// 후보 입력 쿼리문 변수에 저장
		stmt.execute(query);						//  후보 입력 쿼리문 실행
		
		stmt.close();		// Statment 자원 반납
		conn.close();		// Connection 자원 반납
	%>
</head>

<body>
	<div align="center">
		<table class="banner" border=1 cellspacing=1>
			<tr>
				<th bgcolor=yellow><a href="http://192.168.23.94:8002/ch05/tupyo/A_01.jsp">후보등록</a></th>
				<td><a href="http://192.168.23.94:8002/ch05/tupyo/B_01.jsp">투표</a></td>
				<td><a href="http://192.168.23.94:8002/ch05/tupyo/C_01.jsp">개표결과</a></td>
			</tr>
		</table>
		
		<br>
		
		<b>후보 등록 결과 : 후보가 추가되었습니다.</b>		<!-- 입력 완료 메세지 출력 -->

	</div>
</body>
</html>
