package br.ce.wcaquino.dao;

import java.util.List;

import br.ce.wcaquino.entidades.Conta;

public interface ContaDAO {

    Conta save(Conta conta) throws Exception;

    Conta edit(Conta conta) throws Exception;
 
    Conta findById(Long contaId) throws Exception;

    Conta findByName(String nomeConta) throws Exception;
 
    void delete(Conta conta) throws Exception;
 
    List<Conta> list() throws Exception;
}
