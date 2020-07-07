package br.ce.wcaquino.service;

import java.util.List;

import br.ce.wcaquino.dao.TransacaoDAO;
import br.ce.wcaquino.dao.impl.TransacaoDAOImpl;
import br.ce.wcaquino.entidades.TipoTransacao;
import br.ce.wcaquino.entidades.Transacao;

public class TransacaoService {
	
	private TransacaoDAO dao;
	
	public TransacaoService() {
		dao = new TransacaoDAOImpl();
	}
	
	public Transacao salvar(Transacao transacao) throws Exception {
		if((transacao.getTipo().equals(TipoTransacao.RECEITA) && transacao.getValor() < 0) ||
				(transacao.getTipo().equals(TipoTransacao.DESPESA) && transacao.getValor() > 0))
			transacao.setValor(transacao.getValor() * -1); 
		return (transacao.getId() == null)? dao.save(transacao): dao.edit(transacao);
	}
	
	public Transacao findById(Long id) throws Exception {
		return dao.findById(id);
	}
	
	public void delete(Transacao transacao) throws Exception {
		dao.delete(transacao);
	}
	
	public List<Transacao> getAll() throws Exception {
		return dao.list();
	}
	
	public void printAll() throws Exception{
		System.out.println("----- Relação de transacoes ------");
		List<Transacao> Transacaos = getAll();
		if(Transacaos.isEmpty()) {
			System.out.println("Sem transacoes cadastradas");
		} else {
			for(Transacao Transacao: Transacaos) {
				System.out.println(Transacao);
			}
		}
		System.out.println("--------------------------------");
	}

}
