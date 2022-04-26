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

public class Main_kkma_gyeonggi {

	public static void main(String[] args) throws IOException {
		File f = new File("C:\\KOPO\\git_tracking\\기본프로그래밍_java\\crawling\\bakery\\data\\bakery_list_gyeonggi.csv");
		BufferedReader br = new BufferedReader(new FileReader(f));
		
		File wf = new File("C:\\KOPO\\git_tracking\\기본프로그래밍_java\\crawling\\bakery\\data\\keyword_gyeonggi.csv");
		BufferedWriter bw = new BufferedWriter(new FileWriter(wf));
		
		MorphemeAnalyzer ma = new MorphemeAnalyzer();
		
		String line_read = "";
		String contents = "";
		while ((line_read = br.readLine()) != null) {
			contents = contents + line_read + ".\n";	// 모든 제목 contents문자열에 누적
		}
		
		keTest(bw, contents);							// 누적한 contents 문자열에서 키워드 추출
		
		br.close();
	}

	public static void keTest(BufferedWriter bw, String txt) throws IOException
	{
		//String[] word_exp = {"디저트", "빵집", "베이커리", "맛집", "빵", "맛", "집", "경기", "카페", "순례", "후기", "추천", "브레드", "택배", "빵맛집", "경기 빵맛집"};
		String[] word_exp = {};								// 제외할 키워드 배열 형태로 저장
		
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
	         if (Integer.parseInt(data.split(" ")[1]) >= 1) {
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
