package estrategia5;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

// aula 27 estrategia 5 Massa de dados geral
public class ContaTesteWeb {
	
	private static FirefoxDriver driver;
	
	@BeforeClass
	public static void reset() {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Stefania\\drives\\geckodriver.exe");
		
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("http://seubarriga.wcaquino.me/");

		driver.findElement(By.id("email")).sendKeys("ster@teste");
		driver.findElement(By.id("senha")).sendKeys("123456");
		driver.findElement(By.tagName("button")).click();
		
		driver.findElement(By.linkText("reset")).click();
	}
	
	@AfterClass
	public static void fechar() {
		driver.quit();
	}
	
	
	@Test
	public void inserir() {
		driver.findElement(By.linkText("Contas")).click();
		driver.findElement(By.linkText("Adicionar")).click();
		driver.findElement(By.id("nome")).sendKeys("Conta estrategia 5");
		driver.findElement(By.tagName("button")).click();
		String msgSucesso = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
		Assert.assertEquals("Conta adicionada com sucesso!", msgSucesso);
		//System.out.println("teste");
	}
	
	@Test
	public void consultar() {
		driver.findElement(By.linkText("Contas")).click();
		driver.findElement(By.linkText("Listar")).click();
		driver.findElement(By.xpath("//td[contains(text(),'Conta para saldo')]/..//a")).click();
		String nomeConta = driver.findElement(By.id("nome")).getAttribute("value");
		Assert.assertEquals("Conta para saldo", nomeConta);
	}
	
	@Test
	public void alterar() {
		driver.findElement(By.linkText("Contas")).click();
		driver.findElement(By.linkText("Listar")).click();
		driver.findElement(By.xpath("//td[contains(text(),'Conta para alterar')]/..//a")).click();
		driver.findElement(By.id("nome")).sendKeys("alterada");
		driver.findElement(By.tagName("button")).click();
		String msgSucesso = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
		Assert.assertEquals("Conta alterada com sucesso!", msgSucesso);
	}
	
	@Test
	public void deletar() {
		driver.findElement(By.linkText("Contas")).click();
		driver.findElement(By.linkText("Listar")).click();
		driver.findElement(By.xpath("//td[contains(text(),'Conta mesmo nome')]/..//a[2]")).click();
		String msgSucesso = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
		Assert.assertEquals("Conta removida com sucesso!", msgSucesso);
	}
	
}
