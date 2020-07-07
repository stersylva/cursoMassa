package estrategia1;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.service.UsuarioService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UsuarioServicoTest {
	
	private UsuarioService service = new UsuarioService();
	private static Usuario usuarioGlobal;
	
	@Test
	public void teste_1_Inserir() throws Exception {
		Usuario usuario = new Usuario("Usuario estrategia #1", "user@email.com", "password");
		usuarioGlobal = service.salvar(usuario);
		Assert.assertNotNull(usuarioGlobal.getId());
	}
	
	@Test
	public void teste_2_consultar() throws Exception {
		Usuario usurio = service.findById(usuarioGlobal.getId());
		Assert.assertEquals("Usuario estrategia #1", usurio.getNome());
	}
	
	@Test
	public void teste_3_alterar() throws Exception {
		Usuario usuario = service.findById(usuarioGlobal.getId());
		usuario.setNome("Usuario Alterado");
		Usuario usuarioAlterado = service.salvar(usuario);
		Assert.assertEquals("Usuario Alterado", usuarioAlterado.getNome());
		
	}
	
	@Test
	public void teste_4_excluir() throws Exception {
		service.delete(usuarioGlobal);
		Usuario usuarioRemovido = service.findById(usuarioGlobal.getId());
		Assert.assertNull(usuarioRemovido);
		
	}

}
