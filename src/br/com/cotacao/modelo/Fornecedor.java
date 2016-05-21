package br.com.cotacao.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Fornecedor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	private String nome;
	private String cnpj;
	private String contato;
	@OneToOne
	private Endereco endereco;
	@OneToMany(fetch=FetchType.EAGER)
	private List<Telefone> telefones = new ArrayList<Telefone>();

	public List<Telefone> getTelefones() {
		return telefones;
	}
	public void adicionaTelefone(Telefone telefone) {
		this.telefones.add(telefone);
	}
	public void removeTelefone(Telefone telefone) {
		this.telefones.remove(telefone);		
	}
	
	public Integer getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getContato() {
		return contato;
	}
	public void setContato(String contato) {
		this.contato = contato;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}