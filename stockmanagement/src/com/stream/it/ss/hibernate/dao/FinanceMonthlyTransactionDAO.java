package com.stream.it.ss.hibernate.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.stream.it.ss.hibernate.domain.FinanceMonthlyTransaction;



@Component
public class FinanceMonthlyTransactionDAO extends BaseDAO{
	@SuppressWarnings("unchecked")
	public List<FinanceMonthlyTransaction> listAll() throws Exception{   
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        List<FinanceMonthlyTransaction> result = (List<FinanceMonthlyTransaction>) hibernateTemplate.find("from FinanceMonthlyTransaction");        
                
        return result;
    }
	
	public Long verifyFinance(int month, int year) throws Exception{   
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        List result = (List<Integer>) hibernateTemplate.find("select count(*) from FinanceMonthlyTransaction where month = ? and year = ?",month, year);        
                     
        return (Long) result.get(0);
    }

	public FinanceMonthlyTransaction findById(Integer id)throws Exception{
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		FinanceMonthlyTransaction product = (FinanceMonthlyTransaction)hibernateTemplate.get(FinanceMonthlyTransaction.class, id);
		
		return product;
	}
	
    public void create(FinanceMonthlyTransaction financeMonthlyTransaction)throws Exception{
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        hibernateTemplate.save(financeMonthlyTransaction);        
    }
    
    public void update(FinanceMonthlyTransaction financeMonthlyTransaction)throws Exception{     
    	HibernateTemplate hibernateTemplate = getHibernateTemplate();
        hibernateTemplate.update(financeMonthlyTransaction);             
    } 
    
    public void delete(FinanceMonthlyTransaction financeMonthlyTransaction)throws Exception{
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        hibernateTemplate.delete(financeMonthlyTransaction);
    }
    
    public void deleteByMonthYear(int month, int year) throws DataAccessResourceFailureException, HibernateException, IllegalStateException, SQLException{
    	logger.info("begin....");
    	logger.info("month : "+month);
    	logger.info("year  : "+year);
    	String sql = "DELETE FROM finance_monthly_transaction WHERE MONTH = "+month+" AND YEAR = "+year;
    	getSession().connection().createStatement().execute(sql);    	
    	
    	logger.info("end....");
    }
}
