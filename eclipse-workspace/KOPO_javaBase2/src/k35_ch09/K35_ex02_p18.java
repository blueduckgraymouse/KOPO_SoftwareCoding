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
		JSONObject k35_jsonObject = new JSONObject();
		JSONArray k35_datasArray = new JSONArray();
		
		JSONObject k35_dataObject = new JSONObject();
		k35_dataObject.put("name", "나연");
		k35_dataObject.put("studentid", 209901);
		
		JSONArray k35_score = new JSONArray();
		k35_score.add(90);
		k35_score.add(100);
		k35_score.add(95);
		k35_dataObject.put("score", k35_score);
		k35_datasArray.add(k35_dataObject);
		
		k35_datasArray.add(oneRec("정연", 209902, 100, 85, 75));
		k35_datasArray.add(oneRec("모모", 209903, 90, 75, 85));
		k35_datasArray.add(oneRec("사나", 209904, 90, 85, 75));
		k35_datasArray.add(oneRec("지효", 209905, 80, 75, 85));
		k35_datasArray.add(oneRec("미나", 209906, 90, 85, 55));
		k35_datasArray.add(oneRec("다연", 209907, 70, 75, 65));
		k35_datasArray.add(oneRec("채영", 209908, 100, 75, 95));
		k35_datasArray.add(oneRec("쯔위", 209909, 80, 65, 95));
		
		try {
			FileWriter k35_file = new FileWriter("C:\\KOPO\\git_tracking\\소프트웨어코딩(v2022)-자바심화\\9강\\test.json");
			k35_file.write(k35_datasArray.toJSONString());
			k35_file.flush();
			k35_file.close();
		} catch(IOException k35_e) {
			k35_e.printStackTrace();
		}
		System.out.println("JSON 만든거 : " + k35_datasArray);
		
	}

	public static JSONObject oneRec(String k35_name, int k35_studentid, int k35_kor, int k35_eng, int k35_mat) {
		JSONObject k35_dataObject = new JSONObject();
		k35_dataObject.put("name", k35_name);
		k35_dataObject.put("studentid", k35_studentid);
		JSONArray k35_score = new JSONArray();
		k35_score.add(k35_kor);
		k35_score.add(k35_eng);
		k35_score.add(k35_mat);
		k35_dataObject.put("score", k35_score);
		
		return k35_dataObject;
	}
}
