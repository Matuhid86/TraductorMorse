package com.morse.commons.infrastructure.daos;

import java.util.ArrayList;
import java.util.List;


//import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
//import org.hibernate.criterion.Restrictions;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.commons.filters.FilterBase;
import com.base.commons.pojos.PagedResult;
import com.base.infrastructure.daos.DaoHibernate;
import com.morse.commons.filters.FilterCaracter;
//import com.morse.commons.filters.FilterCaracter;
import com.morse.commons.model.entities.Caracter;

@Service
public class DaoCaracter extends DaoHibernate<Caracter> {
	
	//@Autowired
	//private SessionFactory sessionFactory;
	
	@Override
	protected SessionFactory getSessionFactory() {
		return null;
	//	return sessionFactory;
	}

	protected Class<Caracter> getModelClass() {
		return Caracter.class;
	}
	
	// Este metodo se sobreescribe para poner los datos de forma estatica. Si no se sobreescribe, la herencia de clases
	// y hibernate se encarga de buscar sobre la base correspondiente estos datos.
	@Override
	public PagedResult<Caracter> find(FilterBase filterBase) throws Exception {
		List<Caracter> entities = new ArrayList<Caracter>();
		
		entities.add(new Caracter("A", ".-"));
		entities.add(new Caracter("B", "-..."));
		entities.add(new Caracter("C", "-.-."));
		entities.add(new Caracter("D", "-.."));
		entities.add(new Caracter("E", "."));
		entities.add(new Caracter("F", "..-."));
		entities.add(new Caracter("G", "--."));
		entities.add(new Caracter("H", "...."));
		entities.add(new Caracter("I", ".."));
		entities.add(new Caracter("J", ".---"));
		entities.add(new Caracter("K", "-.-"));
		entities.add(new Caracter("L", ".-.."));
		entities.add(new Caracter("M", "--"));
		entities.add(new Caracter("N", "-."));
		entities.add(new Caracter("O", "---"));
		entities.add(new Caracter("P", ".--."));
		entities.add(new Caracter("Q", "--.-"));
		entities.add(new Caracter("R", ".-."));
		entities.add(new Caracter("S", "..."));
		entities.add(new Caracter("T", "-"));
		entities.add(new Caracter("U", "..-"));
		entities.add(new Caracter("V", "...-"));
		entities.add(new Caracter("W", ".--"));
		entities.add(new Caracter("X", "-..-"));
		entities.add(new Caracter("Y", "-.--"));
		entities.add(new Caracter("Z", "--.."));
		entities.add(new Caracter("0", "-----"));
		entities.add(new Caracter("1", ".----"));
		entities.add(new Caracter("2", "..---"));
		entities.add(new Caracter("3", "...--"));
		entities.add(new Caracter("4", "....-"));
		entities.add(new Caracter("5", "....."));
		entities.add(new Caracter("6", "-...."));
		entities.add(new Caracter("7", "--..."));
		entities.add(new Caracter("8", "---.."));
		entities.add(new Caracter("9", "----."));
		entities.add(new Caracter("_", ".-.-.-")); //FULLSTOP
		
		if (filterBase != null) {
			List<Caracter> entitiesCaracter = entities;
			FilterCaracter filter = (FilterCaracter)filterBase;
			
			if (filter.getActivo() != null) {
				entitiesCaracter = new ArrayList<Caracter>();
						
				for (Caracter caracter : entities) {
					if (caracter.getActivo().equals(filter.getActivo()))
						entitiesCaracter.add(caracter);
				}
				
				entities = entitiesCaracter;
			}
		}
		
		return new PagedResult<Caracter>(entities.size(), entities);
	}
	
	@Override
	public Boolean isNewEntity(Caracter entity) throws Exception {
		return (entity.getHumano() == null || entity.getMorse() == null);
	}

	// Metodo para armar el "WHERE" de la SELECT armada por HIBERNATE
//	@Override
//	public void setFilters(Criteria criteria, FilterBase filterBase) {
//		if (filterBase != null && filterBase instanceof FilterCaracter) {
//			FilterCaracter filter = (FilterCaracter) filterBase;
//			
//			if (filter.getHumano() != null)
//				criteria = criteria.add(Restrictions.eq("this.humano", filter.getHumano()));
//			
//			if (filter.getMorse() != null)
//				criteria = criteria.add(Restrictions.eq("this.morse", filter.getMorse()));
//			
//			if (filter.getActivo() != null)
//				criteria = criteria.add(Restrictions.eq("this.activo", filter.getActivo()));
//			
//			super.setFilters(criteria, filter);
//		}
//	}
}
