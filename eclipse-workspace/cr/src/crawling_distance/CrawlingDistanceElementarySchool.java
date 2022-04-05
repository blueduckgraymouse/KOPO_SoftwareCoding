package crawling_distance;

//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CrawlingDistanceElementarySchool {
	//private static final String filePath = "c:\\KOPO\\git_tarcking\\기본프로그래밍_java\\Pro\\Data.csv";
	private static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	private static final String WEB_DRIVER_PATH = "C:\\chromedriver\\chromedriver.exe";

	public static void main(String[] args) {
		//File CSVfile = null;
		//BufferedWriter bw = null;
		//String NewLine = System.lineSeparator();
		
		//CSVfile = new File(filePath);
		//bw = new BufferedWriter(new FileWriter(CSVfile));
		
		try {
			System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		ChromeOptions options = new ChromeOptions();
		WebDriver driver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
		
		driver.get("https://new.land.naver.com/complexes?ms=37.566427,126.977872,13&a=APT:ABYG:JGC&e=RETAIL");
		
		// 광역시 선택 클릭
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/section/div[2]/div/div[1]/div/div/a/span[1]"))).click();
		try {
			Thread.sleep(100);
		} catch(Exception e) {
			e.printStackTrace();
		}
		// 광역시 중 서울시 선택
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/section/div[2]/div/div[1]/div/div/div/div[2]/ul/li[1]/label"))).click();
		wait100ms();
		
		
		// 자동으로 중구 선택을 넘어감, 마포구 선택
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/section/div[2]/div/div[1]/div/div/div/div[2]/ul/li[13]/label"))).click();
		String now_gu = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/section/div[2]/div/div[1]/div/div/div/div[2]/ul/li[13]/label"))).getText();
		wait100ms();
		
		// 자동으로 동 선택으로 넘어감, 공덕동 선택
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/section/div[2]/div/div[1]/div/div/div/div[2]/ul/li[1]/label"))).click();
		String now_dong = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/section/div[2]/div/div[1]/div/div/div/div[2]/ul/li[1]/label"))).getText();
		wait100ms();
		
		// 자동으로 단지선택으로 넘어감, 공덕Sk 선택
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/section/div[2]/div/div[1]/div/div/div/div[3]/ul/li[1]/a"))).click();
		wait100ms();
		
		// 단지 정보 펼쳐짐, 학군정보 배너 선택
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/section/div[2]/div[2]/div/div[2]/div[2]/div/div/a[4]/span"))).click();

		// 한국정보 펼쳐짐, 초등학교까지 거리 수집
		String distance = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/section/div[2]/div[2]/div/div[2]/div[6]/div/div[1]/div[2]/div[2]/div[2]"))).getText();
		
		System.out.println(distance);
		
		// 현재 단지 닫기
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/section/div[2]/div[2]/div/button"))).click();
		
		// 닫앗는데 지도 움직임?으로 다른 동으로 동네 설정됬을 수도.
		// 목표하는 구와 돋이 맞는지 확인.
		
		
		
		
		
		
		
		//bw.write("sadf");
		//bw.flush();
		//bw.close();
	}
	
	
	
	
	public static void wait100ms() {
		try {
			Thread.sleep(100);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
