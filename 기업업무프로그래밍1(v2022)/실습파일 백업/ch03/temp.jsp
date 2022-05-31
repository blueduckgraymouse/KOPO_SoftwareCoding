<%@ page language="java" contentType="text/html; charset=UTF-8" %> 

<html>
<body>
	<%! 
		private Integer a() {
			Integer a = 1;		<!-- 변수 a에 1 저장 -->
			Integer b = 2;		<!-- 변수 a에 2 저장 -->
			return (a+b);
		}
	%>

	<%=a()%>
</body>
</html>