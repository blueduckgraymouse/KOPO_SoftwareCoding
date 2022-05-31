<%@ page contentType="text/html; charset=UTF-8" %>
		<!-- 디렉티브 태그를 이용하여 생성할 문서의 컨텐츠 유형을 text/html로, 문자열세트를 utf-8로 지정-->
<html>
<head>
	<%!		
		private class AA{ 						// 클래스 AA 생성
			private int sum(int i, int j) {		// 파라미터 i,j를 받아 합을 리턴하는 메서드
				return i+j;
			}
		}
		AA aa = new AA();						// AA의 객체 aa 생성
	%>
</head>

<body>
	<% out.println("2+3=" + aa.sum(2,3)); %> <br>	<!-- aa객체의 sum메서드 호출 -->
	Good..
</body>
</html>