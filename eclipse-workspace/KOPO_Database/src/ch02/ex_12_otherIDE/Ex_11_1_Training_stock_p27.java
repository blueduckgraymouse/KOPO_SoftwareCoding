package ch02.ex_12_otherIDE;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Ex_11_1_Training_stock_p27 {

	public static void main(String[] args) throws ClassNotFoundException , SQLException , IOException {
		Connection conn = getConnection();				// connecion객체 가져오는 메서드 호출
		Statement stmt = null;							// statement 변수 선언
		
		String sql ="create table stock_total("			// 주식 파일의 항목 필드로 선언
				+ "shrn_iscd_standard varchar(20),"
				+ "bsop_date varchar(20),"
				+ "shrn_iscd varchar(20),"
				+ "stck_prpr varchar(20),"
				+ "stck_oprc varchar(20),"
				+ "stck_hgpr varchar(20),"
				+ "stck_lwpr varchar(20),"
				+ "prdy_vrss_sign varchar(20),"
				+ "prdy_vrss varchar(20),"
				+ "prdy_ctrt varchar(20),"
				
				+ "prdy_vol varchar(20),"
				+ "acml_vol varchar(20),"
				+ "acml_tr_pbmn varchar(20),"
				+ "askp1 varchar(20), bidp1 varchar(20),"
				+ "total_askp_rsqn varchar(20),"
				+ "total_bidp_rsqn varchar(20),"
				+ "seln_cntg_smtn varchar(20),"
				+ "shnu_cntg_smtn varchar(20),"
				+ "seln_tr_pbmn varchar(20),"
				+ "shnu_tr_pbmn varchar(20),"
				
				+ "seln_cntg_csnu varchar(20),"
				+ "shnu_cntg_csnu varchar(20),"
				+ "w52_hgpr varchar(20),"
				+ "w52_lwpr varchar(20),"
				+ "w52_hgpr_date varchar(20),"
				+ "w52_lwpr_date varchar(20),"
				+ "ovtm_untp_bsop_hour varchar(20),"
				+ "ovtm_untp_prpr varchar(20),"
				+ "ovtm_untp_prdy_vrss varchar(20),"
				+ "ovtm_untp_prdy_vrss_sign varchar(20),"
				
				+ "ovtm_untp_askp1 varchar(20),"
				+ "ovtm_untp_bidp1 varchar(20),"
				+ "ovtm_untp_vol varchar(20),"
				+ "ovtm_untp_tr_pbmn varchar(20),"
				+ "ovtm_untp_oprc varchar(20),"
				+ "ovtm_untp_hgpr varchar(20),"
				+ "ovtm_untp_lwpr varchar(20),"
				+ "mkob_otcp_vol varchar(20),"
				+ "mkob_otcp_tr_pbmn varchar(20),"
				+ "mkfa_otcp_vol varchar(20),"
				
				+ "mkfa_otcp_tr_pbmn varchar(20),"
				+ "mrkt_div_cls_code varchar(20),"
				+ "pstc_dvdn_amt varchar(20),"
				+ "lstn_stcn varchar(20),"
				+ "stck_sdpr varchar(20),"
				+ "stck_fcam varchar(20),"
				+ "wghn_avrg_stck_prc varchar(20),"
				+ "issu_limt_rate varchar(20),"
				+ "frgn_limt_qty varchar(20),"
				+ "oder_able_qty varchar(20),"
				
				+ "frgn_limt_exhs_cls_code varchar(20),"
				+ "frgn_hldn_qty varchar(20),"
				+ "frgn_hldn_rate varchar(20),"
				+ "hts_frgn_ehrt varchar(20),"
				+ "itmt_last_nav varchar(20),"
				+ "prdy_last_nav varchar(20),"
				+ "trc_errt varchar(20), "
				+ "dprt varchar(20),"
				+ "ssts_cntg_qty varchar(20),"
				+ "ssts_tr_pbmn varchar(20),"
				
				+ "frgn_ntby_qty varchar(20),"
				+ "flng_cls_code varchar(20),"
				+ "prtt_rate varchar(20),"
				+ "acml_prtt_rate varchar(20),"
				+ "stdv varchar(20),"
				+ "beta_cfcn varchar(20),"
				+ "crlt_cfcn varchar(20),"
				+ "bull_beta varchar(20),"
				+ "bear_beta varchar(20),"
				+ "bull_dvtn varchar(20),"
				
				+ "bear_dvtn varchar(20),"
				+ "bull_crlt varchar(20),"
				+ "bear_crlt varchar(20),"
				+ "stck_mxpr varchar(20),"
				+ "stck_llam varchar(20),"
				+ "icic_cls_code varchar(20),"
				+ "itmt_vol varchar(20),"
				+ "itmt_tr_pbmn varchar(20),"
				+ "fcam_mod_cls_code varchar(20),"
				+ "revl_issu_reas_code varchar(20),"
				
				+ "orgn_ntby_qty varchar(20),"
				+ "adj_prpr varchar(20),"
				+ "fn_oprc varchar(20),"
				+ "fn_hgpr varchar(20),"
				+ "fn_lwpr varchar(20),"
				+ "fn_prpr varchar(20),"
				+ "fn_acml_vol varchar(20),"
				+ "fn_acml_tr_pbmn varchar(20),"
				+ "fn_prtt_rate varchar(20),"
				+ "fn_flng_cls_code varchar(20),"
				
				+ "buyin_nor_prpr varchar(20),"
				+ "buyin_nor_prdy_vrss varchar(20),"
				+ "buyin_nor_vol varchar(20),"
				+ "buyin_nor_tr_pbmn varchar(20),"
				+ "buyin_tod_prpr varchar(20),"
				+ "buyin_tod_prdy_vrss varchar(20),"
				+ "buyin_tod_vol varchar(20),"
				+ "buyin_tod_tr_pbmn varchar(20),"
				
				+ "primary key(shrn_iscd, bsop_date));";	// primary key 단축코드랑 일자 설정
		
		try {
			stmt = conn.createStatement();		// DBMS에 쿼리문을 실행하는 statement 객체 생성
			stmt.execute(sql);					// 쿼리문 실행
			System.out.println("테이블 생성 완료");	// 테이블 생성 완료시 출력
		} catch (Exception e) {					// 에러 발생시
			e.printStackTrace();				//   에러 내용 출력
		} finally {
			close(stmt);		// 자원 반납
			close(conn);		// 자원 반납
		}
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
}
