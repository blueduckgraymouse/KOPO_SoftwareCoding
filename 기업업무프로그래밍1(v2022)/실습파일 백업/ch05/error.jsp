<%@ page contentType="text/html; charset=UTF-8" %>
	<!-- 디렉티브 태그를 이용하여 생성할 문서의 컨텐츠 유형을 text/html로, 문자열세트를 utf-8로 지정-->
<%@ page isErrorPage = "true" %>
	<!-- 현재페이지를 에러처리 페이지임을 명시하여 exception 객체를 사용할 수 있다. -->
<html>
	<head>
	</head>
	
	<body>
		<script>
			if ("<%= exception.getMessage() %>" === "Table 'kopoctc.examtable' doesn't exist") {	<!-- 에러 메세지 내용이 테이블이 없다는 내용이면 -->
				document.write("<h1>테이블이 없어요~ 테이블을 생성 후 입력해주세요~<h1>");								<!-- 안내 메세지 출력 -->
			} else if ("<%= exception.getMessage() %>" === "Table 'examtable' already exists") {	<!-- 에러 메세지 내용이 테이블이 없다는 내용이면 -->
				document.write("<h1>이미 테이블이 존재해요~<h1>");												<!-- 안내 메세지 출력 -->
			} else if ("<%= exception.getMessage() %>" === "Unknown table 'kopoctc.examtable'") {	<!-- 에러 메세지 내용이 테이블이 없다는 내용이면 -->
				document.write("<h1>삭제할 테이블이 없어요~<h1>");												<!-- 안내 메세지 출력 -->
			} else {																				<!-- 해당하는 메세지 내용이 없다면 -->
				document.write("<h1>에러 발생!! 관리자에게 문의하세요~</h1>");										<!-- 안내 메세지 출력 -->	
			}
		</script>
	</body>
<html>