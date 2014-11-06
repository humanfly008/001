package com.stream.it.ss.hibernate.dao;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.stream.it.ss.hibernate.domain.MItemType;


@Component
public class MItemTypeDAO extends BaseDAO{

	@SuppressWarnings("unchecked")
	public List<MItemType> listAll() throws Exception{   
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        List<MItemType> result = (List<MItemType>) hibernateTemplate.find("from MItemType");        
                
        return result;
    }
	
	public MItemType findById(Integer id)throws Exception{
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		MItemType item = (MItemType)hibernateTemplate.get(MItemType.class, id);
		
		return item;
	}
	
    public void create(MItemType itemType)throws Exception{
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        hibernateTemplate.save(itemType);        
    }
    
    public void update(MItemType itemType)throws Exception{     
    	HibernateTemplate hibernateTemplate = getHibernateTemplate();
        hibernateTemplate.update(itemType);             
    } 
    
    public void delete(MItemType itemType)throws Exception{
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        hibernateTemplate.delete(itemType);
    }
    
}
