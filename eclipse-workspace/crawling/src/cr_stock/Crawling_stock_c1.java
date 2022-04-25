package cr_stock;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Crawling_stock_c1 {
	
	//private static final String filePath = "c:\\KOPO\\git_tarcking\\기본프로그래밍_java\\Pro\\Data.csv";
	private static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	//private static final String WEB_DRIVER_PATH = "D:\\KOPO\\utility\\chromedriver_win32\\chromedriver.exe";
	private static final String WEB_DRIVER_PATH = "C:\\chromedriver\\chromedriver.exe";
	
	
	public static void main(String[] args) throws IOException {
		File f = new File("C:\\KOPO\\git_tracking\\기본프로그래밍_java\\Pro\\stock\\data_samsumgBioLogics.csv");
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
	
		try {
			System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		ChromeOptions options = new ChromeOptions();
		WebDriver driver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
		
		driver.get("https://finance.naver.com/sise/");

		// 검색창에 종목 입력
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"stock_items\"]"))).sendKeys("삼성바이오로직스");
		wait500ms();
		
		// 종목 검색 클릭
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"header\"]/div[1]/div/div[2]/form/fieldset/div/button"))).click();
		wait500ms();
		
		// 투자자별 매매동향 배너 클릭
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"content\"]/ul/li[4]/a"))).click();
		wait500ms();
		
		String a = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"content\"]/div[2]/table[1]/tbody/tr[4]/td[1]/span"))).getText();
		
		// 시세 배너에서 날짜별 시세 가져오기
		loop:
			while (true) {
				// 데이터 수집(날짜, 종가)
				String date = "";
				String price = "";
				for (int i = 0 ; i < 4 ; i++) {
					for (int j = 8 * i + 4 ; j <= 8 * ( i + 1) ; j++) {
						date = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"content\"]/div[2]/table[1]/tbody/tr[" + j + "]/td[1]/span"))).getText();
						price = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"content\"]/div[2]/table[1]/tbody/tr[" + j + "]/td[2]/span"))).getText();
						bw.write(date + "," + price.replaceAll(",", ""));
						bw.newLine();
						System.out.println(date + " // " + price);
						if(date.equals("2018.04.25")) {
							break loop;
						}
					}
				}
				
				// 페이징 처리
				int now_num_page = Integer.parseInt(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"content\"]/div[2]/table[2]/tbody/tr/td[@class=\"on\"]"))).getText());
				wait500ms();
				
				int max_num_page;
				if(now_num_page % 10 != 0) {
					max_num_page = ((now_num_page / 10) + 1) * 10;
				} else {
					max_num_page = now_num_page;
				}
				System.out.println(now_num_page + "/" + max_num_page);
				
				if (now_num_page == max_num_page) {//*[@id="content"]/div[2]/table[2]/tbody/tr/td[4]/a
					if (now_num_page == 10) {
						wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[3]/div[2]/div[2]/div[1]/div[2]/table[2]/tbody/tr/td[12]/a"))).click();
						wait500ms();
					} else {
						wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[3]/div[2]/div[2]/div[1]/div[2]/table[2]/tbody/tr/td[13]/a"))).click();
						wait500ms();
					}
				} else {
					if (now_num_page == 1) {
						wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"content\"]/div[2]/table[2]/tbody/tr/td[" + (now_num_page + 1) + "]/a"))).click();
					} else if(now_num_page < 10) { 
						wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"content\"]/div[2]/table[2]/tbody/tr/td[" + (now_num_page + 2) + "]/a"))).click();
					}
					else {
						wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"content\"]/div[2]/table[2]/tbody/tr/td[" + (now_num_page % 10 + 3) + "]/a"))).click();
					}
				}
		}

		bw.flush();
		bw.close();
	}
	
	public static void wait500ms() {
		try {
			Thread.sleep(500);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
