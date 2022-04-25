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
public class K35_ex03_p19 {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {		// 발생한 가능한 에러 이벤트에 대해 예외 처리
		JSONParser k35_parser = new JSONParser();							// JSON파일을 해석하기 위한 parser인 JSONParser객체 생성
		
		Object k35_obj = k35_parser.parse(new FileReader("C:\\KOPO\\git_tracking\\소프트웨어코딩(v2022)-자바심화\\9강\\test.json"));	// 읽을 JSON파일을 FileReader객체로 생성하여
																																// JSONParser객체의 parse()메서드를 통해
																																// parsing한 결과를 Object객체로 생성
		JSONArray k35_array = (JSONArray) k35_obj;														// parsing한 object객체를 JSONArray로 형변환
		
		System.out.println("*********************************************");
		for (int k35_i = 0 ;k35_i < k35_array.size() ; k35_i++) {										// JSONArray객체에 담긴 요소 수(트와이스 맴버 수)만큼 i 반복
			JSONObject k35_result = (JSONObject) k35_array.get(k35_i);									// JSONArray객체에서 한 명에 대한 정보인 JSONObject객체를 가져와 변수로 저장
			System.out.println("이름 : " + k35_result.get("name"));										// JSONObject객체에 속성명 "name"에 해당하는 값 가져와 출력
			System.out.println("학번 : " + k35_result.get("studentid"));									// JSONObject객체에 속성명 "studentid"에 해당하는 값 가져와 출력
			
			JSONArray k35_score = (JSONArray) k35_result.get("score");									// JSONObject객체에 속성명 "score"에 해당하는 JSONArray객체 가져와 변수로 저장
			long k35_kor = (long) k35_score.get(0);														// 점수 데이터가 담긴 JSONArray객체에서 첫번째 데이터인 국어점수 가져와 저장
			long k35_eng = (long) k35_score.get(1);														// 점수 데이터가 담긴 JSONArray객체에서 두번째 데이터인 국어점수 가져와 저장
			long k35_mat = (long) k35_score.get(2);														// 점수 데이터가 담긴 JSONArray객체에서 세번째 데이터인 국어점수 가져와 저장
			System.out.println("국어 : " + k35_kor);
			System.out.println("영어 : " + k35_eng);
			System.out.println("수학 : " + k35_mat);
			System.out.println("총점 : " + (k35_kor + k35_eng + k35_mat));
			System.out.println("평균 : " + (k35_kor + k35_eng + k35_mat) / 3);
			System.out.println("*********************************************");
		}
	}
}
