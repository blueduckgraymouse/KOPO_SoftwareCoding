package ch02.ex_12_otherIDE;

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
import java.util.Calendar;

public class Ex_11_3_Training_stock_p27 {
	static final int size_field = 98;
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// JDBC 접속 준비
		Connection conn = getConnection();		// connecion객체 가져오는 메서드 호출
		PreparedStatement pstmt = null;			// PreparedStatement 변수 선언
		conn.setAutoCommit(false); 				// 입력할 데이터의 수가 많으므로 작업시간을 줄여주기 위하여 autoCommit 기능 off
		
		// 입력할 파일 열기
		File f = new File("C:\\KOPO\\학습자료\\소프트웨어코딩(v2022)-자바심화\\실습데이터\\day_data_text\\THTSKS010H00.dat");	// DB에 입력할 데이터가 담긴 CSV파일 File 객체로 생성
		BufferedReader br = new BufferedReader(new FileReader(f));			// file객체를 파라미터로 BufferedReader객체를 생성 
		
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");	// 날짜출력 포팻을 설정하여 날짜 객체 생성 후 변수에 저장
		
		String sql = "insert into stock_total("								// 주식 파일의 모든 필드의 데이털르 insert하는 쿼리문 저장
				+ "shrn_iscd_standard, bsop_date, shrn_iscd, stck_prpr, stck_oprc, stck_hgpr, stck_lwpr, prdy_vrss_sign, prdy_vrss, prdy_ctrt, prdy_vol, "
				+ "acml_vol, acml_tr_pbmn, askp1, bidp1, total_askp_rsqn, total_bidp_rsqn, seln_cntg_smtn, shnu_cntg_smtn, seln_tr_pbmn, shnu_tr_pbmn,"
				+ "seln_cntg_csnu, shnu_cntg_csnu, w52_hgpr, w52_lwpr, w52_hgpr_date, w52_lwpr_date, ovtm_untp_bsop_hour, ovtm_untp_prpr, ovtm_untp_prdy_vrss, ovtm_untp_prdy_vrss_sign,"
				+ "ovtm_untp_askp1, ovtm_untp_bidp1, ovtm_untp_vol, ovtm_untp_tr_pbmn, ovtm_untp_oprc, ovtm_untp_hgpr, ovtm_untp_lwpr, mkob_otcp_vol, mkob_otcp_tr_pbmn, mkfa_otcp_vol,"
				+ "mkfa_otcp_tr_pbmn, mrkt_div_cls_code, pstc_dvdn_amt, lstn_stcn, stck_sdpr, stck_fcam, wghn_avrg_stck_prc, issu_limt_rate, frgn_limt_qty, oder_able_qty,"
				+ "frgn_limt_exhs_cls_code, frgn_hldn_qty, frgn_hldn_rate, hts_frgn_ehrt, itmt_last_nav, prdy_last_nav, trc_errt, dprt, ssts_cntg_qty, ssts_tr_pbmn,"
				+ "frgn_ntby_qty, flng_cls_code, prtt_rate, acml_prtt_rate, stdv, beta_cfcn, crlt_cfcn, bull_beta, bear_beta, bull_dvtn,"
				+ "bear_dvtn, bull_crlt, bear_crlt, stck_mxpr, stck_llam, icic_cls_code, itmt_vol, itmt_tr_pbmn, fcam_mod_cls_code, revl_issu_reas_code,"
				+ "orgn_ntby_qty, adj_prpr, fn_oprc, fn_hgpr, fn_lwpr, fn_prpr, fn_acml_vol, fn_acml_tr_pbmn, fn_prtt_rate, fn_flng_cls_code,"
				+ "buyin_nor_prpr, buyin_nor_prdy_vrss, buyin_nor_vol, buyin_nor_tr_pbmn, buyin_tod_prpr, buyin_tod_prdy_vrss, buyin_tod_vol, buyin_tod_tr_pbmn)"
				+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
					   + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
					   + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
					   + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
					   + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
					   + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
					   + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
					   + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
					   + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
					   + "?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		String readtxt;								// 파일 내용을 줄 단위로 읽을 때 마다 한 줄의 내용이 저장될 변수 선언
		int cnt = 0;								// 파일 내용을 읽은 줄의 수를 셀 변수 0으로 초기화
		int wcnt = 0;								// 파일 내용을 쓴 줄의 수를 셀 변수 0으로 초기화
		
		Calendar cal1 = Calendar.getInstance();		// 시작할 때 시간을 Calendar객체에 저장
		
		// 파일 읽어와 쿼리 실행
		try {
			pstmt = conn.prepareStatement(sql);
			
			while ((readtxt = br.readLine()) != null) {						// 파일의 모든 내용을 읽을 때까지 반복
				StringBuffer s = new StringBuffer();						// 잦은 string버퍼 생성을 막기 위하여 String 대신 읽은 파일 내용 출력에 사용할 StringBuffer 객체 생성
				String[] field = readtxt.split("%_%");						// %_%기준으로 현재 읽고 있는 한줄 쪼개서 문자열 배열에 저장
				if (field.length > 2 && field[2].replace("^", "").trim().substring(0,1).equals("A")) {	// 쪼갠 문자열 배열의 길이가 3이상이고 셋번째 문자열 배열 요소의 ^를 제거하고 양쪽 공백을 제거 후 첫 글자가 A라면
					s.append(field[0].replace("^", "").trim());											// 첫번째 문자열 배열 요소의 ^를 제거하고 양쪽 공백을 제거하여 StringBuffer객체에 저장
					for (int j = 1 ; j < field.length ; j++) {				// 문자열 배열의 길이만큼 i를 증가하며 반복
						s.append("," + field[j].replace("^", "").trim());	// i번째 문자열 배열 요소의 ^를 제거하고 양쪽 공백을 제거하여 StringBuffer객체에 저장
					}
					
					String line = s.toString();								// 정제된 데이터
					line = input_zero(line);								// 데이터 마지막에 존재하는 연속된,에 의해 ,로 split을 하면 제대로 field의 크기만큼 배열이 생성되지 않는 예외를 처리하기 위해 ,,사이에 0을 넣어준다.
					String[] field_new = line.split(",");					// ,기준으로 나눠 배열로 저장
					try {							
						for(int i = 0 ; i < size_field ; i++) {
							pstmt.setString(i+1, field_new[i]);				// 편의를 위해 모든 필드를 varchar타입으로 하였으므로 전부 setString으로 필드값 저장
						}
						pstmt.addBatch();								// 쿼리문을 PreparedStatement객체의 sql command에 저장
						pstmt.clearParameters();						// PreparedStatement객체의 쿼리문의 ?에 해당하는 파라미터 초기화
						wcnt++;											// 쓴 줄의 수 증가
						if ((wcnt % 1000) == 0){						// PreparedStatement객체의 sql command에 저장된 쿼리문이 100000개일 때마다
							pstmt.executeBatch();						// PreparedStatement객체의 sql command에 존재하는 모든 쿼리문 실행
							pstmt.clearBatch();							// PreparedStatement객체의 sql command 초기화
							conn.commit();								// 트렌젝션 커밋
							System.out.println("commit : " + wcnt + "/" + cnt + "<=" + field_new[0] + "/" + field_new[1]);	// 현황 출력
						}
					} catch (Exception e) {
						System.out.println(wcnt + "/" + field_new[0] + "/" + field_new[1]);
						e.printStackTrace();
					}
				}
				
				cnt++;				// 읽은 줄 수 증가
			}
			
			pstmt.executeBatch();	// 메모리에 저장된 쿼리문 실행
			conn.commit();			// 트렌젝션 커밋
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(br);				// BufferedReader 자원 반납
			close(pstmt);			// PreparedStatement 자원 반납
			close(conn);			// Connection 자원 반납
		}
		
		// 결과 출력
		Calendar cal2 = Calendar.getInstance();														// 종료 시간을 Calendar객체에 저장
		System.out.println("\n\n\n시작 시간 : " + sdf.format(cal1.getTime()));
		System.out.println("종료 시간 : " + sdf.format(cal2.getTime()));								// 종료 시간 출력
		System.out.println("소요 시간(초 단위) : " + (cal2.getTimeInMillis() - cal1.getTimeInMillis()));// 걸린 시간 계산
		
		System.out.printf("Program End. [%d] / [%d] insert complete\n", wcnt, cnt);					// 읽은 줄 수 와 쓴 줄수를 출력한다.
	}
	
	
	
	// 데이터에 마지막에 연속되는 빈값으로 인해 ,,,,와 같이 마지막에 ,여러 개는 split이 제대로 되지 않는 것을 막기 위해서 ,사이에 0을 넣어 반환하는 메서드
	private static String input_zero(String line) {
		while(line.contains(",,")) {			// ,,가 없을 때까지 ,,를 ,0,으로 replace
			line = line.replace(",,", ",0,");
		}
		return line;
	}

	// JDBC로드하여 conneciont 얻어오는 메서드
	private static Connection getConnection() {
		Connection conn = null;					// connection 변수 선언
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");					// mysql jdbc 드라이버 로드
			conn = DriverManager.getConnection("jdbc:mysql://192.168.23.94:3306/kopoctc", "root", "abcd1234");	// 리눅스 서버내 mysql의 connection 객체 생성
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	// PreparedStatement 자원을 반납하는 메서드
	private static void close(Statement stmt) {
		try {
			stmt.close();
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
	
	// BufferedReader 자원을 반납하는 메서드
	private static void close(BufferedReader br) {
		try {
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
