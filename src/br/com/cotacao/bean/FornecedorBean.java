package br.com.cotacao.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.cotacao.dao.DAO;
import br.com.cotacao.modelo.Cidade;
import br.com.cotacao.modelo.Endereco;
import br.com.cotacao.modelo.Fornecedor;
import br.com.cotacao.modelo.Telefone;

@Named
@ViewScoped
public class FornecedorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Fornecedor fornecedor = new Fornecedor();
	private Telefone telefone = new Telefone();
	private Endereco endereco;
	
	private Integer fornecedorId;
	private List<Fornecedor> fornecedores;
	private List<Cidade> cidades;
	
	private Integer telefoneId;
	private Integer enderecoId;
	private Integer estadoId;
	private Integer cidadeId;
	

	public List<Cidade> getCidades() {
		CidadeBean cidadeBean = new CidadeBean();
		if(this.estadoId != null){
				this.cidades = cidadeBean.porEstado(this.estadoId);
		}
		return cidades;
	}
	public Integer getCidadeId() {
		return cidadeId;
	}
	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public Integer getEstadoId() {
		return estadoId;
	}
	public void setEstadoId(Integer estadoId) {
		this.estadoId = estadoId;
	}
	public void setTelefoneId(Integer telefoneId) {
		this.telefoneId = telefoneId;
	}
	public Integer getTelefoneId() {
		return telefoneId;
	}
	
	public void setEnderecoId(Integer enderecoId){
		this.enderecoId = enderecoId;
	}
	public Integer getEnderecoId(){
		return enderecoId;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public Integer getFornecedorId() {
		return fornecedorId;
	}
	public void setFornecedorId(Integer fornecedorId) {
		this.fornecedorId = fornecedorId;
	}
	public List<Fornecedor> getFornecedores() {
		DAO<Fornecedor> dao = new DAO<Fornecedor>(Fornecedor.class);
		
		if(this.fornecedores == null){
			this.fornecedores = dao.listaTodos();
		}
		return fornecedores;
	}

	public List<Telefone> getTelefoneDoFornecedor() {
		return this.fornecedor.getTelefones();
	}

	public void gravarTelefone() {

		if (this.telefone.getId() == null) {
			new DAO<Telefone>(Telefone.class).adiciona(this.telefone);
		} else {
			new DAO<Telefone>(Telefone.class).atualiza(this.telefone);
		}
		this.fornecedor.adicionaTelefone(telefone);
		System.out.println("Telefone do Fornecedor: " + telefone.getTelefone());

		this.telefone = new Telefone();
	}

	public void gravar() {
		System.out.println("Gravando fornecedor " + this.fornecedor.getNome());

		/**
		if (fornecedor.getTelefones().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("autor",
					new FacesMessage("Livro deve ter pelo menos um Autor."));
			return;
		}
		*/
		
		DAO<Fornecedor> dao = new DAO<Fornecedor>(Fornecedor.class);
		
		if (this.fornecedor.getId() == null) {
			dao.adiciona(this.fornecedor);
			this.fornecedores = dao.listaTodos();
		} else {
			dao.atualiza(this.fornecedor);
		}

		this.fornecedor = new Fornecedor();
	}

	public void carregar(Fornecedor fornecedor) {
		System.out.println("Carregando fornecedor " + fornecedor.getNome());
		this.fornecedor = fornecedor;
	}

	public void remover(Fornecedor fornecedor) {
		System.out.println("Removendo fornecedor " + fornecedor.getNome());
		new DAO<Fornecedor>(Fornecedor.class).remove(fornecedor);
	}

	public void removerTelefoneDoFornecedor(Telefone telefone) {
		this.fornecedor.removeTelefone(telefone);
	}

	/** criar metodo para salvar os telefones
	public String formAutor() {
		System.out.println("Chamanda do formulário do Autor.");
		return "autor?faces-redirect=true";
	}
	*/

	public void carregarFornecedorPelaId() {
		this.fornecedor = new DAO<Fornecedor>(Fornecedor.class).buscaPorId(fornecedorId);
	}
	public Telefone getTelefone() {
		return telefone;
	}
	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
	
}
