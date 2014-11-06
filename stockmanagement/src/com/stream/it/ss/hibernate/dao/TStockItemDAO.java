package com.stream.it.ss.hibernate.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.stream.it.ss.hibernate.domain.TStockItem;


@Component
public class TStockItemDAO extends BaseDAO{
	
	@SuppressWarnings("unchecked")
	public List<TStockItem> listAll() throws Exception{   
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        List<TStockItem> result = (List<TStockItem>) hibernateTemplate.find("from TStockItem");        
                
        return result;
    }
	
	public TStockItem findById(Integer id)throws Exception{
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		TStockItem stock = (TStockItem)hibernateTemplate.get(TStockItem.class, id);
		
		return stock;
	}
	
    public void create(TStockItem stock )throws Exception{
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        stock.setStockCode(this.generateTransactionId());
        hibernateTemplate.save(stock);        
    }
    
    public void update(TStockItem stock )throws Exception{     
    	HibernateTemplate hibernateTemplate = getHibernateTemplate();
        hibernateTemplate.update(stock);             
    } 
    
    public void delete(TStockItem stock )throws Exception{
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        hibernateTemplate.delete(stock);
    }
    
    
    private String generateTransactionId() throws Exception {
		String tId = "";
		String nowDateStr = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date nowDate = new Date();
		nowDateStr = dateFormat.format(nowDate);

		int counterInt = 0;
		short maxPid = 4;
		
		tId = this.findMaxTransactionId();
		if(tId == null)
			counterInt = 0000;
		else
			counterInt = Integer.parseInt(tId.substring(tId.length()-4,tId.length()));
		
		
		StringBuffer pidSBuffer = new StringBuffer("T01-");
		pidSBuffer.append(nowDateStr);
		pidSBuffer.append("-");
		
		counterInt++;
		for(int pos=maxPid-String.valueOf(counterInt).length(); pos>0; pos--){
			pidSBuffer.append("0");
		}			
		pidSBuffer.append(counterInt);
		tId = pidSBuffer.toString();
		
		return tId;
	}
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public String findMaxTransactionId() throws Exception{   
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        String transactionId = "";
        List<String> result = (List) hibernateTemplate.find("select max(stockCode)as maxId from TStockItem");  
        	if(result != null && result.size() > 0)
        		transactionId = result.get(0);  
        
        return transactionId;
    }
}
