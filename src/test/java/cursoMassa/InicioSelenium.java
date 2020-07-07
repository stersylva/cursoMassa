package cursoMassa;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class InicioSelenium {
	
	public static void main(String[] args) {
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\Stefania\\drives\\chromedriver.exe");
		//ChromeDriver driver = new ChromeDriver();
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Stefania\\drives\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("http://seubarriga.wcaquino.me/");
		
		driver.findElement(By.id("email")).sendKeys("ster@teste");
		driver.findElement(By.id("senha")).sendKeys("123456");
		driver.findElement(By.tagName("button")).click();
		
		driver.quit();
	}

}
