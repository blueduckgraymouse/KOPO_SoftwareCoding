<%@ page contentType="text/html; charset=UTF-8" %>
	<!-- 디렉티브 태그를 이용하여 생성할 문서의 컨텐츠 유형을 text/html로, 문자열세트를 utf-8로 지정-->
<html>
<head>
</head>

<body>
	<%
		String arr[] = new String[] {"111", "222", "333"};	// string 배열 변수에 "111", "222", "333" 저장
		try {
			out.println(arr[4] + "<br>");					// arr배열 변수의 4번째 요소 출력 시도
		} catch (Exception e) {
			out.print("error=>" + e + "<=========<br>");	// 에러 발생시 에러 출력
		}
	%>
	Good...
</body>
</html>