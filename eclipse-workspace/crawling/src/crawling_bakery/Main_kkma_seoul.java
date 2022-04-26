package crawling_bakery;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.snu.ids.kkma.index.Keyword;
import org.snu.ids.kkma.index.KeywordExtractor;
import org.snu.ids.kkma.index.KeywordList;
import org.snu.ids.kkma.ma.MExpression;
import org.snu.ids.kkma.ma.MorphemeAnalyzer;
import org.snu.ids.kkma.ma.Sentence;
import org.snu.ids.kkma.util.Timer;

public class Main_kkma_seoul {

	public static void main(String[] args) throws IOException {
		File rf = new File("C:\\KOPO\\git_tracking\\기본프로그래밍_java\\crawling\\bakery\\data\\bakery_list_seoul.csv");
		BufferedReader bf = new BufferedReader(new FileReader(rf));
		File wf = new File("C:\\KOPO\\git_tracking\\기본프로그래밍_java\\crawling\\bakery\\data\\keyword_seoul.csv");
		BufferedWriter bw = new BufferedWriter(new FileWriter(wf));
		
		MorphemeAnalyzer ma = new MorphemeAnalyzer();
		
		String line_read = "";
		String contents = "";
		while ((line_read = bf.readLine()) != null) {	// 모든 제목 contents문자열에 누적
			contents = contents + line_read + ".\n";
		}
		
		count_kewords(bw, contents);	// 누적한 contents 문자열에서 키워드 추출
		
		bf.close();
	}
	
	public static void count_kewords(BufferedWriter bw, String txt) throws IOException
	{
		//String[] word_exp = {"맛", "맛집", "빵", "빵지순례", "순례", "베이커리", "집", "서울", "카페", "빵집", "빵맛집"};
		String[] word_exp = {};									// 제외할 키워드 배열 형태로 저장
		
		ArrayList<String> list = new ArrayList<String>();
		
		// 키워드와 횟수 추출 & ArrayList객체에 저장
		KeywordExtractor ke = new KeywordExtractor();
		KeywordList kl = ke.extractKeyword(txt, true);
		for (int i = 0; i < kl.size(); i++) {
			Keyword kwrd = kl.get(i);
			list.add(kwrd.getString() + " " + kwrd.getCnt());
		}
		
		// 횟수에 따라 내림차순으로 정렬
	    Collections.sort(list, new UserSort());
	    
	    // 출력
	    Iterator iterator = list.iterator();
	    while (iterator.hasNext()) {
	         String data = (String)iterator.next();
	         if (Integer.parseInt(data.split(" ")[1]) >= 1) {	// 최수 횟수 설정
	        	 boolean d = false;
	        	 for (String word : word_exp) {
	        		 if (word.equals(data.split(" ")[0])) {
	        			 d = true;
	        			 break;
	        		 }
	        	 }
	        	 if (d == false) {
	        		 bw.write(data.split(" ")[0] + "," + data.split(" ")[1]);
	        		 bw.newLine();
	        		 System.out.print(data.split(" ")[0] + " // " + data.split(" ")[1] + "\n");
	        	 }
	        }
	    }
	}
	
}
