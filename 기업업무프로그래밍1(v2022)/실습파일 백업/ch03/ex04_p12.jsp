<%@ page contentType="text/html; charset=UTF-8" %> 
	<!-- 디렉티브 태그를 이용하여 생성할 문서의 컨텐츠 유형을 text/html로, 문자열세트를 utf-8로 지정-->
<html>
<head>
	<%!						
		private String call1() {	
			String a = "abc";	// 변수 a에 abc 저장
			String b = "efg";	// 변수 a에 efg 저장
			return (a+b);		// a+b연산결과 반환 
		}
		private Integer call2() {
			Integer a = 1;		// 변수 a에 1 저장
			Integer b = 2;		// 변수 a에 2 저장
			return (a+b);		// a+b연산결과 반환
		}
	%>
</head>

<body>
	String연산: <%=call1()%> <br>		<!-- 표현식 태그를 이용하여 call1함수 호출-->
	integer연산: <%=call2()%> <br>	<!-- 표현식 태그를 이용하여 call2함수 호출-->
	Good...
</body>
</html>