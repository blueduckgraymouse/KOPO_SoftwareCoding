package ch02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Ex_10_3_BasicTraining2_score_p25 {
	static final int size_page = 30;								// 한 페이지 당 출력할 학생 개수 변수로 선언
	
	public static void main(String[] args) {
		
		Connection conn = getConnection();							// connecion객체 가져오는 메서드 호출
		
		try {	
			print_score_summury_sheet(conn, size_page);				// 성적 집계표 출력 메서드 호출
		} catch (Exception e) { 									// 에러 발생시
			e.printStackTrace();									//   에러 출력
		} finally {
			close(conn);											// connection 자원 반납
		}
	}
	
	
	
	// JDBC로드하여 conneciont 얻어오는 메서드
	private static Connection getConnection() {
		Connection conn = null;										// 커넥션 변수 선언					
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");				// mysql jdbc 드라이버 로드
			conn = DriverManager.getConnection("jdbc:mysql://192.168.23.94:3306/kopoctc", "root", "abcd1234");	// 리눅스 서버내 mysql의 connection 객체 생성
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}
	
	// 성적집계표를 출력하는 메서드
	private static void print_score_summury_sheet(Connection conn, int sizePage) {
		int size_total = check_total_size(conn);					// 총 레코드 개수 조회 메서드 호출
		
		// 성적 집계표 출력
		for (int no_page = 0 ; no_page * size_page < size_total ; no_page++) {	// 마지막 레코드의 페이지까지 반복
			System.out.printf("%25s", "성적집계표\n");
			print_pageNum_And_date(no_page);						// 페이지 수와 날짜 출력 메서드 호출
			
			System.out.println("==============================================================");
			print_header();											// 헤더('번호', '이름' 등등)를 출력하는 메서드 호출
			System.out.println("==============================================================");
			try {
				print_score(conn, no_page, size_page);				// 현재 페이지 번호에 해당하는 레코드 조회하여 모두 출력하는 메서드 호출
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			System.out.println("==============================================================");
			System.out.println("현재페이지");
			try {
				print_summury(conn, no_page * sizePage, sizePage);	// 현재 페이지에 대한 집계표 출력
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			System.out.println("==============================================================");
			System.out.println("누적페이지");
			try {
				print_summury(conn, 0, (no_page+1) * sizePage);		// 누적 페이지에 대한 집계 출력
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			System.out.println("\n");
		}
	}

	// 테이블 내의 총 레코드 수를 조회해 반환해주는 메서드
	private static int check_total_size(Connection conn) {
		String sql = "select count(*) from score;";					// 총 레코드 수 조회하는 쿼리문 저장
		PreparedStatement pstmt = null;								// prepareStatement 변수 선언
		int size = 0;												// 반환할 총 레코드 수 크기 변수 선언
		
		try {
			pstmt = conn.prepareStatement(sql);						// DBMS에 쿼리문을 실행하는 prepareStatement 객체 생성하고 쿼리문 파라미터로 저장
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			ResultSet rset = pstmt.executeQuery();					// 쿼리 실행한 후 결과를 ResultSet객체에 저장
			rset.next();											// 첫 레코드로 커서 이동
			size = rset.getInt(1);									// 조죄한 총 레코드 수 크기 변수에 저장
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);											// prepareStatement 자원 반납
		}
		
		return size;												// 조죄한 총 레코드 수 크기 반환
	}

	// 헤더(모든 필드명)를 출력하는 메서드
	private static void print_header() {
		String[] subjects = {"국어", "영어", "수학"};
		System.out.printf("%-7s", "학번");
		System.out.printf("%-8s", "이름");
		for (String subject : subjects) {		
			System.out.printf("%-4s", subject);						// 모든 과목명 출력
		}
		System.out.printf("%-4s", "총점");
		System.out.printf("%5s\n", "평균");
	}
	
	// 출력일자는 출력하는 메서드
	private static void print_pageNum_And_date(int no_page) {
		Calendar calendar = Calendar.getInstance();										// 현재 날짜 정보를 갖는 객체 생성 및 초기화
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY.MM.dd HH:mm:ss");				// 출력형태에 맞는 date 포맷 객체 생성
		System.out.printf("%-32s%s", "PAGE : " + (no_page + 1), "출력일자 : " + sdf.format(calendar.getTime()) + "\n");	// 파라미터로 받아온 현재 출력하고 있는 페이지 넘버와
																						//현재 일자를 출력일자로 출력
	}
	
	// 학생들의 데이터를 출력하는 메서드
	private static void print_score(Connection conn, int no_page, int size_page) throws SQLException {
		String sql = "select *, (kor+eng+mat), ((kor+eng+mat)/3)  from score limit ?, ?;";		// 현재 페이지에 출력할 레코드 조회하는 쿼리문 저장
		PreparedStatement pstmt = null;													// prepareStatement 변수 선언
		
		try {
			pstmt = conn.prepareStatement(sql);											// DBMS에 쿼리문을 실행하는 prepareStatement 객체 생성하고 쿼리문 파라미터로 저장
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			pstmt.setInt(1, no_page * size_page);										// 조회된 레코드 중 DB에서 꺼내올(출력할) 레코드의 시작 인덱스 번호 설정
			pstmt.setInt(2, size_page);													// 시작 인덱스로 부터 몇 개의 레코드를 가져올지 설정
			
			ResultSet rset = pstmt.executeQuery();										// 쿼리 실행한 후 결과를 ResultSet객체에 저장
			
			while (rset.next()) {
				System.out.printf("%4d%5s", rset.getInt(1), "");						// 번호 출력
				System.out.printf("%-7s",rset.getString(2));							// 이름 출력
				System.out.printf("%4d%2s", rset.getInt(3), "");						// 국어 성적 출력
				System.out.printf("%4d%2s", rset.getInt(4), "");						// 영어 성적 출력
				System.out.printf("%4d%2s", rset.getInt(5), "");						// 수학 성적 출력
				System.out.printf("%4d%2s", rset.getInt(6), "");						// 3과목의 총점 출력
				System.out.printf("%7.2f\n", rset.getDouble(7), "");					// 3과목의 평균 출력
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
		}
	}
	
	// 학생들의 데이터를 집계한 정보(합계, 평균)를 출력하는 메서드
	private static void print_summury(Connection conn, int index_start, int size) throws SQLException {
		String sql = "select sum(kor), sum(eng), sum(mat), sum(kor+eng+mat), sum((kor+eng+mat)/3) from (select * from score limit ?, ?) as apage;"; // 현재 페이지의 합계를 조회할 쿼리문 저장
		
		PreparedStatement pstmt = null;										// prepareStatement 변수 선언
		
		try {
			pstmt = conn.prepareStatement(sql);								// DBMS에 쿼리문을 실행하는 prepareStatement 객체 생성하고 쿼리문 파라미터로 저장
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			// 
			pstmt.setInt(1, index_start);									// 조회된 레코드 중 DB에서 꺼내올(출력할) 레코드의 시작 인덱스 번호 설정
			pstmt.setInt(2, size);											// 시작 인덱스로 부터 몇 개의 레코드를 가져올지 설정
			
			ResultSet rset1 = pstmt.executeQuery();							// 쿼리 실행한 후 결과를 ResultSet객체에 저장
				
			rset1.next();													// 첫 레코드로 커서 이동
			System.out.printf("%-15s", "합계");		
			System.out.printf("%6d", rset1.getInt(1));						// 모든 학생의 국어 성적 누적 합계 출력
			System.out.printf("%6d", rset1.getInt(2));						// 모든 학생의 영어 성적 누적 합계 출력
			System.out.printf("%6d", rset1.getInt(3));						// 모든 학생의 수학 성적 누적 합계 출력
			System.out.printf("%6d", rset1.getInt(4));						// 모든 학생의 3과목의 총점 누적 합계 출력
			System.out.printf("%9.2f\n", rset1.getDouble(5));				// 모든 학생의 3과목의 평균 누적 합계 출력
			
			
			String sql2 = "select avg(kor), avg(eng), avg(mat), avg(kor+eng+mat), avg((kor+eng+mat)/3) from (select * from score limit ?, ?) as apage;"; // 현재 페이지의 평균을 조회할 쿼리문 저장
			pstmt = conn.prepareStatement(sql2);							// DBMS에 쿼리문을 실행하는 prepareStatement 객체 생성하고 쿼리문 파라미터로 저장	
			
			pstmt.setInt(1, index_start);									// 조회된 레코드 중 DB에서 꺼내올(출력할) 레코드의 시작 인덱스 번호 설정
			pstmt.setInt(2, size);											// 시작 인덱스로 부터 몇 개의 레코드를 가져올지 설정
			
			ResultSet rset2 = pstmt.executeQuery();							// 쿼리 실행한 후 결과를 ResultSet객체에 저장
			
			rset2.next();													// 첫 레코드로 커서 이동
			System.out.printf("%-15s", "평균");
			System.out.printf("%6d",  rset2.getInt(1));						// 모든 학생의 국어 성적 누적 합계의 평균 출력
			System.out.printf("%6d",  rset2.getInt(2));						// 모든 학생의 영어 성적 누적 합계의 평균 출력
			System.out.printf("%6d",  rset2.getInt(3));						// 모든 학생의 수학 성적 누적 합계의 평균 출력
			System.out.printf("%6d",  rset2.getInt(4));						// 모든 학생의 3과목의 총점 누적 합계의 평균 출력
			System.out.printf("%9.2f\n",  rset2.getDouble(5));				// 모든 학생의 3과목의 평균 누적 합계의 평균 출력
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
		}
	}
	
	// PreparedStatement 자원을 반납하는 메서드
	private static void close(PreparedStatement pstmt) {
		try {
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Connection 자원을 반납하는 메서드
	private static void close(Connection conn) {
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
