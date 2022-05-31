<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
	<!-- http-equiv 속성은 content 속성에 명시된 값에 대한 HTTP 헤더를 제공 -->
<%@ page errorPage="./error.jsp" %>					<!-- 디렉티브 태그를 이용하여 에러 발생시 호출할 페이지 지정-->
<%@ page contentType="text/html; charset=UTF-8" %>	<!-- 디렉티브 태그를 이용하여 생성할 문서의 컨텐츠 유형을 text/html로, 문자열세트를 utf-8로 지정-->
<%@ page import="java.sql.*" %>						<!-- 디렉티브 태그를 이용하여 자바 패키지 내 sql 하위 모든 클래스를 import -->
<html>
<head>
	<style>
		a {							/* a태그의 css속성 지정 */
			color : grey;			/* 글자 색 회색으로 지정 */
			text-decoration : none;	/* a태그 글자의 밑줄 삭제 */
		}
		a:hover {					/* a태그에 마우스가 올라왔을 때의 css속성 지정*/
			color : blue;			/* 글자 색 파란색으로 지정 */
		}
		#paging td {				
			font-size : 20px;		/* 페이징처리 부분의 글자 크기를 20px로 설정 */
			padding : 5px;
		}
	</style>
	<%
		String no_page = "1";			// 현재 페이지에 출력할 페이지 번호가 저장되는 변수 1으로 초기화, getparamter메서드로 값을 받아올 것이므로 string타입
		int size_page = 10;			// 한 페이지에 뿌려진 레코드 개수가 저장되는 변수 10으로 초기화
		int count_total = 0;			// 총 레코드수가 저장될 변수 0으로 초기화
		int size_group_page = 10;			// 몇 개의 페이지가 하나의 그룹이 되어 << < > >> 사이에 나타낼건지 의미하는 변수 10으로 초기화
		
		if (request.getParameter("page") != null) {	// 현재 페이지에 들어온 요청에 page이란 파라미터의 값이 존재하면
			no_page = request.getParameter("page");	// page이란 파라미터의 값을 변수 no_page에 저장
		}
	
		Class.forName("com.mysql.jdbc.Driver");		// jdbc 로드
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/kopoctc", "root", "abcd1234");	// 계정정보로 DB에 접속한 connecion 객체 저장
		
		Statement stmt = conn.createStatement();	// DB에 쿼리문를 날려주는 statement 객체 생성
		
		String query1 = "select count(*) from examtable;";
		ResultSet rset1 = stmt.executeQuery(query1);		// 총 레코드 수를 조회하는 쿼리문 실행하여 결과를 ResetSet 객체에 저장
		rset1.next();
		count_total = rset1.getInt(1);						// 페이징 처리를 위한 총 레코드 개수
		
		String query2 = "select *, " + 						// examtable 테이블의 현재 페이지 번호에 해당하는 레코드 조회 쿼리문을 query변수에 저장
						"(t.kor+t.eng+t.mat) as sum, " +
						"(t.kor+t.eng+t.mat)/3 as ave, " + 
						"(select count(*)+1 from examtable as r where r.kor+r.eng+r.mat > sum) as rank_sum " + 
						"from examtable as t limit "+ ((Integer.parseInt(no_page)-1)*size_page) + ", " + size_page + ";";
		ResultSet rset2 = stmt.executeQuery(query2); 	// 쿼리문 실행한 결과를 ResultSet객체에 저장
	%>
</head>

<body>
	<div align="center">
		<table cellspacing=1 border=1>		<!-- 테이블의 셀간 여백 1, 선 두께 1로 지정 -->
		<tr height=40px  bgcolor=gray>		<!-- 현재 행의 높이를 40px, 배경색을 회색으로 지정 -->
			<th width=100px>				<!-- 현재 열의 너비를 100px로 지정 -->
				이름
			</th>
			<th width=100px>				<!-- 현재 열의 너비를 100px로 지정 -->
				학번
			</th>
			<th width=100px>				<!-- 현재 열의 너비를 100px로 지정 -->
				국어 점수
			</th>
			<th width=100px>				<!-- 현재 열의 너비를 100px로 지정 -->
				영어 점수
			</th>
			<th width=100px>				<!-- 현재 열의 너비를 100px로 지정 -->
				수학 점수
			</th>	
			<th width=100px>				<!-- 현재 열의 너비를 100px로 지정 -->
				총점
			</th>
			<th width=100px>				<!-- 현재 열의 너비를 100px로 지정 -->
				평균
			</th>	
			<th width=100px>				<!-- 현재 열의 너비를 100px로 지정 -->
				등수
			</th>		
		</tr>
		<%
			while (rset2.next()) {						// ResultSet에 저장된 모든 레코드에 대하여 반복
				out.println("<tr height=50px>");												// 이름을 a태그로 감싸고 링크에 이름을 파라미터로 갖는 oneviewDB.jsp로 지정
				out.println("<td width=50><p align='center'><a href='oneviewDB.jsp?name=" + rset2.getString(1) + "'> " + rset2.getString(1) + "</a></p></td>");
				out.println("<td width=50><p align='center'>" + rset2.getInt(2) + "</p></td>");	// 학번 출력
				out.println("<td width=50><p align='center'>" + rset2.getObject(3) + "</p></td>");	// 국어 점수 출력
				out.println("<td width=50><p align='center'>" + rset2.getObject(4) + "</p></td>");	// 영어 점수 출력
				out.println("<td width=50><p align='center'>" + rset2.getObject(5) + "</p></td>"); 	// 수학 점수 출력
				out.println("<td width=50><p align='center'>" + rset2.getObject(6) + "</p></td>"); 	// 총점 출력
				out.println("<td width=50><p align='center'>" + rset2.getObject(7) + "</p></td>"); 	// 평균 출력
				out.println("<td width=50><p align='center'>" + rset2.getObject(8) + "</p></td>"); 	// 등수 출력
				out.println("</tr>");
			}
		%>
		</table>
		
		<%	
			rset1.close();		// ResultSet 자원 반납
			rset2.close();		// ResultSet 자원 반납
			stmt.close();		// Statment 자원 반납
			conn.close();		// Connection 자원 반납
		%>
		
		<br>
		
		<%
			// 페이징 처리
			int no_page_now = Integer.parseInt(no_page);	// request 파라미터로 들어온 현재 페이지 번호를 int형으로 변환하여 변수에 저장
			
			int no_page_start  = (no_page_now / size_group_page) * size_group_page + 1;	// 페이징 그룹의 첫 페이지 번호
			if ((no_page_now % size_group_page)==0) {		// 페이징 사이즈로 나누어 떨어지는 마지막 번호를 누르면 페이지가 넘어가는 예외를 처리
				no_page_start-=size_group_page;
			}
			
			int no_page_last = 0;						// 와이파이 게시판의 마지막 페이지 번호
			if ((count_total % size_page)== 0) {		// 총 레코드수가 리스트 사이즈로 나눠 떨어지면 그 몫이 총 페이지 개수 이지만
				no_page_last = count_total / size_page; 				
			} else {									// 안 나눠 떨어지면 +1해줘야 총 페이지 개수이다.
				no_page_last = count_total / size_page+1; 				
			}
			
			out.println("<table id=paging>");
			
			if ( no_page_start != 1) {					// 첫 페이징 그룹이 아닐 때만 <와 << 표시
				out.println("<tr><td><a href='http://192.168.23.94:8002/ch05/allViewDB.jsp'>&lt;&lt;</a></td>");			// << 출력하고 첫 페이지로 이동 링크
				out.println("<td><a href='http://192.168.23.94:8002/ch05/allViewDB.jsp?page=" + (no_page_start-1) + "'>&lt;</a></td>"); // < 출력하고 이전 페이징 그룹의 마지막 페이지 링크
			}
			
			for (int i = no_page_start ; i < no_page_start + size_group_page ; i++) {	// 현재 페이지 번호가 속한 페이징의 그룹의 (첫 페이지 번호) 부터 (첫 페이지 번호+페이징 그룹의 크기) 까지 반복
				if (i > no_page_last) {					// i가 마지막 페이지 번호이면 없는 이후 페이지 번호는 출력할 필요 없으므로
					break;								//   반복문 종료
				}
				
				if (i == no_page_now) {					// 현재에 해당하는 페이지 번호이면 굵은 글씨로 표시
					out.println("<td><b><a href='http://192.168.23.94:8002/ch05/allViewDB.jsp?page=" + i + "'>" + i + "</a></b></td>");	// 현재 페이징 그룹에 해당하는 페이지 번호 출력
				} else {								// 아니면 일반 굵기로 표시
					out.println("<td><a href='http://192.168.23.94:8002/ch05/allViewDB.jsp?page=" + i + "'>" + i + "</a></td>");	// 현재 페이징 그룹에 해당하는 페이지 번호 출력
				}
			}
			
			if (!(no_page_start <= no_page_last && no_page_last <= no_page_start + size_group_page) && (no_page_last!=0)) {	// 마지막 페이징 그룹이 아닐 때와 출력할 데이터가 있을 때만 >와 >> 표시
				out.println("<td><a href='http://192.168.23.94:8002/ch05/allViewDB.jsp?page=" + (no_page_start + size_group_page) + "'>&gt;</a></td>"); // > 출력하고 다음 페이징 그룹의 첫 페이지 링크
				out.println("<td><a href='http://192.168.23.94:8002/ch05/allViewDB.jsp?page=" + no_page_last + "'>&gt;&gt;</a></td>");				// >> 출력하고 마지막 페이지로 이동 링크
			}
			
			out.println("<tr></table>");
		%>
	</div>
</body>
</html>
