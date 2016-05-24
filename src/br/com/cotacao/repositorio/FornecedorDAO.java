package br.com.cotacao.repositorio;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.cotacao.dao.Transactional;
import br.com.cotacao.modelo.Fornecedor;

public class FornecedorDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	@Transactional
	public List<Fornecedor> todosFornecedoresCompleto() {
		return manager.createQuery(
				"from Fornecedor f " + "inner join fetch f.endereco e "
						+ "inner join fetch e.cidade c "
						+ "inner join fetch c.estado"
						+ "inner join fetch f.telefones t"
						+ "inner join fetch t.telefone", Fornecedor.class)
				.getResultList();
	}

	@Transactional
	public void remove(Fornecedor fornecedor) {
		manager.remove(manager.merge(fornecedor));
	}

	@Transactional
	public void adiciona(Fornecedor fornecedor) {
		manager.persist(fornecedor);
	}

	@Transactional
	public void atualiza(Fornecedor fornecedor) {
		manager.merge(fornecedor);
	}
}
