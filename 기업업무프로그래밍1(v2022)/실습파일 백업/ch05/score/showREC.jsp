<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
	<!-- http-equiv 속성은 content 속성에 명시된 값에 대한 HTTP 헤더를 제공 -->
<%@ page errorPage="./error.jsp" %>					<!-- 디렉티브 태그를 이용하여 에러 발생시 호출할 페이지 지정-->
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
		input::-webkit-inner-spin-button {
			appearance : none;			/* number type의 input태그 옆에 값 조절 버튼 제거 */
		}
		a {								/* a태그의 css속성 지정 */
			color : grey;				/* 글자 색 회색으로 지정 */
			text-decoration : none;		/* a태그 글자의 밑줄 삭제 */
		}
		a:hover {						/* a태그에 마우스가 올라왔을 때의 css속성 지정*/
			color : blue;				/* 글자 색 파란색으로 지정 */
		}
	</style>
	<%
		String id_str = request.getParameter("id_str");	// 파라미터로 넘어온 조회할 학번 변수에 저장
		Class.forName("com.mysql.jdbc.Driver");			// jdbc 로드
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/kopoctc", "root", "abcd1234");	// 계정정보로 DB에 접속한 connecion 객체 저장
		
		Statement stmt = conn.createStatement();		// DB에 쿼리문를 날려주는 statement 객체 생성
		String query = "select * from examtable where studentid = " + id_str + ";";		// examtable 테이블 조회 쿼리 query변수에 저장
		ResultSet rset = stmt.executeQuery(query); 		// 쿼리문 실행한 결과를 ResultSet객체에 저장
		
		String name_str = "";							// 조회 값이 저장될 변수 선언, 해당학번 없는 경우가 있으므로 변수로 한번 저장해서 처리.
		String studentid = "";
		String score_kor = "";
		String score_eng = "";
		String score_mat = "";
		
		if (rset.next()) {								// 조회한 학번의 레코드가 존재할 경우 조회값을 변수에 저장
			name_str = rset.getString(1);
			studentid = Integer.toString(rset.getInt(2));
			score_kor = String.valueOf(rset.getObject(3));
			score_eng = String.valueOf(rset.getObject(4));
			score_mat = String.valueOf(rset.getObject(5));
		} else {										// 조회할 학번의 레코드가 없는 경우
			name_str = "해당학번없음";						
		}
	
		rset.close();		// ResultSet 자원 반납
		stmt.close();		// Statment 자원 반납
		conn.close();		// Connection 자원 반납
	%>
</head>

<body>
	<div align=center >
		<h1>성적 조회 후 정정 / 삭제</h1>
		
		<br>
		
		<form id="insert_id" method="post" action="showREC.jsp">
			<b>조회할 학번</b>&nbsp;<input type="number" name="id_str"  required>	&nbsp;<input type="submit" class="button" value="조회" />
		</form>
		
		<br>
		
		<form id="update_form" method="post">
			<table>
				<tr>
					<td width=100px>
						이름
					</td>
					<td width=250px>
						<input type=text name="name_str" id="name_str" value="<%= name_str %>" class="inputs">
					</td>
				</tr>
				<tr>
					<td>
						학번
					</td>
					<td>
						<input type=number name="id_str" id="id_str" value="<%= studentid %>" class="inputs" readOnly>
					</td>																<!-- 학번은 pk이므로 항상 readOnly -->
				</tr>
				<tr>
					<td>
						국어
					</td>
					<td>
						<input type=number name="score_kor" id="score_kor" value="<%= score_kor %>" class="inputs">
					</td>
				</tr>		
				<tr>
					<td>
						영어
					</td>
					<td>
						<input type=number name="score_eng" id="score_eng" value="<%= score_eng %>" class="inputs">
					</td>
				</tr>
				<tr>
					<td>
						수학
					</td>
					<td>
						<input type=number name="score_mat" id="score_mat" value="<%= score_mat %>" class="inputs">
					</td>			
				</tr>		
			</table>
			
			<br>
			
			<div id="btn_onoff">									<!-- 학번이 없는 경우 버튼을 숨기기 위해 div태그로 감싸서 토글 -->
				<input type="button" onclick="update_record(this.form);" id="btn_save" class="button" value="저장" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" onclick="delete_record(this.form);" id="btn_delete" class="button" value="삭제" />
			</div>
		</form>
	</div>
	
	<script>
		// 조회한 학번의 레코드가 없는 경우 input태그를 모두 readOnly로 설정하고, "저장", "삭제"버튼을 숨김.
		if ("<%=name_str%>" === "해당학번없음") {
			var inputs = document.getElementsByClassName('inputs');
			for (var i = 0 ; i < inputs.length ; i++) {
				inputs[i].readOnly = true;
			}
			document.getElementById('btn_onoff').style.display = "none";
		}

		// 수정 버튼 클릭시 유효성검사 & 이동할 페이지를 수정 페이지로 지정하는 함수
		function update_record(form_update) {
			// 이름 길이 1~20글자 제한
			var name = form_update.name_str.value;
			if (name.length > 20 || name.length < 1) {
				window.alert("이름은 1~20글자로 입력 가능합니다.");
				form_update.name_str.focus();
				return false;
			}
			// 이름에 들어갈 수 있는 문자 한글, 영서 숫자로 제한
			let regex = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|]+$/;
			if (!regex.test(name)) {
				window.alert("이름에는 한글, 영어, 숫자 입력이 가능합니다.");
				form_update.name_str.focus();
				return false;
			}
			// 국어점수 필수 입력
			var score_k = form_update.score_kor.value;
			if (score_k.length < 1) {
				window.alert("국어점수는 필수 입력입니다.");
				form_update.name_str.focus();
				return false;
			}
			// 국어점수 0~100점 입력 제한
			if (score_k > 100 || score_k < 0) {
				window.alert("국어 점수는 0~100점까지 입력이 가능합니다.");
				form_update.score_kor.focus();
				return false;
			}
			// 영어점수 필수 입력
			var score_e = form_update.score_eng.value;
			if (score_e.length < 1) {
				window.alert("영어 점수는 필수 입력입니다.");
				form_update.name_str.focus();
				return false;
			}
			// 영어점수 0~100점 입력 제한				
			if (score_e > 100 || score_e < 0) {
				window.alert("영어 점수는 0~100점까지 입력이 가능합니다.");
				form_update.score_eng.focus();
				return false;
			}
			// 수학점수 필수 입력
			var score_m = form_update.score_mat.value;
			if (score_m.length < 1) {
				window.alert("수학 점수는 필수 입력입니다.");
				form_update.name_str.focus();
				return false;
			}
			// 수학점수 0~100점 입력 제한					
			if (score_m > 100 || score_m < 0) {
				window.alert("수학 점수는 0~100점까지 입력이 가능합니다.");
				form_update.score_mat.focus();
				return false;
			}
			// 이동할 페이지 지정 & 이동
			form_update.action = "./updateDB.jsp";
			form_update.submit();
		}
		
		// 삭제 버튼 클릭시 팝업창으로 최종확인 & 이동할 페이지를 삭제 페이지로 지정하는 함수
		function delete_record(form_delete) {
			if(window.confirm("삭제하시겠습니까?") == false) {
				return false;
			}
			form_delete.action = "./deleteDB.jsp";
			form_delete.submit();
		}
	</script>
</body>
</html>
