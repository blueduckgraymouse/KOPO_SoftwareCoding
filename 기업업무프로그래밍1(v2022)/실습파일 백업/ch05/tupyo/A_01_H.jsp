<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
	<!-- http-equiv 속성은 content 속성에 명시된 값에 대한 HTTP 헤더를 제공 -->
<%@ page contentType="text/html; charset=UTF-8" %>	<!-- 디렉티브 태그를 이용하여 생성할 문서의 컨텐츠 유형을 text/html로, 문자열세트를 utf-8로 지정-->
<%@ page import="java.sql.*" %>						<!-- 디렉티브 태그를 이용하여 자바 패키지 내 sql 하위 모든 클래스를 import -->
<html>
<head>
	<style>
		.banner {
			border : 1px blue solid;
			font-size : 20px;
		}
		.btn {
			border-radius : 5px;
			width : 80px;
			height : 25px;
			background-color : green;
		}
		input[type=text], input[type=number] {
			 border: 2px solid green;
		}
	</style>
</head>

<body>
	<div align="center">
		<table class="banner" border=1 cellspacing=1>
			<tr>
				<th bgcolor=yellow>후보등록</th>
				<td>투표</td>
				<td>개표결과</td>
			</tr>
		</table>
		
		<br>
		
		<table cellspacing=1 padding=5px>
		<form method=get action="./A_02.jsp">
			<tr>
				<td width=150px>기호번호 : n</td>
				<td width=300px>후보명 : 아무개</td>
				<td width=100px><input type="button" value="삭제" class="btn"></td>
			</tr>
		</form>
		<form method=get action="./A_03.jsp">
			<tr>
				<td width=200px>기호번호 : <input type="number" name="id_hubo" id="id_hubo" style="width:50px;"></td>
				<td width=300px>후보명 : <input type="text" name="name_hubo" id="name_hubo"></td>
				<td width=100px><input type="button" value="추가" class="btn"></td>
			</tr>
		</form>		
		</table>

	</div>
</body>
</html>
