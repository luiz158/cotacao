package br.com.cotacao.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.cotacao.dao.DAO;
import br.com.cotacao.modelo.Estado;

@ManagedBean
@ViewScoped
public class EstadoBean {

	private Estado estado = new Estado();
	
	private Integer estadoId;

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Estado> getEstados() {
		System.out.println("Carregando lista de estados");
		return new DAO<Estado>(Estado.class).listaTodos();
	}

	public void carregar(Estado estado) {
		System.out.println("Carregando estado");
		this.estado = estado;
	}
	public void carregarAutorPelaId() {
		this.estado = new DAO<Estado>(Estado.class).buscaPorId(estadoId);
	}
}
