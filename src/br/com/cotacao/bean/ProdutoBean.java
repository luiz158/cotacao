package br.com.cotacao.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.cotacao.modelo.Produto;
import br.com.cotacao.repositorio.ProdutoDAO;

@Named
@ViewScoped
public class ProdutoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Produto produto = new Produto();
	
	private Integer produtoId;
	
	@Inject
	private ProdutoDAO dao;

	public List<Produto> getProdutos() {
		return dao.listaTodos();
	}

	public String gravar() {
		System.out.println("Gravando produto " + this.produto.getDescricao());

		if (this.produto.getId() == null) {
			dao.adiciona(this.produto);
		} else {
			dao.atualiza(this.produto);
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
		dao.remove(produto);
	}

	public void carregarProdutoPelaId() {
		this.produto = dao.buscaPorId(produtoId);
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
