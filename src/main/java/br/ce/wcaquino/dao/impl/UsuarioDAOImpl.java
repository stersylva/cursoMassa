package br.ce.wcaquino.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import br.ce.wcaquino.dao.UsuarioDAO;
import br.ce.wcaquino.dao.utils.ConnectionFactory;
import br.ce.wcaquino.entidades.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {

	public Usuario save(Usuario usuario) throws Exception {
		PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(
				"INSERT INTO usuarios (nome, email, senha, conta_principal_id) VALUES (?, ?, ?, ?) RETURNING id");
		int count = 1;
		stmt.setString(count++, usuario.getNome());
		stmt.setString(count++, usuario.getEmail());
		stmt.setString(count++, usuario.getSenha());
		if(usuario.getContaPrincipalId() == null) {
			stmt.setNull(count++, Types.INTEGER);
		} else {
			stmt.setLong(count++, usuario.getContaPrincipalId());
		}
		ResultSet rs = stmt.executeQuery();
		rs.next();
		usuario.setId(rs.getLong("id"));
		rs.close();
		stmt.close();
		return usuario;
	}

	public Usuario edit(Usuario usuario) throws Exception {
		PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(
				"UPDATE usuarios SET nome = ?, email = ?, senha = ? where id = ?");
		int count = 1;
		stmt.setString(count++, usuario.getNome());
		stmt.setString(count++, usuario.getEmail());
		stmt.setString(count++, usuario.getSenha());
		stmt.setLong(count++, usuario.getId());
		stmt.executeUpdate();
		stmt.close();
		return usuario;
	}

	public Usuario findById(Long usuarioId) throws Exception {
		PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(
				"SELECT * FROM usuarios WHERE id = ?");
		stmt.setLong(1, usuarioId);
		ResultSet rs = stmt.executeQuery();
		if(!rs.next()) return null;
		Usuario user = getUserFromRS(rs);
		rs.close();
		stmt.close();
		return user;
	}

	public void delete(Usuario usuario) throws Exception {
		PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(
				"DELETE FROM usuarios WHERE ID = ?");
		stmt.setLong(1, usuario.getId());
		stmt.executeUpdate();
		stmt.close();
	}

	public List<Usuario> list() throws Exception {
		PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(
				"SELECT * FROM usuarios");
		ResultSet rs = stmt.executeQuery();
		List<Usuario> lista = new ArrayList<Usuario>();
		while(rs.next()){
			lista.add(getUserFromRS(rs));
		}
		rs.close();
		stmt.close();
		return lista;
	}
	
	private Usuario getUserFromRS(ResultSet rs) throws SQLException {
		Usuario usuario = new Usuario();
		usuario.setId(rs.getLong("id"));
		usuario.setNome(rs.getString("nome"));
		usuario.setEmail(rs.getString("email"));
		usuario.setSenha(rs.getString("senha"));
		return usuario;
	}
}
