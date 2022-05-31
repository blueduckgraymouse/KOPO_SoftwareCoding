<%@ page contentType="text/html; charset=utf-8"%>	<!-- 디렉티브 태그를 이용하여 생성할 문서의 컨텐츠 유형을 text/html로, 문자열세트를 utf-8로 지정-->
<%@ page import="java.sql.*, javax.sql.*, java.io.*, java.net.*, org.w3c.dom.*, javax.xml.parsers.*"%>	<!-- 디렉티브 태그를 이용하여 필요한 자바 패키지를 import -->
<html>
<head>
</head>

<body>
<%
	try {
		DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();			// DocumentBuilderFactory를 이용하여 DocumentBuilder객체 생성
		Document doc = docBuilder.parse("http://192.168.23.94:8002/ch06/xmlmake.jsp");					// 생성한 DocumentBuilder객체에 xml파일의 위치 혹은 url를 지정하여 파싱한 결과를 Document객체에 저장
		//Document doc = docBuilder.parse(new File("/var/lib/tomcat9/webapps/ROOT/ch06/testData.xml"));
		
		Element root = doc.getDocumentElement();						// 파싱한 document 전체를 Element객체에 저장
		NodeList tag_name = doc.getElementsByTagName("name");			// 태그이름이 name인 모든 태그를 nodelist형태로 묶어 변수에 저장
		NodeList tage_studentid = doc.getElementsByTagName("studentid");// 태그이름이 studentid인 모든 태그를 nodelist형태로 묶어 변수에 저장
		NodeList tag_kor = doc.getElementsByTagName("kor");				// 태그이름이 kor인 모든 태그를 nodelist형태로 묶어 변수에 저장
		NodeList tag_eng = doc.getElementsByTagName("eng");				// 태그이름이 eng인 모든 태그를 nodelist형태로 묶어 변수에 저장
		NodeList tag_mat = doc.getElementsByTagName("mat");				// 태그이름이 mat인 모든 태그를 nodelist형태로 묶어 변수에 저장
		
		out.println("<table cellspacing=1 width=500 border=1>");		// 테이블의 필드명 출력
		out.println("<tr>");
		out.println("<td width=100>이름</td>");
		out.println("<td width=100>학번</td>");
		out.println("<td width=100>국어</td>");
		out.println("<td width=100>영어</td>");
		out.println("<td width=100>수학</td>");
		out.println("</tr>");
		
		for (int i = 0 ; i < tag_name.getLength() ; i++) {				// 모든 레코드에 대하여 반복
			out.println("<tr>");
			out.println("<td width=100>" + tag_name.item(i).getFirstChild().getNodeValue() + "</td>");			// 모든 name태그를 저장한 nodelist 객체에 i번째 node에 접근하여 요소를 출력
			out.println("<td width=100>" + tage_studentid.item(i).getFirstChild().getNodeValue() + "</td>");	// 모든 studentid태그를 저장한 nodelist 객체에 i번째 node에 접근하여 요소를 출력
			out.println("<td width=100>" + tag_kor.item(i).getFirstChild().getNodeValue() + "</td>");			// 모든 kor태그를 저장한 nodelist 객체에 i번째 node에 접근하여 요소를 출력
			out.println("<td width=100>" + tag_eng.item(i).getFirstChild().getNodeValue() + "</td>");			// 모든 eng태그를 저장한 nodelist 객체에 i번째 node에 접근하여 요소를 출력
			out.println("<td width=100>" + tag_mat.item(i).getFirstChild().getNodeValue() + "</td>");			// 모든 mat태그를 저장한 nodelist 객체에 i번째 node에 접근하여 요소를 출력
			out.println("</tr>");
		}
		out.println("</table>");
	} catch (Exception e) {
		out.println(e);
	}
%>
</body>
</html>
