package com.stream.it.ss.hibernate.dao;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.stream.it.ss.hibernate.domain.MUnit;


@Component
public class MUnitDAO extends BaseDAO{
	
	@SuppressWarnings("unchecked")
	public List<MUnit> listAll() throws Exception{   
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        List<MUnit> result = (List<MUnit>) hibernateTemplate.find("from MUnit");        
                
        return result;
    }
	
	public MUnit findById(Integer id)throws Exception{
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		MUnit unit = (MUnit)hibernateTemplate.get(MUnit.class, id);
		
		return unit;
	}
	
    public void create(MUnit unit)throws Exception{
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        hibernateTemplate.save(unit);        
    }
    
    public void update(MUnit unit)throws Exception{     
    	HibernateTemplate hibernateTemplate = getHibernateTemplate();
        hibernateTemplate.update(unit);             
    } 
    
    public void delete(MUnit unit)throws Exception{
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        hibernateTemplate.delete(unit);
    }
    
}
