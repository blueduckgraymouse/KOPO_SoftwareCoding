package ch02.ex_10_otherIDE;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Ex_10_3_Training_stock_p26 {
	static String path_file = "C:\\KOPO\\git_tracking\\소프트웨어코딩(v2022)-자바심화\\8강\\day_data\\THTSKS010H00.dat";	// 읽을 파일의 경로

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		Connection conn = getConnection();							// connecion객체 가져오는 메서드 호출
		PreparedStatement pstmt = null;
		String sql = "insert into stock2 values(?, ?, ?, ?, ?, ?, ?, ?)";	// 한 줄에 대한 데이터를 insert하는 쿼리문 작성하여 변수에 저장
		conn.setAutoCommit(false); 									// 입력할 데이터의 수가 많으므로 작업시간을 줄여주기 위하여 autoCommit 기능 off
		
		BufferedReader br = getBufferedReader(path_file);			// 읽을 파일을 BufferedReader객체로 가져오는 메서드 호출
		
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");	// 날짜출력 포팻을 설정하여 날짜 객체 생성 후 변수에 저장
		Calendar cal1 = Calendar.getInstance();								// 시작할 때 시간을 Calendar객체에 저장
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			String readtxt;													// 파일 내용을 줄 단위로 읽을 때 마다 한 줄의 내용이 저장될 변수 선언
			int cnt = 0;													// 파일 내용을 읽은 줄의 수를 셀 변수 0으로 초기화
			int wcnt = 0;													// 파일 내용을 쓴 줄의 수를 셀 변수 0으로 초기화
			
			while ((readtxt = br.readLine()) != null) {						// 파일의 모든 내용을 읽을 때까지 반복
				StringBuffer s = new StringBuffer();						// 잦은 string버퍼 생성을 막기 위하여 String 대신 읽은 파일 내용 출력에 사용할 StringBuffer 객체 생성
				String[] field = readtxt.split("%_%");						// %_%기준으로 현재 읽고 있는 한줄 쪼개서 문자열 배열에 저장
				if(field.length > 2 && field[2].replace("^", "").trim().substring(0,1).equals("A")) {	// 쪼갠 문자열 배열의 길이가 3이상이고 셋번째 문자열 배열 요소의 ^를 제거하고 양쪽 공백을 제거 후 첫 글자가 A라면
					s.append(field[0].replace("^", "").trim());				// 첫번째 문자열 배열 요소의 ^를 제거하고 양쪽 공백을 제거하여 StringBuffer객체에 저장
					for (int j = 1 ; j < field.length ; j++) {				// 문자열 배열의 길이만큼 i를 증가하며 반복
						s.append("," + field[j].replace("^", "").trim());	// i번째 문자열 배열 요소의 ^를 제거하고 양쪽 공백을 제거하여 StringBuffer객체에 저장
					}
					
					String line = s.toString();								// 정제된 한 줄 line변수에 저장
					String[] field_new = line.split(",");					// ,기준으로 나눠 배열로 저장
					try {
						pstmt.setString(1,field_new[2]);					// 단축코드에 해당하는 필드값 저장
						pstmt.setInt(2,Integer.parseInt(field_new[1]));		// 일자에 해당하는 필드값 저장
						pstmt.setInt(3,Integer.parseInt(field_new[4]));		// 시가에 해당하는 필드값 저장
						pstmt.setInt(4,Integer.parseInt(field_new[5]));		// 고가에 해당하는 필드값 저장
						pstmt.setInt(5,Integer.parseInt(field_new[6]));		// 저가에 해당하는 필드값 저장
						pstmt.setInt(6,Integer.parseInt(field_new[3]));		// 종가에 해당하는 필드값 저장
						pstmt.setLong(7,Long.parseLong(field_new[11]));		// 거래량에 해당하는 필드값 저장
						pstmt.setLong(8,Long.parseLong(field_new[12]));		// 거래대금에 해당하는 필드값 저장
						pstmt.addBatch();									// 쿼리문을 PreparedStatement객체의 sql command에 저장
						pstmt.clearParameters();							// PreparedStatement객체의 쿼리문의 ?에 해당하는 파라미터 초기화
						wcnt++;												// 쓴 줄의 수 증가
						if( (wcnt % 100000) == 0){							// PreparedStatement객체의 sql command에 저장된 쿼리문이 100000개일 때마다 
							pstmt.executeBatch();							// PreparedStatement객체의 sql command에 존재하는 모든 쿼리문 실행
							pstmt.clearBatch();								// PreparedStatement객체의 sql command 초기화
							conn.commit();									// 트렌젝션 커밋
							System.out.println(wcnt + "/" + cnt);			// 현황 출력
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
				
				cnt++;				// 읽은 줄 수 증가
			}
			
			pstmt.executeBatch();	// 메모리에 저장된 쿼리문 실행
			conn.commit();			// 트렌젝션 커밋
			
			Calendar cal2 = Calendar.getInstance();														// 종료 시간을 Calendar객체에 저장
			System.out.println("\n\n\n시작 시간 : " + sdf.format(cal1.getTime()));
			System.out.println("종료 시간 : " + sdf.format(cal2.getTime()));								// 종료 시간 출력
			System.out.println("소요 시간(초 단위) : " + (cal2.getTimeInMillis() - cal1.getTimeInMillis()));// 걸린 시간 계산
			
			System.out.printf("Program End. [%d] / [%d] insert complete\n", wcnt, cnt);					// 읽은 줄 수 와 쓴 줄수를 출력한다.
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(br);				// BufferedReader 자원 반납
			close(pstmt);			// PreparedStatement 자원 반납
			close(conn);			// Connection 자원 반납
		}
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
	
	// BufferedReader 자원을 반납하는 메서드
	private static void close(BufferedReader br) {
		try {
			br.close();			// PreparedStatement 객체 반환
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
