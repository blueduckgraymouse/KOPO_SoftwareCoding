<%@ page contentType="text/html; charset=UTF-8" %>
	<!-- 디렉티브 태그를 이용하여 생성할 문서의 컨텐츠 유형을 text/html로, 문자열세트를 utf-8로 지정-->
<html>
<head>
</head>

<body>
	<%
		String arr[] = new String[] {"111", "222", "333"};	// string 배열 변수에 "111", "222", "333" 저장
		String str = "abc,efg,hij";			// 문자열 변수에 "abc,efg,hij" 저장
		String str_arr[] = str.split(",");	// str 변수의 값을 ,기준으로 나눠 배열 변수 str_arr에 저장
	%>
	arr[0]:<%=arr[0]%> <br>				<!-- arr배열 변수의 첫번째 요소 출력 -->
	arr[1]:<%=arr[1]%> <br>				<!-- arr배열 변수의 두번째 요소 출력 -->
	arr[2]:<%=arr[2]%> <br>				<!-- arr배열 변수의 세번째 요소 출력 -->
	str_arr[0]:<%=str_arr[0]%><br>		<!-- str_arr 변수의 첫번째 요소 출력 -->
	str_arr[1]:<%=str_arr[1]%><br>		<!-- str_arr 변수의 두번째 요소 출력 -->
	str_arr[2]:<%=str_arr[2]%><br>		<!-- str_arr 변수의 세번째 요소 출력 -->
	Good...
</body>
</html>