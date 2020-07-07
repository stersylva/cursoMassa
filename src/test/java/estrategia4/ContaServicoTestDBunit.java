package estrategia4;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ce.wcaquino.dao.utils.ConnectionFactory;
import br.ce.wcaquino.entidades.Conta;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.service.ContaService;
import br.ce.wcaquino.service.UsuarioService;

// aula 24 Massa de dados especificas 
public class ContaServicoTestDBunit {
	
	ContaService contaService = new ContaService();
	UsuarioService userService = new UsuarioService();
	
	@Before
	public void banco() throws Exception {
		Connection conn = ConnectionFactory.getConnection();
		conn.createStatement().executeUpdate("DELETE FROM transacoes");
		conn.createStatement().executeUpdate("DELETE FROM contas");
		conn.createStatement().executeUpdate("DELETE FROM usuarios");
		conn.createStatement().executeUpdate("INSERT INTO usuarios (id, nome, email, senha)"
					+ "VALUES(1, 'usuario de controle', 'usuarioa@teste.com', 'password')");
	}
	
	@Test
	public void testInserir() throws Exception {
		Usuario usuario = userService.findById(1L);
		Conta conta = new Conta("Conta Salva", usuario);
		Conta contaSalva = contaService.salvar(conta);
		Assert.assertNotNull(contaSalva.getId());
		
		contaService.printAll();
		
	}
	
	@Test
	public void testAlterar() throws Exception {
		ConnectionFactory.getConnection().createStatement().executeUpdate(
				 "INSERT INTO contas (id, nome, usuario_id)"
				+ "VALUES(1, 'conta para alterar', 1)");
		Conta contaTest = contaService.findByName("conta para alterar");
		contaService.printAll();
		contaTest.setNome("conta alterada");
		Conta contaAlterada = contaService.salvar(contaTest);
		Assert.assertEquals("conta alterada", contaAlterada.getNome());
		contaService.printAll();
	}
	
	@Test
	public void testConsultar() throws Exception {
		ConnectionFactory.getConnection().createStatement().executeUpdate(
				 "INSERT INTO contas (id, nome, usuario_id)"
				+ "VALUES(1, 'conta para consultar', 1)");
		Conta contaBuscada = contaService.findById(1L);
		Assert.assertEquals("conta para consultar", contaBuscada.getNome());	
	}
	
	@Test
	public void testDeletar() throws Exception {
		ConnectionFactory.getConnection().createStatement().executeUpdate(
				 "INSERT INTO contas (id, nome, usuario_id)"
				+ "VALUES(1, 'conta para excluir', 1)");
		Conta contaTest = contaService.findByName("conta para excluir");
		contaService.printAll();
		contaService.delete(contaTest);
		Conta contaBuscada = contaService.findById(contaTest.getId());
		Assert.assertNull(contaBuscada);
		contaService.printAll();
		
	}
	
	

}
