package estrategia1;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ContaTesteWeb {
	
	private FirefoxDriver driver;
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
	public void a_inserir() {
		driver.findElement(By.linkText("Contas")).click();
		driver.findElement(By.linkText("Adicionar")).click();
		driver.findElement(By.id("nome")).sendKeys("conta estratégia 1");
		driver.findElement(By.tagName("button")).click();
		String msgSucesso = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
		Assert.assertEquals("Conta adicionada com sucesso!", msgSucesso);
		//System.out.println("teste");
	}
	
	@Test
	public void b_consultar() {
		driver.findElement(By.linkText("Contas")).click();
		driver.findElement(By.linkText("Listar")).click();
		driver.findElement(By.xpath("//td[contains(text(),'conta estratégia 1')]/..//a")).click();
		String nomeConta = driver.findElement(By.id("nome")).getAttribute("value");
		Assert.assertEquals("conta estratégia 1", nomeConta);
	}
	
	@Test
	public void c_alterar() {
		driver.findElement(By.linkText("Contas")).click();
		driver.findElement(By.linkText("Listar")).click();
		driver.findElement(By.xpath("//td[contains(text(),'conta estratégia 1')]/..//a")).click();
		driver.findElement(By.id("nome")).clear();
		driver.findElement(By.id("nome")).sendKeys("conta estratégia 1 alterada");
		driver.findElement(By.tagName("button")).click();
		String msgSucesso = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
		Assert.assertEquals("Conta alterada com sucesso!", msgSucesso);
	}
	
	@Test
	public void d_deletar() {
		driver.findElement(By.linkText("Contas")).click();
		driver.findElement(By.linkText("Listar")).click();
		driver.findElement(By.xpath("//td[contains(text(),'conta estratégia 1')]/..//a[2]")).click();
		String msgSucesso = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
		Assert.assertEquals("Conta removida com sucesso!", msgSucesso);
	}
	
	@After
	public void fechar() {
		driver.quit();
	}
	

}
