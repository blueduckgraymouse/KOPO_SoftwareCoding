<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
	<!-- http-equiv 속성은 content 속성에 명시된 값에 대한 HTTP 헤더를 제공 -->
<%@ page contentType="text/html; charset=UTF-8" %>	<!-- 디렉티브 태그를 이용하여 생성할 문서의 컨텐츠 유형을 text/html로, 문자열세트를 utf-8로 지정-->
<%@ page import="java.sql.*" %>						<!-- 디렉티브 태그를 이용하여 자바 패키지 내 sql 하위 모든 클래스를 import -->
<html>
<head>
	<style>
		table, tr, td {
			border: 1px solid black;	/* 꽉찬 검정 경계선 1px  */
			border-cellpadding : 0;		/* 셀간 경계선 사이 여백 제거  */
			border-collapse: collapse;	/* 셀간 경계선 충돌 허용 */
		}
		td {
			text-align : center;		/* 문자 중앙 정렬, 높이 35px 설정 */
			height : 35px;				
		}
		.button {
			background-color: yellow;	/* button 클래스의 배경 노란색, 높이 30px, 너비 100px 설정 */
			height : 30px;
			width : 100px;
		}
		
	</style>
	
	<%
		request.setCharacterEncoding("UTF-8");					// request객체의 인코딩을 utf-8로 명시
	
		String name_str = request.getParameter("name_str");		// 입력할 레코드값을 파라미터로 전달받아 변수에 저장
		String[] scores = {request.getParameter("score_kor"),
						request.getParameter("score_eng"),
						request.getParameter("score_mat")};
	
		Class.forName("com.mysql.jdbc.Driver");				// jdbc 로드
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/kopoctc", "root", "abcd1234");	// 계정정보로 DB에 접속한 connecion 객체 저장
		
		Statement stmt = conn.createStatement();			// DB에 쿼리문를 날려주는 statement 객체 생성
		
		String query1 = "select studentid+1 from examtable where (studentid+1) not in (select studentid from examtable);";	// 기존 존재하는 학번 번호 중 비어있는 학번의 가장 빠른 학번 번호 조회
		ResultSet rset1 = stmt.executeQuery(query1);
		int no_new = 209901;								// 기본 시작 학번 번호
		try{												// 조회한 다음으로 올 학번번호
			rset1.next();
			no_new = rset1.getInt(1);
		} catch(Exception e) {}
		
		String query2 = "select studentid from examtable limit 1";	// 기존 존재하는 학번 번호 중 비어있는 학번의 가장 빠른 학번 번호 조회
		ResultSet rset2 = stmt.executeQuery(query2);
		int no_first = 0;								// 기본 시작 학번 번호
		try{									// 조회한 다음으로 올 학번번호
			rset2.next();
			no_first = rset2.getInt(1);
		} catch(Exception e) {
			no_new = 209901;
		}
		if ( no_first != 209901) {
			no_new = 209901;
		}
		
		
		
		// 임의의 데이터 입력 쿼리 실행
		String query3 = "insert into examtable (name, studentid, kor, eng, mat) values('" +
						name_str + "'," + no_new + ", " + scores[0] + ", " + scores[1] + ", " + scores[2] + ");";
		stmt.execute(query3);

		stmt.close();		// Statment 자원 반납
		conn.close();		// Connection 자원 반납
	%>
	
	<script>
		function goback() {
			window.alert("뒤로 가긴 하지만 데이터는 이미 저장된거 아시죠?");
			history.go(-1);
		}
	</script>
</head>

<body>
	<div align=center >
		<h1>성적 입력</h1>
		
		<table>
			<tr>
				<td width=100px>
					이름
				</td>
				<td width=250px>
					<%= request.getParameter("name_str") %>
				</td>			
			</tr>
			<tr>
				<td>
					학번
				</td>
				<td>
					<%= no_new %>
				</td>			
			</tr>		
			<tr>
				<td>
					국어
				</td>
				<td>
					<%= request.getParameter("score_kor") %>
				</td>			
			</tr>		
			<tr>
				<td>
					영어
				</td>
				<td>
					<%= request.getParameter("score_eng") %>
				</td>			
			</tr>		
			<tr>
				<td>
					수학
				</td>
				<td>
					<%= request.getParameter("score_mat") %>
				</td>			
			</tr>		
		</table>
		<br><br>
		<button onclick="goback();" class="button"><b>뒤로가기<b></button>
	</div>
	
</body>
</html>