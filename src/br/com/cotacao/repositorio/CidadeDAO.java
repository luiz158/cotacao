package br.com.cotacao.repositorio;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.cotacao.modelo.Cidade;
import br.com.cotacao.modelo.Estado;

public class CidadeDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	public List<Cidade> porEstado(Estado estado) {
		System.out.println("Carregando lista de cidades do estado de: " + estado.getNome());
		return manager.createQuery("from Cidade c where c.estado = :estado", Cidade.class)
										.setParameter("estado", estado)
										.getResultList();
	}

	public Cidade porCodigo(Long codigo) {
		return manager.find(Cidade.class, codigo);
	}
	
}






