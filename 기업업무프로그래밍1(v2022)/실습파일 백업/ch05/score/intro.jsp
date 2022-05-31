<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
	<!-- http-equiv 속성은 content 속성에 명시된 값에 대한 HTTP 헤더를 제공 -->
				<!-- 디렉티브 태그를 이용하여 에러 발생시 호출할 페이지 지정-->
<%@ page contentType="text/html; charset=UTF-8" %>	<!-- 디렉티브 태그를 이용하여 생성할 문서의 컨텐츠 유형을 text/html로, 문자열세트를 utf-8로 지정-->
<%@ page import="java.sql.*, java.io.*" %>						<!-- 디렉티브 태그를 이용하여 자바 패키지 내 sql 하위 모든 클래스를 import -->
<%@ page errorPage="./error.jsp" %>					<!-- 디렉티브 태그를 이용하여 에러 발생시 호출할 페이지 지정-->
<html>
<head>
	<style>
	</style>
</head>

<body>
	<h1> JSP Database 실습1</h1>		<!-- 처음 index.html이 열렸을 때  -->
	
	<%
		File f = new File("/var/lib/tomcat9/webapps/ROOT/ch05/cnt.txt");		// 카운트가 저장될 txt파일 위치를 파일 객체로 저장
		BufferedReader br = new BufferedReader(new FileReader(f));				// 읽기 위하여 BufferedReader객체로 생성
		
		int cnt_read = Integer.parseInt(br.readLine());							// 저장된 방문수 변수에 저장
		cnt_read++;																// 방문 수 증가
		out.println("<br><br>* 현재 홈페이지 방문조회수는 [" + cnt_read + "] 입니다.");		// 현재 방문을 포함한 누적 방문 수 출력
		br.close();																// BufferedReader 자원 반환
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));				// 증가된 방문 수를 저장하기 위해 BufferedReader객체로 생성
		bw.write(Integer.toString(cnt_read));									// 증가된 방문자 수 파일에 쓴다.
		bw.flush();																// 파일 저장
		bw.close();																// BufferedReader객체 반환
	%>
</body>
</html>
