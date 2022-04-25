package crawling_stock;

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
		
		// 종목 검색 클릭
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"header\"]/div[1]/div/div[2]/form/fieldset/div/button"))).click();
		
		// 시세 배너 클릭
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"content\"]/ul/li[2]/a"))).click();
		
		// 시세 배너에서 날짜별 시세 가져오기
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"content\"]/ul/li[2]/a"))).click();
		loop:
			while (true) {
				String date = "";
				String price = "";
				for (int i = 3 ; i <= 7 ; i++) {
					date = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/table[1]/tbody/tr[" + i + "]/td[1]/span"))).getText();
					price = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/table[1]/tbody/tr[" + i + "]/td[2]/span"))).getText();
					bw.write(date + "," + price.replaceAll(",", ""));
					bw.newLine();
					if(date.equals("2018.04.25")) {
						break loop;
					}
				}
				for (int i = 11 ; i <= 15 ; i++) {
					date = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/table[1]/tbody/tr[" + i + "]/td[1]/span"))).getText();
					price = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/table[1]/tbody/tr[" + i + "]/td[2]/span"))).getText();
					bw.write(date + "," + price.replaceAll(",", ""));
					bw.newLine();
					if(date.equals("2018.04.25")) {
						break loop;
					}
				}
				
				int now_num_page = Integer.parseInt(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/table[2]/tbody/tr/td[@class=\"ON\"]"))).getText());
				//int min_num_page = (now_num_page / 10) * 10 + 1;
				int max_num_page = ((now_num_page / 10) + 1) * 10;
				
				if (now_num_page == max_num_page) {
					if (now_num_page == 10) {
						wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/table[2]/tbody/tr/td[11]/a"))).click();
					} else {
						wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/table[2]/tbody/tr/td[13]/a"))).click();
					}
	//				
	//				/html/body/table[2]/tbody/tr/td[1]/a - 1
	//				/html/body/table[2]/tbody/tr/td[10]/a - 10
	//				/html/body/table[2]/tbody/tr/td[11]/a - 다음
	//				/html/body/table[2]/tbody/tr/td[12]/a - 맨뒤
	//				
	//				
	//				
	//				/html/body/table[2]/tbody/tr/td[1]/a - 맨앞
	//				/html/body/table[2]/tbody/tr/td[2]/a - 이전
	//				/html/body/table[2]/tbody/tr/td[3]/a - 11
	//				/html/body/table[2]/tbody/tr/td[12]/a - 20
	//				/html/body/table[2]/tbody/tr/td[13]/a - 다음
	//				/html/body/table[2]/tbody/tr/td[13]/a - 맨뒤
				}
			
		}
		
		
		
		
//		/html/body/table[1]/tbody/tr[3]/td[1]/span
//		~
//		/html/body/table[1]/tbody/tr[7]/td[1]/span
//		
//		/html/body/table[1]/tbody/tr[11]/td[1]/span
//		~
//		/html/body/table[1]/tbody/tr[15]/td[1]/span

		
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"region_filter\"]/div/a/span[3]"))).click();
		
		//String guName = driver.findElement(By.xpath("//*[@id=\"region_filter\"]/div/div/div[1]/div/a[2]")).getText();
		
		
		
		
		bw.flush();
		bw.close();
	}

}
