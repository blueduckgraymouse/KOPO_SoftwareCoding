<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
	<!-- http-equiv 속성은 content 속성에 명시된 값에 대한 HTTP 헤더를 제공 -->
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
		input::-webkit-inner-spin-button {
			appearance : none;			/* number type의 input태그 옆에 값 조절 버튼 제거 */
		}
	</style>
	
	<%
		Class.forName("com.mysql.jdbc.Driver");		// jdbc 로드
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/kopoctc", "root", "abcd1234");	// 계정정보로 DB에 접속한 connecion 객체 저장
		
		Statement stmt = conn.createStatement();	// DB에 쿼리문를 날려주는 statement 객체 생성
		

		String query1 = "select id+1 from hubo_table where (id+1) not in (select id from hubo_table);";	// 기존 존재하는 학번 번호 중 비어있는 학번의 가장 빠른 학번 번호 조회
		ResultSet rset1 = stmt.executeQuery(query1);
		int no_new = 1;								// 기본 시작 학번 번호
		try{												// 조회한 다음으로 올 학번번호
			rset1.next();
			no_new = rset1.getInt(1);
		} catch(Exception e) {}
		
		String query2 = "select id from hubo_table limit 1";	// 기존 존재하는 학번 번호 중 비어있는 학번의 가장 빠른 학번 번호 조회
		ResultSet rset2 = stmt.executeQuery(query2);
		int no_first = 0;								// 기본 시작 학번 번호
		try{									// 조회한 다음으로 올 학번번호
			rset2.next();
			no_first = rset2.getInt(1);
		} catch(Exception e) {
			no_new = 1;
		}
		if ( no_first != 1) {
			no_new = 1;
		}
		
		String query = "select * from hubo_table;";	// 모든 후보 레코드 조회 쿼리문 저장
		
		ResultSet rset = stmt.executeQuery(query);	// 모든 후보 레코드 조회
	%>
		
	<script>
		// 후보 삭제 버튼 클릭시 호출되는 함수
		function delete_hubo(num_hubo) {
			if (window.confirm("삭제하시겠습니까?") == false) {	// 팝업창으로 최종 확인
				return false;
			}
			document.form_delete.no_hubo.value = num_hubo;	// 현재 눌린 버튼의 표시값을 가져와 hidden으로 선언된 input태그에 값을 저장
			document.form_delete.submit();
		}
	
		// 후보 저장 버튼 클릭시 호출되는 함수
		function insert_hubo(form) {
			// 저장할 후보 이름의 유효성 검사 - 글자수
			name = form.name_hubo.value;
			if (name.length > 20 || name.length < 1) {
				window.alert("이름은 1~20글자로 입력 가능하며 필수 입력입니다.");
				form.name_hubo.focus();
				return false;
			}
			// 이름에 들어갈 수 있는 문자 한글, 영서 숫자로 제한
			const regex = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|]+$/;
			if (!regex.test(name)) {
				window.alert("이름에는 한글, 영어, 숫자 입력이 가능합니다.");
				form.name_hubo.focus();
				return false;
			}
			
			document.form_insert.submit();
		}
	</script>
</head>

<body>
	<div align="center">
		<table class="banner" border=1 cellspacing=1>			<!-- 배너에 해당하는 테이블 -->
			<tr>
				<th bgcolor=yellow><a href="http://192.168.23.94:8002/ch05/tupyo/A_01.jsp">후보등록</a></th>
				<td><a href="http://192.168.23.94:8002/ch05/tupyo/B_01.jsp">투표</a></td>
				<td><a href="http://192.168.23.94:8002/ch05/tupyo/C_01.jsp">개표결과</a></td>
			</tr>
		</table>
		
		<br>
		
		<table padding=5px border=3 cellspacing=0>										<!-- 후보 목록과 추가 입력 가능한 줄 출력하는 테이블 -->
			<form method=post action="./A_02.jsp" name="form_delete" id="form_delete">	<!-- 삭제버튼들의 form 태그 -->
				<input type="hidden" name="no_hubo" id="no_hubo">				<!-- 삭제 버튼 클릭시 클릭한 버튼의 후보 번호가 이 hidden태그의 값을 저장되어 파라미터로 전달된다 -->
				<%
					while (rset.next()) {										// 모든 후보에 대하여 반복
						out.println("<tr>");
						out.println("<td width=150px>기호번호 : <span class='nums_hubo'>" + rset.getInt(1) + "</span></td>");	// 후보 번호 출력
						out.println("<td width=300px>후보명 : " + rset.getString(2) + "</td>");								// 후보 이름 출력
						out.println("<td width=100px align=center><input type='button' onclick='delete_hubo(" +  rset.getInt(1) + ")' value='삭제' class='btn'></td>");
						out.println("</tr>");									// 각 삭제 버튼마다 호출되는 함수의 파라미터로 해당하는 후보의 id가 지정되어 있다.
					}
				%>
			</form>
			
			<form method=post action="./A_03.jsp" name="form_insert" id="form_insert">	<!-- 추가 버튼의 form태그 -->
				<tr>
					<td width=200px>기호번호 : <input type="number" name="id_hubo" id="id_hubo" value="<%= no_new %>"style="width:50px;" readOnly></td>
					<td width=300px>후보명 : <input type="text" name="name_hubo" id="name_hubo" ></td>
					<td width=100px align=center><input type="button" value="추가" onclick="insert_hubo(this.form)" class="btn"></td>
				</tr>														<!-- 저장버튼 클릭시 insert_hubo함수 호출 -->
			</form>		
		</table>
	</div>
	
	<%
		rset.close();		// ResultSet 자원 반납
		stmt.close();		// Statment 자원 반납
		conn.close();		// Connection 자원 반납
	%>
</body>
</html>
