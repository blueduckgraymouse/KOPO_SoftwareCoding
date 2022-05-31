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
			border-radius : 5px;		/* 버튼 클래스의 경계선 라운딩 처리, 너비 80, 높이25px, 배경색 녹색 지정 */
			width : 80px;
			height : 25px;
			background-color : green;
		}
		input[type=text], input[type=number] {	
			 border: 2px solid green;	/* 숫자와 텍스트 타입의 input태그의 경계선을 꽉 찬 녹색 2px로 지정 */
		}
	</style>
	<%
		Class.forName("com.mysql.jdbc.Driver");		// jdbc 로드
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/kopoctc", "root", "abcd1234");	// 계정정보로 DB에 접속한 connecion 객체 저장
		
		Statement stmt = conn.createStatement();	// DB에 쿼리문를 날려주는 statement 객체 생성
		
		String query = "select * from hubo_table;";	// 모든 후보 테이블의 레코드를 조회하는 퀴리문 변수에 저장
		
		ResultSet rset = stmt.executeQuery(query);	//모든 후보 테이블의 레코드를 조회한 결과를 ResultSet객체에 저장
	%>
	<script>
		function check(form_t) {
			if(form_t.select_hubo.value == "") {
				window.alert("투표할 후보가 없습니다.");
				return false;
			}
			form_t.submit();
		}
	</script>
</head>

<body>
	<div align="center">
		<table class="banner" border=1 cellspacing=1>				<!-- 배너에 해당하는 테이블 -->
			<tr>
				<td><a href="http://192.168.23.94:8002/ch05/tupyo/A_01.jsp">후보등록</a></td>
				<th  bgcolor=yellow><a href="http://192.168.23.94:8002/ch05/tupyo/B_01.jsp">투표</a></th>
				<td><a href="http://192.168.23.94:8002/ch05/tupyo/C_01.jsp">개표결과</a></td>
			</tr>
		</table>
		
		<br>
		<form name="form_tupyo" id="form_tupyo" method=post action="./B_02.jsp">
		<table>
			<tr>
				<td>
					<select id="select_hubo" name="select_hubo">			<!-- 모든 후보들을 선택 태그로 나타낸다. -->
						<%
							while (rset.next()) {
								out.println("<option value='" + rset.getInt(1) + "'>" + rset.getInt(1) + "번 " + rset.getString(2) + "</option>");
							}
						%>
					</select>
				</td>
				<td>
					<select id="select_ageRange" name="select_ageRange">	<!-- 10~90대를 의미하는 1~9를 선택 태그로 나타낸다. -->
						<%
							for(int i = 1 ; i < 10 ; i++) {
								out.println("<option value='" + i + "'>" + i + "0대</option>");
							}
						%>
					</select>
				</td>				
				<td>
					<input type="button" class="btn" onclick="check(this.form);" value="투표하기" />
				</td>						
			</tr>
		</table>
		</form>
	</div>
	<%
		rset.close();		// ResultSet 자원 반납
		stmt.close();		// Statment 자원 반납
		conn.close();		// Connection 자원 반납
	%>
</body>
</html>
