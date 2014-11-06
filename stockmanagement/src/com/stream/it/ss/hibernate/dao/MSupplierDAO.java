package com.stream.it.ss.hibernate.dao;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.stream.it.ss.hibernate.domain.MSupplier;


@Component
public class MSupplierDAO extends BaseDAO{
	
	@SuppressWarnings("unchecked")
	public List<MSupplier> listAll() throws Exception{   
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        List<MSupplier> result = (List<MSupplier>) hibernateTemplate.find("from MSupplier");        
                
        return result;
    }
	
	public MSupplier findById(Integer id)throws Exception{
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		MSupplier item = (MSupplier)hibernateTemplate.get(MSupplier.class, id);
		
		return item;
	}
	
    public void create(MSupplier supplier)throws Exception{
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        hibernateTemplate.save(supplier);        
    }
    
    public void update(MSupplier supplier)throws Exception{     
    	HibernateTemplate hibernateTemplate = getHibernateTemplate();
        hibernateTemplate.update(supplier);             
    } 
    
    public void delete(MSupplier supplier)throws Exception{
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        hibernateTemplate.delete(supplier);
    }
    
}
