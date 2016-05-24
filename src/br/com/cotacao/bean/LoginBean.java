package br.com.cotacao.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.cotacao.modelo.Usuario;
import br.com.cotacao.repositorio.UsuarioDAO;

@Named
@ViewScoped
public class LoginBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public String efetuaLogin() {
		System.out.println("Fazendo login do usuário "
				+ this.usuario.getEmail());
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		boolean existe = new UsuarioDAO().existe(this.usuario);

		if (existe) {
			
			context.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);
			return "produtos?faces-redirect=true";
		}

		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Usuário ou senha incorretos"));
		
		return "login?faces-redirect=true";
	}
	
	public String deslogar(){
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("usuarioLogado");
		
		return "login?faces-redirect=true";
	}

}
