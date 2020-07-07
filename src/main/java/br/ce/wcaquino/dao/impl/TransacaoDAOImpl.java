package br.ce.wcaquino.dao.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.ce.wcaquino.dao.ContaDAO;
import br.ce.wcaquino.dao.TransacaoDAO;
import br.ce.wcaquino.dao.UsuarioDAO;
import br.ce.wcaquino.dao.utils.ConnectionFactory;
import br.ce.wcaquino.entidades.Conta;
import br.ce.wcaquino.entidades.TipoTransacao;
import br.ce.wcaquino.entidades.Transacao;
import br.ce.wcaquino.entidades.Usuario;

public class TransacaoDAOImpl implements TransacaoDAO {
	
	private UsuarioDAO usuarioDao = new UsuarioDAOImpl();
	private ContaDAO contaDao = new ContaDAOImpl();

	public Transacao save(Transacao transacao) throws Exception {
		PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(
				"INSERT INTO transacoes (descricao, envolvido, tipo, data_transacao, valor, "
				+ "status, conta_id, usuario_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING id");
		int count = 1;
		stmt.setString(count++, transacao.getDescricao());
		stmt.setString(count++, transacao.getEnvolvido());
		stmt.setString(count++, transacao.getTipo().getTexto());
		stmt.setDate(count++, new Date(transacao.getData().getTime()));
		stmt.setDouble(count++, transacao.getValor());
		stmt.setBoolean(count++, transacao.getStatus());
		stmt.setLong(count++, transacao.getConta().getId());
		stmt.setLong(count++, transacao.getUsuario().getId());
		ResultSet rs = stmt.executeQuery();
		rs.next();
		transacao.setId(rs.getLong("id"));
		rs.close();
		stmt.close();
		return transacao;
	}

	public Transacao edit(Transacao transacao) throws Exception {
		PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(
				"UPDATE transacoes SET descricao = ?, envolvido = ?, tipo = ?, data_transacao = ?, "
						+ "valor = ?, status = ?, conta_id = ? where id = ?");
		int count = 1;
		stmt.setString(count++, transacao.getDescricao());
		stmt.setString(count++, transacao.getEnvolvido());
		stmt.setString(count++, transacao.getTipo().getTexto());
		stmt.setDate(count++, new Date(transacao.getData().getTime()));
		stmt.setDouble(count++, transacao.getValor());
		stmt.setBoolean(count++, transacao.getStatus());
		stmt.setLong(count++, transacao.getConta().getId());
		stmt.setLong(count++, transacao.getId());
		stmt.executeUpdate();
		stmt.close();
		return transacao;
	}

	public Transacao findById(Long transacaoId) throws Exception {
		PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(
				"SELECT * FROM transacoes WHERE id = ?");
		stmt.setLong(1, transacaoId);
		ResultSet rs = stmt.executeQuery();
		if(!rs.next()) return null;
		Transacao tra = getTransacaoFromRS(rs);
		rs.close();
		stmt.close();
		return tra;
	}
	
	public void delete(Transacao transacao) throws Exception {
		PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(
				"DELETE FROM transacoes WHERE ID = ?");
		stmt.setLong(1, transacao.getId());
		stmt.executeUpdate();
		stmt.close();
	}

	public List<Transacao> list() throws Exception {
		PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(
				"SELECT * FROM transacoes");
		ResultSet rs = stmt.executeQuery();
		List<Transacao> lista = new ArrayList<Transacao>();
		while(rs.next()){
			lista.add(getTransacaoFromRS(rs));
		}
		rs.close();
		stmt.close();
		return lista;
	}
	
	private Transacao getTransacaoFromRS(ResultSet rs) throws Exception {
		Transacao transacao = new Transacao();
		transacao.setId(rs.getLong("id"));
		transacao.setDescricao(rs.getString("descricao"));
		transacao.setEnvolvido(rs.getString("envolvido"));
		transacao.setTipo(TipoTransacao.getPorTexto(rs.getString("tipo")));
		transacao.setData(rs.getDate("data_transacao"));
		transacao.setValor(rs.getDouble("valor"));
		transacao.setStatus(rs.getBoolean("status"));
		Conta conta = contaDao.findById(rs.getLong("conta_id"));
		transacao.setConta(conta);
		Usuario usuario = usuarioDao.findById(rs.getLong("usuario_id"));
		transacao.setUsuario(usuario);
		return transacao;
	}
}
