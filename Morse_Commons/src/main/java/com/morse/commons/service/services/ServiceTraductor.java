package com.morse.commons.service.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.morse.commons.enums.TipoTraduccion;
import com.morse.commons.filters.FilterCaracter;
import com.morse.commons.filters.FilterTraductor;
import com.morse.commons.pojos.CaracterPojo;

@Service
public class ServiceTraductor {
	
	@Autowired
	private ServiceCaracter serviceCaracter;
	
	// Se genera un unico metodo para traducir en donde se le pasa el texto y el tipo de traduccion a realizar.
	public String traducir(FilterTraductor filter) throws Exception {
		String textoTraducido = "";
		String palabra = "";
		List<String> palabrasTraducidas = new ArrayList<String>();
		Integer cantidadEspaciosEntrePalabras = 1;
		Integer cantidadEspaciosEntreCaracteres = 0;

		if (filter != null && filter.getTexto() != null) {
			String texto = filter.getTexto();
	
			// Se obtienen los caracteres y su conversion
			HashMap<String, String> caracteresGuardados = this.getCaracteres(filter.getTipo());
			
			// Se obtienen la cantidad de espacios entre palabras y espacio entre caracteres
			HashMap<String, Integer> cantidadEspacios = this.getCantidadesEspacios(texto, filter.getTipo());
			
			Integer cantidadSeguidaDeEspacios = 0;
			
			cantidadEspaciosEntrePalabras = cantidadEspacios.get("P");
			cantidadEspaciosEntreCaracteres = cantidadEspacios.get("C");
			
			if (cantidadEspaciosEntrePalabras == cantidadEspaciosEntreCaracteres)
				cantidadEspaciosEntrePalabras++;

			// Se recorre caracter a caracter y se van formando las palabras ya traducidas en base a la cantidad
			// de espacios entre caracteres y entre palabras
			
 			// Se divide la logica para las diferentes traducciones 
			if (filter.getTipo().equals(TipoTraduccion.A_MORSE)) {
				for (char caracterHumano : texto.toCharArray()) {
					if (caracterHumano != ' ') {
						String caracterTraducido = caracteresGuardados.get(String.valueOf(caracterHumano));
						
						if (caracterTraducido == null)
							throw new Exception("No es posible traducir el caracter: '" + caracterHumano + "'");
						
						palabra = palabra + caracterTraducido + " ";
						cantidadSeguidaDeEspacios = 0;
					}
					else {
						cantidadSeguidaDeEspacios++;
						
						if (cantidadSeguidaDeEspacios == cantidadEspaciosEntrePalabras) {
							palabrasTraducidas.add(palabra);
							palabra = "";
						}
					}
				}
				
				cantidadEspaciosEntreCaracteres = 1;
				cantidadEspaciosEntrePalabras = 2;
			}
			
			if (filter.getTipo().equals(TipoTraduccion.A_HUMANO)) {
				String caracterATraducir = "";
				
				for (char caracterMorse : texto.toCharArray()) { 
					if (caracterMorse != ' ') {
						caracterATraducir = caracterATraducir + String.valueOf(caracterMorse);
						cantidadSeguidaDeEspacios = 0;
					}
					else {
						cantidadSeguidaDeEspacios++;
						
						if (caracterATraducir != "") {
							String caracterPalabra = caracteresGuardados.get(caracterATraducir);
							
							if (caracterPalabra == null)
								throw new Exception("No es posible traducir el caracter: '" + caracterATraducir + "'");
							
							if (caracterPalabra == "_") {
								caracterATraducir = "";
								break;
							}
							else								
								palabra = palabra + caracteresGuardados.get(caracterATraducir);
						}
						
						if (cantidadSeguidaDeEspacios == cantidadEspaciosEntrePalabras) {
							if (palabra != "")
								palabrasTraducidas.add(palabra);
		
							palabra = "";
						}
						
						caracterATraducir = "";
					}
				}
				
				if (caracterATraducir != "") {
					String caracterPalabra = caracteresGuardados.get(caracterATraducir);
					
					if (caracterPalabra == null)
						throw new Exception("No es posible traducir el caracter: '" + caracterATraducir + "'");
					
					if (caracterPalabra != "_")
						palabra = palabra + caracterPalabra;
				}

				cantidadEspaciosEntrePalabras = 1;
			}
		}
		
		if (palabra != "")
			palabrasTraducidas.add(palabra);
		
		// Una vez obtenidas las palabras traducidas, se procede a armar el texto con los espacios entre palabras
		for (String palabraTraducida : palabrasTraducidas) {
			if (textoTraducido != "") {
				for (Integer numeroEspacio = 1; numeroEspacio <= cantidadEspaciosEntrePalabras; numeroEspacio++)
					textoTraducido = textoTraducido + " ";
			}
			textoTraducido = textoTraducido + palabraTraducida.trim();
		}
		
		return textoTraducido.trim();
	}
	
	private HashMap<String, Integer> getCantidadesEspacios(String texto, TipoTraduccion tipo) {
		Integer cantidadEspaciosEntrePalabras = 1;
		Integer cantidadEspaciosEntreCaracteres = 0;
		Integer cantidadSeguidaDeEspacios = 0;
		List<Integer> posicionesEspacios = this.getPosiciones(texto, ' ');
		HashMap<String, Integer> cantidadesEspacios = new HashMap<String, Integer>();
		
		// Veo cuandos espacios tengo seguidos. 
		// Primero obtengo la cantidad de espacios seguido maxima y, para mi, es el espacio entre palabras
		// (SIEMPRE EL ESPACIO ENTRE PALABRAS DEBE SER MAYOR AL ESPACIO ENTRE CARACTERES)
		
		for (Integer indexEspacio = 0; indexEspacio < posicionesEspacios.size(); indexEspacio++) {
			if (indexEspacio == 0 || ((posicionesEspacios.get(indexEspacio) - 1) == posicionesEspacios.get(indexEspacio - 1))) {
				cantidadSeguidaDeEspacios++;
			}
			else {
				if (cantidadEspaciosEntrePalabras < cantidadSeguidaDeEspacios)
					cantidadEspaciosEntrePalabras = cantidadSeguidaDeEspacios;
				cantidadSeguidaDeEspacios = 1;
			}
		}
		
		if (cantidadEspaciosEntrePalabras < cantidadSeguidaDeEspacios)
			cantidadEspaciosEntrePalabras = cantidadSeguidaDeEspacios;
		
		// Luego obtengo la cantidad de espacios seguida que es menor a la cantidad de espacios entre palabras y esa es la 
		// cantidad de espacios entre caracteres.
		
		for (Integer indexEspacio = 0; indexEspacio < posicionesEspacios.size(); indexEspacio++) {
			if (indexEspacio > 0 && ((posicionesEspacios.get(indexEspacio) - 1) == posicionesEspacios.get(indexEspacio - 1))) {
				cantidadSeguidaDeEspacios++;
			}
			else {
				if (cantidadSeguidaDeEspacios != cantidadEspaciosEntrePalabras)
					cantidadEspaciosEntreCaracteres = cantidadSeguidaDeEspacios;
				cantidadSeguidaDeEspacios = 1;
			}
		}
		
		if (cantidadSeguidaDeEspacios != cantidadEspaciosEntrePalabras)
			cantidadEspaciosEntreCaracteres = cantidadSeguidaDeEspacios;
		
		cantidadesEspacios.put("C", cantidadEspaciosEntreCaracteres);
		cantidadesEspacios.put("P", cantidadEspaciosEntrePalabras);

		return cantidadesEspacios;
	}
	
	private List<Integer> getPosiciones(String texto, char caracter) {
		List<Integer> posiciones = new ArrayList<Integer>();
		
		// Guardo las posiciones donde estan los espacios.
		
		for (Integer index = 0; index < texto.length(); index++) {
			char caracterTexto = texto.charAt(index);
			
			if (caracterTexto == caracter)
				posiciones.add(index);
		}
		
		return posiciones;
	}
	
	private HashMap<String, String> getCaracteres(TipoTraduccion tipoTraduccion) {
		HashMap<String, String> caracteres = new HashMap<String, String>();
		FilterCaracter filter = new FilterCaracter();
		filter.setActivo(true);
		
		try {
			// Obtengo los caracteres guardados y armo el HashMap para que me quede como KEY el caracter a traducir de acuerdo al 
			// tipo de traduccion que quiero hacer.
			List<CaracterPojo> caracteresGuardados = this.serviceCaracter.find(filter).getEntities();
			
			for (CaracterPojo caracter : caracteresGuardados) {
				if (tipoTraduccion.equals(TipoTraduccion.A_HUMANO)) {
					if (!caracteres.containsKey(caracter.getMorse()))
						caracteres.put(caracter.getMorse(), caracter.getHumano());
				}
				if (tipoTraduccion.equals(TipoTraduccion.A_MORSE)) {
					if (!caracteres.containsKey(caracter.getHumano()))
						caracteres.put(caracter.getHumano(), caracter.getMorse());
				}
			}
		}
		catch (Exception ex) { }
		
		return caracteres;
	}
}