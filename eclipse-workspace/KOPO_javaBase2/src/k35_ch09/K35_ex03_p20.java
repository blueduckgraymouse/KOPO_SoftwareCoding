package k35_ch09;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/** 소프트웨어코딩 심화 9강 - XML, JSON
 * 
 * JSON 만들기 - p18, p19
 * 
 * @author KOPO
 *
 */
public class K35_ex03_p20 {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		JSONParser k35_parser = new JSONParser();
		
		Object k35_obj = k35_parser.parse(new FileReader("C:\\KOPO\\git_tracking\\소프트웨어코딩(v2022)-자바심화\\9강\\test.json"));
		
		JSONArray k35_array = (JSONArray) k35_obj;
		
		System.out.println("*********************************************");
		for (int k35_i = 0 ;k35_i < k35_array.size() ; k35_i++) {
			JSONObject k35_result = (JSONObject) k35_array.get(k35_i);
			System.out.println("이름 : " + k35_result.get("name"));
			System.out.println("학번 : " + k35_result.get("studentid"));
			
			JSONArray k35_score = (JSONArray) k35_result.get("score");
			long k35_kor = (long) k35_score.get(0);
			long k35_eng = (long) k35_score.get(1);
			long k35_mat = (long) k35_score.get(2);
			System.out.println("국어 : " + k35_kor);
			System.out.println("영어 : " + k35_eng);
			System.out.println("수학 : " + k35_mat);
			System.out.println("총점 : " + (k35_kor + k35_eng + k35_mat));
			System.out.println("평균 : " + (k35_kor + k35_eng + k35_mat) / 3);
			System.out.println("*********************************************");
		}
	}
}
