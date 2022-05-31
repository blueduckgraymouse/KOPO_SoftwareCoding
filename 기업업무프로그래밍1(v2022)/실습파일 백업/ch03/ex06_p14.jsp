<%@ page contentType="text/html; charset=UTF-8" %>
	<!-- 디렉티브 태그를 이용하여 생성할 문서의 컨텐츠 유형을 text/html로, 문자열세트를 utf-8로 지정-->
<html>
<head>
	<%!
		String str = "abcdeffasdsd";		// str 문자열 변수 안에 암의의 문자열 저장
		int str_len = str.length();			// str의 길이 변수에 저장
		String str_sub = str.substring(5);	// str의 5번째 문자부터 끝까지 잘라서 변수에 저장
		int str_loc =str.indexOf("cd");		// str에서 cd글차의 인덱스 번호 변수에 저장
		String strL = str.toLowerCase();	// str를 대문자로 일괄 변환하여 변수에 저장
		String strU = str.toUpperCase();	// str를 소문자로 일괄 변환하여 변수에 저장
	%>
</head>

<body>
	str : <%= str %> <br>					<!-- str 변수의 값 출력 -->
	str_len : <%= str_len %> <br>			<!-- str_len 변수의 값 출력 -->
	str_sub : <%= str_sub %> <br>			<!-- str_sub 변수의 값 출력 -->
	str_loc : <%= str_loc %> <br>			<!-- str_loc 변수의 값 출력 -->
	strL : <%= strL %> <br>					<!-- strL 변수의 값 출력 -->
	strU : <%= strU %> <br>					<!-- strU 변수의 값 출력 -->
	Good..
</body>
</html>