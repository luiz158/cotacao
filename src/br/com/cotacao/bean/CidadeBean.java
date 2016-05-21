package br.com.cotacao.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.com.cotacao.dao.DAO;
import br.com.cotacao.dao.JPAUtil;
import br.com.cotacao.modelo.Cidade;
import br.com.cotacao.modelo.Estado;

@ManagedBean
@ViewScoped
public class CidadeBean {
	
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
