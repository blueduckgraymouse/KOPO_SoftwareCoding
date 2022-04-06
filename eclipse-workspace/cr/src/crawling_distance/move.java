package crawling_distance;

//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//importing the package
import org.openqa.selenium.JavascriptExecutor;

public class move {
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
		
		driver.get("https://www.tripadvisor.co.kr/Airlines");
		
		//creating a reference 
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//calling the method 
		js.executeScript("onclick=\"widgetEvCall('handlers.changeMainPage', event, this, 300, 'pagination_number', 6, false, '');");
		
		
		
		
		// 닫앗는데 지도 움직임?으로 다른 동으로 동네 설정됬을 수도.
		// 목표하는 구와 돋이 맞는지 확인.
		
		
		
		
		
		
		
		//bw.write("sadf");
		//bw.flush();
		//bw.close();
	}
	
	private static void closeComplexInformation(WebDriverWait wait) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/section/div[2]/div[2]/div/button"))).click();
		wait100ms();
	}

	private static String collectDistance(WebDriverWait wait) {
		String distance = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"detailContents5\"]/div/div[1]"))).getText();
		wait100ms();
		return distance;
	}

	private static void clickSchoolDistrict(WebDriver driver, WebDriverWait wait) {
//		List bannerName = driver.findElements(By.xpath("//*[@id=\"ct\"]/div[2]/div[2]/div/div[2]/div[2]/div/div/a"));
//		for(int i = 0 ; i < bannerName.size() ; i++) {
//			System.out.println(bannerName.get(i));
//		}
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/section/div[2]/div[2]/div/div[2]/div[2]/div/div/a[4]/span")));
		
		
		
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/section/div[2]/div[2]/div/div[2]/div[2]/div/div/a[4]/span"))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/section/div[2]/div[2]/div/div[2]/div[2]/div/div/a[2]/span"))).click();
		wait100ms();
	}

	private static void selectComplex(WebDriverWait wait, int k) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/section/div[2]/div/div[1]/div/div/div/div[3]/ul/li[" + k + "]/a"))).click();
		wait100ms();
	}
	
	private static int checkComplexSize(WebDriver driver) {
		return driver.findElements(By.xpath("//*[@class=\"complex_item\"]")).size();
	}
	
	private static void selectDong(WebDriverWait wait, int j) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/section/div[2]/div/div[1]/div/div/div/div[2]/ul/li[" + j + "]/label"))).click();
		wait100ms();
	}


	private static int checkRegionSize(WebDriver driver) {
		return driver.findElements(By.xpath("//*[@class=\"area_item\"]")).size();
	}
	//div[@class=\"town_detail\"]"

	private static void clickSelectionCity(WebDriverWait wait) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/section/div[2]/div/div[1]/div/div/a/span[1]"))).click();
		wait100ms();
	}


	private static void selectSeoul(WebDriverWait wait) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/section/div[2]/div/div[1]/div/div/div/div[2]/ul/li[1]/label"))).click();
		wait100ms();
	}


	public static void selectGu(WebDriverWait wait, int guIndex) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/section/div[2]/div/div[1]/div/div/div/div[2]/ul/li[" + guIndex + "]/label"))).click();
		wait100ms();
	}
	
	public static void wait100ms() {
		try {
			Thread.sleep(500);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
