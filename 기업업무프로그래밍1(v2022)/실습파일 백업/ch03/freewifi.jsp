<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
<%@ page contentType="text/html; charset=UTF-8" %>	<!-- 디렉티브 태그를 이용하여 생성할 문서의 컨텐츠 유형을 text/html로, 문자열세트를 utf-8로 지정-->
<%@ page import="java.sql.*" %>						<!-- 디렉티브 태그를 이용하여 자바 패키지 내 sql 하위 모든 클래스를 import -->
<html>

<head>
	<style>
		#paging td {				
			font-size : 20px;		/* 페이징처리 부분의 글자 크기를 20px로 설정 */
		}
		a {							/* a태그의 css속성 지정 */
			color : grey;			/* 글자 색 회색으로 지정 */
			text-decoration : none;	/* a태그 글자의 밑줄 삭제 */
			font-size : 20px;		/* 글자 색 회색으로 지정 */
		}
	</style>
	<%
		Connection conn = null;		// Connection 변수 선언
		Statement stmt = null;		// Statement 변수 선언
		ResultSet rset1 = null;		// 총 레코드 수 조회 쿼리의 결과가 저장될 ResultSet 변수 선언
		ResultSet rset2 = null;		// 현재 페이지에 출력한 모든 레코드 조회 쿼리의 결과가 저장될 ResultSet 변수 선언
		
		String from = "1";			// 현재 페이지에 출력할 페이지 번호가 저장되는 변수 0으로 초기화, getparamter메서드로 값을 받아올 것이므로 string타입
		String cnt = "10";			// 한 페이지에 뿌려진 레코드 개수가 저장되는 변수 10으로 초기화, getparamter메서드로 값을 받아올 것이므로 string타입
		int count_total = 0;			// 총 레코드수가 저장될 변수 0으로 초기화
		int paging_size = 10;		// 몇 개의 페이지가 하나의 그룹이 되어 << < > >> 사이에 나타낼건지 의미하는 변수 10으로 초기화
		
		if (request.getParameter("from") != null) {	// 현재 페이지에 들어온 요청에 from이란 파라미터의 값이 존재하면
			from = request.getParameter("from");	// from이란 파라미터의 값을 변수 from에 저장
		}
		if (request.getParameter("cnt") != null) {	// 현재 페이지에 들어온 요청에 cnt이란 파라미터의 값이 존재하면
			cnt = request.getParameter("cnt");		// cnt이란 파라미터의 값을 변수 cnt에 저장
		}

		try {
			Class.forName("com.mysql.jdbc.Driver");	// jdbc 로드
			conn = DriverManager.getConnection("jdbc:mysql://localhost/kopoctc", "root", "abcd1234"); // 계정정보로 DB에 접속한 connecion 객체 저장
		} catch (Exception e) {
			out.println(e);
		}
	%>
</head>

<body>
	<div align="center">
		<h1>무료 와이파이</h1>					<!-- 제목 "와이파이" 출력 -->
		<table cellspacing=1 border=1>		<!-- 테이블의 셀간 여백 1, 선 두께 1로 지정 -->
			<tr height=40px>				<!-- 현재 행의 높이를 40px로 지정 -->
				<th width=20px>				<!-- 현재 열의 너비를 20px로 지정 -->
					번호
				</th>
				<th width=300px>			<!-- 현재 열의 너비를 300px로 지정 -->
					주소
				</th>
				<th width=50px>				<!-- 현재 열의 너비를 50px로 지정 -->
					위도
				</th>
				<th width=50px>				<!-- 현재 열의 너비를 50px로 지정 -->
					경도
				</th>
				<th width=250px>			<!-- 현재 열의 너비를 250px로 지정 -->
					현재 위치로부터의 거리(km)
				</th>
			</tr>	
			<%
				try {
					stmt = conn.createStatement();			// DB에 쿼리문를 날려주는 statement 객체 생성
					
					rset1 = stmt.executeQuery("select count(*) from freewifi;");		// 총 레코드 수를 조회하는 쿼리문 실행하여 결과를 ResetSet 객체에 저장
					rset1.next();
					count_total = rset1.getInt(1);
					
					stmt.execute("call print_freewifi(" + from + "," + cnt + ");"); 	// 페이지 번호와 페이지 사이즈를 파라미터로 기존에 만들어 놓은 print_freewifi 프로시저 호출 쿼리를 실행
					rset2 = stmt.executeQuery("select * from list_freewifi;"); 			// 프로시저에 의해 list_freewifi 테이블 조회 결과를 ResultSet 객체에 저장
					
					while (rset2.next()) {					// ResultSet에 저장된 모든 레코드에 대하여 반복
						out.println("<tr height=50px>");
						out.println("<td width=50><p align='center'>" + rset2.getInt(1) + "</p></td>");		// 레코드 번호 출력
						out.println("<td width=50><p align='center'>" + rset2.getString(2) + "</p></td>");	// 주소 출력 
						out.println("<td width=50><p align='center'>" + rset2.getDouble(3) + "</p></td>");	// 위도 출력 
						out.println("<td width=50><p align='center'>" + rset2.getDouble(4) + "</p></td>");	// 경도 출력 
						out.println("<td width=50><p align='center'>" + rset2.getDouble(5) + "</p></td>");	// 융기원으로부터 거리 출력
						out.println("</tr>");
					}
				} catch (Exception e) {
					out.print(e);
				} finally {
					try {
						rset1.close();	// ResultSet 자원 반납
						rset2.close();	// ResultSet 자원 반납
						stmt.close();	// Statment 자원 반납
						conn.close();	// Connection 자원 반납
					} catch(Exception e) {
						out.print(e);
					}
				}
			%>
		</table>
	
		<br>
		
		<%
			int no_page_now = Integer.parseInt(from);	// request 파라미터로 들어온 현재 페이지 번호를 int형으로 변환하여 변수에 저장
			int size_list = Integer.parseInt(cnt);		// requset 파라미터로 들어온 페이지에 출력한 레코드 개수를 int형으로 변환하여 변수에 저장
			
			int no_page_start  = (no_page_now / paging_size) * paging_size + 1;	// 페이징 그룹의 첫 페이지 번호
			if((no_page_now % paging_size)==0) {		// 페이징 사이즈로 나누어 떨어지는 마지막 번호를 누르면 페이지가 넘어가는 예외를 처리
				no_page_start-=paging_size;
			}
			int no_page_last = 0;						// 와이파이 게시판의 마지막 페이지 번호
			if((count_total % size_list)== 0) {			// 총 레코드수가 리스트 사이즈로 나눠 떨어지면 그 몫이 총 페이지 개수 이지만
				no_page_last = count_total / size_list; 				
			} else {									// 안 나눠 떨어지면 +1해줘야 총 페이지 개수이다.
				no_page_last = count_total / size_list+1; 				
			}
			
			out.println("<table id=paging>");
			
			if ( no_page_start != 1) {					// 첫 페이징 그룹이 아닐 때만 <와 << 표시
				out.println("<tr><td><a href='http://192.168.23.94:8002/ch03/freewifi.jsp'>&lt;&lt;</a></td>");			// << 출력하고 첫 페이지로 이동 링크
				out.println("<td><a href='http://192.168.23.94:8002/ch03/freewifi.jsp?from=" + (no_page_start-1) + "&cnt=" + size_list + "'>&lt;</a></td>"); // < 출력하고 이전 페이징 그룹의 마지막 페이지 링크
			}
			
			for (int i = no_page_start ; i < no_page_start + paging_size ; i++) {	// 현재 페이지 번호가 속한 페이징의 그룹의 (첫 페이지 번호) 부터 (첫 페이지 번호+페이징 그룹의 크기) 까지 반복
				if (i > no_page_last) {					// i가 마지막 페이지 번호이면 없는 이후 페이지 번호는 출력할 필요 없으므로
					break;								//   반복문 종료
				}
				
				if (i == no_page_now) {					// 현재에 해당하는 페이지 번호이면 굵은 글씨로 표시
					out.println("<td><b><a href='http://192.168.23.94:8002/ch03/freewifi.jsp?from=" + i + "&cnt=" + size_list + "'>" + i + "</a></b></td>");	// 현재 페이징 그룹에 해당하는 페이지 번호 출력
				} else {								// 아니면 일반 굵기로 표시
					out.println("<td><a href='http://192.168.23.94:8002/ch03/freewifi.jsp?from=" + i + "&cnt=" + size_list + "'>" + i + "</a></td>");	// 현재 페이징 그룹에 해당하는 페이지 번호 출력
				}
			}
			
			if (!(no_page_start < no_page_last && no_page_last < no_page_start+10)) {	// 마지막 페이징 그룹이 아닐 때만 >와 >> 표시
				out.println("<td><a href='http://192.168.23.94:8002/ch03/freewifi.jsp?from=" + (no_page_start + paging_size) + "&cnt=" + size_list + "'>&gt;</a></td>"); // > 출력하고 다음 페이징 그룹의 첫 페이지 링크
				out.println("<td><a href='http://192.168.23.94:8002/ch03/freewifi.jsp?from=" + no_page_last + "&cnt=" + size_list + "'>&gt;&gt;</a></td>");				// >> 출력하고 마지막 페이지로 이동 링크
			}
			
			out.println("<tr></table>");
		%>
	</div>
</body>

</html>