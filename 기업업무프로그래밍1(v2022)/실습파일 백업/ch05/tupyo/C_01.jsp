<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
	<!-- http-equiv 속성은 content 속성에 명시된 값에 대한 HTTP 헤더를 제공 -->
<%@ page contentType="text/html; charset=UTF-8" %>	<!-- 디렉티브 태그를 이용하여 생성할 문서의 컨텐츠 유형을 text/html로, 문자열세트를 utf-8로 지정-->
<%@ page import="java.sql.*" %>						<!-- 디렉티브 태그를 이용하여 자바 패키지 내 sql 하위 모든 클래스를 import -->
<%@ page errorPage="./error.jsp" %>					<!-- 디렉티브 태그를 이용하여 에러 발생시 호출할 페이지 지정-->
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
		.banner {
			border : 1px blue solid;	/* 배너 클래스에 꽉 찬 바란색 경계선 두께 1px로 지정 & 폰트 사이즈 20px */
			font-size : 20px;
		}
		.btn {
			border-radius : 5px;	/* 버튼 클래스의 경계선 라운딩 처리, 너비 80, 높이25px, 배경색 녹색 지정 */
			width : 80px;
			height : 25px;
			background-color : green;
		}
		input[type=text], input[type=number] {	
			 border: 2px solid green;			/* 숫자와 텍스트 타입의 input태그의 경계선을 꽉 찬 녹색 2px로 지정 */
		}
		hr {
			float : left;						/* hr태그의 한줄 공간 차지를 변경하여 이어서 다른 태그나 html이 위치할 수 있도록 설정, 두께 15px로 지정 */
			height : 15px;
		}
		#bar tr {
			height : 50px;
		}
	</style>
	<%
		Class.forName("com.mysql.jdbc.Driver");		// jdbc 로드
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/kopoctc", "root", "abcd1234");	// 계정정보로 DB에 접속한 connecion 객체 저장
		
		Statement stmt = conn.createStatement();	// DB에 쿼리문를 날려주는 statement 객체 생성
		
		String query = "select h.id, h.name, " + 
					"ifnull((select count(*) from tupyo_table as t where t.id=h.id group by t.id), 0) as cnt, " + 
					"ifnull((select count(*) from tupyo_table as t where t.id=h.id group by t.id), 0)/(select count(*) from tupyo_table) as rate " + 
					"from hubo_table as h group by id;";	// 모든 후보에 대하여 모든 득표수와 득표율을 조회하는 쿼리를 변수에 저장
		
		ResultSet rset = stmt.executeQuery(query);	// 모든 후보에 대하여 모든 득표수와 득표율을 조회하는 쿼리 실행
	%>
</head>

<body>
	<div align="center">
		<table class="banner" border=1 cellspacing=1>
			<tr>
				<td><a href="http://192.168.23.94:8002/ch05/tupyo/A_01.jsp">후보등록</a></td>
				<td><a href="http://192.168.23.94:8002/ch05/tupyo/B_01.jsp">투표</a></td>
				<th bgcolor=yellow><a href="http://192.168.23.94:8002/ch05/tupyo/C_01.jsp">개표결과</a></th>
			</tr>
		</table>
		
		<br>
		
		<table border=1 id="bar">
			<%
				while (rset.next()) {		// 조회한 모든 후보 레코드에 대하여 반복
					out.println("<tr><td><a href='./C_02.jsp?id_hubo=" + rset.getInt(1) + "'>" + rset.getInt(1) + ". " + rset.getString(2) +"</a></td>");
					if(rset.getInt(3) == 0) {
						out.println("<td width=450 height=15>" + rset.getInt(3) + "표 (" + (rset.getDouble(4)*100) + "%)</td>");
					} else {
						out.println("<td width=450 height=15><hr color='red' width=" + (int)(rset.getDouble(4)*300) +
								" height=15>&nbsp;&nbsp;" + rset.getInt(3) + "표 (" + (rset.getDouble(4)*100) + "%)</td>");
					}
				}							// 이름은 a태그로 c_02.jsp로 링크, 그래프는 득표율 100%기준일 때 길이가 300px.
			%>
		</table>
	</div>
	
	<%
		rset.close();		// ResultSet 자원 반납
		stmt.close();		// Statment 자원 반납
		conn.close();		// Connection 자원 반납
	%>
</body>
</html>
