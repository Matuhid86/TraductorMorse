package com.morse.commons.service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import com.base.commons.filters.FilterBase;
import com.base.commons.pojos.PagedResult;
import com.base.infrastructure.daos.DaoBase;
import com.base.service.mappers.GenericMapper;
import com.base.service.services.ServiceBase;
import com.morse.commons.infrastructure.daos.DaoCaracter;
import com.morse.commons.model.entities.Caracter;
import com.morse.commons.service.mappers.CaracterMapper;
import com.morse.commons.pojos.CaracterPojo;

@Service
public class ServiceCaracter extends ServiceBase<CaracterPojo, Caracter> {
	
	@Autowired
	private DaoCaracter dao;
	
	@Override
	protected DaoBase<Caracter> getDao() {
		return dao;
	}
	
	@Override
	protected GenericMapper<CaracterPojo, Caracter> getMapper() {
		return new CaracterMapper();
	}

	//@Transactional(rollbackFor = Exception.class, transactionManager = "transactionManager")
	@Override
	public CaracterPojo findOne(FilterBase filterBase) throws Exception {
		return super.findOne(filterBase);
	}
	
	//@Transactional(rollbackFor = Exception.class, transactionManager = "transactionManager")
	@Override
	public PagedResult<CaracterPojo> find(FilterBase filterBase) throws Exception {
		return super.find(filterBase);
	}
	
	//@Transactional(rollbackFor = Exception.class, transactionManager = "transactionManager")
	@Override
	public CaracterPojo saveOrUpdate(CaracterPojo pojo, FilterBase filterBase) throws Exception {
		return super.saveOrUpdate(pojo, filterBase);
	}
	
	//@Transactional(rollbackFor = Exception.class, transactionManager = "transactionManager")
	@Override
	public List<CaracterPojo> saveOrUpdateList(List<CaracterPojo> pojos, FilterBase filterBase) throws Exception {
		return super.saveOrUpdateList(pojos, filterBase);
	}
	
	//@Transactional(rollbackFor = Exception.class, transactionManager = "transactionManager")
	@Override
	public void delete(CaracterPojo pojo, FilterBase filterBase) throws Exception {
		super.delete(pojo, filterBase);
	}

	//@Transactional(rollbackFor = Exception.class, transactionManager = "transactionManager")
	@Override
	public void deleteList(List<CaracterPojo> pojos, FilterBase filterBase) throws Exception {
		super.deleteList(pojos, filterBase);
	}
}