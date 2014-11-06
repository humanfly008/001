package com.stream.it.ss.hibernate.dao;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.stream.it.ss.hibernate.domain.SalaryTransaction;


@Component
public class SalaryTrasnactionDAO extends BaseDAO{
	
	@SuppressWarnings("unchecked")
	public List<SalaryTransaction> listAll() throws Exception{   
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        List<SalaryTransaction> result = (List<SalaryTransaction>) hibernateTemplate.find("from SalaryTransaction");        
                
        return result;
    }
	
	public List<SalaryTransaction> listByUserId(String transactionType, String userId, String id, int month, int year) throws Exception{   
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        List<SalaryTransaction> result = (List<SalaryTransaction>) hibernateTemplate.find("from SalaryTransaction where transactionType = ? and userId = ? and id = ? and month = ? and year = ?",transactionType, userId, id, month, year);        
                
        return result;
    }
	
	public SalaryTransaction findById(Integer id)throws Exception{
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		SalaryTransaction salaryTransaction = (SalaryTransaction)hibernateTemplate.get(SalaryTransaction.class, id);
		
		return salaryTransaction;
	}
	
    public void create(SalaryTransaction salaryTransaction)throws Exception{
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        hibernateTemplate.save(salaryTransaction);        
    }
    
    public void update(SalaryTransaction salaryTransaction)throws Exception{     
    	HibernateTemplate hibernateTemplate = getHibernateTemplate();
        hibernateTemplate.update(salaryTransaction);             
    } 
    
    public void delete(SalaryTransaction salaryTransaction)throws Exception{
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        hibernateTemplate.delete(salaryTransaction);
    }
    
}
