package br.com.cotacao.repositorio;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.cotacao.dao.Transactional;
import br.com.cotacao.modelo.Endereco;
import br.com.cotacao.modelo.Fornecedor;

public class EnderecoDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	public Endereco porFornecedor(Fornecedor fornecedor) {
		System.out.println("Carregando endereco do fornecedor: " + fornecedor.getNome());
		Endereco endereco = (Endereco) manager.createQuery(
				"from Endereco e where e.fornecedor = :fornecedor", Endereco.class)
				.setParameter("fornecedor", fornecedor);
		return endereco;
	}
	
	@Transactional
	public void adicionar(Endereco endereco) {
		manager.persist(endereco);
	}
	
	@Transactional
	public void remove(Endereco endereco) {
		manager.remove(manager.merge(endereco));
	}
	
	@Transactional
	public void atualiza(Endereco endereco) {
		manager.merge(endereco);
	}
}






