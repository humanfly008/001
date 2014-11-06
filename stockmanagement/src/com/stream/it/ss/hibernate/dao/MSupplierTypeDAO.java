package com.stream.it.ss.hibernate.dao;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.stream.it.ss.hibernate.domain.MSupplierType;


@Component
public class MSupplierTypeDAO extends BaseDAO{
	
	@SuppressWarnings("unchecked")
	public List<MSupplierType> listAll() throws Exception{   
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        List<MSupplierType> result = (List<MSupplierType>) hibernateTemplate.find("from MSupplierType");        
                
        return result;
    }
	
	public MSupplierType findById(Integer id)throws Exception{
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		MSupplierType supplierType = (MSupplierType)hibernateTemplate.get(MSupplierType.class, id);
		
		return supplierType;
	}
	
    public void create(MSupplierType supplierType)throws Exception{
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        hibernateTemplate.save(supplierType);        
    }
    
    public void update(MSupplierType supplierType)throws Exception{     
    	HibernateTemplate hibernateTemplate = getHibernateTemplate();
        hibernateTemplate.update(supplierType);             
    } 
    
    public void delete(MSupplierType supplierType)throws Exception{
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        hibernateTemplate.delete(supplierType);
    }
    
}
