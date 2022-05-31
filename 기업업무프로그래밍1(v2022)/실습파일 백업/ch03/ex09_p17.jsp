<%@ page errorPage="error.jsp" %>
	<!-- 디렉티브 태그를 이용하여 에러 발생시 호출할 페이지 지정-->
<html>
<head>
</head>

<body>
	<%
		String arr[] = new String[] {"111", "222", "333"};	// string 배열 변수에 "111", "222", "333" 저장

		out.println(arr[4] + "<br>");		// arr배열 변수의 4번째 요소 출력 시도 -> 에러 발생
	%>
	Good...
</body>
</html>