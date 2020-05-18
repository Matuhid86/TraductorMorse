package com.morse.commons.service.mappers;

import com.base.service.mappers.GenericMapperDozer;
import com.morse.commons.model.entities.Caracter;
import com.morse.commons.pojos.CaracterPojo;

public class CaracterMapper extends GenericMapperDozer<CaracterPojo, Caracter> {

	@Override
	protected Class<Caracter> getEntityClass() {
		return Caracter.class;
	}

	@Override
	protected Class<CaracterPojo> getPojoClass() {
		return CaracterPojo.class;
	}
}

