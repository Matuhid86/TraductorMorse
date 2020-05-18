package com.morse.commons.pojos;

import com.base.commons.pojos.EntityBasePojo;

@SuppressWarnings("serial")
public class CaracterPojo extends EntityBasePojo {	
	
	private String humano;
	private String morse;
	private Boolean activo;
	
	protected CaracterPojo() { 
		this.activo = true;
	}
	
	public CaracterPojo(String humano, String morse) {
		this.humano = humano;
		this.morse = morse;
		this.activo = true;
	}

	public String getHumano() {
		return humano;
	}
	public void setHumano(String humano) {
		this.humano = humano;
	}

	public String getMorse() {
		return this.morse;
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
	
	@Override
	public boolean equals(Object obj)
	{
      if (obj != null && obj instanceof CaracterPojo)
      {
    	  CaracterPojo pojo = (CaracterPojo)obj;

    	  if (this.humano != null && pojo.getHumano() != null && this.morse != null && pojo.getMorse() != null)
        	  return this.humano.equals(pojo.getHumano()) && this.morse.equals(pojo.getMorse());
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

