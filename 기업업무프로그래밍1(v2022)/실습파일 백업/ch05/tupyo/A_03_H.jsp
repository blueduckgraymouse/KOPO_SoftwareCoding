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
		
		<b>후보 등록 결과 : 후보가 추가되었습니다.</b>

	</div>
</body>
</html>
