<%@ page import="java.sql.*, javx.mail.*, javax.mail.internet.*" contentType="text/html; charset=UTF-8" %>
	<!-- 디렉티브 태그를 이용하여 자바 패키지 내 sql, mail, mail.internet 하위의 클래스를 import -->
	<!-- 디렉티브 태그를 이용하여 생성할 문서의 컨텐츠 유형을 text/html로, 문자열세트를 utf-8로 지정-->
<html>
<body>
	<% String myUrl = "http://www.kopo.ac.kr"; %>
		<!-- 스크립틀릿 태그를 이용하여 myUrl이라는 변수에 폴리테 대학 url을 저장 -->
	<a href="<%=myUrl%>">Hello</a>World.
		<!-- 표현문 태그를 이용하여 a태그의 href속성값으로 myUrl에 저장된 값 지정 -->
</body>
</html>