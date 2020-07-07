package br.ce.wcaquino.service;

import java.util.List;

import br.ce.wcaquino.dao.ContaDAO;
import br.ce.wcaquino.dao.impl.ContaDAOImpl;
import br.ce.wcaquino.entidades.Conta;

public class ContaService {
	
	private ContaDAO dao;
	
	public ContaService() {
		dao = new ContaDAOImpl();
	}
	
	public Conta salvar(Conta conta) throws Exception {
		Conta contaBuscada = dao.findByName(conta.getNome());
		if(contaBuscada != null && contaBuscada.getId() != conta.getId())
			throw new RuntimeException("Usu√°rio j√° possui uma conta com o mesmo nome");
		return (conta.getId() == null)? dao.save(conta): dao.edit(conta);
	}
	
	public Conta findById(Long id) throws Exception {
		return dao.findById(id);
	}
	
	public Conta findByName(String name) throws Exception {
		return dao.findByName(name);
	}
	
	public void delete(Conta conta) throws Exception {
		dao.delete(conta);
	}
	
	public List<Conta> getAll() throws Exception {
		return dao.list();
	}
	
	public void printAll() throws Exception{
		System.out.println("----- RelaÁ„oo de contas ------");
		List<Conta> Contas = getAll();
		if(Contas.isEmpty()) {
			System.out.println("Sem contas cadastradas");
		} else {
			for(Conta Conta: Contas) {
				System.out.println(Conta);
			}
		}
		System.out.println("------------------------------");
	}

}
