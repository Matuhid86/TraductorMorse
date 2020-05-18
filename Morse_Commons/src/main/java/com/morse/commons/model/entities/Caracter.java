package com.morse.commons.model.entities;

//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;

import com.base.model.entities.EntityBase;

@SuppressWarnings("serial")
//@Entity
//@Table(name = "CARACTER")
public class Caracter extends EntityBase {	
	
	private String humano;
	private String morse;
	private Boolean activo;
	
	protected Caracter() { 
		this.activo = true;
	}
	
	public Caracter(String humano, String morse) {
		this.humano = humano;
		this.morse = morse;
		this.activo = true;
	}
	
	//@Id 
	//@Column(name = "HUMANO")
	public String getHumano() {
		return humano;
	}
	public void setHumano(String humano) {
		this.humano = humano;
	}
	
	//@Id
	//@Column(name = "MORSE")
	public String getMorse() {
		return this.morse;
	}
	public void setMorse(String morse) {
		this.morse = morse;
	}
	
	//@Column(name = "ACTIVO")
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
	@Override
	public boolean equals(Object obj)
	{
      if (obj != null && obj instanceof Caracter)
      {
    	  Caracter entity = (Caracter)obj;

    	  if (this.humano != null && entity.getHumano() != null && this.morse != null && entity.getMorse() != null)
        	  return this.humano.equals(entity.getHumano()) && this.morse.equals(entity.getMorse());
      }
      
      return super.equals(obj);
  	}
	
	@Override
	public int hashCode() {
		if (this.humano != null && this.morse != null)
			return this.humano.hashCode() + this.morse.hashCode();
		
		return super.hashCode();
	}
}

