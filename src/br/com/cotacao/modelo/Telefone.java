package br.com.cotacao.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Telefone {

	@Id
	@GeneratedValue
	private Integer id;
	private String telefone;
	
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Integer getId() {
		return id;
	}
	
	
}
