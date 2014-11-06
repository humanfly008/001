package com.stream.it.ss.hibernate.dao;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.stream.it.ss.hibernate.domain.MItem;


@Component
public class MItemDAO extends BaseDAO{
	
	@SuppressWarnings("unchecked")
	public List<MItem> listAll() throws Exception{   
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        List<MItem> result = (List<MItem>) hibernateTemplate.find("from MItem");        
                
        return result;
    }
	
	public MItem findById(Integer id)throws Exception{
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		MItem item = (MItem)hibernateTemplate.get(MItem.class, id);
		
		return item;
	}
	
    public void create(MItem item)throws Exception{
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        hibernateTemplate.save(item);        
    }
    
    public void update(MItem item)throws Exception{     
    	HibernateTemplate hibernateTemplate = getHibernateTemplate();
        hibernateTemplate.update(item);             
    } 
    
    public void delete(MItem item)throws Exception{
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        hibernateTemplate.delete(item);
    }
    
}
