package ch02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex_07_4_freewifi_insert_p23 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");											// mysql jdbc 드라이버 로드
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.94:3306/kopoctc", "root", "abcd1234");	// 리눅스 서버내 mysql의 connection 객체 생성
		
		Statement stmt = conn.createStatement();				// DBMS에 쿼리문을 실행하는 statement 객체 생성
		
		File f = new File("C:\\KOPO\\git_tracking\\eclipse-workspace\\KOPO_Database\\src\\ch02\\전국무료와이파이표준데이터_utf_8.csv");	// 전국 와이파이 정보가 들어있는 csv파일 파일 객체로 생성
		BufferedReader br = new BufferedReader(new FileReader(f));		// file객체를 파라미터로 BufferedReader객체를 생성 
		
		String readTxt;													// 한줄에 대한 데이터를 저장할 String 변수 선언
		
		if ((readTxt = br.readLine()) == null) {						// 빈 파일인 경우
			System.out.println("빈 파일입니다.");						//   메세지 출력
			return;														//   프로그램 종료
		}
		
		String[] field_name = readTxt.split(",");						// 첫 줄은 필드명
		int lineCnt = 0;												// 읽은 데이터 수를 카운트할 변수 0 으로 초기화
		
		while ((readTxt = br.readLine()) != null) {						// 마지막 데이터까지 반복하여 한 줄씩 읽기
			readTxt = remove(readTxt);									// 데이터 자체에 존재하는 ,제거
			
			String[] field = readTxt.split(",");						// 읽어온 한 줄의 데이터를 ,단위로 나눈다.
			String queryTxt;											// 쿼리문이 저장될 변수 선언
			
			
			queryTxt = String.format("insert into freewifi("			// 전국 와이파이 데이터를 넣어주는 쿼리문 완성하여 저장
					+ "inst_place, inst_place_detail, inst_city, inst_country, inst_place_flag,"
					+ "service_provider, wifi_ssid, inst_date, place_addr_road, place_addr_land,"
					+ "manage_office, manage_office_phone, latitude, longitude, write_date) "
					+ "values ("
					+ "'%s','%s','%s','%s','%s',"
					+ "'%s','%s','%s','%s','%s',"
					+ "'%s','%s',%s, %s,'%s');",
					field[0], field[1], field[2], field[3], field[4], 
					field[5], field[6], reformat(field[7]), field[8], field[9], 
					field[10], field[11], field[12], field[13], field[14]);
			
			try {								// try-catch문을 이용하여 중복 데이터 때문에 PRIMARY KEY의 duplicate에 의한 에러로 입력이 멈추는 것을 방지
				stmt.execute(queryTxt);			// 쿼리문 실행
				System.out.printf("%d번째 항목 insert OK [%s]\n", lineCnt, queryTxt);	// 현재 읽어온 데이터 한 줄에 대한 insert문 결과 출력(성공)
			} catch (SQLException e) {			// 쿼리문 실행 중 에러 발생시	
				e.printStackTrace();			// 에러 출력
				System.out.printf("%d번째 항목 insert Fail [%s]\n", lineCnt, queryTxt); // 현재 읽어온 데이터 한 줄에 대한 insert문 결과 출력(실패)
			}
			
			lineCnt++;		// 카운트 변수 증가
		}
		
		
		stmt.close();		// 자원 반납
		conn.close();		// 자원 반납
	}

	// csv파일의 데이터에 존재하는 ,를 제거
	static String remove(String readTxt) {
		Pattern ptrn = Pattern.compile("\"(.*?)\"");
		Matcher matcher = ptrn.matcher(readTxt);
		
		while (matcher.find()) {
			readTxt = matcher.replaceFirst(matcher.group().replace(",", " ").replace("\"", ""));
			matcher = ptrn.matcher(readTxt);
		}
		
		return readTxt;
	}
	
	// 08-AUG
	static String reformat(String inst_date) {
		SimpleDateFormat[] sdfs  = {new SimpleDateFormat("YYYY-MM"), new SimpleDateFormat("YYYY-MM-dd"), new SimpleDateFormat("YYYY년 MM월"), new SimpleDateFormat("YYYY-MM(예정)"), 
									new SimpleDateFormat("YY-MMM", new Locale("en", "US")),	new SimpleDateFormat("MMM-YY", new Locale("en", "US")),
									new SimpleDateFormat("YYYY")};
		SimpleDateFormat[] sdfs_f = {new SimpleDateFormat("YYYY-MM"), new SimpleDateFormat("20YY-MM"), new SimpleDateFormat("YYYY-01")};
		
		java.util.Date date = null;
		
		for (int i = 0 ; i < sdfs.length ; i++) {
			try {
				date = sdfs[i].parse(inst_date);
				if (i < 4)
					return sdfs_f[0].format(date);
				else if (i == 4 || i == 5)
					return sdfs_f[1].format(date);
				else
					return sdfs_f[2].format(date);
			} catch (Exception e) {
				
			}
			
		}

		return "";									// 공백일 경우
	}
	
}
