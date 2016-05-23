package br.com.cotacao.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.cotacao.dao.DAO;
import br.com.cotacao.modelo.Estado;

@Named
@ViewScoped
public class EstadoBean implements Serializable{

	private static final long serialVersionUID = 1L;

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
