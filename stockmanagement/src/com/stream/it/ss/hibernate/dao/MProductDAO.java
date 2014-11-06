package com.stream.it.ss.hibernate.dao;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.stream.it.ss.hibernate.domain.MProduct;


@Component
public class MProductDAO extends BaseDAO{
	
	@SuppressWarnings("unchecked")
	public List<MProduct> listAll() throws Exception{   
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        List<MProduct> result = (List<MProduct>) hibernateTemplate.find("from MProduct");        
                
        return result;
    }
	
	public MProduct findById(Integer id)throws Exception{
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		MProduct product = (MProduct)hibernateTemplate.get(MProduct.class, id);
		
		return product;
	}
	
    public void create(MProduct product)throws Exception{
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        hibernateTemplate.save(product);        
    }
    
    public void update(MProduct product)throws Exception{     
    	HibernateTemplate hibernateTemplate = getHibernateTemplate();
        hibernateTemplate.update(product);             
    } 
    
    public void delete(MProduct product)throws Exception{
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        hibernateTemplate.delete(product);
    }
    
}
