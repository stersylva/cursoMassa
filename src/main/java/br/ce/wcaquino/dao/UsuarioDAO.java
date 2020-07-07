package br.ce.wcaquino.dao;

import java.util.List;

import br.ce.wcaquino.entidades.Usuario;

public interface UsuarioDAO {

    Usuario save(Usuario usuario) throws Exception;

    Usuario edit(Usuario usuario) throws Exception;
 
    Usuario findById(Long usuarioId) throws Exception;
 
    void delete(Usuario usuario) throws Exception;
 
    List<Usuario> list() throws Exception;
}
