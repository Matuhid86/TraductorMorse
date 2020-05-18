package com.morse.commons.enums;

import java.util.ArrayList;
import java.util.List;

public enum TipoTraduccion {	
	
	A_HUMANO(0, "A texto Humano"), A_MORSE(1, "A texto Morse");
	
	private int id;
	private String descripcion;

	TipoTraduccion(int id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}

	public int getId() {
		return id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public static TipoTraduccion getEnum(int id) {
		for (int indice = 0; indice < TipoTraduccion.values().length; indice++) {
			TipoTraduccion entity = TipoTraduccion.values()[indice];
			if (entity.getId() == id)
				return entity;
		}
		return null;
	}

	public static TipoTraduccion getEnum(String descripcion) {
		for (int indice = 0; indice < TipoTraduccion.values().length; indice++) {
			TipoTraduccion entity = TipoTraduccion.values()[indice];
			if (entity.getDescripcion().equals(descripcion))
				return entity;
		}
		return null;
	}

	public static List<TipoTraduccion> getAll() {
		List<TipoTraduccion> entities = new ArrayList<TipoTraduccion>();

		for (int indice = 0; indice < TipoTraduccion.values().length; indice++)
			entities.add(TipoTraduccion.values()[indice]);

		return entities;
	}
}


