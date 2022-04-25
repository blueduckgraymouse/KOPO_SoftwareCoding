package crawling_bakery;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
		File f = new File("C:\\KOPO\\git_tracking\\기본프로그래밍_java\\crawling\\bakery\\bakery_list_seoul.csv");
		BufferedReader bf = new BufferedReader(new FileReader(f));
		
		MorphemeAnalyzer ma = new MorphemeAnalyzer();
		
		String line_read = "";
		String contents = "";
		while((line_read = bf.readLine()) != null) {
			contents = contents + line_read + ".\n";
		}
		
		keTest(contents);
		//maTest(contents);
		
		bf.close();
	}
	

	public static void keTest(String txt)
	{
		String[] word_exp = {};
		
		ArrayList<String> list = new ArrayList<String>();
		
		KeywordExtractor ke = new KeywordExtractor();
		KeywordList kl = ke.extractKeyword(txt, true);
		for( int i = 0; i < kl.size(); i++ ) {
			Keyword kwrd = kl.get(i);
			list.add(kwrd.getString() + " " + kwrd.getCnt());
		}
		
	    //Collections.sort(list, new UserSort());
	    
	    Iterator iterator = list.iterator();
	    while (iterator.hasNext()) {
	         String data = (String)iterator.next();
	         if (Integer.parseInt(data.split(" ")[1]) >= 0) {
	        	 boolean d = false;
	        	 for (String word : word_exp) {
	        		 if (word.contains(data.split(" ")[0])) {
	        			 d = true;
	        			 break;
	        		 }
	        	 }
	        	 if (d == false) {
	        		 System.out.print(data.split(" ")[0] + " // " + data.split(" ")[1] + "\n");
	        	 }
	         }
	    }
	}
	
	public static void maTest(String txt)
	{
		System.out.println(txt);
		try {
			MorphemeAnalyzer ma = new MorphemeAnalyzer();
			ma.createLogger(null);
			Timer timer = new Timer();
			timer.start();
			List<MExpression> ret = ma.analyze(txt);
			timer.stop();
			timer.printMsg("Time");
			

			ret = ma.postProcess(ret);								// - 띄어쓰기 자동
//			Iterator iterator = ret.iterator();
//			while(iterator.hasNext()) {
//				System.out.println(iterator.next());
//			}
//			
			ret = ma.leaveJustBest(ret);							// 날리는게 나은듯.

			List<Sentence> stl = ma.divideToSentences(ret);	
			for( int i = 0; i < stl.size(); i++ ) {
				Sentence st = stl.get(i);
				System.out.println(i + "=============================================  " + st.getSentence());
				for( int j = 0; j < st.size(); j++ ) {
					System.out.println(st.get(j));
				}
			}

			ma.closeLogger();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
