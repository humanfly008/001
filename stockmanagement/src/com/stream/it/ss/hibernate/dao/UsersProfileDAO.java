package com.stream.it.ss.hibernate.dao;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.stream.it.ss.hibernate.domain.UsersProfile;


@Component
public class UsersProfileDAO extends BaseDAO{
	
	@SuppressWarnings("unchecked")
	public UsersProfile authentificationUserLogon(String userId, String password){
		UsersProfile userAuthfication = null;
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		List<UsersProfile> result = (List<UsersProfile>) hibernateTemplate.find("from UsersProfile where userId = ? and password = ?", userId, password); 
		if(result.size()>0)
			userAuthfication = result.get(0);
			
		return userAuthfication;
	}
	
	@SuppressWarnings("unchecked")
	public List<UsersProfile> listAll() throws Exception{   
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        List<UsersProfile> result = (List<UsersProfile>) hibernateTemplate.find("from UsersProfile");        
                
        return result;
    }
	
	public UsersProfile findById(Integer id)throws Exception{
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		UsersProfile usersProfile = (UsersProfile)hibernateTemplate.get(UsersProfile.class, id);
		
		return usersProfile;
	}
	
	@SuppressWarnings("unchecked")
	public UsersProfile findByUserId(String userId)throws Exception{
		UsersProfile usersProfile = null;
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		List<UsersProfile> result = (List<UsersProfile>) hibernateTemplate.find("from UsersProfile where userId = ?", userId); 
		if(result.size()>0)
			usersProfile = result.get(0);
			
		return usersProfile;
	}
	
	@SuppressWarnings("unchecked")
	public UsersProfile findByUserId(String id, String userId)throws Exception{
		UsersProfile usersProfile = null;
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		List<UsersProfile> result = (List<UsersProfile>) hibernateTemplate.find("from UsersProfile where id = ? and userId = ?", Integer.parseInt(id), userId); 
		if(result.size()>0)
			usersProfile = result.get(0);
			
		return usersProfile;
	}
	
	public void create(UsersProfile usersProfile)throws Exception{
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        hibernateTemplate.save(usersProfile);        
    }
    
    public void update(UsersProfile usersProfile)throws Exception{     
    	HibernateTemplate hibernateTemplate = getHibernateTemplate();
        hibernateTemplate.update(usersProfile);             
    } 
    
    public void delete(UsersProfile usersProfile)throws Exception{
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        hibernateTemplate.delete(usersProfile);
    }
}
