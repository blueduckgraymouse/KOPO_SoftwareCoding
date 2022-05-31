<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.sql.*, javax.sql.*, java.io.*, java.net.*, org.w3c.dom.*, javax.xml.parsers.*"%>
<html>
<head>
</head>

<body>
<%
	try {
		request.setCharacterEncoding("UTF-8");		// request객체의 인코딩을 utf-8로 명시

		String gridx = request.getParameter("gridx");	// 기상정보를 조회할 x좌표 값 파라미터로 받아 변수에 저장
		String gridy = request.getParameter("gridy");	// 기상정보를 조회할 x좌표 값 파라미터로 받아 변수에 저장

		
		DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = docBuilder.parse("http://www.kma.go.kr/wid/queryDFS.jsp?gridx=" + gridx + "&gridy=" + gridy);
		//Document doc = docBuilder.parse(new File("/var/lib/tomcat9/webapps/ROOT/ch06/testData.xml"));
		
		NodeList tag_data = doc.getElementsByTagName("data");
		
		String tm = doc.getElementsByTagName("tm").item(0).getFirstChild().getNodeValue();	// 예보 발표일시 찾아서 변수에 저장
		String x = doc.getElementsByTagName("x").item(0).getFirstChild().getNodeValue();	// 예보 대상 지역 x좌표 찾아서 변수에 저장
		String y = doc.getElementsByTagName("y").item(0).getFirstChild().getNodeValue();	// 예보 대상 지역 y좌표 찾아서 변수에 저장
		
		String[] data_name_eng = {"seq", "hour" ,"day", "temp", "tmx", "tmn", "sky", "pty", "wfKor", "wfEn", "pop", "r12", "s12", "ws", "wd", "wdKor", "wdEn", "reh", "r06", "s06"};	// 찾아야하는 데이터의 태그명 배열로 저장
		String[] data_name_kor = {"구분", "시간" ,"+n일", "평균 온도", "최고 온도", "최저 온도", "하늘 상태", "강수 상태", "날씨(한국어)", "날씨(영어)",
									"강수 확률", "12시간 예상 강수량", "12시간 예상 적설량", 	// 출력할 한글 태그명 배열로 저장
										"풍속(m/s)", "풍향", "풍향(한국어)", "풍량(영어)", "습도(%%)", "6시간 예상 강수량", "6시간 예상 적설량"};
		
		String[][] data_value = new String[tag_data.getLength()][data_name_eng.length];	// 데이터가 2차원 배열 선언
		
		for (int i = 0 ; i < tag_data.getLength() ; i++) {							// seq의 수만큼 반복
			data_value[i][0] = tag_data.item(i).getAttributes().getNamedItem("seq").getNodeValue();
			for (int j = 1 ; j < data_name_eng.length; j++) {						// data태그 하위 태그의 개수만큼 반복
				data_value[i][j] = doc.getElementsByTagName(data_name_eng[j]).item(i).getFirstChild().getNodeValue();	// 해당 seq번호와 배열에 저장된 해당 태그명을 통해서 데이터 값 찾아 배열에 저장
			}
		}
		out.println("<h1>날씨 조회</h1>");
		out.println("<h3>[" + tm.substring(0, 4) + "년 " + tm.substring(4, 6) + "월 " + tm.substring(6, 8) + "일 " + tm.substring(8, 10) + "시 발표]");	// 예보 발표일시 출력
		out.println("x축 - " + x +  " , y축 - " +  y + "</h3>");																						// 예보 대상좌표 출력
		
		out.println("<table cellspacing=1 border=1>");
		out.println("<tr>");
		// 테이블의 필드명 출력
		for (int i = 0 ; i < data_name_kor.length ; i++) {
			if(i == 2) {										// 시간, , +n일 정보를 합치기 위해 시간 필드명만 출력
				continue;
			}
			out.println("<td width=100>" + data_name_kor[i] + "</td>");
		}
		out.println("</tr>");
		// 테이블의 레코드 출력
		for (int i = 0 ; i < tag_data.getLength() ; i++) {		//  seq 반복 
			out.println("<tr>");
			for (int j = 0 ; j < data_name_eng.length ; j++) {	// seq 내 시간별 반복
				if (j == 1) {									// 시간, , +n일 정보를 합쳐서 "오늘 18시" 꼴로 출력
					String date_d = "";
					if (data_value[i][j+1].equals("0")) {
						date_d = "오늘";
					} else if(data_value[i][j+1].equals("1")) {
						date_d = "내일";
					} else if(data_value[i][j+1].equals("2")) {
						date_d = "모래";
					}
					date_d = date_d + " " + data_value[i][j] + "시";
					j++;
					out.println("<td width=100>" + date_d + "</td>");
					continue;
				}
				if (j == 6) {	// sky, 하늘 상태를 숫자값 대신 이미지로 표시
					out.println("<td width=100><img class='sky" + data_value[i][j] + "' src='#'></td>");
					continue;
				}				
				if (j == 7) {	// pty, 강수 상태를 숫자값 대신 이미지로 표시
					out.println("<td width=100><img class='rain" + data_value[i][j] + "' src='#'></td>");
					continue;
				}
				if (j == 14) {	// pty, 풍향 상태를 숫자값 대신 이미지로 표시
					out.println("<td width=100><img class='wind" + data_value[i][j] + "' src='#'></td>");
					continue;
				}
				out.println("<td>" + data_value[i][j] + "</td>");
			}
			out.println("</tr>");
		}
		
		out.println("</table>");
	} catch (Exception e) {
		out.println(e);
	}
%>
</body>

<script>				<!-- 클래스명에 따라 img태그의 src 속성을 변경하여 해당 이미지를 표시 -->
	var sky1s = document.getElementsByClassName("sky1");
	for(var i = 0 ; i < sky1s.length ; i++) {
		sky1s[i].src = "./img_sky/1.PNG";
	}
	var sky2s = document.getElementsByClassName("sky2");
	for(var i = 0 ; i < sky2s.length ; i++) {
		sky2s[i].src = "./img_sky/2.PNG";
	}
	var sky3s = document.getElementsByClassName("sky3");
	for(var i = 0 ; i < sky3s.length ; i++) {
		sky3s[i].src = "./img_sky/3.PNG";
	}
	var sky4s = document.getElementsByClassName("sky4");
	for(var i = 0 ; i < sky4s.length ; i++) {
		sky4s[i].src = "./img_sky/4.PNG";
	}	
	
	
	var rain0s = document.getElementsByClassName("rain0");
	for(var i = 0 ; i < rain0s.length ; i++) {
		rain0s[i].src = "./img_rain/0.PNG";
	}
	var rain1s = document.getElementsByClassName("rain1");
	for(var i = 0 ; i < rain1s.length ; i++) {
		rain1s[i].src = "./img_rain/1.PNG";
	}
	var rain2s = document.getElementsByClassName("rain2");
	for(var i = 0 ; i < rain2s.length ; i++) {
		rain2s[i].src = "./img_rain/2.PNG";
	}
	var rain3s = document.getElementsByClassName("rain3");
	for(var i = 0 ; i < rain3s.length ; i++) {
		rain3s[i].src = "./img_rain/3.PNG";
	}	
	var rain4s = document.getElementsByClassName("rain4");
	for(var i = 0 ; i < rain4s.length ; i++) {
		rain4s[i].src = "./img_rain/4.PNG";
	}	
	
	
	var wind0s = document.getElementsByClassName("wind0");
	for(var i = 0 ; i < wind0s.length ; i++) {
		wind0s[i].src = "./img_wind/0.PNG";
	}
	var wind1s = document.getElementsByClassName("wind1");
	for(var i = 0 ; i < wind1s.length ; i++) {
		wind1s[i].src = "./img_wind/1.PNG";
	}
	var wind2s = document.getElementsByClassName("wind2");
	for(var i = 0 ; i < wind2s.length ; i++) {
		wind2s[i].src = "./img_wind/2.PNG";
	}
	var wind3s = document.getElementsByClassName("wind3");
	for(var i = 0 ; i < wind3s.length ; i++) {
		wind3s[i].src = "./img_wind/3.PNG";
	}	
	var wind4s = document.getElementsByClassName("wind4");
	for(var i = 0 ; i < wind4s.length ; i++) {
		wind4s[i].src = "./img_wind/4.PNG";
	}	
	var wind5s = document.getElementsByClassName("wind5");
	for(var i = 0 ; i < wind5s.length ; i++) {
		wind5s[i].src = "./img_wind/5.PNG";
	}	
	var wind6s = document.getElementsByClassName("wind6");
	for(var i = 0 ; i < wind6s.length ; i++) {
		wind6s[i].src = "./img_wind/6.PNG";
	}	
	var wind7s = document.getElementsByClassName("wind7");
	for(var i = 0 ; i < wind7s.length ; i++) {
		wind7s[i].src = "./img_wind/7.PNG";
	}		
</script>
</html>
