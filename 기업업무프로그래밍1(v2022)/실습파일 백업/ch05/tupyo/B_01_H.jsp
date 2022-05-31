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
				<th bgcolor=yellow><a href="http://192.168.23.94:8002/ch05/tupyo/A_01.jsp">후보등록</a></th>
				<td><a href="#">투표</a></td>
				<td><a href="#">개표결과</a></td>
			</tr>
		</table>
		
		<br>
		<form name="form_tupyo" id="form_tupyo" method=get action="./B_02.jsp">
		<table>
			<tr>
				<td>
					<select id="select_hubo" name="select_hubo">
						<option value="1">1번 김일돌</option>
						<option value="5">5번 김오돌</option>
					</select>
				</td>
				<td>
					<select id="select_ageRange" name="select_ageRange">
						<option value="1">10대</option>
						<option value="9">90대</option>
					</select>
				</td>				
				<td>
					<input type="submit" class="btn" value="투표하기" />
				</td>						
			</tr>
		</table>
		</form>

	</div>
</body>
</html>
