package com.stream.it.ss.hibernate.dao;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.stream.it.ss.hibernate.domain.MProductItemGroup;


@Component
public class MProductItemGroupDAO extends BaseDAO{
	
	@SuppressWarnings("unchecked")
	public List<MProductItemGroup> listAll() throws Exception{   
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        List<MProductItemGroup> result = (List<MProductItemGroup>) hibernateTemplate.find("from MProductItemGroup");        
                
        return result;
    }
	
	@SuppressWarnings("unchecked")
	public List<MProductItemGroup> list(Integer productId) throws Exception{   
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        List<MProductItemGroup> result = (List<MProductItemGroup>) hibernateTemplate.find("from MProductItemGroup where mProductItemGroupPK.productId = ?", productId);        
                
        return result;
    }
	
    public void create(MProductItemGroup productItemGroup)throws Exception{
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        hibernateTemplate.save(productItemGroup);        
    }
    
    public void update(MProductItemGroup productItemGroup)throws Exception{     
    	HibernateTemplate hibernateTemplate = getHibernateTemplate();
        hibernateTemplate.update(productItemGroup);             
    } 
    
    public void delete(MProductItemGroup productItemGroup)throws Exception{
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        hibernateTemplate.delete(productItemGroup);
    }
    
    public void delete(Integer productId)throws Exception{
    	HibernateTemplate hibernateTemplate = getHibernateTemplate();
    	hibernateTemplate.getSessionFactory().openSession().createSQLQuery("DELETE FROM M_PRODUCT_ITEM_GROUP WHERE PRODUCT_ID = "+productId).executeUpdate();
    }
    
}
