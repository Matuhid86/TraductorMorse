package com.morse.ws.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.morse.commons.enums.TipoTraduccion;
import com.morse.commons.filters.FilterTraductor;
import com.morse.commons.service.services.ServiceTraductor;

@RestController
@RequestMapping("/translate")
public class TraductorWebService {
	
	@Autowired
	private ServiceTraductor serviceTraductor;

	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value = "/2text", method = RequestMethod.POST)
	public @ResponseBody String toText(@RequestBody FilterTraductor filter) throws Exception {
		if (filter == null || filter.getTexto() == null)
			throw new Exception("Faltan parametros.");
			
		filter.setTipo(TipoTraduccion.A_HUMANO);
		return this.serviceTraductor.traducir(filter);
	}
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value = "/2morse", method = RequestMethod.POST)
	public @ResponseBody String toMorse(@RequestBody FilterTraductor filter) throws Exception {
		if (filter == null || filter.getTexto() == null)
			throw new Exception("Faltan parametros.");
		
		filter.setTipo(TipoTraduccion.A_MORSE);
		return this.serviceTraductor.traducir(filter);
	}
	
	@ExceptionHandler({ Exception.class })
    public String handleException(Exception ex) {
		return ex.getMessage();
    }
}
