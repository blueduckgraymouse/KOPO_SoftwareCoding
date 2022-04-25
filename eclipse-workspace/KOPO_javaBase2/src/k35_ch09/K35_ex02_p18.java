package k35_ch09;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/** 소프트웨어코딩 심화 9강 - XML, JSON
 * 
 * JSON 만들기 - p18, p19
 * 
 * @author KOPO
 *
 */
public class K35_ex02_p18 {

	public static void main(String[] args) {
		JSONObject k35_jsonObject = new JSONObject();			// JSONObject객체 k35_jsonObject 생성
																//  - 전체 데이터를 담는 용도로 선언하였으나 그 역할로 JSONArray 사용
		JSONArray k35_datasArray = new JSONArray();				// JSONArray객체 k35_datasArray 생성
		
		// 한 명에 대한 데이터를 k35_datasArray에 저장하는 과정
		JSONObject k35_dataObject = new JSONObject();			// JSONArray객체에 담을 JSONObject객체 k35_dataObject 생성
		k35_dataObject.put("name", "나연");						// k35_dataObject 객체에 속성으로 "name", 값으로 "나연" 저장 - put()함수의 파라미터는 object타입으로 모든 class객체를 담을 수 있다.
		k35_dataObject.put("studentid", 209901);				// k35_dataObject 객체에 속성으로 "studentid", 값으로 209901" 저장
		JSONArray k35_score = new JSONArray();					// 다수의 과목 점수를 담아 k35_dataObject에 넣을 JSONArray객체 k35_score 생성
		k35_score.add(90);										// k35_score객체에 90 저장	- add()함수의 파라미터는 object타입으로 모든 class객체를 담을 수 있다.
		k35_score.add(100);										// k35_score객체에 100 저장
		k35_score.add(95);										// k35_score객체에 95 저장
		k35_dataObject.put("score", k35_score);					// k35_dataObject객체에 다수의 점수가 저장된 k35_score객체를 저장
		k35_datasArray.add(k35_dataObject);						// k35_datasArray객체에 나연 한 명에 대한 모든 데이터가 들어있는 k35_dataObject객체 저장
		
		// 위 24~32줄의 과정을 함수로 구현하여 한 줄로 해결
		k35_datasArray.add(oneRec("정연", 209902, 100, 85, 75));	// 학생의 데이터를 oneRec()메서드를 통해 JSONObject객체에 담아 k35_datasArray에 저장
		k35_datasArray.add(oneRec("모모", 209903, 90, 75, 85));
		k35_datasArray.add(oneRec("사나", 209904, 90, 85, 75));
		k35_datasArray.add(oneRec("지효", 209905, 80, 75, 85));
		k35_datasArray.add(oneRec("미나", 209906, 90, 85, 55));
		k35_datasArray.add(oneRec("다연", 209907, 70, 75, 65));
		k35_datasArray.add(oneRec("채영", 209908, 100, 75, 95));
		k35_datasArray.add(oneRec("쯔위", 209909, 80, 65, 95));
		
		try {
			FileWriter k35_file = new FileWriter("C:\\KOPO\\git_tracking\\소프트웨어코딩(v2022)-자바심화\\9강\\test.json"); // k35_datasArray에 담은 데이터로 만들 JSON파일을 FileWriter 객체로 생성
			k35_file.write(k35_datasArray.toJSONString());	// k35_datasArray담긴 데이터를 toJSONString()메서드를 통해서 문자열로 변환 후 k35_file객체에 쓴다.
			k35_file.flush();								// 버퍼에 남아있는 내용을 FileWriter객체에 쓴다.
			k35_file.close();								// FileWriter객체를 닫는다.(저장)
		} catch (IOException k35_e) {						// IO관련 에러 이벤트가 발생하면
			k35_e.printStackTrace();						// 이벤트의 stacktrace를 출력
		}
		System.out.println("JSON 만든거 : " + k35_datasArray);// k35_datasArray객체의 내용 출력
		
	}

	/** 데이터를 속성과 키의 쌍으로 맞춰 JSONObject객체에 담아 반환하는 메서드
	 * @param k35_name		: 저장할 학생명
	 * @param k35_studentid : 저장할 학생ID
	 * @param k35_kor		: 저장할 국어점수
	 * @param k35_eng		: 저장할 영어점수
	 * @param k35_mat		: 저장할 수학점수
	 * @return
	 */
	public static JSONObject oneRec(String k35_name, int k35_studentid, int k35_kor, int k35_eng, int k35_mat) {
		JSONObject k35_dataObject = new JSONObject();		// JSONArray객체에 담을 JSONObject객체 k35_dataObject 생성
		k35_dataObject.put("name", k35_name);				// k35_dataObject 객체에 속성(attribute)으로 "name", 값(value)으로 파라미터 k35_name의 값 저장
		k35_dataObject.put("studentid", k35_studentid);		// k35_dataObject 객체에 속성으로 "studentid", 값으로 파라미터 k35_studentid"의 값 저장
		JSONArray k35_score = new JSONArray();				// 다수의 과목 점수를 담아 k35_dataObject에 넣을 JSONArray객체 k35_score 생성
		k35_score.add(k35_kor);								// k35_score객체에 파라미터 k35_kor의 값 저장
		k35_score.add(k35_eng);								// k35_score객체에 파라미터 k35_eng의 값 저장
		k35_score.add(k35_mat);								// k35_score객체에 파라미터 k35_mat의 값 저장
		k35_dataObject.put("score", k35_score);				// k35_dataObject객체에 다수의 점수가 저장된 k35_score객체를 저장
		
		return k35_dataObject;								// 파라미터의 모든 페이터를 저장한 JSONObject객체 k35_dataObject를 반환
	}
}
