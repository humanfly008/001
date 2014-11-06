package com.stream.it.ss.hibernate.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.stream.it.ss.hibernate.domain.Menu;

@Component
public class MenuDAO extends BaseDAO{
	private static final Log logger = LogFactory.getLog(MenuDAO.class);

	public List<Menu> listAll() throws Exception{   
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        List<Menu> result = (List) hibernateTemplate.find("from Menu");        
                
        return result;
    }
	
	public List<Menu> listMenuSource() throws Exception{   
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        List<Menu> result = (List) hibernateTemplate.find("select menuSourceId from Menu");        
                
        return result;
    }
	
	public Menu findById(String id)throws Exception{
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		Menu Menu = (Menu)hibernateTemplate.get(Menu.class, id);
		
		return Menu;
	}
	  
	public String findMAXId()throws Exception{
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		List<String> result = hibernateTemplate.find("select max(menuId) from Menu");
		
		if(result != null){
			return result.get(0);
		}else{
			return null;
		}
	}

	public boolean findMenuName(String menuname)throws Exception{
		boolean result = true;
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		List resultList = hibernateTemplate.find("select UPPER(menuName) from Menu where UPPER(menuName) = ?",menuname.toUpperCase());
		if(resultList.size()>0){
			result = false;
		}
		
		return result;
	}
	
	public boolean findFunction(String id)throws Exception{
		boolean result = true;
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		List resultList = hibernateTemplate.find("select menuId from Menu where menuSourceId like ?","%"+id+"%");
		if(resultList.size()>0){
			result = false;
		}
		
		return result;
	}
	
	public List<Menu> findMenuListBySourceId(String functionId)throws Exception{
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		List<Menu> result = (List) hibernateTemplate.find("from Menu where 1=1 and menuSourceId like ?","%"+functionId+"%");
		
		return result;
	}
	
    public void create(Menu menu)throws Exception{
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        hibernateTemplate.save(menu);        
    }
    
    public void update(Menu menu)throws Exception{     
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        hibernateTemplate.update(menu);               
    } 
    
    public void delete(Menu menu)throws Exception{
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        hibernateTemplate.delete(menu);
    }
    
}
