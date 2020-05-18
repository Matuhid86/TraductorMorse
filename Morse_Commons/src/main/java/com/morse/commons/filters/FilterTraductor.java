package com.morse.commons.filters;

import com.base.commons.filters.FilterHibernate;
import com.morse.commons.enums.TipoTraduccion;

@SuppressWarnings("serial")
public class FilterTraductor extends FilterHibernate {

	private String texto;
	private TipoTraduccion tipo;
	
	public String getTexto() {
		return this.texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	public TipoTraduccion getTipo() {
		return this.tipo;
	}
	public void setTipo(TipoTraduccion tipo) {
		this.tipo = tipo;
	}	
}	
