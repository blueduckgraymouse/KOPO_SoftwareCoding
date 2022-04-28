package crawling_distance;

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

public class CrawlingDistanceElementarySchool_final  {
	final static int startGuIndex = 13;
	
	private static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	private static final String WEB_DRIVER_PATH = "C:\\chromedriver\\chromedriver.exe";

	public static void main(String[] args) throws IOException {
		File f = new File("C:\\KOPO\\git_tracking\\기본프로그래밍_java\\Pro\\schooldistance\\data.csv");
		BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
	
		try {
			System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		ChromeOptions options = new ChromeOptions();
		WebDriver driver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
		
		driver.get("https://new.land.naver.com/complexes?ms=37.566427,126.977872,13&a=APT:ABYG:JGC&e=RETAIL");
		
//		int[] target = {23};
		
		try {
			// 광역시 배너 클릭
			clickSelectionCity(wait);
			
			// 광역시 중 서울시 선택 -> 자동으로 구 선택을 넘어감
			selectSeoul(wait);
			
			// 총 구의 개수 확인
			int guSize = checkRegionSize(driver);
			
			// 구 개수 만큼 반복
			for (int i = 1 ; i <= guSize ; i++) {
//			for (int ii = 0 ; ii < target.length ; ii++) {
//				int i = target[ii];
				// 서울인지 확인 다르면 처리
				// 추가 예정
				
				if (driver.findElement(By.xpath("//*[@id=\"region_filter\"]/div/div")).getAttribute("aria-hidden").equals("true")) {
					// 동 목록 열기
					openGuSelection(wait);
				}

				// 구 선택 -> 자동으로 동 선택으로 넘아감
				selectGu(wait, i);
				String guName = getGuName(driver);
				
				// 총 동의 개수 확인, 26
				int dongSize = checkRegionSize(driver);
				
				// 동 개수 만큼 반복
				for (int j = 1 ; j <= dongSize ; j++) {
//				for (int j = 1 ; j <= dongSize ; j++) {
//					if(i==23 && j==1) {
//						j = 86;
//					}
					
					// 동 선택 -> 자동으로 단지 선택으로 넘어감
					// 단지 목록이 닫혀잇으면
					if (driver.findElement(By.xpath("//*[@id=\"region_filter\"]/div/div")).getAttribute("aria-hidden").equals("true")) {
						// 동 목록 열기
						openDongSelection(wait);
					}

					selectDong(wait, j);
					
					
					// 동이름 확인
					String dongName = getDongName(driver);
						
					// 총 단지의 개수 확인
					int complexSize = checkComplexSize(driver);
					
					if (complexSize == 0) {
						closeSelection(wait);
					}
					
					// 단지 개수만큼 반복
					for(int k = 1 ; k <= complexSize ; k++) {
//					for (int k = 1 ; k <= complexSize ; k++) {
//						if(i==23 && j==86 && k==1) {
//							k = 1;
//						}
				
						// 단지 목록이 닫혀잇으면
						if (driver.findElement(By.xpath("//*[@id=\"region_filter\"]/div/div")).getAttribute("aria-hidden").equals("true")) {
							// 단지 목록 열기
							openCitySelection(wait);
						}
						System.out.println(complexSize + " / " + k);
//						if(!(k == 1 || i==23 && j==86 && k==1)) {
						if(!(k == 1)) {
							selectSeoul(wait);
							
							selectGu(wait, i);
						
							selectDong(wait, j);
						}
						
						// 단지 선택 - 자동으로 단지 정보로 펼쳐짐
						selectComplex(wait, k);
						
						// 아파트 단지명 수집
						String complexName = "";
						try {
							complexName = collectComplexName(wait);
						} catch(Exception e) {
							complexName = "정보 없음";
						}
						
						// 아파트 매매가 범위 수집
						String priceRange = "";
						try {
							priceRange = collectComplexPriceRange(wait);
						} catch(Exception e) {
							priceRange = "정보 없음";
						}
						
						// 아파트 단지 규모 수집
						String complexScale = "";
						try {
							complexScale = collectComplexScale(wait);
						} catch(Exception e) {
							complexScale = "정보 없음";
						}
						
						// 아파트 단지 배너 클릭
						clickSchoolDistrict(driver, wait);
						
						// 초등학교까지 거리 수집
						String distance = "";
						try {
							String schoolData = collectDistance(wait);
							distance = schoolData.split("도보로")[1].split("분")[0];
						} catch(Exception e) {
							distance = "정보 없음";
						}

						bw.write(guName + "," + dongName + "," + complexName.replaceAll(",", "") + "," + priceRange.replaceAll(",", "") + ",");
						
						String[] scales = complexScale.split("\n");
						int count = 0;
						for (int n = 0 ; n < scales.length ; n++) {
							if (scales[n].equals("세대수")) {
								bw.write(scales[n + 1] + ",");
								count++;
							}
						}
						
						for (int n = 0 ; n < scales.length ; n++) {
							if (scales[n].equals("동수")) {
								bw.write(scales[n + 1] + ",");
								count++;
							}
						}
						
						for (int n = 0 ; n < scales.length ; n++) {
							if (scales[n].equals("면적")) {
								bw.write(scales[n + 1] + ",");
								count++;
							}
						}
						for (int n = 0 ; n < 3-count ; n++) {
							bw.write(",");
						}
						
						System.out.println(guName + "("  + guSize + "/" + i + ")" + " - " + dongName + "("  + dongSize + "/" + j + ")" +  " - " + complexName + "(" + complexSize + "/" + k + ")" +  " - " + priceRange + " - " + complexScale + " - " + distance);
						

						
						bw.write(distance.trim());
						bw.newLine();
						
						// 현재 단지 닫기
						closeComplexInformation(wait);
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		bw.flush();
		bw.close();
	}
	
	private static void closeSelection(WebDriverWait wait) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"region_filter\"]/div/a/span[3]"))).click();
		wait500ms();
	}

	private static String collectComplexScale(WebDriverWait wait) {
		return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"summaryInfo\"]/dl"))).getText();
	}
	
	private static String collectComplexPriceRange(WebDriverWait wait) {
		return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"summaryInfo\"]/div[2]/div[1]/div/dl[1]/dd"))).getText();
	}
	
	private static String collectComplexName(WebDriverWait wait) {
		return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"complexTitle\"]"))).getText();
	}

	private static void openDongSelection(WebDriverWait wait) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"region_filter\"]/div/a/span[3]"))).click();
		wait500ms();
	}

	private static void openGuSelection(WebDriverWait wait) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"region_filter\"]/div/a/span[2]"))).click();
		wait500ms();
	}
	
	private static void openCitySelection(WebDriverWait wait) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"region_filter\"]/div/a/span[1]"))).click();
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
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"region_filter\"]/div/div/div[3]/ul/li[" + k + "]/a"))).click();
		wait500ms();
	}
	
	private static int checkComplexSize(WebDriver driver) {
		
		return driver.findElements(By.xpath("//*[@id=\"region_filter\"]/div/div/div[3]/ul/li")).size();
	}
	
	private static void selectDong(WebDriverWait wait, int j) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/section/div[2]/div/div[1]/div/div/div/div[2]/ul/li[" + j + "]/label"))).click();
		wait500ms();
	}

	private static int checkRegionSize(WebDriver driver) {
		return driver.findElements(By.xpath("//*[@class=\"area_item\"]")).size();
	}

	private static void clickSelectionCity(WebDriverWait wait) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"region_filter\"]/div/a/span[1]"))).click();
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