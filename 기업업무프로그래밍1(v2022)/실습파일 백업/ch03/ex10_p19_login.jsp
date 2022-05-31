<%@ page contentType="text/html; charset=UTF-8" %>
	<!-- 디렉티브 태그를 이용하여 생성할 문서의 컨텐츠 유형을 text/html로, 문자열세트를 utf-8로 지정-->
<html>
<head>
</head>

<body>
	<form method="post" action="ex11_p19_member.jsp">		<!-- form태그로 하위 입력 태그의 입력 값이 post방식으로 ex11_p19_member.jsp로 전송 지정 -->
		이름: <input type="text" name="username"> <br>		<!-- 텍스트 타입의 입력을 받는 input 태그, 이름을 username로 지정 -->
		비밀번호: <input type="password" name="userpasswd"> <br>	<!-- 비밀번호 타입의 입력을 받는 input 태그, 이름을 userpasswd 지정 -->
		<input type="submit" value="전송">					<!-- submit타입의 버튼을 만드는 input 태그, 버튼 안에 "전송"글자 표시 -->
	</form>
</body>
</html>