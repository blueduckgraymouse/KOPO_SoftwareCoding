<%@ page contentType="text/html; charset=UTF-8" %> 
	<!-- 디렉티브 태그를 이용하여 생성할 문서의 컨텐츠 유형을 text/html로, 문자열세트를 utf-8로 지정-->
<html>
<head>
	<script>
		function call1() {		<!-- 함수 call1을 정의 -->
			var a = "abc";		<!-- 변수 a에 abc 저장 -->
			var b = "efg";		<!-- 변수 a에 efg 저장 -->
			document.write("String연산 : " + (a+b));	<!-- a+b연산결과 출력 -->
		}
		function call2() {		<!-- 함수 call1을 정의 -->
			var a = 1;			<!-- 변수 a에 1 저장 -->
			var b = 2;			<!-- 변수 a에 2 저장 -->
			document.write("integer연산 : " + (a+b));	<!-- a+b연산결과 출력 -->
		}
	</script>
</head>

<body>
	<script>	
		call1();	<!-- call1함수 호출-->
	</script>
	<script>
		call2();	<!-- call2함수 호출-->
	</script>	
</body>
</html>