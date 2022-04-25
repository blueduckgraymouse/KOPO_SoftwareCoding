package k35_ch09;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/** 소프트웨어코딩 심화 9강 - XML, JSON
 * 
 * XML파싱 기본 - p9
 * 
 * @author KOPO
 *
 */
public class K35_ex01_p9 {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {		// 발생한 가능한 에러 이벤트에 대해 예외 처리
		DocumentBuilder k35_docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();	// XML파일을 DOM tree로 만들어 pasrsing(해석)을 해주는 parser 객체 생성
		
		Document k35_doc = k35_docBuilder.parse(new File("C:\\KOPO\\git_tracking\\eclipse-workspace\\KOPO_javaBase2\\src\\k35_ch09\\score.xml"));	// XML파일을 file객체로 생성하여 parser객체에서 parsing하여
																																					// DOM의 document 클래스 타입의 인터페이스 객체로 초기화
		Element k35_root = k35_doc.getDocumentElement();						// dom객체의 tree에서 최상위 요소(root element) 노드를 가져온다. 따라서 k35_root 하위 요소로 k35_doc의 모든 요소를 가져온다.
		
		NodeList k35_tag_name = k35_doc.getElementsByTagName("name");			// document 객체 내에 존재하는 "name"이라는 태그명을 갖는 요소를 모두 가져와 NodeList타입의 객체에 저장한다.
		NodeList k35_tag_studentid = k35_doc.getElementsByTagName("studentid");	// document 객체 내에 존재하는 "studentid"이라는 태그명을 갖는 요소를 모두 가져와 NodeList타입의 객체에 저장한다.
		NodeList k35_tag_kor = k35_doc.getElementsByTagName("kor");				// document 객체 내에 존재하는 "kor"이라는 태그명을 갖는 요소를 모두 가져와 NodeList타입의 객체에 저장한다.
		NodeList k35_tag_eng = k35_doc.getElementsByTagName("eng");				// document 객체 내에 존재하는 "eng"이라는 태그명을 갖는 요소를 모두 가져와 NodeList타입의 객체에 저장한다.
		NodeList k35_tag_mat = k35_doc.getElementsByTagName("mat");				// document 객체 내에 존재하는 "mat"이라는 태그명을 갖는 요소를 모두 가져와 NodeList타입의 객체에 저장한다.
		
//		NodeList k35_tag_name = k35_root.getElementsByTagName("name");
//		NodeList k35_tag_studentid = k35_root.getElementsByTagName("studentid");
//		NodeList k35_tag_kor = k35_root.getElementsByTagName("kor");
//		NodeList k35_tag_eng = k35_root.getElementsByTagName("eng");
//		NodeList k35_tag_mat = k35_root.getElementsByTagName("mat");
		
		System.out.printf("************************************\n");
		for (int k35_i = 0 ; k35_i < k35_tag_name.getLength() ; k35_i++) {									// k35_tag_name에 담긴 node의 개수만큼 반복하며 i를 증가
			System.out.printf("이름 : %s\n", k35_tag_name.item(k35_i).getFirstChild().getNodeValue());		// k35_tag_name라는 nodelist 내 i번째 node 밑의 하위 노드 중 첫번째 노드의 value를 가져와 출력
			System.out.printf("학번 : %s\n", k35_tag_studentid.item(k35_i).getFirstChild().getNodeValue());	// k35_tag_studentid라는 nodelist 내 i번째 node 밑의 하위 노드 중 첫번째 노드의 value를 가져와 출력
			System.out.printf("국어 : %s\n", k35_tag_kor.item(k35_i).getFirstChild().getNodeValue());			// k35_tag_kor라는 nodelist 내 i번째 node 밑의 하위 노드 중 첫번째 노드의 value를 가져와 출력
			System.out.printf("영어 : %s\n", k35_tag_eng.item(k35_i).getFirstChild().getNodeValue());			// k35_tag_eng라는 nodelist 내 i번째 node 밑의 하위 노드 중 첫번째 노드의 value를 가져와 출력
			System.out.printf("수학 : %s\n", k35_tag_mat.item(k35_i).getFirstChild().getNodeValue());			// k35_tag_mat라는 nodelist 내 i번째 node 밑의 하위 노드 중 첫번째 노드의 value를 가져와 출력
			System.out.printf("************************************\n");
		}
		
	}

}
