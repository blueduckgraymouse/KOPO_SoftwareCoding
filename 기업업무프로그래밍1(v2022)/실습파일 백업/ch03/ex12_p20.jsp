<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.sql.*, javax.sql.*, java.io.*" %>
<html>
<head>
	<%
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/kopoctc", "root", "abcd1234");
			stmt = conn.createStatement();
			rset = stmt.executeQuery("select * from examtable;"); 
		} catch (Exception e) {
			out.println(e);
		}
	%>
</head>

<body>
	<table cellspacing=1 width=600 border=1>
	<%
		try {
			while (rset.next()) {
				out.println("<tr>");
				out.println("<td width=50>" + rset.getString(1) + "</td>");
				out.println("<td width=50><p align='center'>" + rset.getInt(2) + "</p></td>");
				out.println("<td width=50><p align='center'>" + rset.getInt(3) + "</p></td>");
				out.println("<td width=50><p align='center'>" + rset.getInt(4) + "</p></td>");
				out.println("<td width=50><p align='center'>" + rset.getInt(5) + "</p></td>");
				out.println("</tr>");
			}
		} catch (Exception e) {
			out.print(e);
		}
		rset.close();
		stmt.close();
		conn.close();
	%>
	</table>
</body>
</html>