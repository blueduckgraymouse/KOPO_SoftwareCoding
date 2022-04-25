package temp;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Ex02_webDriverWait {
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:\\chromedriver\\chromedriver.exe";
	
	public static void main(String[] args) {
		try {
			System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		ChromeOptions options = new ChromeOptions();
		
		WebDriver driver = new ChromeDriver(options);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
		
		driver.get("https://vibe.naver.com/today?utm_source=brandsearch_PC&utm_medium=title");
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"app\"]/div[2]/div/div/a[2]"))).click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"search_keyword\"]"))).click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"search_keyword\"]"))).click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"search_keyword\"]"))).sendKeys("다비치");
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"search_keyword\"]"))).sendKeys(Keys.ENTER);
		
		String text1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"content\"]/div[2]/div/div[1]/div[1]/div[2]/div[1]/span[1]/a/span"))).getText();
		String text2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"content\"]/div[2]/div/div[1]/div[2]/div[2]/div[1]/span[1]/a/span"))).getText();
		String text3 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"content\"]/div[2]/div/div[1]/div[3]/div[2]/div[1]/span[1]/a/span"))).getText();
		
		System.out.println(text1);
		System.out.println(text2);
		System.out.println(text3);
	}

}
