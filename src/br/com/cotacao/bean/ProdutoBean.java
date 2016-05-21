package br.com.cotacao.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.cotacao.dao.DAO;
import br.com.cotacao.modelo.Produto;

@ManagedBean
@ViewScoped
public class ProdutoBean {

	private Produto produto = new Produto();
	
	private Integer produtoId;

	public List<Produto> getProdutos() {
		return new DAO<Produto>(Produto.class).listaTodos();
	}

	public String gravar() {
		System.out.println("Gravando produto " + this.produto.getDescricao());

		if (this.produto.getId() == null) {
			new DAO<Produto>(Produto.class).adiciona(this.produto);
		} else {
			new DAO<Produto>(Produto.class).atualiza(this.produto);
		}

		this.produto = new Produto();

		return "produto?faces-redirect=true";
	}

	public void carregar(Produto produto) {
		System.out.println("Carregando produto");
		this.produto = produto;
	}

	public void remover(Produto produto) {
		System.out.println("Removendo produto");
		new DAO<Produto>(Produto.class).remove(produto);
	}

	public void carregarProdutoPelaId() {
		this.produto = new DAO<Produto>(Produto.class).buscaPorId(produtoId);
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Integer produtoId) {
		this.produtoId = produtoId;
	}
	
}
