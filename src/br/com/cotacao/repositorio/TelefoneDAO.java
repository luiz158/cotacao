package br.com.cotacao.repositorio;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.cotacao.dao.Transactional;
import br.com.cotacao.modelo.Fornecedor;
import br.com.cotacao.modelo.Telefone;

public class TelefoneDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	public List<Telefone> porFornecedor(Fornecedor fornecedor) {
		System.out.println("Carregando lista de telefones do fornecedor: " + fornecedor.getNome());
		return manager.createQuery("from Telefone t where t.fornecedor = :fornecedor", Telefone.class)
										.setParameter("fornecedor", fornecedor)
										.getResultList();
	}
	
	@Transactional
	public void adicionar(Telefone telefone) {
		manager.persist(telefone);
	}
	
	@Transactional
	public void remove(Telefone telefone) {
		manager.remove(manager.merge(telefone));
	}
	
	@Transactional
	public void atualiza(Telefone telefone) {
		manager.merge(telefone);
	}
}






