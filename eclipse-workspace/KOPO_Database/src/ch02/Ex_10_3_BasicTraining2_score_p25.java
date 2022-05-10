package ch02;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Ex_10_3_BasicTraining2_score_p25 {
	static final int iPerson = 1000;													// 점수 통계 할 총 인원 설정
	static final int nums_list = 30;													// 한 페이지 당 출력할 학생 개수
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		Class.forName("com.mysql.cj.jdbc.Driver");					// mysql jdbc 드라이버 로드
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.94:3306/kopoctc", "root", "abcd1234");	// 리눅스 서버내 mysql의 connection 객체 생성

		try {		
			for (int no_page = 0 ; no_page * nums_list < iPerson ; no_page++) {	// 1000번 반복
				System.out.printf("%25s", "성적집계표\n");
				k35_print_pageNum_date(no_page);								// 페이지 수와 날짜 출력
				System.out.println("==============================================================");
				k35_print_header();													// 헤더('번호', '이름' 등등)를 출력하는 메서드 호출
				System.out.println("==============================================================");
				k35_print_score(conn, no_page, nums_list);	// 페이지 출력 후 방금 출력한 페이지의 끝 번호(다음페이지 출력시 시작할 첫 번호) 반환받아 변수에 저장
				System.out.println("==============================================================");
				System.out.println("현재페이지");
				k35_print_total(conn, no_page, nums_list);		// 현재 페이지에 대한 집계 출력
				System.out.println("==============================================================");
				System.out.println("누적페이지");
				k35_print_total(conn, no_page, nums_list);						// 누적 페이지에 대한 집계 출력
				System.out.println("\n");
			}
		} catch (Exception e) { 									// 에러 발생시
			e.printStackTrace();									//   에러 출력
		} finally {
			conn.close();		// 자원 반납
		}
	}
	
	// 헤더('번호', '이름' 등등)를 출력하는 메서드
		private static void k35_print_header() {
			String[] k35_subjects = {"국어", "영어", "수학"};
			System.out.printf("%-7s", "학번");
			System.out.printf("%-8s", "이름");
			for (String k35_subject : k35_subjects) {		
				System.out.printf("%-4s", k35_subject);								// 모든 과목명 출력
			}
			System.out.printf("%-4s", "총점");
			System.out.printf("%5s\n", "평균");
		}
		
		// 출력일자는 출력하는 메서드
		private static void k35_print_pageNum_date(int no_page) {
			Calendar k35_calendar = Calendar.getInstance();										// 현재 날짜 정보를 갖는 객체 생성 및 초기화
			SimpleDateFormat k35_sdf = new SimpleDateFormat("YYYY.MM.dd HH:mm:ss");				// 출력형태에 맞는 date 포맷 객체 생성
			System.out.printf("%-32s%s", "PAGE : " + (no_page + 1), "출력일자 : " + k35_sdf.format(k35_calendar.getTime()) + "\n");	// 파라미터로 받아온 현재 출력하고 있는 페이지 넘버와
																																	//현재 일자를 출력일자로 출력
		}
		
		// 학생들의 데이터를 출력하는 메서드
		private static void k35_print_score(Connection conn, int no_page, int nums_list) throws SQLException {
			String sql = "select *, (kor+eng+mat), ((kor+eng+mat)/3)  from score limit ?, 30;";				// select 쿼리문 저장
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			try {
				pstmt.setInt(1, no_page * nums_list);
				
				ResultSet rset = pstmt.executeQuery();
				
				while (rset.next()) {
					System.out.printf("%4d%5s", rset.getInt(1), "");								// 번호 출력
					System.out.printf("%-7s",rset.getString(2));									// 이름 출력
					System.out.printf("%4d%2s", rset.getInt(3), "");								// 국어 성적 출력
					System.out.printf("%4d%2s", rset.getInt(4), "");								// 영어 성적 출력
					System.out.printf("%4d%2s", rset.getInt(5), "");								// 수학 성적 출력
					System.out.printf("%4d%2s", rset.getInt(6), "");								// 3과목의 총점 출력
					System.out.printf("%7.2f\n", rset.getDouble(7), "");							// 3과목의 평균 출력
				}
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				pstmt.close();
			}
		}
		
		// 현재 페이지의 학생들의 데이터를 집계한 정보(합계, 평균)를 출력하는 메서드
		private static void k35_print_total(Connection conn, int no_page, int nums_list) throws SQLException {
			String sql = "select sum(kor), sum(eng), sum(mat), sum(kor+eng+mat), avg((kor+eng+mat)/3) from (select * from score limit ?, 30) as apage;";// select 쿼리문 저장
			PreparedStatement pstmt = conn.prepareStatement(sql);
			try {
				pstmt.setInt(1, no_page * nums_list);
				
				ResultSet rset1 = pstmt.executeQuery();
				
				rset1.next();
				System.out.printf("%-15s", "합계");		
				System.out.printf("%6d", rset1.getInt(1));							// 모든 학생의 국어 성적 누적 합계 출력
				System.out.printf("%6d", rset1.getInt(2));							// 모든 학생의 영어 성적 누적 합계 출력
				System.out.printf("%6d", rset1.getInt(3));							// 모든 학생의 수학 성적 누적 합계 출력
				System.out.printf("%6d", rset1.getInt(4));							// 모든 학생의 3과목의 총점 누적 합계 출력
				System.out.printf("%9.2f\n", rset1.getDouble(5));					// 모든 학생의 3과목의 평균 누적 합계 출력
				
				
				String sql2 = "select sum(kor), sum(eng), sum(mat), sum(kor+eng+mat), avg((kor+eng+mat)/3) from (select * from score limit 0, ?) as apage;";// select 쿼리문 저장
				pstmt = conn.prepareStatement(sql2);
				
				pstmt.setInt(1, (no_page + 1) * nums_list);
				
				ResultSet rset2 = pstmt.executeQuery();
				
				rset2.next();
				System.out.printf("%-15s", "평균");
				System.out.printf("%6d",  rset1.getInt(1));						// 모든 학생의 국어 성적 누적 합계의 평균 출력
				System.out.printf("%6d",  rset1.getInt(2));						// 모든 학생의 영어 성적 누적 합계의 평균 출력
				System.out.printf("%6d",  rset1.getInt(3));						// 모든 학생의 수학 성적 누적 합계의 평균 출력
				System.out.printf("%6d",  rset1.getInt(4));						// 모든 학생의 3과목의 총점 누적 합계의 평균 출력
				System.out.printf("%9.2f\n",  rset1.getDouble(5));					// 모든 학생의 3과목의 평균 누적 합계의 평균 출력
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				pstmt.close();
			}
		}
	
}
