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

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilder k35_docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		
		Document k35_doc = k35_docBuilder.parse(new File("C:\\KOPO\\git_tracking\\eclipse-workspace\\KOPO_javaBase2\\src\\k35_ch09\\score.xml"));
		
		Element k35_root = k35_doc.getDocumentElement();
		
		NodeList k35_tag_name = k35_doc.getElementsByTagName("name");
		NodeList k35_tag_studentid = k35_doc.getElementsByTagName("studentid");
		NodeList k35_tag_kor = k35_doc.getElementsByTagName("kor");
		NodeList k35_tag_eng = k35_doc.getElementsByTagName("eng");
		NodeList k35_tag_mat = k35_doc.getElementsByTagName("mat");
		
		System.out.printf("************************************\n");
		for (int k35_i = 0 ; k35_i < k35_tag_name.getLength() ; k35_i++) {
			System.out.printf("이름 : %s\n", k35_tag_name.item(k35_i).getFirstChild().getNodeValue());
			System.out.printf("학번 : %s\n", k35_tag_studentid.item(k35_i).getFirstChild().getNodeValue());
			System.out.printf("국어 : %s\n", k35_tag_kor.item(k35_i).getFirstChild().getNodeValue());
			System.out.printf("영어 : %s\n", k35_tag_eng.item(k35_i).getFirstChild().getNodeValue());
			System.out.printf("수학 : %s\n", k35_tag_mat.item(k35_i).getFirstChild().getNodeValue());
			System.out.printf("************************************\n");
		}
		
	}

}
