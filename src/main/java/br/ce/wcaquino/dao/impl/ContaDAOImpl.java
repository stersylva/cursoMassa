package br.ce.wcaquino.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.ce.wcaquino.dao.ContaDAO;
import br.ce.wcaquino.dao.UsuarioDAO;
import br.ce.wcaquino.dao.utils.ConnectionFactory;
import br.ce.wcaquino.entidades.Conta;
import br.ce.wcaquino.entidades.Usuario;

public class ContaDAOImpl implements ContaDAO {
	
	private UsuarioDAO usuarioDao = new UsuarioDAOImpl();

	public Conta save(Conta conta) throws Exception {
		PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(
				"INSERT INTO contas (nome, usuario_id) VALUES (?, ?) RETURNING id");
		int count = 1;
		stmt.setString(count++, conta.getNome());
		stmt.setLong(count++, conta.getUsuario().getId());
		ResultSet rs = stmt.executeQuery();
		rs.next();
		conta.setId(rs.getLong("id"));
		rs.close();
		stmt.close();
		return conta;
	}

	public Conta edit(Conta conta) throws Exception {
		PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(
				"UPDATE contas SET nome = ? where id = ?");
		int count = 1;
		stmt.setString(count++, conta.getNome());
		stmt.setLong(count++, conta.getId());
		stmt.executeUpdate();
		stmt.close();
		return conta;
	}

	public Conta findById(Long contaId) throws Exception {
		PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(
				"SELECT * FROM contas WHERE id = ?");
		stmt.setLong(1, contaId);
		ResultSet rs = stmt.executeQuery();
		if(!rs.next()) return null;
		Conta conta = getContaFromRS(rs);
		rs.close();
		stmt.close();
		return conta;
	}
	
	public Conta findByName(String nomeConta) throws Exception {
		PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(
				"SELECT * FROM contas WHERE nome = ?");
		stmt.setString(1, nomeConta);
		ResultSet rs = stmt.executeQuery();
		if(!rs.next()) return null;
		Conta conta = getContaFromRS(rs);
		rs.close();
		stmt.close();
		return conta;
	}

	public void delete(Conta conta) throws Exception {
		PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(
				"DELETE FROM contas WHERE ID = ?");
		stmt.setLong(1, conta.getId());
		stmt.executeUpdate();
		stmt.close();
	}

	public List<Conta> list() throws Exception {
		PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(
				"SELECT * FROM contas");
		ResultSet rs = stmt.executeQuery();
		List<Conta> lista = new ArrayList<Conta>();
		while(rs.next()){
			lista.add(getContaFromRS(rs));
		}
		rs.close();
		stmt.close();
		return lista;
	}
	
	private Conta getContaFromRS(ResultSet rs) throws Exception {
		Conta conta = new Conta();
		conta.setId(rs.getLong("id"));
		conta.setNome(rs.getString("nome"));
		Usuario usuario = usuarioDao.findById(rs.getLong("usuario_id"));
		conta.setUsuario(usuario);
		return conta;
	}
}
