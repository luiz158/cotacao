package br.com.cotacao.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.cotacao.modelo.Cidade;
import br.com.cotacao.repositorio.CidadeDAO;

@FacesConverter(forClass = Cidade.class)
public class CidadeConverter implements Converter {

	@Inject
	private CidadeDAO cidades;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Cidade retorno = null;
		
		if (value != null) {
			retorno = this.cidades.porCodigo(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Cidade) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());
			
			return retorno;
		}
		
		return "";
	}

}