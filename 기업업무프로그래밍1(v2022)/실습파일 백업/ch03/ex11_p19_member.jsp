<%@ page contentType="text/html; charset=UTF-8" %>
	<!-- 디렉티브 태그를 이용하여 생성할 문서의 컨텐츠 유형을 text/html로, 문자열세트를 utf-8로 지정-->
<%
	String name = request.getParameter("username");			// 현재 페이지로 들어온 request에 이름이 username인 파라미터의 값을 name변수에 저장
	String password = request.getParameter("userpasswd");	// 현재 페이지로 들어온 request에 이름이 userpasswd 파라미터의 값을 name변수에 저장
%>
<html>
<head>
</head>

<body>
	이름 : <%=name%><br>			<!-- 변수 name의 값 출력 -->
	비밀번호 : <%=password%><br>	<!-- 변수 password 값 출력 -->
</body>
</html>