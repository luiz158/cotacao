package br.com.cotacao.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.cotacao.modelo.Estado;
import br.com.cotacao.repositorio.EstadoDAO;


@FacesConverter(forClass = Estado.class)
public class EstadoConverter implements Converter {

	@Inject
	private EstadoDAO estados;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Estado retorno = null;
		
		if (value != null) {
			retorno = this.estados.porCodigo(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Estado) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());
			
			return retorno;
		}
		
		return "";
	}

}

