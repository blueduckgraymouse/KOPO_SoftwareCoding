package temp;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Ex01_driver_findElement {
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
		
		driver.get("https://vibe.naver.com/today?utm_source=brandsearch_PC&utm_medium=title");
		try {
			Thread.sleep(2000);
		} catch(Exception e) {
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div/div/a[2]")).click();
		try {
			Thread.sleep(1000);
		} catch(Exception e) {
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//*[@id=\"search_keyword\"]")).click();
		try {
			Thread.sleep(1000);
		} catch(Exception e) {
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//*[@id=\"search_keyword\"]")).sendKeys("다비치");
		try {
			Thread.sleep(1000);
		} catch(Exception e) {
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//*[@id=\"search_keyword\"]")).sendKeys(Keys.ENTER);
		try {
			Thread.sleep(1000);
		} catch(Exception e) {
			e.printStackTrace();
		}
		String text1 = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div/div[1]/div[1]/div[2]/div[1]/span[1]/a/span")).getText();
		String text2 = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div/div[1]/div[2]/div[2]/div[1]/span[1]/a/span")).getText();
		String text3 = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div/div[1]/div[3]/div[2]/div[1]/span[1]/a/span")).getText();
		System.out.println(text1);
		System.out.println(text2);
		System.out.println(text3);
	}

}
