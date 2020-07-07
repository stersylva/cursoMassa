package estrategia2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.javafaker.Faker;

import br.ce.wcaquino.entidades.Conta;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.service.ContaService;
import br.ce.wcaquino.service.UsuarioService;

// aula 13
public class ContaServicoTest {
	
	private static Faker faker = new Faker(); // biblioteca para massa de dados
	ContaService contaService = new ContaService();
	UsuarioService userService = new UsuarioService();
	private static Usuario usuarioGlobal;
	private Conta contaTest;
	
	@BeforeClass
	public static void criarUsuario() {
		usuarioGlobal = new Usuario(faker.name().fullName(), faker.internet().emailAddress(), faker.internet().password());
	}
	
	@Before
	public void inserirConta() throws Exception {
		Usuario usuarioSalvo = userService.salvar(usuarioGlobal);
		Conta conta = new Conta(faker.superhero().name(), usuarioSalvo);
		contaTest = contaService.salvar(conta);
	}
	
	@Test
	public void testInserir() throws Exception {
		Conta conta = new Conta(faker.superhero().name(), usuarioGlobal);
		Conta contaSalva = contaService.salvar(conta);
		Assert.assertNotNull(contaSalva.getId());
		
		userService.printAll();
		contaService.printAll();
		
	}
	
	@Test
	public void testAlterar() throws Exception {		
		String novoNomeConta = faker.ancient().god() + " " + faker.ancient();
		contaTest.setNome(novoNomeConta);
		Conta contaAlterada = contaService.salvar(contaTest);
		Assert.assertEquals(novoNomeConta, contaAlterada.getNome());
		contaService.printAll();
	}
	
	@Test
	public void testConsultar() throws Exception {
		Conta contaBuscada = contaService.findById(contaTest.getId());
		Assert.assertEquals(contaTest.getNome(), contaBuscada.getNome());	
	}
	
	@Test
	public void testDeletar() throws Exception {
		contaService.delete(contaTest);
		Conta contaBuscada = contaService.findById(contaTest.getId());
		Assert.assertNull(contaBuscada);
		
	}
	
	

}
