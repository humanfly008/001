package com.stream.it.ss.hibernate.dao;

import java.io.Serializable;
import java.util.List;

import com.stream.it.ss.hibernate.domain.Functions;


import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;



@Component
public class FunctionsDAO extends BaseDAO implements Serializable{

	public List<Functions> listAll() throws Exception{   
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        List<Functions> result = (List) hibernateTemplate.find("from Functions");        
                
        return result;
    }
	
	public String findMAXId(){
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		List<String> result = hibernateTemplate.find("select max(funcId) from Functions");
		
		if(result != null){
			return result.get(0);
		}else{
			return null;
		}
	}
	
	public List<Functions> findNotIn(String sql) throws Exception{   
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        List<Functions> result = (List) hibernateTemplate.find("from Functions where func_id NOT IN ?",sql);        
                
        return result;
    }
	
	public boolean findFnNamebyName(String funcName) throws Exception{   
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        List result = (List) hibernateTemplate.find("from Functions where UPPER(func_name) = ?",funcName.toUpperCase());        
        if(result != null && result.size() > 0)
        	return true;
        else
        	return false;
    }
	
	public String findFnUrlbyName(String funcName) throws Exception{
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		List<Functions> result = (List) hibernateTemplate.find("from Functions where func_name = ?",funcName);
		if(result != null && result.size() > 0)
			return result.get(0).getFuncUrl();
		else
			return null;
	}
	public Functions findById(String id){
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		Functions Functions = (Functions)hibernateTemplate.get(Functions.class, id);
		
		return Functions;
	}
	  
    public void create(Functions functions)throws Exception{
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        hibernateTemplate.save(functions);        
    }
    
    public void update(Functions functions)throws Exception{     
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        hibernateTemplate.update(functions);               
    } 
    
    public void delete(Functions functions){
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        hibernateTemplate.delete(functions);
    }
    
}
