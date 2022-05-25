package ch02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex_07_4_freewifi_insert_p23 {
	static String path_file = "C:\\KOPO\\git_tracking\\eclipse-workspace\\KOPO_Database\\src\\ch02\\전국무료와이파이표준데이터_utf_8.csv";	// 읽을 파일의 경로

	public static void main(String[] args) {
		// DBMS접속 준비
		Connection conn = getConnection();							// connecion객체 가져오는 메서드 호출
		PreparedStatement pstmt = null;
		String queryTxt = String.format("insert into freewifi("			// 전국 와이파이 데이터를 넣어주는 쿼리문 완성하여 저장
				+ "inst_place, inst_place_detail, inst_city, inst_country, inst_place_flag,"
				+ "service_provider, wifi_ssid, inst_date, place_addr_road, place_addr_land,"
				+ "manage_office, manage_office_phone, latitude, longitude, write_date) "
				+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
		
		BufferedReader br = getBufferedReader(path_file);				// 읽을 파일을 BufferedReader객체로 가져오는 메서드 호출
		
		try {
			pstmt = conn.prepareStatement(queryTxt);						// DBMS에 쿼리문을 실행하는 prepareStatement 객체 생성하고 쿼리문 파라미터로 저장
			String readTxt;													// 한줄에 대한 데이터를 저장할 String 변수 선언
			int lineCnt = 0;												// 읽은 데이터 수를 카운트할 변수 0 으로 초기화
			
			if ((readTxt = br.readLine()) == null) {						// 빈 파일인 경우
				System.out.println("빈 파일입니다.");						//   메세지 출력
				return;														//   프로그램 종료
			}
			
			readTxt.split(",");												// 첫 줄은 필드명이므로 다음줄로 커서 이동
			
			while ((readTxt = br.readLine()) != null) {						// 마지막 데이터까지 반복하여 한 줄씩 읽기
				readTxt = remove(readTxt);									// 데이터 자체에 존재하는 ,제거
				
				String[] field = readTxt.split(",");						// 읽어온 한 줄의 데이터를 ,단위로 나눈다.
				
				for (int i = 0 ; i < field.length ; i++) {					// PreparedStatement객체에 저장한 쿼리문의 ?에 들어갈 값 저장
					if (i == 7 || i == 12 || i == 13)						// 필드 타입이 varchar가 아닌 필드는 제외
						continue;
					pstmt.setString(i+1, field[i]);
				}
				pstmt.setDate(8, reformat(field[7]));						// 설치일자는 reformat()메서드를 이용하여 정제 후 sql.Date형으로 변환하여 저장
				pstmt.setDouble(13, Double.parseDouble(field[12]));			// 위도는 double타입
				pstmt.setDouble(14, Double.parseDouble(field[13]));			// 경도는 double타입

				try {								// try-catch문을 이용하여 중복 데이터 때문에 PRIMARY KEY의 duplicate에 의한 에러로 입력이 멈추는 것을 방지
					pstmt.execute();
					System.out.printf("%d번째 항목 insert OK [%s]\n", lineCnt, queryTxt);	// 현재 읽어온 데이터 한 줄에 대한 insert문 결과 출력(성공)
				} catch (SQLException e) {			// 쿼리문 실행 중 에러 발생시(중복데이터)
					e.printStackTrace();			// 에러 출력
					System.out.printf("%d번째 항목 insert Fail [%s]\n", lineCnt, queryTxt); // 현재 읽어온 데이터 한 줄에 대한 insert문 결과 출력(실패)
				}
				
				lineCnt++;		// 카운트 변수 증가
			}
		} catch (Exception e) {	// 에러 발생시
			e.printStackTrace();//   출력
		} finally {
			close(pstmt);		// 자원 반납
			close(conn);		// 자원 반납
		}
	}

	
	
	
	// 읽을 파일을 열어 BufferedReader객체로 반환하는 메서드
	private static BufferedReader getBufferedReader(String path_file) {
		BufferedReader br = null;
		try {
			File f = new File(path_file);	// 읽을 파일을 File 객체로 생성
			br = new BufferedReader(new FileReader(f));		// file객체를 파라미터로 BufferedReader객체를 생성 
		} catch (Exception e) {				// 에러 발생시
			e.printStackTrace();			//   출력
		}
		return br;							// BufferedReader객체 반환				
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
	
	// 설치일자의 날짜 읽어 sql.Date형으로 형변환하여 반환하는 메서드
	static java.sql.Date reformat(String inst_date) {
		SimpleDateFormat[] sdfs  = {new SimpleDateFormat("yyyy-MM"), new SimpleDateFormat("yyyy-MM-dd"), new SimpleDateFormat("yyyy년 MM월"), new SimpleDateFormat("yyyy-MM(예정)"), 
									new SimpleDateFormat("MMM-yy", Locale.ENGLISH),	new SimpleDateFormat("yy-MMM", Locale.ENGLISH),
									new SimpleDateFormat("YYYY")};		// 파일 내 존재하는 설치일자의 날짜 형식 모두 SimpleDateFormat객체 배열로 초기화
		
		Date date = null;			// 설치일자가 저장될 util.date객체 선언
		
		for (int i = 0 ; i < sdfs.length ; i++) {		// SimpleDateFormat 객체 배열의 크기만 큼반복
			try {
				if (inst_date.equals("")) {				// 설치일자가 공백일 경우
					date = new Date();					//   오늘 날짜로 입력
					break;
				} else {								// 설치일자에 날짜가 있는 경우
					if (i != 6) {
						date = sdfs[i].parse(inst_date);	// 읽어온 설치일자를 parse하여 date객체에 저장
						break;								
					} else {								// yyyy형태 일 때
						inst_date = inst_date + "-01";		//   yyyy형태를 파싱하면 이전 년도 12월30일로 date에 저장되는 예외처리 
						date = sdfs[0].parse(inst_date);	
						break;
					}
				}
			} catch (Exception e) {	// SimpleDateFormat 객체 배열의 모든 형태로 파싱을 시도하므로 파싱 안되는 경우를
									//   try catch로 pass 시킨다.
			}
		}

		long timeInMillSeconds = date.getTime();		// sql.Date타입으로 바꾸기 위하여 ms기준으로 숫자값 저장
		java.sql.Date sqlDate = new java.sql.Date(timeInMillSeconds);	// sql.Date타입으로 변환
		
		return sqlDate;				// 변환된 java.sql.Date 객체 반환
	}
	
	// JDBC로드하여 conneciont 얻어오는 메서드
	private static Connection getConnection() {
		Connection conn = null;								// 커넥션 변수 선언					
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");		// mysql jdbc 드라이버 로드
			conn = DriverManager.getConnection("jdbc:mysql://192.168.23.94:3306/kopoctc", "root", "abcd1234");	// 리눅스 서버내 mysql의 connection 객체 생성
		} catch (Exception e) {								// 에러 발생시		
			e.printStackTrace();							//   에러 출력
		}

		return conn;
	}
	
	// PreparedStatement 자원을 반납하는 메서드
	private static void close(PreparedStatement pstmt) {
		try {
			pstmt.close();			// PreparedStatement 객체 반환
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Connection 자원을 반납하는 메서드
	private static void close(Connection conn) {
		try {
			conn.close();			// PreparedStatement 객체 반환
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
