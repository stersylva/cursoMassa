package br.ce.wcaquino.service;

import java.util.List;

import br.ce.wcaquino.dao.UsuarioDAO;
import br.ce.wcaquino.dao.impl.UsuarioDAOImpl;
import br.ce.wcaquino.entidades.Usuario;

public class UsuarioService {
	
	private UsuarioDAO dao;
	
	public UsuarioService() {
		dao = new UsuarioDAOImpl();
	}
	
	public Usuario salvar(Usuario usuario) throws Exception {
		return (usuario.getId() == null)? dao.save(usuario): dao.edit(usuario);
	}
	
	public Usuario findById(Long id) throws Exception {
		return dao.findById(id);
	}
	
	public void delete(Usuario usuario) throws Exception {
		dao.delete(usuario);
	}
	
	public List<Usuario> getAll() throws Exception {
		return dao.list();
	}
	
	public void printAll() throws Exception{
		System.out.println("----- Relaçãoo de usuários ------");
		List<Usuario> usuarios = getAll();
		if(usuarios.isEmpty()) {
			System.out.println("Sem usuários cadastrados");
		} else {
			for(Usuario usuario: usuarios) {
				System.out.println(usuario);
			}
		}
		System.out.println("--------------------------------");
	}

}
