package br.com.cotacao.repositorio;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import br.com.cotacao.dao.Transactional;
import br.com.cotacao.modelo.Produto;

public class ProdutoDAO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	@Transactional
	public List<Produto> listaTodos() {
		
		CriteriaQuery<Produto> query = manager.getCriteriaBuilder().createQuery(Produto.class);
		query.select(query.from(Produto.class));

		List<Produto> lista = manager.createQuery(query).getResultList();
		
		return lista;
	}
	@Transactional
	public void adiciona(Produto produto) {
		manager.persist(produto);
	}
	@Transactional
	public void atualiza(Produto produto) {
		manager.merge(produto);
	}
	@Transactional
	public void remove(Produto produto) {
		manager.remove(manager.merge(produto));
	}
	@Transactional
	public Produto buscaPorId(Integer produtoId) {
		Produto produto = manager.find(Produto.class, produtoId);
		return produto;
	}
}
