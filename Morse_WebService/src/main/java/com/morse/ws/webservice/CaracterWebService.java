package com.morse.ws.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.base.service.services.ServiceBase;
import com.base.ws.webservice.WebServiceBase;
import com.morse.commons.filters.FilterCaracter;
import com.morse.commons.model.entities.Caracter;
import com.morse.commons.pojos.CaracterPojo;
import com.morse.commons.service.services.ServiceCaracter;

@RestController
@RequestMapping("/caracterWS")
public class CaracterWebService extends WebServiceBase<CaracterPojo, Caracter, FilterCaracter> {
	
	@Autowired
	private ServiceCaracter serviceCaracter;

	@Override
	protected ServiceBase<CaracterPojo, Caracter> getService() {
		return serviceCaracter;
	}
}
