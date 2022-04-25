package k35_ch09;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/** 소프트웨어코딩 심화 9강 - XML, JSON
 * 
 * XML파싱 기본 - p9
 * 
 * @author KOPO
 *
 */
public class K35_ex04_p13 {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {		// 발생한 가능한 에러 이벤트에 대해 예외 처리
		DocumentBuilder k35_docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();	// XML파일을 DOM tree로 만들어 pasrsing(해석)을 해주는 parser 객체 생성
		SimpleDateFormat k35_sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm");
		Document k35_doc = k35_docBuilder.parse(new File("C:\\KOPO\\git_tracking\\소프트웨어코딩(v2022)-자바심화\\9강\\queryDFS.xml"));	// XML파일을 file객체로 생성하여 parser객체에서 parsing하여
		NodeList k35_tag_data = k35_doc.getElementsByTagName("data");								// document 객체 내에 존재하는 "data"이라는 태그명을 갖는 요소를 모두 가져와 NodeList타입의 객체에 저장한다.
		

		String k35_tm = k35_doc.getElementsByTagName("tm").item(0).getFirstChild().getNodeValue();	// 예보 발표일시 찾아서 변수에 저장
		String k35_x = k35_doc.getElementsByTagName("x").item(0).getFirstChild().getNodeValue();	// 예보 대상 지역 x좌표 찾아서 변수에 저장
		String k35_y = k35_doc.getElementsByTagName("y").item(0).getFirstChild().getNodeValue();	// 예보 대상 지역 y좌표 찾아서 변수에 저장
		
		String[] k35_data_name_eng = {"seq", "hour" ,"day", "temp", "tmx", "tmn", "sky", "pty", "wfKor", "wfEn", "pop", "r12", "s12", "ws", "wd", "wdKor", "wdEn", "reh", "r06", "s06"};	// 찾아야하는 데이터의 태그명 배열로 저장
		String[] k35_data_name_kor = {"구분", "시간" ,"+n일", "평균 온도", "최고 온도", "최저 온도", "하늘 상태", "강수 상태", "날씨(한국어)", "날씨(영어)", "강수 확률", "12시간 예상 강수량", "12시간 예상 적설량", 	// 출력할 한글 태그명 배열로 저장
										"풍속(m/s)", "풍향", "풍향(한국어)", "풍량(영어)", "습도(%%)", "6시간 예상 강수량", "6시간 예상 적설량"};
		
		String[][] k35_data_value = new String[k35_tag_data.getLength()][k35_data_name_eng.length];	// 데이터가 2차원 배열 선언
		
		for (int k35_i = 0 ; k35_i < k35_tag_data.getLength() ; k35_i++) {							// seq의 수만큼 반복
			k35_data_value[k35_i][0] = k35_tag_data.item(k35_i).getAttributes().getNamedItem("seq").getNodeValue();
			for (int k35_j = 1 ; k35_j < k35_data_name_eng.length; k35_j++) {						// data태그 하위 태그의 개수만큼 반복
				k35_data_value[k35_i][k35_j] = k35_doc.getElementsByTagName(k35_data_name_eng[k35_j]).item(k35_i).getFirstChild().getNodeValue();	// 해당 seq번호와 배열에 저장된 해당 태그명을 통해서 데이터 값 찾아 배열에 저장
			}
		}
		
		System.out.printf("\n%s\n", "[" + k35_tm.substring(0, 4) + "년 " + k35_tm.substring(4, 6) + "월 " + k35_tm.substring(6, 8) + "일 " + k35_tm.substring(8, 10) + "시 발표]");	 // 예보 발표일시 출력
		System.out.println("x축 - " + k35_x +  " , y축 - " +  k35_y);																												 // 예보 대상좌표 출력
		
		System.out.printf("\n-------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("-------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("-----------------------------------------------------------------------------------------------------------\n");
		
		for (int k35_i = 0 ; k35_i < k35_data_name_kor.length ; k35_i++) {		// 예보 데이터의 필드명 출력
			k35_auto_format_item(20, k35_data_name_kor[k35_i]);
		}
		
		System.out.printf("\n-------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("-------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("-----------------------------------------------------------------------------------------------------------\n");
		
		for (int k35_i = 0 ; k35_i < k35_tag_data.getLength() ; k35_i++) {		// 배열에 저장된 예보 데이터를 출력
			for (int k35_j = 0 ; k35_j < k35_data_name_eng.length ; k35_j++) {
				k35_auto_format_item(20, k35_data_value[k35_i][k35_j]);
			}
			System.out.println();
		}

	}

	/**
	 * 정해진 크기의 항목 칸에 들어갈 상품 이름을 출력하고 나머지 공간에 공백을 채운다.
	 * @param width_size_item : 세팅한 항목 칸의 크기
	 * @param item			  : 항목 칸에 들어갈 상품 이름 문자열
	 */
	public static void k35_auto_format_item(int k35_max_width_item, String k35_item) {
		// 계산에 필요한 변수 선언
		int k35_sum_length = 0;																		// 한글2, 나머지 1로 앞글자부터 차례로 카운트할 때 누적 길이가 저장될 값 [byte 단위]
		int k35_i_Chr = 0;																			// 상품명에서 현재 확인하고 있는 글자의 인덱스 번호, 반복문이 끝나면 영수증에 찍히는 마지막 글자의 인덱스가 저장되어 있다.
																									// 즉 0~i번째 글자까지의 byte가 k35_max_width_item 혹은 k35_max_width_item + 1
		// 한글자씩 확인하며 상품명의 총 길이 확인(영수증 출력 범위 내, 출력 가능 길이를 초과하면 영수증에 출력되는 마지막 글자까지) & 영수증에 출력되는 마지막 글자의 인덱스 번호 확인
		for (k35_i_Chr = 0 ; k35_sum_length < k35_max_width_item && k35_i_Chr < k35_item.length() ; k35_i_Chr++) {	// 총 길이가 26(영수증 상품명이 들어갈 수 있는 최대 길이)를 넘거나 마지막 글자까지 다 확인할 때까지 반복
			String k35_chr = k35_item.substring(k35_i_Chr, k35_i_Chr + 1);							// 확인할 글자 추출
			if (k35_chr.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*"))												// 한글이면
				k35_sum_length += 2;																//	 누적 길이 +2
			 else																					// 아니면
				k35_sum_length++;																	//	 누적 길이 +1
		}
		// 남은 공백(제품명 끝 ~ 가격) 채우기
		for (int i = 0 ; i < k35_max_width_item - k35_sum_length ; i++) {							// 할당 받은 26자리 중 남은 자리가 있으면 공백으로 채운다.
			System.out.printf(" ");
		}
		
		// 상품명 출력
		if ((k35_sum_length > k35_max_width_item) && k35_item.substring(k35_i_Chr-1, k35_i_Chr).matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*"))	// 출력할 상품명 길이가 초과하는데 마지막 글자의 자리가 1byte크기일 경우 영수증 출력 글자가 깨져서 나오므로
			System.out.printf("%s ", k35_item.substring(0, k35_i_Chr - 1));							//   마지막 한글 글자 빼고 출력
		else 																						// 상관없으면
			System.out.printf("%s", k35_item.substring(0, k35_i_Chr));								//   그냥 출력 출력
	}

}
