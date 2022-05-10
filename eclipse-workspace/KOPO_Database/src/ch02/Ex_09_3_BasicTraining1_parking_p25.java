package ch02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex_09_3_BasicTraining1_parking_p25 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// JDBC 커넥션 열기
		Class.forName("com.mysql.cj.jdbc.Driver");											// mysql jdbc 드라이버 로드
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.94:3306/kopoctc", "root", "abcd1234");	// 리눅스 서버내 mysql의 connection 객체 생성
		
		conn.setAutoCommit(false); 															// 입력할 데이터의 수가 많으므로 작업시간을 줄여주기 위하여 autoCommit 기능 off
		
		
		// 입력할 파일 열기
		File f = new File("C:\\KOPO\\git_tracking\\eclipse-workspace\\KOPO_Database\\src\\ch02\\전국주차장정보표준데이터_utf_8.csv");	// DB에 입력할 데이터가 담긴 CSV파일 File 객체로 생성
		BufferedReader bf = new BufferedReader(new FileReader(f));							// file객체를 파라미터로 BufferedReader객체를 생성 
		
		bf.readLine();		// 필드 제목
		
		String readTxt = "";																// 한 줄에 대한 데이터를 저장할 String 변수 초기화
		String sql = "insert into parking values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"				// 한 줄에 대한 데이터를 insert하는 쿼리문 작성하여 변수에 저장
											 + " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
											 + " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
											 + " ?, ?, ?);";
		int count_total = 0;												// 총 데이터 개수를 셀 변수 0으로 초기화
		int count_insert = 0;												// 입력 완료된 데이터 개수를 셀 변수 0으로 초기화
		
		PreparedStatement pstmt = conn.prepareStatement(sql);				// DBMS에 쿼리문을 실행하는 statement 객체 생성하고 쿼리문 파라미터로 저장
		
		while ((readTxt = bf.readLine()) != null) {							// 마지막 데이터까지 반복하여 한 줄씩 읽기
			count_total++;													// 총 데이터 카운트 수 증가
			readTxt = remove(readTxt);										// 데이터 자체에 존재하는 ,제거
			try {
				String[] field = readTxt.split(",");						// 읽어온 한 줄의 데이터를 ,단위로 나눈다.
				
				for (int i = 0 ; i < field.length ; i++) {					// table 생성시 위도와 경도를 제외하고 모두 varchar타입으로 정의했으므로					
					if (i == 28 || i == 29)									// 위도와 경도의 인덱스를 제외하고
						continue;
					pstmt.setString(i+1, field[i]);							// 다른 필드값은 setString이용하여 그대로 pstmt에 저장
				}
				pstmt.setDouble(29, Double.parseDouble(field[28]));			// 위도의 필드 타입은 double이므로 형변환하여 setDouble으로 저장		
				pstmt.setDouble(30, Double.parseDouble(field[29]));			// 경도의 필드 타입은 double이므로 형변환하여 setDouble으로 저장

				pstmt.execute();											// 쿼리문 실행
				System.out.println(count_total + "번째 데이터 입력 성공");	// 해당 한 줄의 데이터에 대해 insert 성공 여부 출력
				count_insert++;
				
			} catch (Exception e) {											// 중복 데이터이거나 위경도가 존재 하지 않으면 에러 발생 -> insert하지 않는다.
				e.printStackTrace();
				System.out.println(count_total + "번째 데이터 입력 제외\n   =>  " + readTxt);	// 해당 한 줄의 데이터에 대해 insert 성공 여부 출력
			}
		}
		
		conn.commit();					// 지금까지 insert한 데이터 한번에 commit
		
		System.out.println("\n*******************************\n");
		System.out.println("=> 총 데이터 " + count_total + " 중 " + count_insert + " 입력 완료 [위도 혹은 경도가 없는 데이터 / 중복데이터 제외]");	// 결과 출력

		pstmt.close();		// 자원 반납
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
	
}
