package ch02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex_07_2_and_3_freewifi_insert_p23 {

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
					field[5], field[6], field[7], field[8], field[9], 
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
		Pattern ptrn = Pattern.compile("\"(.*?)\"");			// 큰따옴표로 감싸진 문자 패턴을 Pattern객체로 초기화
		Matcher matcher = ptrn.matcher(readTxt);				// 파라미터로 들어온 문자열 내에 Pattern객체의 패턴과 일치 정보가 담긴 Matcher객체로 생성
		
		while (matcher.find()) {								// Matcher객체 내의 다음 시퀀스가 존재하면 반복, 모든 패턴 일치 부분에 접근
			readTxt = matcher.replaceFirst(matcher.group().replace(",", " ").replace("\"", ""));	// 현재 접근한 패턴 일치 부분에서 ,을 공백으로 바꾸고 " 제거하여 원본 readTxt에 저장
			matcher = ptrn.matcher(readTxt);					// 변경된 readTxt에 대해 다시 패턴 일치 부분 Matcher객체로 저장 -> 패턴 일치하는 부분을 모두 수정할 때까지 반복하는 결과를 가져온다.
		}
		
		return readTxt;											// 수정된 문자열 반환
	}
}
