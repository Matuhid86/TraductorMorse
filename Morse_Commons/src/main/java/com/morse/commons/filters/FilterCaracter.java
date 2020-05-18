package com.morse.commons.filters;

import com.base.commons.filters.FilterHibernate;

@SuppressWarnings("serial")
public class FilterCaracter extends FilterHibernate {

	private String humano;
	private String morse;
	private Boolean activo;
	
	public String getHumano() {
		return humano;
	}
	public void setHumano(String humano) { 
		this.humano = humano;
	}
	
	public String getMorse() {
		return morse;
	}
	public void setMorse(String morse) { 
		this.morse = morse;
	}
	
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) { 
		this.activo = activo;
	}
}	
