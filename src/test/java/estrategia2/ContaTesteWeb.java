package estrategia2;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.github.javafaker.Faker;


public class ContaTesteWeb {
	
	private FirefoxDriver driver;
	private Faker faker = new Faker(); // biblioteca para massa de dados
	
	@Before
	public void login() {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Stefania\\drives\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("http://seubarriga.wcaquino.me/");

		driver.findElement(By.id("email")).sendKeys("ster@teste");
		driver.findElement(By.id("senha")).sendKeys("123456");
		driver.findElement(By.tagName("button")).click();

	}
	
	@Test
	public void inserir() {
		driver.findElement(By.linkText("Contas")).click();
		driver.findElement(By.linkText("Adicionar")).click();
		driver.findElement(By.id("nome")).sendKeys(faker.harryPotter().character());
		driver.findElement(By.tagName("button")).click();
		String msgSucesso = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
		Assert.assertEquals("Conta adicionada com sucesso!", msgSucesso);
		//System.out.println("teste");
	}
	
	@Test
	public void consultar() {
		String conta = inserirConta();
		driver.findElement(By.linkText("Contas")).click();
		driver.findElement(By.linkText("Listar")).click();
		driver.findElement(By.xpath("//td[contains(text(),'"+conta+"')]/..//a")).click();
		String nomeConta = driver.findElement(By.id("nome")).getAttribute("value");
		Assert.assertEquals(conta, nomeConta);
	}
	
	@Test
	public void alterar() {
		String conta = inserirConta();
		driver.findElement(By.linkText("Contas")).click();
		driver.findElement(By.linkText("Listar")).click();
		driver.findElement(By.xpath("//td[contains(text(),'"+conta+"')]/..//a")).click();
		driver.findElement(By.id("nome")).sendKeys(" alterada");
		driver.findElement(By.tagName("button")).click();
		String msgSucesso = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
		Assert.assertEquals("Conta alterada com sucesso!", msgSucesso);
	}
	
	@Test
	public void deletar() {
		String conta = inserirConta();
		driver.findElement(By.linkText("Contas")).click();
		driver.findElement(By.linkText("Listar")).click();
		driver.findElement(By.xpath("//td[contains(text(),'"+conta+"')]/..//a[2]")).click();
		String msgSucesso = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
		Assert.assertEquals("Conta removida com sucesso!", msgSucesso);
	}
	
	@After
	public void fechar() {
		driver.quit();
	}
	
	public String inserirConta() {
		String registro = faker.harryPotter().character();
		driver.findElement(By.linkText("Contas")).click();
		driver.findElement(By.linkText("Adicionar")).click();
		driver.findElement(By.id("nome")).sendKeys(registro);
		driver.findElement(By.tagName("button")).click();
		return registro;
	}
	

}
