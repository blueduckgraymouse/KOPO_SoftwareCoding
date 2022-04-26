package crawling_bakery;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Crawling_bakery_seoul {
	
	private static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	private static final String WEB_DRIVER_PATH = "C:\\chromedriver\\chromedriver.exe";

	public static void main(String[] args) throws IOException {
		try {
			System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ChromeOptions options = new ChromeOptions();
		WebDriver driver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
		JavascriptExecutor js = (JavascriptExecutor) driver;					// 스크롤 이동을 위한 js실행 객체
		
		driver.get("https://www.naver.com/");
		
		File f = new File("C:\\KOPO\\git_tracking\\기본프로그래밍_java\\crawling\\bakery\\bakery_list_seoul.csv");
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		
		bw = crawling_Bakery_seoul(bw, js, wait);
		
		bw.close();
	}
	
	
	private static BufferedWriter crawling_Bakery_seoul(BufferedWriter bw,JavascriptExecutor js, WebDriverWait wait) {
		// "서울 빵 맛집"이라고 검색창에 입력
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"query\"]"))).sendKeys("서울 빵 맛집");
				wait300ms();
				
				// 검색버튼 클릭
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"search_btn\"]/span[2]"))).click();
				wait300ms();
				
				// 'view'배너 클릭
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"lnb\"]/div[1]/div/ul/li[2]/a"))).click();
				wait300ms();
				
				// 옵션 버튼 클릭
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"snb\"]/div[1]/div/div[3]/a"))).click();
				wait300ms();
				
				// 1년 클릭
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"snb\"]/div[2]/ul/li[3]/div/div[1]/a[8]"))).click();
				wait300ms();
				
				// 블로그 클릭
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"snb\"]/div[2]/ul/li[1]/div/div/a[2]"))).click();
				wait300ms();
				
				int count = 0;
				while (true) {
					try {
						// 제목 읽기
						String title = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[3]/div[2]/div/div[1]/section/html-persist/div/more-contents/div/ul/li["
										+ (count + 1) + "]/div/div/a"))).getText();
						System.out.println(title);
						wait300ms();
						
						// 파일에 제목 한 줄 쓰기
						bw.write(title);
						bw.newLine();
						
						// 30개 단위로 수집 완료되면 스크롤 내리기
						if ((count + 1) % 30 == 0) { 
							js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
							wait300ms();
							wait300ms();
							wait300ms();
						}
					} catch (Exception e) {			// 끝에 도달해서 에러 나면 반복문 종료
						e.printStackTrace();
						break;
					}

					count++;
				}
				
				System.out.println("수집한 제목 수 : " + count);
				
				return bw;
	}


	public static void wait300ms() {
		try {
			Thread.sleep(300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
