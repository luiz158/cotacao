package br.com.cotacao.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;

import br.com.cotacao.dao.DAO;
import br.com.cotacao.dao.JPAUtil;
import br.com.cotacao.modelo.Cidade;
import br.com.cotacao.modelo.Estado;

@Named
@ViewScoped
public class CidadeBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Estado estado = new Estado();
	private EntityManager manager = new JPAUtil().getEntityManager();
	
	public List<Cidade> porEstado(Integer estadoId ) {
		this.estado = new DAO<Estado>(Estado.class).buscaPorId(estadoId);
		System.out.println("Carregando lista de cidades do estado de: " + estado.getNome());
		return manager
				.createQuery("from Cidade c where c.estado = :estado",
						Cidade.class).setParameter("estado", this.estado)
				.getResultList();
	}

	public Cidade porCodigo(Integer id) {
		return manager.find(Cidade.class, id);
	}
	
}
