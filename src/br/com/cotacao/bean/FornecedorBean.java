package br.com.cotacao.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.cotacao.modelo.Cidade;
import br.com.cotacao.modelo.Estado;
import br.com.cotacao.modelo.Fornecedor;
import br.com.cotacao.modelo.Telefone;
import br.com.cotacao.repositorio.CidadeDAO;
import br.com.cotacao.repositorio.EstadoDAO;
import br.com.cotacao.repositorio.FornecedorDAO;
import br.com.cotacao.repositorio.TelefoneDAO;

@Named
@ViewScoped
public class FornecedorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FornecedorDAO fornecedorDAO;
	@Inject
	private EstadoDAO estadoDAO;
	@Inject
	private CidadeDAO cidadeDAO;
	@Inject
	private TelefoneDAO telefoneDAO;

	private Fornecedor fornecedor;
	private Estado estado;
	private Telefone telefone;

	private List<Estado> todosEstados;
	private List<Cidade> cidadesPorEstado;
	private List<Fornecedor> todosFornecedores;

	public void inicializar() {
		fornecedor = new Fornecedor();
		estado = null;
		todosFornecedores = fornecedorDAO.todosFornecedoresCompleto();

		if (!FacesContext.getCurrentInstance().isPostback()) {
			todosEstados = estadoDAO.todos();
		}
	}

	public void onEstadoChange() {
		cidadesPorEstado = null;
		if (estado != null) {
			cidadesPorEstado = cidadeDAO.porEstado(estado);
		}
	}

	public void remover(Fornecedor fornecedor) {
		System.out.println("Removendo fornecedor " + fornecedor.getNome());
		fornecedorDAO.remove(fornecedor);
	}

	public void gravarTelefone() {
		if (this.telefone.getId() == null) {
			telefoneDAO.adicionar(this.telefone);
		} else {
			telefoneDAO.atualiza(this.telefone);
		}
		this.fornecedor.adicionaTelefone(telefone);
		System.out.println("Telefone dicionado: " + telefone.getTelefone());

		this.telefone = new Telefone();
	}

	public void carregar(Fornecedor fornecedor) {
		System.out.println("Carregando fornecedor " + fornecedor.getNome());
		this.fornecedor = fornecedor;
	}

	public void removerTelefoneDoFornecedor(Telefone telefone) {
		this.fornecedor.removeTelefone(telefone);
	}

	public List<Telefone> getTelefoneDoFornecedor() {
		return this.fornecedor.getTelefones();
	}

	public void gravar() {
		System.out.println("Gravando fornecedor " + this.fornecedor.getNome());
		if (this.fornecedor.getId() == null) {
			fornecedorDAO.adiciona(this.fornecedor);
			this.todosFornecedores = fornecedorDAO.todosFornecedoresCompleto();
		} else {
			fornecedorDAO.atualiza(this.fornecedor);
		}
		inicializar();
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public List<Estado> getTodosEstados() {
		return todosEstados;
	}

	public void setTodosEstados(List<Estado> todosEstados) {
		this.todosEstados = todosEstados;
	}

	public List<Cidade> getCidadesPorEstado() {
		return cidadesPorEstado;
	}

	public void setCidadesPorEstado(List<Cidade> cidadesPorEstado) {
		this.cidadesPorEstado = cidadesPorEstado;
	}

	public List<Fornecedor> getTodosFornecedores() {
		return todosFornecedores;
	}

	public void setTodosFornecedores(List<Fornecedor> todosFornecedores) {
		this.todosFornecedores = todosFornecedores;
	}

}
