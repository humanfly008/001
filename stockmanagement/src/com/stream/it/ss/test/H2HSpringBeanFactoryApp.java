package com.stream.it.ss.test;


import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.stream.it.ss.base.databo.SecuriyBO;
import com.stream.it.ss.service.webcustom.transaction.exhausted.StockExhaustedInquiryService;
import com.stream.it.ss.view.jsf.form.transaction.exhausted.StockExhaustedSearchForm;



public class H2HSpringBeanFactoryApp {
	private ClassPathXmlApplicationContext applicationContext;
	private BeanFactory beanFactory;
	
	public H2HSpringBeanFactoryApp(){
		ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext(new String[] {
				"applicationContext.xml", 
				"sessionFactory.xml",
				"transaction.xml",
				"inquery-dropdown.xml",
				"inquery-master.xml",
				"inquery-orders-transactions.xml"
				
		});
		beanFactory = (BeanFactory) appContext;
	}

	public Object getBean(String beanName){
		return beanFactory.getBean(beanName);
	}
	
	public static void main(String[]args) throws Exception{
		H2HSpringBeanFactoryApp springBeanFactoryApp = new H2HSpringBeanFactoryApp();
		try{	
			StockExhaustedSearchForm stockExhaustedSearchForm = new StockExhaustedSearchForm();
			stockExhaustedSearchForm.setSecuriyBO(new SecuriyBO());
			
			StockExhaustedInquiryService inquiryService = (StockExhaustedInquiryService) springBeanFactoryApp.getBean("stockExhaustedInquiryService");
			inquiryService.listTransaction(stockExhaustedSearchForm);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
}
