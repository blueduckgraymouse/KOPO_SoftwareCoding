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
	final static int startGuIndex = 13;
	
	//private static final String filePath = "c:\\KOPO\\git_tarcking\\기본프로그래밍_java\\Pro\\Data.csv";
	private static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	//private static final String WEB_DRIVER_PATH = "D:\\KOPO\\utility\\chromedriver_win32\\chromedriver.exe";
	private static final String WEB_DRIVER_PATH = "C:\\chromedriver\\chromedriver.exe";

	
	public static void main(String[] args) {
	
		try {
			System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		ChromeOptions options = new ChromeOptions();
		WebDriver driver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
		
		driver.get("https://new.land.naver.com/complexes?ms=37.566427,126.977872,13&a=APT:ABYG:JGC&e=RETAIL");
		
		// 광역시 배너 클릭
		clickSelectionCity(wait);
		
		// 광역시 중 서울시 선택 -> 자동으로 구 선택을 넘어감
		selectSeoul(wait);

		// 총 구의 개수 확인
		int guSize = checkRegionSize(driver);
		
		// 구 개수 만큼 반복
		for(int i = 13 ; i < guSize ; i++) {
			// 서울인지 확인 다르면 처리
			// 추가 예정
			
			// 구 선택 -> 자동으로 동 선택으로 넘아감
			selectGu(wait, i);
			String guName = getGuName(driver);
			
			// 총 동의 개수 확인, 26
			int dongSize = checkRegionSize(driver);
			
			// 동 개수 만큼 반복
			for(int j = 1 ; j <= dongSize ; j++) {
				// 두번째 동부터
				if(j != 1) {
					// 지역 확인 후 바꼈으면 재설정
					compareAndRearrangeGu(driver, wait, guName, i);
				}
				
				// 동 선택 -> 자동으로 단지 선택으로 넘어감
				selectDong(wait, j);
				String dongName = getDongName(driver);
				
				// 총 단지의 개수 확인
				int complexSize = checkComplexSize(driver);
				
				// 단지 개수만큼 반복
				for(int k = 1 ; k <= complexSize ; k++) {
					// 두번째 단지부터
					if (k != 1) {
						// 지역 확인 후 바꼈으면 재설정
						compareAndRearrangeGu(driver, wait, guName, i);
						compareAndRearrangeDong(driver, wait, dongName, j);
					}
					
					// 단지 목록이 닫혀잇으면
					if (driver.findElement(By.xpath("//*[@id=\"region_filter\"]/div/div")).getAttribute("aria-hidden").equals("true")) {
						// 단지 목록 열기
						openComplexSelection(wait);
					}
					
					// 단지 선택 - 자동으로 단지 정보로 펼쳐짐
					selectComplex(wait, k);
					
					// 부동산 접속 후 첫 학군 정보 접근이면
//					if (i == startGuIndex && j == 1 && k == 1) {
//						// 학군정보 배너 클릭 - 이후는 유지 된다.
						clickSchoolDistrict(driver, wait);
//					}
					// 지역정보와 아파트명도 수집 예정
					
					// 초등학교까지 거리 수집
					String schoolData = collectDistance(wait);
					String distance = schoolData.split("도보로")[1].split("분")[0];
					System.out.println(guName + " - " + dongName + " - " + distance);
					
					// 현재 단지 닫기
					closeComplexInformation(wait);
				}
			}
			
		}

		//bw.write("sadf");
		//bw.flush();
		//bw.close();
	}
	
//	private static int checkRegionSize(WebDriver driver) {
//		return driver.findElements(By.xpath("//*[@class=\"area_item\"]")).size();
//	}
	
	private static void openComplexSelection(WebDriverWait wait) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/section/div[2]/div[2]/div[1]/div/div/a/span[4]"))).click();
		wait500ms();
	}

	private static void compareAndRearrangeDong(WebDriver driver, WebDriverWait wait, String dongName, int j) {
		String CurrentDongName = driver.findElement(By.xpath("//*[@id=\"region_filter\"]/div/a/span[3]")).getText();
		if (!dongName.equals(CurrentDongName)) {
			openDongSelection(wait);
			selectDong(wait, j);
		}
	}

	private static void openDongSelection(WebDriverWait wait) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"region_filter\"]/div/a/span[3]"))).click();
		wait500ms();
	}

	private static void compareAndRearrangeGu(WebDriver driver, WebDriverWait wait, String guName, int i) {
		String CurrentGuName = driver.findElement(By.xpath("//*[@id=\"region_filter\"]/div/a/span[2]")).getText();
		if (!guName.equals(CurrentGuName)) {
			openGuSelection(wait);
			selectGu(wait, i);
		}
	}

	private static void openGuSelection(WebDriverWait wait) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"region_filter\"]/div/a/span[2]"))).click();
		wait500ms();
	}

	private static String getDongName(WebDriver driver) {
		String dongName = driver.findElement(By.xpath("//*[@id=\"region_filter\"]/div/div/div[1]/div/a[3]")).getText();
		wait500ms();
		return dongName;
	}

	private static String getGuName(WebDriver driver) {
		String guName = driver.findElement(By.xpath("//*[@id=\"region_filter\"]/div/div/div[1]/div/a[2]")).getText();
		wait500ms();
		return guName;
	}

	private static void closeComplexInformation(WebDriverWait wait) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/section/div[2]/div[2]/div/button"))).click();
		wait500ms();
	}

	private static String collectDistance(WebDriverWait wait) {
		String distance = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"detailContents5\"]/div/div[1]"))).getText();
		wait500ms();
		return distance;
	}

	private static void clickSchoolDistrict(WebDriver driver, WebDriverWait wait) {
		int size = driver.findElements(By.xpath("/html/body/div[2]/div/section/div[2]/div[2]/div/div[2]/div[2]/div/div/a")).size();
		for(int i = 1 ; i <= size ; i++) {
			String content = driver.findElement(By.xpath("/html/body/div[2]/div/section/div[2]/div[2]/div/div[2]/div[2]/div/div/a[" + i + "]/span")).getText();
			if(content.equals("학군정보")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/section/div[2]/div[2]/div/div[2]/div[2]/div/div/a[" + i + "]"))).click();
				wait500ms();
				break;
			}
		}
	}

	private static void selectComplex(WebDriverWait wait, int k) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/section/div[2]/div/div[1]/div/div/div/div[3]/ul/li[" + k + "]/a"))).click();
		wait500ms();
	}
	
	private static int checkComplexSize(WebDriver driver) {
		return driver.findElements(By.xpath("//*[@class=\"complex_item\"]")).size();
	}
	
	private static void selectDong(WebDriverWait wait, int j) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/section/div[2]/div/div[1]/div/div/div/div[2]/ul/li[" + j + "]/label"))).click();
		wait500ms();
	}

	private static int checkRegionSize(WebDriver driver) {
		return driver.findElements(By.xpath("//*[@class=\"area_item\"]")).size();
	}

	private static void clickSelectionCity(WebDriverWait wait) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/section/div[2]/div/div[1]/div/div/a/span[1]"))).click();
		wait500ms();
	}

	private static void selectSeoul(WebDriverWait wait) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/section/div[2]/div/div[1]/div/div/div/div[2]/ul/li[1]/label"))).click();
		wait500ms();
	}

	public static void selectGu(WebDriverWait wait, int guIndex) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/section/div[2]/div/div[1]/div/div/div/div[2]/ul/li[" + guIndex + "]/label"))).click();
		wait500ms();
	}
	
	public static void wait500ms() {
		try {
			Thread.sleep(500);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}